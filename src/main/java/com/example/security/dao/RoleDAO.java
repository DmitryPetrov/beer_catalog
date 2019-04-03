
package com.example.security.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.security.mapper.RoleMapper;

@Repository
@Transactional
public class RoleDAO extends JdbcDaoSupport {

    @Autowired
    public RoleDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public List<String> getRoleNamesByUserId(Long userId) {
        String sql = RoleMapper.SELECT_ROLENAME_BY_USERID;

        Object[] params = new Object[] { userId };

        List<String> roles =
                this.getJdbcTemplate().queryForList(sql, params, String.class);

        return roles;
    }
}
