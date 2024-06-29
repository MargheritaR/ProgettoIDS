package it.unicam.IDS.progetto.Service;

import it.unicam.IDS.progetto.Dtos.UtenteDtos;
import it.unicam.IDS.progetto.Eccezioni.Utente.UtenteNotFoundEccezione;
import it.unicam.IDS.progetto.Entita.Ruoli;
import it.unicam.IDS.progetto.Entita.Utente;
import it.unicam.IDS.progetto.Repository.UtenteListRepository;
import it.unicam.IDS.progetto.Security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/utente")
public class UtentiServiceController {

    private UtenteListRepository utenteRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Autowired
    public UtentiServiceController(UtenteListRepository utenteRepository, AuthenticationManager authenticationManager,
                                   PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.utenteRepository = utenteRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        Utente admin = new Utente("admin@gmail.com", passwordEncoder.encode("admin1@"));
        admin.setRuolo(Ruoli.ROLE_GESTOREPIATTAFORMA);
        Utente utente1 = new Utente("margherita@gmail.com", passwordEncoder.encode("Valida1@"));
        utente1.setRuolo(Ruoli.ROLE_CURATORI);
        Utente utente2 = new Utente("daniele@gmail.com", passwordEncoder.encode("Foggia2@"));
        utente2.setRuolo(Ruoli.ROLE_CONTRIBUTORIAUTORIZZATI);
        Utente utente3 = new Utente("bruno@gmail.com", passwordEncoder.encode("Mars1@"));
        utente3.setRuolo(Ruoli.ROLE_ANIMATORI);
        Utente utente4 = new Utente("giulio@gmail.com", passwordEncoder.encode("Ragazzotti1@"));
        utente4.setRuolo(Ruoli.ROLE_CONTRIBUTORI);
        Utente utente5 = new Utente("rosa@gmail.com", passwordEncoder.encode("Dotttore1@"));
        utente5.setRuolo(Ruoli.ROLE_TURISTAUTORIZZATI);
        utenteRepository.save(admin);
        utenteRepository.save(utente1);
        utenteRepository.save(utente2);
        utenteRepository.save(utente3);
        utenteRepository.save(utente4);
        utenteRepository.save(utente5);
    }

    @RequestMapping(value = "/getUtenti")
    public ResponseEntity<Object> getUtenti() {
        return new ResponseEntity(utenteRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getUtenti/{id}")
    public ResponseEntity<Object> getUtenti(@PathVariable("id") int id) {
        if (utenteRepository.existsById(String.valueOf(id))) {
            return new ResponseEntity(utenteRepository.findById(String.valueOf(id)).get(), HttpStatus.OK);
        } else throw new UtenteNotFoundEccezione();
    }

    @PostMapping(value = "/newUtente")
    public ResponseEntity<Object> newUtente(@RequestBody UtenteDtos u) {
        if(!utenteRepository.existsByUsername(u.getUsername())) {
            Utente utente = new Utente(u.getUsername(),passwordEncoder.encode(u.getPassword()));
            utenteRepository.save(utente);
            String token = jwtService.generateToken(utente);
            return new ResponseEntity("Utente creato, il token è: " + '\n' + token, HttpStatus.OK);
        } else throw new UtenteNotFoundEccezione();
    }

    @PutMapping(value = "/assegnamentoRuoli/{id}/{newRuolo}")
    public ResponseEntity<Object> assegnamentoRuoli(@PathVariable("id") int id, @PathVariable("newRuolo") String newRuolo) {
        if (utenteRepository.existsByUsername(String.valueOf(id))) {
            Utente utente = utenteRepository.findById(id);
            switch (newRuolo) {
                case "CONTRIBUTORI":
                    utente.setRuolo(Ruoli.ROLE_CONTRIBUTORI);
                    break;
                case "CURATORI":
                    utente.setRuolo(Ruoli.ROLE_CURATORI);
                    break;
                case "ANIMATORI":
                    utente.setRuolo(Ruoli.ROLE_ANIMATORI);
                    break;
                case "CONTRIBUTORIAUTORIZZATI":
                    utente.setRuolo(Ruoli.ROLE_CONTRIBUTORIAUTORIZZATI);
                    break;
                case "GESTOREPIATTAFORMA":
                    utente.setRuolo(Ruoli.ROLE_GESTOREPIATTAFORMA);
                    break;
                case "TURISTAUTORIZZATI":
                    utente.setRuolo(Ruoli.ROLE_TURISTAUTORIZZATI);
                    break;
            }
            utenteRepository.save(utente);
            return new ResponseEntity("Nuovo ruolo utente assegnato", HttpStatus.OK);
        } else throw new UtenteNotFoundEccezione();
    }

    @RequestMapping(value = "/login")
    public ResponseEntity<Object> login(@RequestBody UtenteDtos u){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(u.getUsername(),u.getPassword()));
        Utente utente = utenteRepository.findByUsername(u.getUsername());
        if (utente == null)
            throw new UsernameNotFoundException("User not found with username");
        String token = jwtService.generateToken(utente);
        return new ResponseEntity<>("Utente loggato correttamente, token: " + token, HttpStatus.OK );
    }

    @RequestMapping(value = "/getMessaggi")
    public ResponseEntity<Object> getMessaggi() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utente utente = utenteRepository.findByUsername(authentication.getName());
        return new ResponseEntity(utente.getListaMessaggiNonLetti(), HttpStatus.OK);
    }

}
