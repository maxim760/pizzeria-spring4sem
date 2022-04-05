package com.example.pizzeria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// mvn clean package
// mvn  spring-boot:run - для получения jar файла


// mvn clean package spring-boot:repackage
// java -jar ./target/pizzeria-0.0.1-SNAPSHOT.jar

@SpringBootApplication
public class PizzeriaApplication {
	public static void main(String[] args) {
		SpringApplication.run(PizzeriaApplication.class, args);
	}
}
