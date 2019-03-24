
package com.example.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.model.Country;

@Component
public class CountryConverter implements Converter<String, Country> {

    @Override
    public Country convert(String id) {
        return new Country(Long.parseLong(id), "");
    }

}
