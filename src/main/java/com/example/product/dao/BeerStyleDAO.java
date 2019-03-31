package com.example.product.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.product.mapper.BeerStyleMapper;
import com.example.product.model.BeerStyle;

@Repository
@Transactional
public class BeerStyleDAO extends JdbcDaoSupport {

    @Autowired
    public BeerStyleDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<BeerStyle> getAll() {
        String sql = BeerStyleMapper.SELECT_ALL;
        Object[] params = new Object[] {};
        BeerStyleMapper mapper = new BeerStyleMapper();
        List<BeerStyle> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<Long> getIdBeerByIdStyle(long id) {
        String sql = BeerStyleMapper.SELECT_BY_ID_STYLE;
        Object[] params = new Object[] { id };
        BeerStyleMapper mapper = new BeerStyleMapper();
        List<BeerStyle> list = this.getJdbcTemplate().query(sql, params, mapper);

        List<Long> beerId = new ArrayList<>();
        for (BeerStyle beerStyle : list) {
            beerId.add(beerStyle.getIdBeer());
        }

        return beerId;
    }

    public List<Long> getIdStyleByIdBeer(long id) {
        String sql = BeerStyleMapper.SELECT_BY_ID_BEER;
        Object[] params = new Object[] { id };
        BeerStyleMapper mapper = new BeerStyleMapper();
        List<BeerStyle> list = this.getJdbcTemplate().query(sql, params, mapper);

        List<Long> stylesId = new ArrayList<>();
        for (BeerStyle beerStyle : list) {
            stylesId.add(beerStyle.getIdStyle());
        }

        return stylesId;
    }

    public int add(long idBeer, long idStyle) {
        String sql = BeerStyleMapper.INSERT;
        Object[] params = new Object[] { idBeer, idStyle };
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }

    public int deleteByIdBeer(long id) {
        String sql = BeerStyleMapper.DELETE_BY_ID_BEER;
        Object[] params = new Object[] { id };
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }

    public int deleteByIdStyle(long id) {
        String sql = BeerStyleMapper.DELETE_BY_ID_STYLE;
        Object[] params = new Object[] { id };
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }
}
