package lu.sfeir.commerce.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecutityConfig {
	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
		return httpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable)
				.authorizeExchange(exchange->exchange.pathMatchers("/eureka/**")
						.permitAll()
						.anyExchange().authenticated())
				.oauth2ResourceServer((oauth)-> oauth.jwt(Customizer.withDefaults()))
						.build();
	}

}
//https://www.youtube.com/watch?v=La082JsJoH4