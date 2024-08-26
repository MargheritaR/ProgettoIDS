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
                        .requestMatchers("/h2-console/**","/utente/registrazione", "/utente/login/{email}/{password}",
                                "/utente/getUtente","/comune/getComune").permitAll()
                        .requestMatchers("/comune/modificaComune/{param}/{elemNuovo}",
                                "/utente/assegnamentoRuoli/{nomeUtente}/{ruolo}")
                        .hasRole("GESTOREPIATTAFORMA")
                        .requestMatchers("/comune/aggiungiPreferitiItinerario/{nomeItinerario}",
                                "/comune/aggiungiPreferitiPDI/{nomePdi}","/comune/rimuoviPreferitiPDI/{nomePdi}",
                                "/comune/rimuoviPreferitiItinerari/{nomeItinerari}")
                        .hasAnyRole("TURISTAUTORIZZATI","GESTOREPIATTAFORMA")
                        .requestMatchers("/comune/creaContest","/comune/eliminaContest/{nomeContest},",
                                "/comune/modificaContest/{nomeContest}/{param}/{elemNuovo}",
                                "/comune/validaContenuti/{nomeContest}/{nomeContenuto}/{approv}",
                                "/comune/decidiContenuto/{nomeContest}/{nomeContenuto}")
                        .hasAnyRole("ANIMATORI","GESTOREPIATTAFORMA")
                        .requestMatchers("/comune/approvStatoPendingPDI/{pdiScelto}/{scelta}",
                                "/comune/approvStatoPendingItinerario/{itinerarioScelto}/{scelta}")
                        .hasAnyRole("CURATORI","GESTOREPIATTAFORMA")
                        .requestMatchers("/comune/rimuoviContenuti/{nomePDI}/{nomeContenuto}","/comune/elimaPDI/{nomePDI}",
                                "/comune/eliminaItinerario/{nomeItinerario}",
                                "/comune/rimuoviPdiItinerario/{nomePuntoInteresse}/{nomeItinerario}",
                                "/comune/rimuoviFotoItinerario/{idFoto}/{nomeItinerario}")
                        .hasAnyRole("CURATORI","CONTRIBUTORIAUTORIZZATI","GESTOREPIATTAFORMA")
                        .requestMatchers("/comune/inserimentoPDI","/comune/aggiungiContenuti/{nomePDI}",
                                "/comune/creaItinerario","/comune/aggiungiPdiItinerario/{nomePuntoInteresse}/{nomeItinerario}",
                                "/comune/aggiungiFotoItinerario/{nomeItinerario}")
                        .hasAnyRole("CONTRIBUTORI","CURATORI","CONTRIBUTORIAUTORIZZATI","GESTOREPIATTAFORMA")
                        .requestMatchers("/comune/proponiContest/{nomeContest}")
                        .hasAnyRole("CONTRIBUTORI","GESTOREPIATTAFORMA")
                        .requestMatchers("/comune/inviaMessaggi","/utente/leggiMessaggi/{titoloMessaggio}")
                        .hasAnyRole("CONTRIBUTORI","CURATORI","CONTRIBUTORIAUTORIZZATI","GESTOREPIATTAFORMA",
                                "ANIMATORI","TURISTAUTORIZZATI")
                        .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsService)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    /*
    .authorizeHttpRequests( request -> request
                        .requestMatchers("/h2-console/**","/utente/newUtente", "/utente/login","/utente/getUtenti",
                                "/utente/getUtenti/{id}","/comune/getComune","/contenuti/getContenuti",
                                "/contestDiContribuzione/getContest",
                                "/contestDiContribuzione/getVincitore/{nomeContest}",
                                "/itinerari/getItinerari","/itinerari/getItinerari/{nomeItinerario}",
                                "/puntoInteresse/getPuntoInteresse","/puntoInteresse/getPuntoInteresseById/{nomePDI}").permitAll()
                        .requestMatchers("/comune/addComune","comune/editComune/{nomeComune}",
                                "/comune/deleteComune/{nomeComune}",
                                "utente/assegnamentoRuoli/{id}/{newRuolo}").hasRole("GESTOREPIATTAFORMA")

                        .requestMatchers("/contestDiContribuzione/addContest","/contestDiContribuzione/inviaMessaggio",
                                "/contestDiContribuzione/deleteContest/{nomeContest}",
                                "/contestDiContribuzione/editContest/{nomeContest}",
                                "/contestDiContribuzione/validaContest/{nomeContest}/{idContenuto}",
                                "/contestDiContribuzione/decidiVincitore/{nomeContest}/{idContenuto}")
                        .hasAnyRole("ANIMATORI","GESTOREPIATTAFORMA")
                        .requestMatchers("/puntoInteresse/getStatoPending",
                                "/puntoInteresse/approvazioneStatoPending/{nomePDI}",
                                "/itinerari/getStatoPending", "/itinerari/approvStatoPending/{nomeItinerario}/{approv}")
                        .hasAnyRole("CURATORI","GESTOREPIATTAFORMA")
                        .requestMatchers("/puntoInteresse/updatePuntoInteresse/{nomePDI}",
                                "/puntoInteresse/deletePuntoInteresse/{nomePDI}","/itinerari/deleteItinerario/{nomeItinerario}",
                                "/contenuti/editContenuti/{nomePdi}/{idContenuti}",
                                "/contenuti/deleteContenuti/{nomePdi}/{idContenuto}")
                        .hasAnyRole("CURATORI","CONTRIBUTORIAUTORIZZATI","GESTOREPIATTAFORMA")
                        .requestMatchers("contenuti/addContenuti/{nomePdi}","/itinerari/newItinerario/{nomeItinerario}",
                                "/itinerari/aggiungiPdi/{nomeItinerario}/{nomePDI}","puntoInteresse/newPuntoInteresse",
                                "/puntoInteresse/fileUpload/{nomePDI}")
                        .hasAnyRole("CONTRIBUTORI","CURATORI","CONTRIBUTORIAUTORIZZATI","GESTOREPIATTAFORMA")

                        .requestMatchers("/itinerari/aggiungiFoto/{nomeItinerario}")
                        .hasAnyRole("CONTRIBUTORI","GESTOREPIATTAFORMA","TURISTAUTORIZZATI")
                        .anyRequest().authenticated()
                )
    */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}