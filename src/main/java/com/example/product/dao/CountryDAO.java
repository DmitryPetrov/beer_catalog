
package com.example.product.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.product.mapper.CountryMapper;
import com.example.product.model.BeerInfo;
import com.example.product.model.Country;

@Repository
@Transactional
public class CountryDAO extends JdbcDaoSupport {

    @Autowired
    private BeerCountryDAO beerCountryDAO;
    
    @Autowired
    public CountryDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Country> getAll() {
        String sql = CountryMapper.SELECT_ALL;
        Object[] params = new Object[] {};
        CountryMapper mapper = new CountryMapper();
        List<Country> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<BeerInfo> getAllLikeBeerInfo() {
        List<Country> list = getAll();
        List<BeerInfo> beerInfo = new ArrayList<>();
        for (Country country : list) {
            beerInfo.add(country);
        }
        return beerInfo;
    }

    public Country get(long id) {
        String sql = CountryMapper.SELECT_BY_ID;
        Object[] params = new Object[] { id };
        CountryMapper mapper = new CountryMapper();

        try {
            Country country = this.getJdbcTemplate().queryForObject(sql, params,
                    mapper);
            return country;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Country get(String name) {
        String sql = CountryMapper.SELECT_BY_NAME;
        Object[] params = new Object[] { name };
        CountryMapper mapper = new CountryMapper();

        try {
            Country country = this.getJdbcTemplate().queryForObject(sql, params,
                    mapper);
            return country;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    public List<Country> getByBeerId(long id) {
        String sql = CountryMapper.SELECT_BY_BEER_ID;
        Object[] params = new Object[] { id };
        CountryMapper mapper = new CountryMapper();
        List<Country> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public int add(Country country) {
        if (get(country.getName()) != null) {
            return 0;
        }

        String sql = CountryMapper.INSERT;
        Object[] params = new Object[] { country.getName() };
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }

    public int put(Country country) {
        if (get(country.getName()) == null) {
            return 0;
        }
        
        String sql = CountryMapper.UPDATE_NAME_BY_ID;
        Object[] params = new Object[] { country.getName(), country.getId() };
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }

    public int delete(Country country) {
        if (get(country.getName()) == null) {
            return 0;
        }
        
        beerCountryDAO.deleteByIdCountry(country.getId());
        
        String sql = CountryMapper.DELETE;
        Object[] params = new Object[] { country.getId() };
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }
}
