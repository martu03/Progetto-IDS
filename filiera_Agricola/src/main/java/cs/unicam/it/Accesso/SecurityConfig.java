package cs.unicam.it.Accesso;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtRequestFilter jwtRequestFilter) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/", "/favicon.ico", "/marker", "/markerLogo.png", "/api/mappa/**", "/index.html").permitAll()
                        .requestMatchers("/api/utenti/**").hasAnyRole("ACQUIRENTE", "GESTORE", "CURATORE", "ANIMATORE", "PRODUTTORE", "TRASFORMATORE", "DISTRIBUTORE")
                        .requestMatchers("/api/gestore/**").hasRole("GESTORE")
                        .requestMatchers("/api/curatore/**").hasRole("CURATORE")
                        .requestMatchers("/api/animatori/**").hasRole("ANIMATORE")
                        .requestMatchers("/api/acquirenti/**").hasRole("ACQUIRENTE")
                        .requestMatchers("/api/aziende/crea-pacchetto").hasRole("DISTRIBUTORE")
                        .requestMatchers("/api/aziende/**").hasAnyRole("PRODUTTORE", "TRASFORMATORE", "DISTRIBUTORE")
                        .anyRequest().authenticated())
                        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }
}
