package com.example.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.support.KeyHolder;

public class BeerIdHolder implements KeyHolder{

    private long id;
    
    @Override
    public Number getKey() throws InvalidDataAccessApiUsageException {
        return new Long(id);
    }

    @Override
    public Map<String, Object> getKeys()
            throws InvalidDataAccessApiUsageException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Map<String, Object>> getKeyList() {
        // TODO Auto-generated method stub
        return null;
    }

}
