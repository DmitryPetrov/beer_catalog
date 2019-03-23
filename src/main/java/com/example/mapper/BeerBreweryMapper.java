package com.example.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.model.BeerBrewery;

public class BeerBreweryMapper implements RowMapper<BeerBrewery> {

    public static final String SELECT_ALL = "";
    public static final String SELECT_BY_ID_BEER = "";
    public static final String SELECT_BY_ID_BREWERY = "";
    
    public static final String INSERT = "";
    public static final String DELETE = "";
    
    @Override
    public BeerBrewery mapRow(ResultSet rs, int rowNum) throws SQLException {

        long idBeer = rs.getLong("id_beer");
        long idBrewery = rs.getLong("id_brewery");

        return new BeerBrewery(idBeer, idBrewery);
    }
}
