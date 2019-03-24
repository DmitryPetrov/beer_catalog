
package com.example.DAO;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.BeerBreweryMapper;
import com.example.model.BeerBrewery;

@Repository
@Transactional
public class BeerBreweryDAO extends JdbcDaoSupport {

    @Autowired
    public BeerBreweryDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public List<BeerBrewery> getAllBeerBreweryTable() {
        String sql = BeerBreweryMapper.SELECT_ALL;
        Object[] params = new Object[] {};
        BeerBreweryMapper mapper = new BeerBreweryMapper();
        List<BeerBrewery> list =
                this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }


    public List<BeerBrewery> getIdBeerByIdBrewery(long idBrewery) {
        String sql = BeerBreweryMapper.SELECT_BY_ID_BREWERY;
        Object[] params = new Object[] { idBrewery };
        BeerBreweryMapper mapper = new BeerBreweryMapper();
        List<BeerBrewery> list =
                this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }


    public List<BeerBrewery> getIdBreweryByIdBeer(long idBeer) {
        String sql = BeerBreweryMapper.SELECT_BY_ID_BEER;
        Object[] params = new Object[] { idBeer };
        BeerBreweryMapper mapper = new BeerBreweryMapper();
        List<BeerBrewery> list =
                this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }


    public int add(long idBeer, long idBrewery) {
        Object[] params = new Object[2];
        params[0] = idBeer;
        params[1] = idBrewery;

        String sql = BeerBreweryMapper.INSERT;
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }


    public int delete(long idBeer, long idBrewery) {
        Object[] params = new Object[2];
        params[0] = idBeer;
        params[1] = idBrewery;

        String sql = BeerBreweryMapper.DELETE_BY_ID_BEER;
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }
}
