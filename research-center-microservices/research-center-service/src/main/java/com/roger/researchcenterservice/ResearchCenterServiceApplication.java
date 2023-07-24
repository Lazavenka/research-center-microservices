package com.roger.researchcenterservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ResearchCenterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResearchCenterServiceApplication.class, args);
	}

}
