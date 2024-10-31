package com.arulJD.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import java.util.Collections;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class AppConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/**").authenticated().anyRequest().permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .formLogin(withDefaults());

        return httpSecurity.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {

        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                CorsConfiguration corsConfiguration = new CorsConfiguration();
                corsConfiguration.addAllowedOrigin(String.valueOf(Collections.singletonList("*")));
                corsConfiguration.addAllowedMethod(String.valueOf(Collections.singletonList("*")));
                corsConfiguration.addAllowedHeader(String.valueOf(Collections.singletonList("*")));
                corsConfiguration.addExposedHeader(String.valueOf(Collections.singletonList("*")));
                corsConfiguration.setMaxAge(3600L);
                return corsConfiguration;
            }
        };
    }
}
