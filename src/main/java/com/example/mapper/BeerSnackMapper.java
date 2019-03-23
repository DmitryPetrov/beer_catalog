package com.example.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.model.BeerSnack;

public class BeerSnackMapper implements RowMapper<BeerSnack> {

    public static final String SELECT_ALL = "";
    public static final String SELECT_BY_ID_BEER = "";
    public static final String SELECT_BY_ID_SNACK = "";
    
    public static final String INSERT = "";
    public static final String UPDATE = "";
    public static final String DELETE = "";
    
    @Override
    public BeerSnack mapRow(ResultSet rs, int rowNum) throws SQLException {

        long idBeer = rs.getLong("id_beer");
        long idSnack = rs.getLong("id_snack");

        return new BeerSnack(idBeer, idSnack);
    }
}
