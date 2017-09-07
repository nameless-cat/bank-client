package ru.atc.bclient.web.controller.converters;

import java.sql.Date;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToSqlDateConverter implements Converter<String, Date> {

  @Override
  public Date convert(String source) {
    Date date = null;

    try {
      date = Date.valueOf(source);
    } catch (IllegalArgumentException e) {
      //log exception
    }

    return date;
  }
}
