package org.rib.observingclient.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Configuration
public class RestConfig {

   private static Random random = new Random();

   @Bean
   public RestTemplate restTemplate(RestTemplateBuilder builder) {
      return builder.build();
   }

}
