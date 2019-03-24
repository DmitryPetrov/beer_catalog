
package com.example.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.model.Brewery;

@Component
public class BreweryConverter implements Converter<String, Brewery> {

    @Override
    public Brewery convert(String id) {
        return new Brewery(Long.parseLong(id), "");
    }

}
