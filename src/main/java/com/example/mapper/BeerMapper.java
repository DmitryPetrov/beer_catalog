package com.example.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.model.Beer;

public class BeerMapper implements RowMapper<Beer> {

    public static final String BASE = "";
    
    public static final String INSERT = "";
    public static final String INSERT_WITH_RETURNING = "";
    
    public static final String UPDATE_ALL_ROWS = "";
    public static final String UPDATE_RATE_COUNT_DESCRIPTION = "";
    public static final String UPDATE_PHOTO = "";
    
    public static final String SELECT_ALL = "SELECT * FROM beer ";
    public static final String SELECT_BY_ID = "";
    public static final String SELECT_BY_RATE = "";
    public static final String SELECT_BY_STYLE = "";
    public static final String SELECT_BY_COUNT = "";
    public static final String SELECT_BY_COUNTY = "";
    public static final String SELECT_BY_STAR = "";
    public static final String SELECT_BY_CRAFT = "";
    public static final String SELECT_BY_BREWERY = "";
    
    
    @Override
    public Beer mapRow(ResultSet rs, int rowNum) throws SQLException {

        long id = rs.getLong("Id");
        int rate = rs.getInt("rate");
        int count = rs.getInt("count");
        boolean star = rs.getBoolean("star");
        boolean craft = rs.getBoolean("craft");
        
        String name = rs.getString("name");
        String description = rs.getString("description");
        String photo = rs.getString("photo");

        return new Beer(id, rate, count, star, craft, name, description, photo);
    }
}
