package com.example.demo2;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration ;

//@ComponentScan(basePackages = {"com.example.demo2.controller","com.example.demo2.service"})
@ComponentScan(basePackages = {"com.example.demo2"})
@EntityScan(basePackages = {"com.example.demo2.models"})
@EnableJpaRepositories("com.example.demo2.repository")
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@SpringBootApplication
public class BookCRUDDemoApplication {
   public static void main(String[] args) {
      SpringApplication.run(BookCRUDDemoApplication.class, args);
   }
   
   @Bean  // ✅ Ensure WebClient.Builder is provided by Spring
   public WebClient.Builder webClientBuilder() {
       return WebClient.builder();
   }
   
}
