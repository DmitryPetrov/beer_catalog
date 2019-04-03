package com.example.security.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.security.model.AppUser;

public class UserMapper implements RowMapper<AppUser>{

    public static final String BASE_SQL =
            "SELECT id, name, encrypted_password FROM app_user ";
    

    public static final String SELECT_ALL = "SELECT * FROM app_user ";
    public static final String SELECT_BY_ID =
            "SELECT * FROM app_user WHERE id = ? ";
    public static final String SELECT_BY_NAME =
            "SELECT * FROM app_user WHERE name = ? ";    
    
    public static final String INSERT = "INSERT INTO app_user(name, encrypted_password) VALUES (?, ?) ON CONFLICT DO NOTHING";   
    public static final String UPDATE_NAME_BY_ID =
            "UPDATE app_user SET name = ? WHERE id = ? ";
    public static final String DELETE =
            "DELETE FROM app_user WHERE id = ? ";
    
    @Override
    public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        Long userId = rs.getLong("id");
        String userName = rs.getString("name");
        String encryptedPassword = rs.getString("encrypted_password");
 
        return new AppUser(userId, userName, encryptedPassword);
    }

}
