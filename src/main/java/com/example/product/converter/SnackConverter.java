
package com.example.product.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.product.model.Snack;

@Component
public class SnackConverter implements Converter<String, Snack> {

    @Override
    public Snack convert(String id) {
        return new Snack(Long.parseLong(id), "");
    }

}
