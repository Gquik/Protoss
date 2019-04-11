package com.gqk.protoss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gqk.protoss")
public class ProtossApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProtossApplication.class, args);
	}

}
