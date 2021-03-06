
package com.example.product.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.product.model.Brewery;

public class BreweryMapper implements RowMapper<Brewery> {

    public static final String SELECT_ALL = "SELECT * FROM brewery ";
    public static final String SELECT_BY_ID =
            "SELECT * FROM brewery WHERE id = ? ";
    public static final String SELECT_BY_NAME =
            "SELECT * FROM brewery WHERE name = ? ";
    public static final String SELECT_BY_BEER_ID =
            "SELECT * FROM brewery WHERE id IN (SELECT id_brewery FROM beer_brewery WHERE id_beer = ?) "; 
    
    public static final String INSERT =
            "INSERT INTO brewery(name) VALUES (?) ON CONFLICT DO NOTHING";
    public static final String UPDATE_NAME_BY_ID =
            "UPDATE brewery SET name = ? WHERE id = ? ";
    public static final String DELETE = "DELETE FROM brewery WHERE id = ? ";


    @Override
    public Brewery mapRow(ResultSet rs, int rowNum) throws SQLException {

        long id = rs.getLong("Id");
        String name = rs.getString("name");

        return new Brewery(id, name);
    }
}
