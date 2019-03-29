
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
    private BeerSnackDAO beerSnackDAO;
    
    @Autowired
    public SnackDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Snack> getAll() {
        String sql = SnackMapper.SELECT_ALL;
        Object[] params = new Object[] {};
        SnackMapper mapper = new SnackMapper();
        List<Snack> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<BeerInfo> getAllLikeBeerInfo() {
        List<Snack> list = getAll();
        List<BeerInfo> beerInfo = new ArrayList<>();
        for (Snack snack : list) {
            beerInfo.add(snack);
        }
        return beerInfo;
    }

    public Snack get(long id) {
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

    public Snack get(String name) {
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

    public int add(Snack snack) {
        if (get(snack.getName()) != null) {
            return 0;
        }
        
        String sql = SnackMapper.INSERT;
        Object[] params = new Object[] { snack.getName() };
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }

    public int put(Snack snack) {
        if (get(snack.getId()) == null) {
            return 0;
        }
        
        String sql = SnackMapper.UPDATE_NAME_BY_ID;
        Object[] params = new Object[] { snack.getName(), snack.getId() };
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }
    
    public int delete(Snack snack) {
        if (get(snack.getId()) == null) {
            return 0;
        }
        
        beerSnackDAO.deleteByIdSnack(snack.getId());
        
        String sql = SnackMapper.DELETE;
        Object[] params = new Object[] { snack.getId() };
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }
}
