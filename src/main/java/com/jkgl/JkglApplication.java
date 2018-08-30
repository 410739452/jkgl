package com.jkgl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan("com.jkgl")
public class JkglApplication {

	public static void main(String[] args) {
		SpringApplication.run(JkglApplication.class, args);
	}
}
