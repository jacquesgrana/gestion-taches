package com.gt.gestiontaches;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class GestiontachesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestiontachesApplication.class, args);
	}

}
