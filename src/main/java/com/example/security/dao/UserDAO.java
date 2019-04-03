
package com.example.security.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.security.mapper.UserMapper;
import com.example.security.model.AppUser;

@Repository
@Transactional
public class UserDAO extends JdbcDaoSupport {

    @Autowired
    public UserDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public AppUser get(String userName) {
        String sql = UserMapper.SELECT_BY_NAME;

        Object[] params = new Object[] { userName };
        UserMapper mapper = new UserMapper();
        try {
            AppUser userInfo =
                    this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return userInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    public int add(String name, String password) {
        String sql = UserMapper.INSERT;
        Object[] params = new Object[] { name,
                new BCryptPasswordEncoder().encode(password) };
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }

}
