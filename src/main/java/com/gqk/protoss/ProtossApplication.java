package com.gqk.protoss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication//(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.gqk.protoss.dao")
public class ProtossApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ProtossApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ProtossApplication.class, args);
	}

}
