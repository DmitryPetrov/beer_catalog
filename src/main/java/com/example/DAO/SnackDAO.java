
package com.example.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.SnackMapper;
import com.example.model.BeerInfo;
import com.example.model.Snack;

@Repository
@Transactional
public class SnackDAO extends JdbcDaoSupport {

    @Autowired
    public SnackDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Snack> getAllSnack() {
        String sql = SnackMapper.SELECT_ALL;
        Object[] params = new Object[] {};
        SnackMapper mapper = new SnackMapper();
        List<Snack> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<BeerInfo> getAllSnackLikeBeerInfo() {
        List<Snack> list = getAllSnack();
        List<BeerInfo> beerInfo = new ArrayList<>();
        for (Snack snack : list) {
            beerInfo.add(snack);
        }
        return beerInfo;
    }

    public Snack getSnack(long id) {
        String sql = SnackMapper.SELECT_BY_ID;
        Object[] params = new Object[] { id };
        SnackMapper mapper = new SnackMapper();

        try {
            Snack snack = this.getJdbcTemplate().queryForObject(sql, params,
                    mapper);
            return snack;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Snack getSnack(String name) {
        String sql = SnackMapper.SELECT_BY_NAME;
        Object[] params = new Object[] { name };
        SnackMapper mapper = new SnackMapper();

        try {
            Snack snack = this.getJdbcTemplate().queryForObject(sql, params,
                    mapper);
            return snack;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int addSnack(Snack snack) {
        if (getSnack(snack.getName()) != null) {
            return 0;
        }

        Object[] params = new Object[] { snack.getName() };

        String sql = SnackMapper.INSERT;
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }

    public int setSnack(Snack snack) {
        if (getSnack(snack.getId()) == null) {
            return 0;
        }

        Object[] params = new Object[] { snack.getName(), snack.getId() };

        String sql = SnackMapper.UPDATE_NAME_BY_ID;
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }
}
