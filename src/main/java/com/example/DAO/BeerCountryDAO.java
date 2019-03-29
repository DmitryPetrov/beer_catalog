
package com.example.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.BeerCountryMapper;
import com.example.model.BeerCountry;
import com.example.model.BeerStyle;

@Repository
@Transactional
public class BeerCountryDAO extends JdbcDaoSupport {

    @Autowired
    public BeerCountryDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<BeerCountry> getAll() {
        String sql = BeerCountryMapper.SELECT_ALL;
        Object[] params = new Object[] {};
        BeerCountryMapper mapper = new BeerCountryMapper();
        List<BeerCountry> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<Long> getIdBeerByIdCountry(long id) {
        String sql = BeerCountryMapper.SELECT_BY_ID_COUNTRY;
        Object[] params = new Object[] { id };
        BeerCountryMapper mapper = new BeerCountryMapper();
        List<BeerCountry> list = this.getJdbcTemplate().query(sql, params, mapper);

        List<Long> beerId = new ArrayList<>();
        for (BeerCountry beerCountry : list) {
            beerId.add(beerCountry.getIdBeer());
        }

        return beerId;
    }

    public List<Long> getIdCountryByIdBeer(long id) {
        String sql = BeerCountryMapper.SELECT_BY_ID_BEER;
        Object[] params = new Object[] { id };
        BeerCountryMapper mapper = new BeerCountryMapper();
        List<BeerCountry> list = this.getJdbcTemplate().query(sql, params, mapper);

        List<Long> countiesId = new ArrayList<>();
        for (BeerCountry beerBrewery : list) {
            countiesId.add(beerBrewery.getIdCountry());
        }

        return countiesId;
    }

    public int add(long idBeer, long idCountry) {
        Object[] params = new Object[] { idBeer, idCountry };
        String sql = BeerCountryMapper.INSERT;
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }

    public int deleteByIdBeer(long id) {
        Object[] params = new Object[] { id };
        String sql = BeerCountryMapper.DELETE_BY_ID_BEER;
        int countUpdated = this.getJdbcTemplate().update(sql, params);       
        return countUpdated;
    }

    public int deleteByIdCountry(long id) {
        Object[] params = new Object[] { id };
        String sql = BeerCountryMapper.DELETE_BY_ID_COUNTRY;
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }
}
