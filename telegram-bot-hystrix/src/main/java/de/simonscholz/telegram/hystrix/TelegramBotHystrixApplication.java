package de.simonscholz.telegram.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class TelegramBotHystrixApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelegramBotHystrixApplication.class, args);
	}
}
