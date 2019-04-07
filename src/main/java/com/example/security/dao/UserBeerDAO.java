
package com.example.security.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.product.form.BeerForm;
import com.example.security.mapper.UserBeerMapper;
import com.example.security.model.UserBeer;

@Repository
@Transactional
public class UserBeerDAO extends JdbcDaoSupport {

    @Autowired
    public UserBeerDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public UserBeer getBeerForUser(String userName, long idBeer) {
        String sql = UserBeerMapper.SELECT_BY_USERNAME_AND_IDBEER;
        Object[] params = new Object[] { userName, idBeer };
        UserBeerMapper mapper = new UserBeerMapper();

        try {
            UserBeer beer =
                    this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return beer;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    public List<UserBeer> getAllBeerForUser(String userName) {
        String sql = UserBeerMapper.SELECT_BY_USERNAME;
        Object[] params = new Object[] { userName };
        UserBeerMapper mapper = new UserBeerMapper();
        List<UserBeer> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }


    public List<UserBeer> getBeerByBeerNameForUser(String userName,
            String beerName) {
        String sql = UserBeerMapper.SELECT_BY_USERNAME_AND_BEERNAME;
        Object[] params = new Object[] { userName, "%" + beerName + "%" };
        UserBeerMapper mapper = new UserBeerMapper();
        List<UserBeer> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }


    public List<UserBeer> getBeerByRateForUser(String userName, int rate) {
        String sql = UserBeerMapper.SELECT_BY_USERNAME_AND_RATE;
        Object[] params = new Object[] { userName, rate };
        UserBeerMapper mapper = new UserBeerMapper();
        List<UserBeer> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }


    public List<UserBeer> getBeerByCountForUser(String userName, int count) {
        String sql = UserBeerMapper.SELECT_BY_USERNAME_AND_COUNT;
        Object[] params = new Object[] { userName, count };
        UserBeerMapper mapper = new UserBeerMapper();
        List<UserBeer> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }


    public List<UserBeer> getBeerByStyleForUser(String userName, int idStyle) {
        String sql = UserBeerMapper.SELECT_BY_USERNAME_AND_STYLE;
        Object[] params = new Object[] { userName, idStyle };
        UserBeerMapper mapper = new UserBeerMapper();
        List<UserBeer> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }


    public List<UserBeer> getBeerByBreweryForUser(String userName,
            int idBrewery) {
        String sql = UserBeerMapper.SELECT_BY_USERNAME_AND_BREWERY;
        Object[] params = new Object[] { userName, idBrewery };
        UserBeerMapper mapper = new UserBeerMapper();
        List<UserBeer> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }


    public List<UserBeer> getBeerByCountryForUser(String userName,
            int idCountry) {
        String sql = UserBeerMapper.SELECT_BY_USERNAME_AND_COUNTRY;
        Object[] params = new Object[] { userName, idCountry };
        UserBeerMapper mapper = new UserBeerMapper();
        List<UserBeer> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }


    public List<UserBeer> getBeerByStarForUser(String userName, boolean star) {
        String sql = UserBeerMapper.SELECT_BY_USERNAME_AND_STAR;
        Object[] params = new Object[] { userName, star };
        UserBeerMapper mapper = new UserBeerMapper();
        List<UserBeer> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }


    public List<UserBeer> getBeerByCraftForUser(String userName,
            boolean craft) {
        String sql = UserBeerMapper.SELECT_BY_USERNAME_AND_CRAFT;
        Object[] params = new Object[] { userName, craft };
        UserBeerMapper mapper = new UserBeerMapper();
        List<UserBeer> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }


    // @Transaction
    public int addBeerForUser(long idUser, BeerForm beerForm) {
        Object[] params = new Object[6];

        params[0] = idUser;
        params[1] = beerForm.getId();
        params[2] = beerForm.getRate();
        params[3] = beerForm.getCount();
        params[4] = beerForm.isStar();
        params[5] = beerForm.getDescription();

        String sql = UserBeerMapper.INSERT;
        int countUpdated = this.getJdbcTemplate().update(sql, params);
        return countUpdated;
    }


    // ??????
    public int putBeerForUser(long idUser, BeerForm beerForm) {
        Object[] params = new Object[6];

        params[0] = beerForm.getRate();
        params[1] = beerForm.getCount();
        params[2] = beerForm.isStar();
        params[3] = beerForm.getDescription();

        params[4] = idUser;
        params[5] = beerForm.getId();

        String sql = UserBeerMapper.UPDATE_BY_USERNAME_AND_IDBEER;

        int countUpdated = this.getJdbcTemplate().update(sql, params);

        return countUpdated;
    }


    public int deleteBeerForUser(long idUser, BeerForm beerForm) {
        Object[] params = new Object[] { idUser, beerForm.getId() };

        String sql = UserBeerMapper.DELETE_BY_USERNAME_AND_IDBEER;

        int countUpdated = this.getJdbcTemplate().update(sql, params);

        return countUpdated;

    }
}
