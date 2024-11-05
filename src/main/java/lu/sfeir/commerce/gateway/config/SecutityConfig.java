package lu.sfeir.commerce.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecutityConfig {
	@Value("${keycloak.issuer}")
    private String issuerUri;

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
		return httpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable)
				.authorizeExchange(exchange->exchange.pathMatchers("/eureka/**")
						.permitAll()
						.anyExchange().authenticated())
				.oauth2ResourceServer((oauth)-> oauth.jwt(Customizer.withDefaults()))
						.build();
	}
	
	@Bean
	public ReactiveJwtDecoder jwtDecoder() {
		return ReactiveJwtDecoders.fromIssuerLocation(issuerUri);
	}

}
//https://www.youtube.com/watch?v=La082JsJoH4