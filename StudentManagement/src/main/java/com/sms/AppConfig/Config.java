package com.sms.AppConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class Config {
    @Bean
    public SecurityFilterChain masaiSecurityConfig(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests( (auth)->auth
//auth.anyRequest().authenticated();
                                .requestMatchers("/admin","/student").authenticated()
                                .requestMatchers("/student","/admin").permitAll()
                ).csrf(csrf -> csrf.disable())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }
}
