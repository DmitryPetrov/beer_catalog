package com.example.DAO;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.BeerSnackMapper;
import com.example.model.BeerSnack;

@Repository
@Transactional
public class BeerSnackDAO extends JdbcDaoSupport {

    @Autowired
    public BeerSnackDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<BeerSnack> getAllBeerSnackTable() {
        String sql = BeerSnackMapper.SELECT_ALL;
        Object[] params = new Object[] {};
        BeerSnackMapper mapper = new BeerSnackMapper();
        List<BeerSnack> list = this.getJdbcTemplate().query(sql, params,
                mapper);
        return list;
    }

    public List<BeerSnack> getIdBeerByIdSnack(long idSnack) {
        String sql = BeerSnackMapper.SELECT_BY_ID_SNACK;
        Object[] params = new Object[] { idSnack };
        BeerSnackMapper mapper = new BeerSnackMapper();
        List<BeerSnack> list = this.getJdbcTemplate().query(sql, params,
                mapper);
        return list;
    }

    public List<BeerSnack> getIdSnackByIdBeer(long idBeer) {
        String sql = BeerSnackMapper.SELECT_BY_ID_BEER;
        Object[] params = new Object[] { idBeer };
        BeerSnackMapper mapper = new BeerSnackMapper();
        List<BeerSnack> list = this.getJdbcTemplate().query(sql, params,
                mapper);
        return list;
    }

    public int add(long idBeer, long idSnack) {
        Object[] params = new Object[2];
        params[0] = idBeer;
        params[1] = idSnack;

        String sql = BeerSnackMapper.INSERT;
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }

    public int delete(long idBeer, long idSnack) {
        Object[] params = new Object[2];
        params[0] = idBeer;
        params[1] = idSnack;

        String sql = BeerSnackMapper.DELETE;
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }
}
