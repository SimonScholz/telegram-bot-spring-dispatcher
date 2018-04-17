package de.simonscholz.telegram.controller;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.filter.reactive.HiddenHttpMethodFilter;

@Configuration
public class RoutesConfig {

	@Bean
	MapReactiveUserDetailsService authentication() {
		return new MapReactiveUserDetailsService(User.withUsername("user").password("pw").roles("USER").build());
	}

	@Bean
	SecurityWebFilterChain authorization(ServerHttpSecurity security) {
		return security.authorizeExchange().pathMatchers("/admin").authenticated().anyExchange().permitAll().and()
				.httpBasic().and().csrf().disable().build();
	}

	@Bean
	DiscoveryClientRouteDefinitionLocator discoveryRoutes(DiscoveryClient dc) {
		return new DiscoveryClientRouteDefinitionLocator(dc);
	}

	@Bean
	RouteLocator gatewayRoutes(RouteLocatorBuilder b) {
		return b.routes().route(r -> r.path("/bot/dmiWebhook").uri("lb://TELEGRAM-BOT-SPRING-DMI-WHEATER/")).build();
	}

	@Bean
	HiddenHttpMethodFilter hiddenHttpMethodFilter() {
		return new HiddenHttpMethodFilter();
	}
}
