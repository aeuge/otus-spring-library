package ru.otus.library.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import ru.otus.library.service.MongoUserDetailsService;

@EnableWebFluxSecurity
public class SecurityConfiguration {
    @Autowired
    private MongoUserDetailsService userDetailsService;
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf().disable()
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //.and()
                //.authorizeRequests().antMatchers("/public").permitAll()
                //.and()
                //.securityContextRepository(NoOpServerSecurityContextRepository.getInstance())//STATELESS
                .authorizeExchange().pathMatchers("/", "/book/**").hasRole("ADMIN")
                .and()
                .formLogin()
                // Включает HTTP-basic
                //.httpBasic()
                .and()
                .logout().logoutUrl("/")
                //.and()
                // Включает Remember-me аутентифкацию
                //.rememberMe().key("someSecret")
        ;
        return http.build(); //4
    }
    
    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
