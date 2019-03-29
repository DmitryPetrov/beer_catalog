package com.example.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.StyleSnackMapper;
import com.example.model.BeerStyle;
import com.example.model.StyleSnack;

@Repository
@Transactional
public class StyleSnackDAO extends JdbcDaoSupport {

    @Autowired
    public StyleSnackDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<StyleSnack> getAll() {
        String sql = StyleSnackMapper.SELECT_ALL;
        Object[] params = new Object[] {};
        StyleSnackMapper mapper = new StyleSnackMapper();
        List<StyleSnack> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<Long> getIdStyleByIdSnack(long id) {
        String sql = StyleSnackMapper.SELECT_BY_ID_SNACK;
        Object[] params = new Object[] { id };
        StyleSnackMapper mapper = new StyleSnackMapper();
        List<StyleSnack> list = this.getJdbcTemplate().query(sql, params, mapper);

        List<Long> styleId = new ArrayList<>();
        for (StyleSnack styleSnack : list) {
            styleId.add(styleSnack.getIdStyle());
        }

        return styleId;
    }

    public List<Long> getIdSnackByIdStyle(long id) {
        String sql = StyleSnackMapper.SELECT_BY_ID_STYLE;
        Object[] params = new Object[] { id };
        StyleSnackMapper mapper = new StyleSnackMapper();
        List<StyleSnack> list = this.getJdbcTemplate().query(sql, params, mapper);

        List<Long> snackId = new ArrayList<>();
        for (StyleSnack styleSnack : list) {
            snackId.add(styleSnack.getIdSnack());
        }

        return snackId;
    }

    public int add(long idStyle, long idSnack) {
        String sql = StyleSnackMapper.INSERT;
        Object[] params = new Object[] { idStyle, idSnack };
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }

    public int deleteByIdStyle(long id) {
        String sql = StyleSnackMapper.DELETE_BY_ID_STYLE;
        Object[] params = new Object[] { id };
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }

    public int deleteByIdSnack(long id) {
        String sql = StyleSnackMapper.DELETE_BY_ID_SNACK;
        Object[] params = new Object[] { id };
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }

}
