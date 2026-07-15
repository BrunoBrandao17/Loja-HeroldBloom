package com.heroldbloom.heroldbloom_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(authorize -> authorize
                        // 1. Clientes podem ver os produtos livremente
                        .requestMatchers(HttpMethod.GET, "/api/produtos").permitAll()

                        // 2. Qualquer um pode tentar se cadastrar ou logar como Dona
                        .requestMatchers( "/api/dona/login").permitAll()

                        // 3. EXIGE autenticação para alterar ou excluir a Dona
                        .requestMatchers("/api/dona/cadastrar","/api/dona/alterar/**", "/api/dona/excluir/**").authenticated()

                        // 4. Qualquer outra rota de produto (POST, PUT, DELETE) exige autenticação
                        .anyRequest().authenticated()
                )

                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}