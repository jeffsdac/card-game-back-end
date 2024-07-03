package br.com.cardgame.jeff.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration{

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
        http.
                authorizeHttpRequests(auth -> auth
                .requestMatchers("/").permitAll()
                .requestMatchers("/user").hasRole("USER")
                .requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated())
                .formLogin(login -> login.permitAll());
            
            
            
        return http.build();
    } 

    
    @Bean
    public UserDetailsService userDetailsService() {
        var user = User.builder()
        .username("jeff")
        .password("{noop}jeff")
        .roles("USER")
        .build();

        var userAdmin = User.builder()
        .username("admin")
        .password("{noop}admin")
        .roles("ADMIN", "USER")
        .build();

        return new InMemoryUserDetailsManager(user, userAdmin);
    }

}
