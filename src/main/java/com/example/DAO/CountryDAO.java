
package com.example.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.CountryMapper;
import com.example.model.BeerInfo;
import com.example.model.Country;

@Repository
@Transactional
public class CountryDAO extends JdbcDaoSupport {

    @Autowired
    public CountryDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Country> getAllCountry() {
        String sql = CountryMapper.SELECT_ALL;
        Object[] params = new Object[] {};
        CountryMapper mapper = new CountryMapper();
        List<Country> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<BeerInfo> getAllCountryLikeBeerInfo() {
        List<Country> list = getAllCountry();
        List<BeerInfo> beerInfo = new ArrayList<>();
        for (Country country : list) {
            beerInfo.add(country);
        }
        return beerInfo;
    }

    public Country getCountry(long id) {
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

    public Country getCountry(String name) {
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

    public int addCountry(Country country) {
        if (getCountry(country.getName()) != null) {
            return 0;
        }

        Object[] params = new Object[] { country.getName() };

        String sql = CountryMapper.INSERT;
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }

    public int setCountry(Country country) {
        if (getCountry(country.getName()) == null) {
            return 0;
        }

        Object[] params = new Object[] { country.getName(), country.getId() };

        String sql = CountryMapper.UPDATE_NAME_BY_ID;
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }
}
