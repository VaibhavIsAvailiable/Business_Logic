package com.crowdfunding_paradigm.platform;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrowdfundingParadigmApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrowdfundingParadigmApplication.class, args);
	}
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
