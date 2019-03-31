
package com.example.product.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.product.mapper.BreweryMapper;
import com.example.product.model.BeerInfo;
import com.example.product.model.Brewery;

@Repository
@Transactional
public class BreweryDAO extends JdbcDaoSupport {

    @Autowired
    private BeerBreweryDAO beerBreweryDAO;
    
    @Autowired
    public BreweryDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Brewery> getAll() {
        String sql = BreweryMapper.SELECT_ALL;
        Object[] params = new Object[] {};
        BreweryMapper mapper = new BreweryMapper();
        List<Brewery> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<BeerInfo> getAllLikeBeerInfo() {
        List<Brewery> list = getAll();
        List<BeerInfo> beerInfo = new ArrayList<>();
        for (Brewery brewery : list) {
            beerInfo.add(brewery);
        }
        return beerInfo;
    }

    public Brewery get(long id) {
        String sql = BreweryMapper.SELECT_BY_ID;
        Object[] params = new Object[] { id };
        BreweryMapper mapper = new BreweryMapper();

        try {
            Brewery brewery = this.getJdbcTemplate().queryForObject(sql, params,
                    mapper);
            return brewery;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Brewery get(String name) {
        String sql = BreweryMapper.SELECT_BY_NAME;
        Object[] params = new Object[] { name };
        BreweryMapper mapper = new BreweryMapper();

        try {
            Brewery brewery = this.getJdbcTemplate().queryForObject(sql, params,
                    mapper);
            return brewery;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int add(Brewery brewery) {
        if (get(brewery.getId()) != null) {
            return 0;
        }
        
        String sql = BreweryMapper.INSERT;
        Object[] params = new Object[] { brewery.getName() };
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }

    public int put(Brewery brewery) {
        if (get(brewery.getId()) == null) {
            return 0;
        }
        
        String sql = BreweryMapper.UPDATE_NAME_BY_ID;
        Object[] params = new Object[] { brewery.getName(), brewery.getId() };
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }

    public int delete(Brewery brewery) {
        if (get(brewery.getId()) == null) {
            return 0;
        }
        
        beerBreweryDAO.deleteByIdBrewery(brewery.getId());
        
        String sql = BreweryMapper.DELETE;
        Object[] params = new Object[] { brewery.getId() };
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }
}
