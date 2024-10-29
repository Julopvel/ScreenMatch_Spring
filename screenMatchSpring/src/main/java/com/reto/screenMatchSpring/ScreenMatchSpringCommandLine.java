package com.reto.screenMatchSpring;

import com.reto.screenMatchSpring.commandLine.CommandLine;
import com.reto.screenMatchSpring.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenMatchSpringCommandLine implements CommandLineRunner {

	@Autowired
	private SerieRepository serieRepository;

	public static void main(String[] args) {
		SpringApplication.run(ScreenMatchSpringCommandLine.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		CommandLine commandLine = new CommandLine(serieRepository);

		commandLine.displayMenu();


	}
}
