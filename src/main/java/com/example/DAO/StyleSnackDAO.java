package com.example.DAO;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.StyleSnackMapper;
import com.example.model.StyleSnack;

@Repository
@Transactional
public class StyleSnackDAO extends JdbcDaoSupport {

    @Autowired
    public StyleSnackDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<StyleSnack> getAllStyleSnackTable() {
        String sql = StyleSnackMapper.SELECT_ALL;
        Object[] params = new Object[] {};
        StyleSnackMapper mapper = new StyleSnackMapper();
        List<StyleSnack> list = this.getJdbcTemplate().query(sql, params,
                mapper);
        return list;
    }

    public List<StyleSnack> getIdStyleByIdSnack(long idSnack) {
        String sql = StyleSnackMapper.SELECT_BY_ID_SNACK;
        Object[] params = new Object[] { idSnack };
        StyleSnackMapper mapper = new StyleSnackMapper();
        List<StyleSnack> list = this.getJdbcTemplate().query(sql, params,
                mapper);
        return list;
    }

    public List<StyleSnack> getIdSnackByIdStyle(long idStyle) {
        String sql = StyleSnackMapper.SELECT_BY_ID_STYLE;
        Object[] params = new Object[] { idStyle };
        StyleSnackMapper mapper = new StyleSnackMapper();
        List<StyleSnack> list = this.getJdbcTemplate().query(sql, params,
                mapper);
        return list;
    }

    public int add(long idStyle, long idSnack) {
        Object[] params = new Object[2];
        params[0] = idStyle;
        params[1] = idSnack;

        String sql = StyleSnackMapper.INSERT;
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }

    public int delete(long idStyle, long idSnack) {
        Object[] params = new Object[2];
        params[0] = idStyle;
        params[1] = idSnack;

        String sql = StyleSnackMapper.DELETE;
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }
}
