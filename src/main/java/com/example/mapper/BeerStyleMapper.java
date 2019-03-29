
package com.example.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.model.BeerStyle;

public class BeerStyleMapper implements RowMapper<BeerStyle> {

    public static final String SELECT_ALL = "SELECT * FROM beer_style ";
    public static final String SELECT_BY_ID_BEER =
            "SELECT * FROM beer_style WHERE id_beer = ?";
    public static final String SELECT_BY_ID_STYLE =
            "SELECT * FROM beer_style WHERE id_style = ?";

    public static final String INSERT = "INSERT INTO beer_style VALUES (?, ?) ON CONFLICT DO NOTHING";
    public static final String DELETE_BY_ID_BEER =
            "DELETE FROM beer_style where id_beer = ?";
    public static final String DELETE_BY_ID_STYLE =
            "DELETE FROM beer_style where id_style = ?";
    

    @Override
    public BeerStyle mapRow(ResultSet rs, int rowNum) throws SQLException {

        long idBeer = rs.getLong("id_beer");
        long idStyle = rs.getLong("id_style");

        return new BeerStyle(idBeer, idStyle);
    }
}
