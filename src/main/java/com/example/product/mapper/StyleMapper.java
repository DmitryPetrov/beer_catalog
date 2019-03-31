
package com.example.product.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.product.model.Style;

public class StyleMapper implements RowMapper<Style> {

    public static final String SELECT_ALL = "SELECT * FROM style ";
    public static final String SELECT_BY_ID =
            "SELECT * FROM style WHERE id = ? ";
    public static final String SELECT_BY_NAME =
            "SELECT * FROM style WHERE name = ? ";   
    public static final String INSERT = "INSERT INTO style(name) VALUES (?) ON CONFLICT DO NOTHING";   
    public static final String UPDATE_NAME_BY_ID =
            "UPDATE style SET name = ? WHERE id = ? ";
    public static final String DELETE =
            "DELETE FROM style WHERE id = ? ";
    
    
    @Override
    public Style mapRow(ResultSet rs, int rowNum) throws SQLException {

        long id = rs.getLong("Id");
        String name = rs.getString("name");

        return new Style(id, name);
    }
}
