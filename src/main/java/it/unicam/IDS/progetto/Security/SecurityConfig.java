package it.unicam.IDS.progetto.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(UserDetailsService userDetailsService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //noinspection removal
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions().disable())
                .authorizeHttpRequests( request -> request
                        .requestMatchers("/h2-console/**","/utente/newUtente", "/utente/login",
                                "/contenuti/getContenuti","/contestDiContribuzione/getContest",
                                "/contestDiContribuzione/getVincitore/","/itinerari/getItinerari/",
                                "/itinerari/getItinerari","/puntoInteresse/getPuntoInteresseById/{nomePDI}",
                                "/puntoInteresse/getPuntoInteresse","/puntoInteresse/getStatoPending","contestDiContribuzione/inviaMessaggio",
                                "/utente/getMessaggi").permitAll()
                        /*
                        .requestMatchers("/utente/**","/contenuti/**","comune/**",
                                "contestDiContribuzione/**","itinerari/**","/puntoInteresse/**",
                                "preferiti/**").hasRole("GESTOREPIATTAFORMA")
                        .requestMatchers("/contenuti/**","/itinerari/**","puntoInteresse/**").hasRole("CURATORI")
                        .requestMatchers("/contenuti/**","/itinerari/**",
                                "/puntoInteresse/**").hasRole("CONTRIBUTORIAUTORIZZATI")
                         */
                        .requestMatchers("/contenuti/addContenuti/", "/contestDiContribuzione/proponiContest/",
                                "/itinerari/newItinerario","/itinerari/AggiungiPdi/","/itinerari/AggiungiFoto/",
                                "/puntoInteresse/newPuntoInteresse","/puntoInteresse/updatePuntoInteresse/{nomePDI}",
                                "/puntoInteresse/fileUpload","/puntoInteresse/approvazioneStatoPending/{nomePDI}").hasRole("CONTRIBUTORI")
                        /*
                        .requestMatchers("/contestDiContribuzione/**").hasRole("ANIMATORI")
                        .requestMatchers("/preferiti/**").hasRole("TURISTAUTORIZZATI")
                         */
                        .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsService)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
