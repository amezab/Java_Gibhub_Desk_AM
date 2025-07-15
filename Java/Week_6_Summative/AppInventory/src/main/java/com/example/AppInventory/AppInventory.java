package com.example.AppInventory;

import com.example.AppInventory.ui.MenuController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppInventory implements CommandLineRunner {

	@Autowired
	private MenuController menuController;

	public static void main(String[] args) {
		SpringApplication.run(AppInventory.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		menuController.startMenu();
	}
}