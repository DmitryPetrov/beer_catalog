
package com.example.DAO;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.BreweryMapper;
import com.example.model.Brewery;

@Repository
@Transactional
public class BreweryDAO extends JdbcDaoSupport {

    @Autowired
    public BreweryDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public List<Brewery> getAllBrewery() {
        String sql = BreweryMapper.SELECT_ALL;
        Object[] params = new Object[] {};
        BreweryMapper mapper = new BreweryMapper();
        List<Brewery> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }


    public Brewery getBrewery(long id) {
        String sql = BreweryMapper.SELECT_BY_ID;
        Object[] params = new Object[] { id };
        BreweryMapper mapper = new BreweryMapper();

        try {
            Brewery brewery =
                    this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return brewery;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    public Brewery getBrewery(String name) {
        String sql = BreweryMapper.SELECT_BY_NAME;
        Object[] params = new Object[] { name };
        BreweryMapper mapper = new BreweryMapper();

        try {
            Brewery brewery =
                    this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return brewery;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    public int addBrewery(Brewery brewery) {
        if (getBrewery(brewery.getId()) != null) {
            return 0;
        }
        Object[] params = new Object[] { brewery.getName() };

        String sql = BreweryMapper.INSERT;
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }


    public int setBrewery(Brewery brewery) {
        if (getBrewery(brewery.getId()) == null) {
            return 0;
        }
        Object[] params = new Object[] { brewery.getName(), brewery.getId() };

        String sql = BreweryMapper.UPDATE_NAME_BY_ID;
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }
}
