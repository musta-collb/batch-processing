package com.example.batchprocessing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.example.batchprocessing.batch",
		"com.example.batchprocessing.config",
		"com.example.batchprocessing.dto",
		"com.example.batchprocessing.entities",
		"com.example.batchprocessing.helpers",
		"com.example.batchprocessing.repositories",
		"com.example.batchprocessing.services"})
@EnableJpaRepositories(basePackages = "com.example.batchprocessing.repositories")
public class BatchProcessingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchProcessingApplication.class, args);
	}

}
