package com.ess.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ess.web.entity.EssUser;

@SpringBootApplication
public class EeswebApplication {

	public static void main(String[] args) {
		SpringApplication.run(EeswebApplication.class, args);
		System.out.println("HI PRAVIN");
	}

}
