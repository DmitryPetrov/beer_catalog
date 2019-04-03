package com.example.security.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.security.model.Role;

public class RoleMapper implements RowMapper<Role>{

    public static final String SELECT_ALL = "SELECT * FROM role ";
    public static final String SELECT_BY_ID = "SELECT * FROM role WHERE id = ? ";
    public static final String SELECT_BY_ROLENAME = "SELECT * FROM role WHERE name = ? ";    
    public static final String SELECT_ROLENAME_BY_USERNAME = "SELECT * FROM role WHERE id IN "
            + "(SELECT id_role FROM user_role WHERE id_user = "
                + "(SELECT id FRON app_user WHERE name = ? ))"; 
    
    public static final String SELECT_ROLENAME_BY_USERID = "SELECT name FROM role WHERE id IN "
            + "(SELECT id_role FROM user_role WHERE id_user = ? )"; 
    
    public static final String INSERT = "INSERT INTO role(name) VALUES (?) ON CONFLICT DO NOTHING";   
    public static final String UPDATE_NAME_BY_ID =
            "UPDATE role SET name = ? WHERE id = ? ";
    public static final String DELETE =
            "DELETE FROM role WHERE id = ? ";
    
    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        Long userId = rs.getLong("id");
        String userName = rs.getString("name");
 
        return new Role(userId, userName);
    }
}
