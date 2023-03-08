package com.app.bank;

import com.app.bank.service.ApplicationSimulatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(BankApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		ApplicationContext applicationContext = application.run(args);

		applicationContext.getBean(ApplicationSimulatorService.class).simulateOperations();
	}

}
