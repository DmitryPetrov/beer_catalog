package com.example.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.model.BeerStyle;

public class BeerStyleMapper implements RowMapper<BeerStyle> {

    public static final String SELECT_ALL = "";
    public static final String SELECT_BY_ID_BEER = "";
    public static final String SELECT_BY_ID_STYLE = "";
    
    public static final String INSERT = "";
    public static final String DELETE = "";
    
    @Override
    public BeerStyle mapRow(ResultSet rs, int rowNum) throws SQLException {

        long idBeer = rs.getLong("id_beer");
        long idStyle = rs.getLong("id_style");

        return new BeerStyle(idBeer, idStyle);
    }
}
