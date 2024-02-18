package com.orosales.microservices.cloudgateway.config.security;


import jakarta.ws.rs.HttpMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    public static final String ISSUE = "issue";
    public static final String READ = "read";
    public static final String MAINTENANCE = "maintenance";
    public static final String ELIMINATE = "eliminate";

    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception {

        http.authorizeExchange(auth -> {

            auth
                    .pathMatchers(HttpMethod.PUT, "/api/driver-licence-service/licences/eliminateExpired")
                    .hasRole(ELIMINATE)
                    .pathMatchers(HttpMethod.GET, "/api/driver-licence-service/licences/list/**")
                    .hasAnyRole(READ)
                    .pathMatchers(HttpMethod.GET, "/api/driver-licence-service/licences/validity/**")
                    .hasAnyRole(READ)
                    .pathMatchers(HttpMethod.POST, "/api/driver-licence-service/licences")
                    .hasRole(ISSUE)
                    .pathMatchers(HttpMethod.PATCH, "/api/driver-licence-service/licences/**")
                    .hasRole(MAINTENANCE)


                    /*.pathMatchers(HttpMethod.PATCH, "/api/driver-licence-service/**")
                    .hasRole(MAINTENANCE)*/
                    .anyExchange().authenticated();

            });

        http.oauth2ResourceServer(auth -> {
            auth.jwt(jwt -> {
                jwt.jwtAuthenticationConverter(jwtAuthConverter);
            });
        });

        http.csrf(ServerHttpSecurity.CsrfSpec::disable);


        return http.build();
    }

}
