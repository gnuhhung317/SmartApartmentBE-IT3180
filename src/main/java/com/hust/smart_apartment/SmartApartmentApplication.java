package com.hust.smart_apartment;

import com.hust.smart_apartment.service.CodeGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
public class SmartApartmentApplication {
	public static void main(String[] args) {
		SpringApplication.run(SmartApartmentApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return args -> {
//			codeGeneratorService.createGeneratedCodes();
		};
	}

}
