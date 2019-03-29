
package com.example.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.BeerBreweryMapper;
import com.example.model.BeerBrewery;
import com.example.model.BeerStyle;

@Repository
@Transactional
public class BeerBreweryDAO extends JdbcDaoSupport {

    @Autowired
    public BeerBreweryDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<BeerBrewery> getAll() {
        String sql = BeerBreweryMapper.SELECT_ALL;
        Object[] params = new Object[] {};
        BeerBreweryMapper mapper = new BeerBreweryMapper();
        List<BeerBrewery> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<Long> getIdBeerByIdBrewery(long idBrewery) {
        String sql = BeerBreweryMapper.SELECT_BY_ID_BREWERY;
        Object[] params = new Object[] { idBrewery };
        BeerBreweryMapper mapper = new BeerBreweryMapper();
        List<BeerBrewery> list = this.getJdbcTemplate().query(sql, params, mapper);

        List<Long> beerId = new ArrayList<>();
        for (BeerBrewery beerBrerwery : list) {
            beerId.add(beerBrerwery.getIdBeer());
        }

        return beerId;
    }

    public List<Long> getIdBreweryByIdBeer(long idBeer) {
        String sql = BeerBreweryMapper.SELECT_BY_ID_BEER;
        Object[] params = new Object[] { idBeer };
        BeerBreweryMapper mapper = new BeerBreweryMapper();
        List<BeerBrewery> list = this.getJdbcTemplate().query(sql, params, mapper);

        List<Long> breweriesId = new ArrayList<>();
        for (BeerBrewery beerBrewery : list) {
            breweriesId.add(beerBrewery.getIdBrewery());
        }

        return breweriesId;
    }

    public int add(long idBeer, long idBrewery) {
        String sql = BeerBreweryMapper.INSERT;
        Object[] params = new Object[] { idBeer, idBrewery };   
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }

    public int deleteByIdBeer(long id) {
        String sql = BeerBreweryMapper.DELETE_BY_ID_BEER;
        Object[] params = new Object[] { id };   
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }

    public int deleteByIdBrewery(long id) {
        String sql = BeerBreweryMapper.DELETE_BY_ID_BREWERY;
        Object[] params = new Object[] { id };
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }
}
