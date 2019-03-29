package com.example.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.model.BeerSnack;

public class BeerSnackMapper implements RowMapper<BeerSnack> {

    public static final String SELECT_ALL = "SELECT * FROM beer_snack ";
    public static final String SELECT_BY_ID_BEER =
            "SELECT * FROM beer_snack WHERE id_beer = ?";
    public static final String SELECT_BY_ID_SNACK =
            "SELECT * FROM beer_snack WHERE id_snack = ?";

    public static final String INSERT = "INSERT INTO beer_snack VALUES (?, ?) ON CONFLICT DO NOTHING";
    public static final String DELETE_BY_ID_BEER =
            "DELETE FROM beer_snack where id_beer = ? ";
    public static final String DELETE_BY_ID_SNACK =
            "DELETE FROM beer_snack where id_snack = ? ";
    
    @Override
    public BeerSnack mapRow(ResultSet rs, int rowNum) throws SQLException {

        long idBeer = rs.getLong("id_beer");
        long idSnack = rs.getLong("id_snack");

        return new BeerSnack(idBeer, idSnack);
    }
}
