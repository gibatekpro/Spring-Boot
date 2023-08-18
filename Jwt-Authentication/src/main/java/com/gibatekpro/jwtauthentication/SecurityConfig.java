package com.gibatekpro.jwtauthentication;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 47. From {@link }
 * 48. Create the Security Filter Chain bean
 * 49. In filter, create white-list requests. These are requests
 * that do not require authentication
 * 50. Create the Session Management policy. We will use STATELESS because we want
 * each request to be self-contained and not require a session
 * 51. We will create an AuthenticationProvider. This will be a Bean that will be created later
 * 52. Now add the jwt Auth Filter. This will be injected
 * 53. Create the AuthenticationProvider Bean in {@link }
 * 91. From {@link }
 * 92. Add the allowed Rest Mappings
 * 93. We are done, But we will create a Demo controller that requires authentication, just for testing
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }


}
