package com.example.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.BeerStyleMapper;
import com.example.model.BeerStyle;

@Repository
@Transactional
public class BeerStyleDAO extends JdbcDaoSupport {

    @Autowired
    public BeerStyleDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<BeerStyle> getAllBeerStyleTable() {
        String sql = BeerStyleMapper.SELECT_ALL;
        Object[] params = new Object[] {};
        BeerStyleMapper mapper = new BeerStyleMapper();
        List<BeerStyle> list = this.getJdbcTemplate().query(sql, params,
                mapper);
        return list;
    }

    public List<BeerStyle> getIdBeerByIdStyle(long idStyle) {
        String sql = BeerStyleMapper.SELECT_BY_ID_STYLE;
        Object[] params = new Object[] { idStyle };
        BeerStyleMapper mapper = new BeerStyleMapper();
        List<BeerStyle> list = this.getJdbcTemplate().query(sql, params,
                mapper);
        return list;
    }

    public List<Long> getIdStyleByIdBeer(long idBeer) {
        String sql = BeerStyleMapper.SELECT_BY_ID_BEER;
        Object[] params = new Object[] { idBeer };
        BeerStyleMapper mapper = new BeerStyleMapper();
        List<BeerStyle> list = this.getJdbcTemplate().query(sql, params,
                mapper);
        
        List<Long> stylesId = new ArrayList<>();

        for (BeerStyle beerStyle : list) {
            stylesId.add(beerStyle.getIdStyle());
        }

        return stylesId;
    }

    public int add(long idBeer, long idStyle) {
        Object[] params = new Object[2];
        params[0] = idBeer;
        params[1] = idStyle;

        String sql = BeerStyleMapper.INSERT;
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }

    public int delete(long idBeer, long idStyle) {
        Object[] params = new Object[2];
        params[0] = idBeer;
        params[1] = idStyle;

        String sql = BeerStyleMapper.DELETE_BY_ID_BEER;
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }
}
