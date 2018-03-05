package de.simonscholz.telegram.controller.rest;

import java.util.Collections;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;

import de.simonscholz.telegram.bot.api.domain.Update;
import reactor.core.publisher.Mono;

@RestController
public class TelegramBotController {

	private RestTemplate restTemplate;
	private WebClient webClient;

	public TelegramBotController(RestTemplate restTemplate, WebClient.Builder webClient) {
		this.restTemplate = restTemplate;
		this.webClient = webClient.build();
	}

	@PostMapping("/blockingdmiWebhook")
	public ResponseEntity<Mono<Void>> webhook(@RequestBody Update newUpdate) throws JsonProcessingException {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<Update> requestEntity = new HttpEntity<>(newUpdate, headers);

		return restTemplate.exchange("http://TELEGRAM-BOT-SPRING-DMI-WHEATER/dmiWebhook", HttpMethod.POST,
				requestEntity, new ParameterizedTypeReference<Mono<Void>>() {
				});
	}

	@PostMapping("/dmiWebhook")
	public Mono<Void> dmiWeatherWebhook(@RequestBody Update newUpdate) {
		return webClient.post().uri("http://TELEGRAM-BOT-SPRING-DMI-WHEATER/dmiWebhook")
				.body(BodyInserters.fromObject(newUpdate)).exchange().then();
	}
}
