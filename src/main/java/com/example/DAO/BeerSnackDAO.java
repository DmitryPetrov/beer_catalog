
package com.example.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.BeerSnackMapper;
import com.example.model.BeerSnack;
import com.example.model.BeerStyle;

@Repository
@Transactional
public class BeerSnackDAO extends JdbcDaoSupport {

    @Autowired
    public BeerSnackDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<BeerSnack> getAll() {
        String sql = BeerSnackMapper.SELECT_ALL;
        Object[] params = new Object[] {};
        BeerSnackMapper mapper = new BeerSnackMapper();
        List<BeerSnack> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<Long> getIdBeerByIdSnack(long idSnack) {
        String sql = BeerSnackMapper.SELECT_BY_ID_SNACK;
        Object[] params = new Object[] { idSnack };
        BeerSnackMapper mapper = new BeerSnackMapper();
        List<BeerSnack> list = this.getJdbcTemplate().query(sql, params, mapper);

        List<Long> beerId = new ArrayList<>();
        for (BeerSnack beerStyle : list) {
            beerId.add(beerStyle.getIdBeer());
        }

        return beerId;
    }

    public List<Long> getIdSnackByIdBeer(long idBeer) {
        String sql = BeerSnackMapper.SELECT_BY_ID_BEER;
        Object[] params = new Object[] { idBeer };
        BeerSnackMapper mapper = new BeerSnackMapper();
        List<BeerSnack> list = this.getJdbcTemplate().query(sql, params, mapper);

        List<Long> snackId = new ArrayList<>();
        for (BeerSnack beerSnack : list) {
            snackId.add(beerSnack.getIdSnack());
        }

        return snackId;
    }

    public int add(long idBeer, long idSnack) {
        String sql = BeerSnackMapper.INSERT;
        Object[] params = new Object[] { idBeer, idSnack };
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }

    public int deleteByIdBeer(long id) {
        String sql = BeerSnackMapper.DELETE_BY_ID_BEER;
        Object[] params = new Object[] { id };
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }

    public int deleteByIdSnack(long id) {
        String sql = BeerSnackMapper.DELETE_BY_ID_SNACK;
        Object[] params = new Object[] { id };
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }
}
