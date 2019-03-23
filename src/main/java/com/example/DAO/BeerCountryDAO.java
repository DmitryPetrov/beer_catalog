package com.example.DAO;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.BeerCountryMapper;
import com.example.model.BeerCountry;

@Repository
@Transactional
public class BeerCountryDAO extends JdbcDaoSupport {

    @Autowired
    public BeerCountryDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<BeerCountry> getAllBeerCountryTable() {
        String sql = BeerCountryMapper.SELECT_ALL;
        Object[] params = new Object[] {};
        BeerCountryMapper mapper = new BeerCountryMapper();
        List<BeerCountry> list = this.getJdbcTemplate().query(sql, params,
                mapper);
        return list;
    }

    public List<BeerCountry> getIdBeerByIdCountry(long idCountry) {
        String sql = BeerCountryMapper.SELECT_BY_ID_COUNTRY;
        Object[] params = new Object[] { idCountry };
        BeerCountryMapper mapper = new BeerCountryMapper();
        List<BeerCountry> list = this.getJdbcTemplate().query(sql, params,
                mapper);
        return list;
    }

    public List<BeerCountry> getIdCountryByIdBeer(long idBeer) {
        String sql = BeerCountryMapper.SELECT_BY_ID_BEER;
        Object[] params = new Object[] { idBeer };
        BeerCountryMapper mapper = new BeerCountryMapper();
        List<BeerCountry> list = this.getJdbcTemplate().query(sql, params,
                mapper);
        return list;
    }

    public int add(long idBeer, long idCountry) {
        Object[] params = new Object[2];
        params[0] = idBeer;
        params[1] = idCountry;

        String sql = BeerCountryMapper.INSERT;
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }

    public int delete(long idBeer, long idCountry) {
        Object[] params = new Object[2];
        params[0] = idBeer;
        params[1] = idCountry;

        String sql = BeerCountryMapper.DELETE;
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }
}
