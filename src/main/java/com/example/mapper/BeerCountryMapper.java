package com.example.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.model.BeerCountry;

public class BeerCountryMapper implements RowMapper<BeerCountry> {

    public static final String SELECT_ALL = "";
    public static final String SELECT_BY_ID_BEER = "";
    public static final String SELECT_BY_ID_COUNTRY = "";
    
    public static final String INSERT = "";
    public static final String DELETE = "";
    
    @Override
    public BeerCountry mapRow(ResultSet rs, int rowNum) throws SQLException {

        long idBeer = rs.getLong("id_beer");
        long idCountry = rs.getLong("id_country");

        return new BeerCountry(idBeer, idCountry);
    }
}
