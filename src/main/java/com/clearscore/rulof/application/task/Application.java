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

	private static void setPort() throws IOException {
		String port = System.getenv("HTTP_PORT");
		if (isParsable(port)){
			System.setProperty("server.port", port);
		}
		else{
			throw new IOException("Cant parse Port in HTTP_PORT environmental variable.");
		}
	}

	public static void main(String[] args) throws Exception {
		setPort();
		SpringApplication.run(Application.class, args);
	}
}
