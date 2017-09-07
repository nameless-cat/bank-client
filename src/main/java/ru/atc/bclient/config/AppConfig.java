package ru.atc.bclient.config;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@SpringBootConfiguration
public class AppConfig {

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer addCustomBigDecimalDeserialization() {
    return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder
        .serializationInclusion(Include.NON_NULL);
  }
}
