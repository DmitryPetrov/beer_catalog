
package com.example.security.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RoleDAO extends JdbcDaoSupport {

    @Autowired
    public RoleDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public List<String> getRoleNames(Long userId) {
        String sql = "SELECT name FROM user_role, role "
                + "WHERE user_role.role_id = role.id "
                + "AND user_role.user_id = ? ";

        Object[] params = new Object[] { userId };

        List<String> roles =
                this.getJdbcTemplate().queryForList(sql, params, String.class);

        return roles;
    }
}
