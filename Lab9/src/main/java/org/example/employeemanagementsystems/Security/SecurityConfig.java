package org.example.employeemanagementsystems.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/employees/**").authenticated() // Secure API endpoints
                        .anyRequest().permitAll()
                )
                .formLogin(login -> login
                        .loginPage("/login") // Custom login page
                        .loginProcessingUrl("/perform_login") // URL for form submission
                        .defaultSuccessUrl("/", true) // Redirect on successful login
                        .failureUrl("/login?error=true") // Redirect on failed login
                        .permitAll()
                )
                .rememberMe(remember -> remember
                        .key("uniqueAndSecret") // Secret key for hashing
                        .tokenValiditySeconds(86400) // Valid for 1 day
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("mihir_hirpara")
                .password("Admin@1234")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
