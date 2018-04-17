package de.simonscholz.telegram.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

@EnableWebFluxSecurity
@SpringCloudApplication
public class TelegramBotControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelegramBotControllerApplication.class, args);
	}
}
