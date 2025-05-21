package it.epicode.CapstoneEpicode.BastoneStudio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors() // Abilita CORS
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/api/auth/**").permitAll() // Login libero
                .requestMatchers("/api/admin/**").hasRole("ADMIN") // Solo admin
                .anyRequest().permitAll(); // Tutte le altre rotte sono pubbliche

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}