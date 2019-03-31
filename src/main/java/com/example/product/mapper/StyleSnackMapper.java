package com.example.product.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.product.model.StyleSnack;

public class StyleSnackMapper implements RowMapper<StyleSnack> {

    public static final String SELECT_ALL = "SELECT * FROM style_snack ";
    public static final String SELECT_BY_ID_STYLE =
            "SELECT * FROM style_snack WHERE id_style = ?";
    public static final String SELECT_BY_ID_SNACK =
            "SELECT * FROM style_snack WHERE id_snack = ?";

    public static final String INSERT = "INSERT INTO style_snack VALUES (?, ?) ON CONFLICT DO NOTHING";
    public static final String DELETE_BY_ID_STYLE =
            "DELETE FROM style_snack where id_style = ? ";
    public static final String DELETE_BY_ID_SNACK =
            "DELETE FROM style_snack where id_snack = ? ";
    
    @Override
    public StyleSnack mapRow(ResultSet rs, int rowNum) throws SQLException {

        long idStyle = rs.getLong("id_style");
        long idSnack = rs.getLong("id_snack");

        return new StyleSnack(idStyle, idSnack);
    }
}
