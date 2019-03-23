package com.example.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.model.StyleSnack;

public class StyleSnackMapper implements RowMapper<StyleSnack> {

    public static final String SELECT_ALL = "";
    public static final String SELECT_BY_ID_STYLE = "";
    public static final String SELECT_BY_ID_SNACK = "";
    
    public static final String INSERT = "";
    public static final String DELETE = "";
    
    @Override
    public StyleSnack mapRow(ResultSet rs, int rowNum) throws SQLException {

        long idStyle = rs.getLong("id_style");
        long idSnack = rs.getLong("id_snack");

        return new StyleSnack(idStyle, idSnack);
    }
}
