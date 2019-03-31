package com.example.security.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.security.model.AppUser;

public class UserMapper implements RowMapper<AppUser>{

    public static final String BASE_SQL =
            "SELECT id, name, encrypted_password FROM app_user ";
    
    @Override
    public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        Long userId = rs.getLong("id");
        String userName = rs.getString("name");
        String encryptedPassword = rs.getString("encrypted_password");
 
        return new AppUser(userId, userName, encryptedPassword);
    }

}
