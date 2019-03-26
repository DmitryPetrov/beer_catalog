
package com.example.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.model.Beer;

public class BeerMapper implements RowMapper<Beer> {

    public static final String BASE = "SELECT * FROM beer ";

    public static final String INSERT = "INSERT INTO beer (rate, count, star, craft, name, description, photo) VALUES "
            + "(?, ?, ?, ?, ?, ?, ?) ";
    public static final String INSERT_WITH_RETURNING = "";

    public static final String UPDATE = "UPDATE beer SET (rate, count, star, craft, name, description, photo) = (?, ?, ?, ?, ?, ?, ?) WHERE id = ?";

    public static final String SELECT_ALL = "SELECT * FROM beer ";
    public static final String SELECT_BY_ID = "SELECT * FROM beer WHERE id = ? ";
    public static final String SELECT_BY_RATE = "SELECT * FROM beer WHERE rate > ? ";
    public static final String SELECT_BY_COUNT = "SELECT * FROM beer WHERE count > ? ";
    
    public static final String SELECT_BY_STYLE = "SELECT * FROM beer WHERE style = ? ";
    public static final String SELECT_BY_BREWERY = "SELECT * FROM beer WHERE brewery = ? ";
    public static final String SELECT_BY_COUNTY = "SELECT * FROM beer WHERE country = ? ";
    
    public static final String SELECT_BY_STAR = "SELECT * FROM beer WHERE star = TRUE ";
    public static final String SELECT_BY_CRAFT = "SELECT * FROM beer WHERE craft = TRUE ";
    

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
