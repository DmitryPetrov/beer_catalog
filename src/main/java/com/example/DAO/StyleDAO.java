
package com.example.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.StyleMapper;
import com.example.model.BeerInfo;
import com.example.model.Style;

@Repository
@Transactional
public class StyleDAO extends JdbcDaoSupport {

    @Autowired
    private BeerStyleDAO beerStyleDAO;
    
    @Autowired
    public StyleDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Style> getAll() {
        String sql = StyleMapper.SELECT_ALL;
        Object[] params = new Object[] {};
        StyleMapper mapper = new StyleMapper();
        List<Style> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<BeerInfo> getAllLikeBeerInfo() {
        List<Style> list = getAll();
        List<BeerInfo> beerInfo = new ArrayList<>();
        for (Style style : list) {
            beerInfo.add(style);
        }
        return beerInfo;
    }

    public Style get(long id) {
        String sql = StyleMapper.SELECT_BY_ID;
        Object[] params = new Object[] { id };
        StyleMapper mapper = new StyleMapper();

        try {
            Style style = this.getJdbcTemplate().queryForObject(sql, params,
                    mapper);
            return style;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Style get(String name) {
        String sql = StyleMapper.SELECT_BY_NAME;
        Object[] params = new Object[] { name };
        StyleMapper mapper = new StyleMapper();

        try {
            Style style = this.getJdbcTemplate().queryForObject(sql, params,
                    mapper);
            return style;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int add(Style style) {
        if (get(style.getName()) != null) {
            return 0;
        }
        
        String sql = StyleMapper.INSERT;
        Object[] params = new Object[] { style.getName() };
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }

    public int put(Style style) {
        if (get(style.getId()) == null) {
            return 0;
        }
        
        String sql = StyleMapper.UPDATE_NAME_BY_ID;
        Object[] params = new Object[] { style.getName(), style.getId() };
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }
    
    public int delete(Style style) {
        if (get(style.getId()) == null) {
            return 0;
        }
        
        beerStyleDAO.deleteByIdStyle(style.getId());
        
        String sql = StyleMapper.DELETE;
        Object[] params = new Object[] { style.getId() };
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }
}
