
package com.example.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.security.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder =
                new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("123")
                .roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("123")
                .roles("ADMIN", "USER");
        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.authorizeRequests().antMatchers("/").permitAll();

        http.authorizeRequests()
                .antMatchers("/user/beer/list", "/user/beer/add",
                        "/user/beer/put", "/user/beer/delete",
                        "/user/beer/filter")
                .hasAnyAuthority("user");

        http.authorizeRequests().antMatchers("/user/info")
                .hasAnyAuthority("user", "admin");

        http.authorizeRequests()
                .antMatchers("/admin", "/beer/add", "/beer/put", "/beer/delete",
                        "/style/add", "/style/put", "/style/delete",
                        "/country/add", "/style/put", "/country/delete",
                        "/brewery/add", "/style/put", "/brewery/delete",
                        "/snack/add", "/style/put", "/snack/delete")
                .hasAnyAuthority("admin");

        http.authorizeRequests().and().exceptionHandling()
                .accessDeniedPage("/403");

        http.authorizeRequests().and().formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/login").defaultSuccessUrl("/user/beer/list")
                .failureUrl("/login?error=true").usernameParameter("username")
                .passwordParameter("password").and().logout()
                .logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");

    }
}
