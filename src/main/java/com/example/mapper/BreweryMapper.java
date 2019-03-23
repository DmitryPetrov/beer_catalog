
package com.example.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.model.Brewery;

public class BreweryMapper implements RowMapper<Brewery> {

    public static final String SELECT_ALL = "SELECT * FROM brewery ";
    public static final String SELECT_BY_ID =
            "SELECT * FROM brewery WHERE id = ? ";
    public static final String SELECT_BY_NAME =
            "SELECT * FROM brewery WHERE name = ? ";
    public static final String INSERT = "INSERT INTO brewery(name) VALUES (?) ";
    public static final String UPDATE_NAME_BY_ID =
            "UPDATE brewery SET name = ? WHERE id = ? ";


    @Override
    public Brewery mapRow(ResultSet rs, int rowNum) throws SQLException {

        long id = rs.getLong("Id");
        String name = rs.getString("name");

        return new Brewery(id, name);
    }
}
