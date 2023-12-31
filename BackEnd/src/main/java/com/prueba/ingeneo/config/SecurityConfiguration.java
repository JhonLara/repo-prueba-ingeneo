package com.prueba.ingeneo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import static com.prueba.ingeneo.entity.Permission.ADMIN_CREATE;
import static com.prueba.ingeneo.entity.Permission.ADMIN_DELETE;
import static com.prueba.ingeneo.entity.Permission.ADMIN_READ;
import static com.prueba.ingeneo.entity.Permission.ADMIN_UPDATE;
import static com.prueba.ingeneo.entity.Permission.MANAGER_CREATE;
import static com.prueba.ingeneo.entity.Permission.MANAGER_DELETE;
import static com.prueba.ingeneo.entity.Permission.MANAGER_READ;
import static com.prueba.ingeneo.entity.Permission.MANAGER_UPDATE;
import static com.prueba.ingeneo.entity.Role.ADMIN;
import static com.prueba.ingeneo.entity.Role.MANAGER;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

	@Autowired
	AuthenticationJwtFilter jwtAuthFilter;
	@Autowired
	AuthenticationProvider authenticationProvider;
	@Autowired
	LogoutHandler logoutHandler;

	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
        .csrf()
        .disable()
        .authorizeHttpRequests()
        .requestMatchers(
                "api/v1/auth/**",
                "api/v1/shipments/**",
                "/v2/api-docs",
                "/v3/api-docs",
                "/v3/api-docs/**",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui/**",
                "/webjars/**",
                "/swagger-ui.html"
        )
          .permitAll()

        .requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name())

        .requestMatchers(GET, "/api/v1/management/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
        .requestMatchers(POST, "/api/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
        .requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
        .requestMatchers(DELETE, "/api/v1/management/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())

        .anyRequest()
          .authenticated()
        .and()
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .logout()
        .logoutUrl("/api/v1/auth/logout")
        .addLogoutHandler(logoutHandler)
        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
    ;
		return http.build();
	}
}
