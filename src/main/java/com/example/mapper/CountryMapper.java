
package com.example.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.model.Country;

public class CountryMapper implements RowMapper<Country> {

    public static final String SELECT_ALL = "SELECT * FROM country ";
    public static final String SELECT_BY_ID =
            "SELECT * FROM country WHERE id = ? ";
    public static final String SELECT_BY_NAME =
            "SELECT * FROM country WHERE name = ? ";
    public static final String INSERT = "INSERT INTO country(name) VALUES (?) ";
    public static final String UPDATE_NAME_BY_ID =
            "UPDATE country SET name = ? WHERE id = ? ";


    @Override
    public Country mapRow(ResultSet rs, int rowNum) throws SQLException {

        long id = rs.getLong("Id");
        String name = rs.getString("name");

        return new Country(id, name);
    }
}
