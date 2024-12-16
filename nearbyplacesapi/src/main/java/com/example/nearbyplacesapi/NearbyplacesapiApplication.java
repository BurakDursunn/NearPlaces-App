package com.example.nearbyplacesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class NearbyplacesapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NearbyplacesapiApplication.class, args);
	}

}
