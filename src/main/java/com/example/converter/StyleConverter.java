
package com.example.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.model.Style;

@Component
public class StyleConverter implements Converter<String, Style> {

    @Override
    public Style convert(String id) {
        return new Style(Long.parseLong(id), "");
    }
}
