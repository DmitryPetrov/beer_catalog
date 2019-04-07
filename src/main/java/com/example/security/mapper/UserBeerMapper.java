package com.example.security.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.security.model.UserBeer;

public class UserBeerMapper implements RowMapper<UserBeer> {

    private static final String BASE = 
      "SELECT "
          + "beer.craft, "
          + "beer.name, "
          + "beer.photo, "
          + "user_beer.id_user, "
          + "user_beer.id_beer, "
          + "user_beer.rate, "
          + "user_beer.count, "
          + "user_beer.star, "
          + "user_beer.description "
    + "FROM "
        + "beer, "
        + "user_beer " 
    + "WHERE "
        + "user_beer.id_user = "
            + "(SELECT id FROM app_user WHERE name = ? ) "
    + "AND "
        + "user_beer.id_beer = beer.id ";
    

    public static final String INSERT = "INSERT INTO user_beer(id_user, id_beer, rate, count, star, description) VALUES "
            + "(?, ?, ?, ?, ?, ?) ON CONFLICT DO NOTHING";

    public static final String UPDATE_BY_USERNAME_AND_IDBEER = "UPDATE user_beer SET (rate, count, star, description) = (?, ?, ?, ?) "
            + "WHERE id_user = ? "
            + "AND id_beer = ? ";

    public static final String DELETE_BY_USERNAME_AND_IDBEER = "DELETE FROM user_beer "
            + "WHERE id_user = ? "
            + "AND id_beer = ? ";

    public static final String SELECT_ALL = "select * FROM user_beer";
    
    public static final String SELECT_BY_USERNAME = BASE;
    
    public static final String SELECT_BY_USERNAME_AND_IDBEER = BASE
            + "AND user_beer.id_beer = ?";
    
    public static final String SELECT_BY_USERNAME_AND_RATE = BASE
            + "AND user_beer.rate > ? ";
   
    public static final String SELECT_BY_USERNAME_AND_COUNT = BASE
            + "AND user_beer.count > ? ";

    public static final String SELECT_BY_USERNAME_AND_STYLE = BASE
            + "AND user_beer.id_beer IN (SELECT id_beer FROM beer_style WHERE id_style = ?) ";
   
    public static final String SELECT_BY_USERNAME_AND_BREWERY = BASE
            + "AND user_beer.id_beer IN (SELECT id_beer FROM beer_brewery WHERE id_brewery = ?) ";
   
    public static final String SELECT_BY_USERNAME_AND_COUNTRY = BASE
            + "AND user_beer.id_beer IN (SELECT id_beer FROM beer_country WHERE id_country = ?) ";

    public static final String SELECT_BY_USERNAME_AND_STAR = BASE
            + "AND user_beer.star = ? ";
    
    public static final String SELECT_BY_USERNAME_AND_CRAFT = BASE
            + "AND beer.craft = ? ";

    public static final String SELECT_BY_USERNAME_AND_BEERNAME = BASE
            + "AND beer.name ILIKE ? ";

    
    @Override
    public UserBeer mapRow(ResultSet rs, int rowNum) throws SQLException {

        boolean craft = rs.getBoolean("craft");
        String name = rs.getString("name");
        String photo = rs.getString("photo");
        long idUser = rs.getLong("id_user");
        long idBeer = rs.getLong("id_beer");
        int rate = rs.getInt("rate");
        int count = rs.getInt("count");
        boolean star = rs.getBoolean("star");
        String description = rs.getString("description");
        

        return new UserBeer(idUser, idBeer, rate, count, star, craft, name, description, photo);
    }
}
