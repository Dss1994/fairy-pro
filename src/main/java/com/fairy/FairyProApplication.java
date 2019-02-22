package com.fairy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
@ServletComponentScan
public class FairyProApplication {
	private static ApplicationContext springContext;
	
	public static ApplicationContext getSpringContext() {
		return springContext;
	}

	public static void main(String[] args) {
		springContext = SpringApplication.run(FairyProApplication.class, args);
	}
	

}

