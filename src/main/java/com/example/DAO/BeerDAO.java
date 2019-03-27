
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

import com.example.form.BeerFilterForm;
import com.example.form.BeerForm;
import com.example.mapper.BeerMapper;
import com.example.model.Beer;
import com.example.model.Brewery;
import com.example.model.Country;
import com.example.model.Style;

@Repository
@Transactional
public class BeerDAO extends JdbcDaoSupport {

    @Autowired
    private BeerBreweryDAO beerBreweryDAO;

    @Autowired
    private BeerCountryDAO beerCountryDAO;

    @Autowired
    private BeerStyleDAO beerStyleDAO;


    @Autowired
    public BeerDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Beer getBeer(long id) {
        String sql = BeerMapper.SELECT_BY_ID;
        Object[] params = new Object[] { id };
        BeerMapper mapper = new BeerMapper();

        try {
            Beer beer =
                    this.getJdbcTemplate().queryForObject(sql, params, mapper);
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


    public List<Beer> getBeerByName(String name) {
        String sql = BeerMapper.SELECT_BY_NAME;
        Object[] params = new Object[] { name };
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


    public List<Beer> getBeerByCount(int count) {
        String sql = BeerMapper.SELECT_BY_COUNT;
        Object[] params = new Object[] { count };
        BeerMapper mapper = new BeerMapper();
        List<Beer> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }


    public List<Beer> getBeerByStyle(int idStyle) {
        String sql = BeerMapper.SELECT_BY_STYLE;
        Object[] params = new Object[] { idStyle };
        BeerMapper mapper = new BeerMapper();
        List<Beer> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }


    public List<Beer> getBeerByBrewery(int idBrewery) {
        String sql = BeerMapper.SELECT_BY_BREWERY;
        Object[] params = new Object[] { idBrewery };
        BeerMapper mapper = new BeerMapper();
        List<Beer> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }


    public List<Beer> getBeerByCountry(int idCountry) {
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


    public List<Beer> getBeerByCraft(boolean craft) {
        String sql = BeerMapper.SELECT_BY_CRAFT;
        Object[] params = new Object[] { craft };
        BeerMapper mapper = new BeerMapper();
        List<Beer> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }


    public List<Beer> getBeerByParams(BeerFilterForm beerFilter) {
        String sql = BeerMapper.SELECT_ALL;

        sql += "WHERE ";

        sql += "id IN ( ";

        sql += "(SELECT id_beer FROM beer_country WHERE id_country = ?) ";

        sql += "AND ";

        sql += "(SELECT id_beer FROM beer_syle WHERE id_style = ?) ";

        sql += "AND ";

        sql += "(SELECT id_beer FROM beer_brewery WHERE id_brewery = ?) ";

        sql += ") ";

        sql += "AND ";

        sql += "craft = TRUE ";

        sql += "AND ";

        sql += "star = TRUE ";

        sql += "AND ";

        sql += "rate > ? ";

        sql += "AND ";

        sql += "count > ? ";

        sql += "AND ";

        sql += "name LIKE '%?%' ";

        Object[] params = new Object[] {};
        BeerMapper mapper = new BeerMapper();
        List<Beer> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }


    // @Transaction
    public void addBeer(BeerForm beerForm) {
        Object[] params = new Object[7];

        params[0] = beerForm.getRate();
        params[1] = beerForm.getCount();
        params[2] = String.valueOf(beerForm.isStar()).toUpperCase();
        params[3] = String.valueOf(beerForm.isCraft()).toUpperCase();
        params[4] = beerForm.getName();
        params[5] = beerForm.getDescription();
        params[6] = beerForm.getPhoto();

        String sql = BeerMapper.INSERT;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        this.getJdbcTemplate().update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(
                    Connection connection) throws SQLException {
                PreparedStatement ps = getConnection().prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
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

        long beerId = (long) keyHolder.getKeys().get("id");

        List<Brewery> breweryList = beerForm.getBreweries();
        if (breweryList != null) {
            for (Brewery brewery : breweryList) {
                beerBreweryDAO.add(beerId, brewery.getId());
            }
        }

        List<Style> styleList = beerForm.getStyles();
        if (styleList != null) {
            for (Style style : styleList) {
                beerStyleDAO.add(beerId, style.getId());
            }
        }

        List<Country> countryList = beerForm.getCountries();
        if (countryList != null) {
            for (Country country : countryList) {
                beerCountryDAO.add(beerId, country.getId());
            }
        }
    }


    // ??????
    public int setBeer(BeerForm beerForm) {
        Object[] params = new Object[8];

        params[0] = beerForm.getRate();
        params[1] = beerForm.getCount();
        params[2] = beerForm.isStar();
        params[3] = beerForm.isCraft();
        params[4] = beerForm.getName();
        params[5] = beerForm.getDescription();
        params[6] = beerForm.getPhoto();
        params[7] = beerForm.getId();

        String sql = BeerMapper.UPDATE;
        
        for(Object param: params) {  
            System.out.println(param);
        }
        int countUpdated = this.getJdbcTemplate().update(sql, params);

        long beerId = beerForm.getId();

        List<Brewery> breweryList = beerForm.getBreweries();
        if (breweryList != null) {
            for (Brewery brewery : breweryList) {
                beerBreweryDAO.add(beerId, brewery.getId());
            }
        }

        List<Style> styleList = beerForm.getStyles();
        if (styleList != null) {
            for (Style style : styleList) {
                beerStyleDAO.add(beerId, style.getId());
            }
        }

        List<Country> countryList = beerForm.getCountries();
        if (countryList != null) {
            for (Country country : countryList) {
                beerCountryDAO.add(beerId, country.getId());
            }
        }
        System.out.println(breweryList);
        System.out.println(styleList);
        System.out.println(countryList);
        return countUpdated;
    }

}
