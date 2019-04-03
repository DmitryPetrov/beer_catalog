
package com.example.product.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.product.model.Snack;

public class SnackMapper implements RowMapper<Snack> {

    public static final String SELECT_ALL = "SELECT * FROM snack ";
    public static final String SELECT_BY_ID =
            "SELECT * FROM snack WHERE id = ? ";
    public static final String SELECT_BY_NAME =
            "SELECT * FROM snack WHERE name = ? ";
    public static final String SELECT_BY_BEER_ID =
            "SELECT * FROM snack WHERE id IN (SELECT id_snack FROM beer_snack WHERE id_beer = ?) "; 
    
    public static final String INSERT = "INSERT INTO snack(name) VALUES (?) ON CONFLICT DO NOTHING";
    public static final String UPDATE_NAME_BY_ID =
            "UPDATE snack SET name = ? WHERE id = ? ";
    public static final String DELETE =
            "DELETE FROM snack WHERE id = ? ";

    
    @Override
    public Snack mapRow(ResultSet rs, int rowNum) throws SQLException {

        long id = rs.getLong("Id");
        String name = rs.getString("name");

        return new Snack(id, name);
    }
}
