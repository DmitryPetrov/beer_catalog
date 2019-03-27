
package com.example.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.model.BeerCountry;

public class BeerCountryMapper implements RowMapper<BeerCountry> {

    public static final String SELECT_ALL = "SELECT * FROM beer_country ";
    public static final String SELECT_BY_ID_BEER =
            "SELECT * FROM beer_country WHERE id_beer = ?";
    public static final String SELECT_BY_ID_COUNTRY =
            "SELECT * FROM beer_country WHERE id_country = ?";

    public static final String INSERT =
            "INSERT INTO beer_country VALUES (?, ?) ON CONFLICT DO NOTHING";
    public static final String DELETE_BY_ID_BEER =
            "DELETE FROM beer_country where id_beer = ? ";


    @Override
    public BeerCountry mapRow(ResultSet rs, int rowNum) throws SQLException {

        long idBeer = rs.getLong("id_beer");
        long idCountry = rs.getLong("id_country");

        return new BeerCountry(idBeer, idCountry);
    }
}
