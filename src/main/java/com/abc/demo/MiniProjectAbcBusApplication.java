package com.abc.demo;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = { "com.abc.model" })
@EnableJpaRepositories(basePackages = { "com.abc.repository" })
@ComponentScan({ "com.abc.controller,com.abc.service,com.abc.exception", "controller", "service" })
public class MiniProjectAbcBusApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(MiniProjectAbcBusApplication.class, args);
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new SpecificationArgumentResolver());
	}

}
