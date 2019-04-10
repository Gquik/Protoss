package com.gqk.protoss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ProtossApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProtossApplication.class, args);
	}

}
