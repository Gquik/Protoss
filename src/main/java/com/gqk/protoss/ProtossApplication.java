package com.gqk.protoss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.gqk.protoss.dao")
public class ProtossApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProtossApplication.class, args);
	}

}
