package br.com.cardgame.jeff.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ConfigSecurity {


    @Autowired
    private JwTAuthEntryPoint jwTAuthEntryPoint;

    @Bean
     SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
            .csrf(csrf -> csrf.disable())
            .exceptionHandling( excpetionHandling -> 
                excpetionHandling.authenticationEntryPoint(jwTAuthEntryPoint))
            .sessionManagement( sm ->
                sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests( autho ->
                autho
                
                .requestMatchers("/api/user/register").permitAll()
                
                .requestMatchers("/api/user/login").permitAll()
                
                .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll() 
                
                .anyRequest().authenticated())
                
                .addFilterBefore(jwTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    
    @Bean
     AuthenticationManager authenticationManager (AuthenticationConfiguration authConf) throws Exception{
        return authConf.getAuthenticationManager();
    }

    @Bean
     PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }

    @Bean
     JwTAuthenticationFilter jwTAuthenticationFilter () {
        return new JwTAuthenticationFilter();
    }
}