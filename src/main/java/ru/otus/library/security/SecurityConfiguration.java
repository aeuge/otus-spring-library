package ru.otus.library.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import ru.otus.library.service.MongoUserDetailsService;

@EnableWebFluxSecurity
public class SecurityConfiguration {
    @Autowired
    private MongoUserDetailsService userDetailsService;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf().disable()
                .authorizeExchange().pathMatchers( "/", "/api/allbooks").hasRole("ADMIN")
                .anyExchange().permitAll()
                .and()
                .formLogin()
                .and()
                .logout()
        ;
        return http.build();
    }

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
        //return NoOpPasswordEncoder.getInstance();
    }
}
