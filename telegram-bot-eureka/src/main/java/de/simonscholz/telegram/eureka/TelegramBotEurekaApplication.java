package de.simonscholz.telegram.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class TelegramBotEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelegramBotEurekaApplication.class, args);
	}
}
