package com.example.DAO;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.form.AddBeerForm;
import com.example.mapper.BeerMapper;
import com.example.model.Beer;

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
    
    //??????@Transaction
    public int addBeer(AddBeerForm beerForm) {
/*        Object[] params = new Object[7];
        
        params[0] = beerForm.getRate();
        params[1] = beerForm.getCount();
        params[2] = beerForm.isStar();
        params[3] = beerForm.isCraft();
        params[4] = beerForm.getName();
        params[5] = beerForm.getDescription();
        params[6] = beerForm.getPhoto();
        
        String sql = BeerMapper.INSERT_WITH_RETURNING;
        Beer beer = this.getJdbcTemplate().update(sql, params);
        
        long[] idBrewery = beerForm.getIdBrewery();
        for(int i = 0; i < idBrewery.length; i++) {
            beerBreweryDAO.add(beer.getId(), idBrewery[i]);
        }
        
        long[] idCountry = beerForm.getIdCountry();
        for(int i = 0; i < idCountry.length; i++) {
            beerCountryDAO.add(beer.getId(), idCountry[i]);
        }
        
        long[] idStyle = beerForm.getIdStyle();
        for(int i = 0; i < idStyle.length; i++) {
            beerCountryDAO.add(beer.getId(), idStyle[i]);
        }        
        
        return countUpdated;*/
        return 0;
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
