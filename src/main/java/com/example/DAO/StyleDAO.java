
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
    public StyleDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Style> getAllStyle() {
        String sql = StyleMapper.SELECT_ALL;
        Object[] params = new Object[] {};
        StyleMapper mapper = new StyleMapper();
        List<Style> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<BeerInfo> getAllStyleLikeBeerInfo() {
        List<Style> list = getAllStyle();
        List<BeerInfo> beerInfo = new ArrayList<>();
        for (Style style : list) {
            beerInfo.add(style);
        }
        return beerInfo;
    }

    public Style getStyle(long id) {
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

    public Style getStyle(String name) {
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

    public int addStyle(Style style) {
        if (getStyle(style.getName()) != null) {
            return 0;
        }

        Object[] params = new Object[] { style.getName() };

        String sql = StyleMapper.INSERT;
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }

    public int setStyle(Style style) {
        if (getStyle(style.getId()) == null) {
            return 0;
        }

        Object[] params = new Object[] { style.getName(), style.getId() };

        String sql = StyleMapper.UPDATE_NAME_BY_ID;
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }
}
