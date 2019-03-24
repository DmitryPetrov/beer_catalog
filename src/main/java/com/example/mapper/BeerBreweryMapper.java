
package com.example.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.model.BeerBrewery;

public class BeerBreweryMapper implements RowMapper<BeerBrewery> {

    public static final String SELECT_ALL = "SELECT * FROM beer_brewery ";
    public static final String SELECT_BY_ID_BEER =
            "SELECT * FROM beer_brewery WHERE id_beer = ?";
    public static final String SELECT_BY_ID_BREWERY =
            "SELECT * FROM beer_brewery WHERE id_brewery = ?";

    public static final String INSERT =
            "INSERT INTO beer_brewery VALUES (?, ?) ";
    public static final String DELETE_BY_ID_BEER =
            "DELETE FROM beer_brewery where id_beer = ? ";


    @Override
    public BeerBrewery mapRow(ResultSet rs, int rowNum) throws SQLException {

        long idBeer = rs.getLong("id_beer");
        long idBrewery = rs.getLong("id_brewery");

        return new BeerBrewery(idBeer, idBrewery);
    }
}
