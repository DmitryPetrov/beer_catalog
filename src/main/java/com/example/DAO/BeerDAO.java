package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.form.AddBeerForm;
import com.example.mapper.BeerMapper;
import com.example.model.Beer;
import com.example.model.Brewery;
import com.example.model.Country;
import com.example.model.Style;

@Repository
@Transactional
public class BeerDAO extends JdbcDaoSupport {

    
    @Autowired
    public BeerDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public Beer getBeer(long id) {
        String sql = BeerMapper.SELECT_BY_ID;
        Object[] params = new Object[] { id };
        BeerMapper mapper = new BeerMapper();

        try {
            Beer beer = this.getJdbcTemplate().queryForObject(sql, params,
                    mapper);
            return beer;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Beer> getAllBeer() {
        String sql = BeerMapper.SELECT_ALL;
        Object[] params = new Object[] {};
        BeerMapper mapper = new BeerMapper();
        List<Beer> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<Beer> getBeerByRate(int rate) {
        String sql = BeerMapper.SELECT_BY_RATE;
        Object[] params = new Object[] { rate };
        BeerMapper mapper = new BeerMapper();
        List<Beer> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<Beer> getBeerbyStyle(int idStyle) {
        String sql = BeerMapper.SELECT_BY_STYLE;
        Object[] params = new Object[] { idStyle };
        BeerMapper mapper = new BeerMapper();
        List<Beer> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<Beer> getBeerByCount(int count) {
        String sql = BeerMapper.SELECT_BY_COUNT;
        Object[] params = new Object[] { count };
        BeerMapper mapper = new BeerMapper();
        List<Beer> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<Beer> getBeerbyCountry(int idCountry) {
        String sql = BeerMapper.SELECT_BY_COUNTY;
        Object[] params = new Object[] { idCountry };
        BeerMapper mapper = new BeerMapper();
        List<Beer> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<Beer> getBeerByStar(boolean star) {
        String sql = BeerMapper.SELECT_BY_STAR;
        Object[] params = new Object[] { star };
        BeerMapper mapper = new BeerMapper();
        List<Beer> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<Beer> getBeerbyCraft(boolean craft) {
        String sql = BeerMapper.SELECT_BY_CRAFT;
        Object[] params = new Object[] { craft };
        BeerMapper mapper = new BeerMapper();
        List<Beer> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<Beer> getBeerbyBrewery(int idBrewery) {
        String sql = BeerMapper.SELECT_BY_BREWERY;
        Object[] params = new Object[] { idBrewery };
        BeerMapper mapper = new BeerMapper();
        List<Beer> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    @Autowired
    private BeerBreweryDAO beerBreweryDAO;
    
    @Autowired
    private BeerCountryDAO beerCountryDAO;
    
    @Autowired
    private BeerStyleDAO beerStyleDAO;
    
    //??????@Transaction
    public long addBeer(AddBeerForm beerForm) {
        Object[] params = new Object[7];
        System.out.println("0");         
        params[0] = beerForm.getRate();
        params[1] = beerForm.getCount();
        params[2] = String.valueOf(beerForm.isStar()).toUpperCase();
        params[3] = String.valueOf(beerForm.isCraft()).toUpperCase();
        params[4] = beerForm.getName();
        params[5] = beerForm.getDescription();
        params[6] = beerForm.getPhoto();
        
        String sql = BeerMapper.INSERT;
        BeerMapper mapper = new BeerMapper();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        System.out.println(keyHolder + " " + keyHolder.getKey());
        //Connection connection = getConnection();
        
        this.getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, beerForm.getRate());
                ps.setInt(2, beerForm.getCount());
                ps.setBoolean(3, beerForm.isStar());
                ps.setBoolean(4, beerForm.isCraft());
                ps.setString(5, beerForm.getName());
                ps.setString(6, beerForm.getDescription());
                ps.setString(7, beerForm.getPhoto());
                
                return ps;
            }
        }, keyHolder);
        
/*        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("rate", beerForm.getRate())
                .addValue("count", beerForm.getCount())
                .addValue("star", String.valueOf(beerForm.isStar()).toUpperCase())
                .addValue("craft", String.valueOf(beerForm.isCraft()).toUpperCase())
                .addValue("name", beerForm.getName())
                .addValue("description", beerForm.getDescription())
                .addValue("photo", beerForm.getPhoto());
        this.getnamedParameterJdbcTemplate().update(sql, parameters, holder, new String[] {"id"});*/
        
        System.out.println(keyHolder + " " + keyHolder.getKeys().get("id"));
        
        long beerId = (long) keyHolder.getKeys().get("id");
/*        Beer beer = this.getJdbcTemplate().queryForObject(sql, params,
                mapper);*/
        
System.out.println("1");        
        List<Brewery> breweryList = beerForm.getBreweries();
        for(Brewery brewery: breweryList) {
            beerBreweryDAO.add(beerId, brewery.getId());
        }
        System.out.println("2");         
        List<Style> styleList = beerForm.getStyles();
        for(Style style: styleList) {
            beerStyleDAO.add(beerId, style.getId());
        }
        System.out.println("3");       
        List<Country> countryList = beerForm.getCountries();
        for(Country country: countryList) {
            beerCountryDAO.add(beerId, country.getId());
        }        
        System.out.println("4"); 
        return beerId;
    }

  //??????
    public int setBeer(Beer beer) {
        Object[] params = new Object[8];
        
        params[0] = beer.getRate();
        params[1] = beer.getCount();
        params[2] = beer.isStar();
        params[3] = beer.isCraft();
        params[4] = beer.getName();
        params[5] = beer.getDescription();
        params[6] = beer.getPhoto();
        params[7] = beer.getId();
        
        String sql = BeerMapper.UPDATE_ALL_ROWS;
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;  
    }

    //??????
    public int setRateCountDescription(Beer beer) {
        Object[] params = new Object[4];
        
        params[0] = beer.getRate();
        params[1] = beer.getCount();
        params[2] = beer.getDescription();
        params[3] = beer.getId();
        
        String sql = BeerMapper.UPDATE_ALL_ROWS;
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;  
    }

    //??????
    public int setPhoto(Beer beer) {
        Object[] params = new Object[2];
        
        params[0] = beer.getPhoto();
        params[1] = beer.getId();
        
        String sql = BeerMapper.UPDATE_ALL_ROWS;
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;  
    }

}
