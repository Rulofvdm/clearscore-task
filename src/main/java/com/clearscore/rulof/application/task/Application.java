package com.clearscore.rulof.application.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class Application {

	public static boolean isParsable(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (final NumberFormatException e) {
			return false;
		}
	}

	private static boolean setPort() {
		String port = System.getenv("HTTP_PORT");
		if (isParsable(port)) {
			System.setProperty("server.port", port);
			return true;
		}
		else {
			return false;
		}
	}

	public static void main(String[] args) {
		if(!setPort()) return;
		SpringApplication.run(Application.class, args);
	}
}
