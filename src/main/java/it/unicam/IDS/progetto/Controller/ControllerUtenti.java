package it.unicam.IDS.progetto.Controller;

import it.unicam.IDS.progetto.Dtos.MessaggioDtos;
import it.unicam.IDS.progetto.Dtos.UtenteDtos;
import it.unicam.IDS.progetto.Eccezioni.Utente.UtenteAlreadyExistsEccezioni;
import it.unicam.IDS.progetto.Entita.Ruoli;
import it.unicam.IDS.progetto.Entita.Utente;
import it.unicam.IDS.progetto.Repository.UtenteRepository;
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
public class ControllerUtenti {

    private UtenteRepository utenteRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Autowired
    public ControllerUtenti(UtenteRepository utenteRepository,AuthenticationManager authenticationManager,
                            PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.utenteRepository = utenteRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        Utente admin = new Utente("admin@gmail.com", passwordEncoder.encode("admin1@"),
                "Luca","Rimaldo");
        admin.setRuolo(Ruoli.ROLE_GESTOREPIATTAFORMA);
        Utente utente1 = new Utente("margherita@gmail.com",passwordEncoder.encode("Valida1"),
                "Margherita","Ramazzotti");
        utente1.setRuolo(Ruoli.ROLE_CURATORI);
        Utente utente2 = new Utente("daniele@gmail.com",passwordEncoder.encode("Valida2"),
                "Daniele","Rossi");
        utente2.setRuolo(Ruoli.ROLE_CONTRIBUTORIAUTORIZZATI);
        Utente utente3 = new Utente("bruno@gmail.com", passwordEncoder.encode("Mars1@"),
                "Bruno","Russo");
        utente3.setRuolo(Ruoli.ROLE_ANIMATORI);
        Utente utente4 = new Utente("giulio@gmail.com", passwordEncoder.encode("Ragazzotti1@"),
                "Giulio","Ragazzotti");
        utente4.setRuolo(Ruoli.ROLE_CONTRIBUTORI);
        Utente utente5 = new Utente("rosa@gmail.com", passwordEncoder.encode("Dotttore1@"),
                "Rosa","Moreo");
        utente5.setRuolo(Ruoli.ROLE_TURISTAUTORIZZATI);
        utenteRepository.save(admin);
        utenteRepository.save(utente1);
        utenteRepository.save(utente2);
        utenteRepository.save(utente3);
        utenteRepository.save(utente4);
        utenteRepository.save(utente5);
    }

    @PutMapping(value = "/assegnamentoRuoli/{nomeUtente}/{ruolo}")
    public ResponseEntity<Object> assegnamentoRuoli(@PathVariable("nomeUtente") String nomeUtente,
                                                    @PathVariable("ruolo") String ruolo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utente utente = utenteRepository.findByEmail(authentication.getName());
        Utente ut2 = utenteRepository.findByNome(nomeUtente);
        utente.assegnamentoRuoli(ruolo,ut2);
        utenteRepository.save(utente);
        return new ResponseEntity<>("La modifica del ruolo dell'utente è stata effettuata", HttpStatus.OK);
    }

    @PostMapping("/leggiMessaggi/{titoloMessaggio}")
    public ResponseEntity<Object> leggiMessaggi(@PathVariable("titoloMessaggio") String titoloMessaggio){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utente utente = utenteRepository.findByEmail(authentication.getName());
        MessaggioDtos messsaggio = utente.leggiMessaggi(titoloMessaggio, utente);
        utenteRepository.save(utente);
        return new ResponseEntity<>(messsaggio,HttpStatus.OK);
    }

    @PostMapping("/registrazione")
    public ResponseEntity<Object> registrazione(@RequestBody UtenteDtos u){
        Utente utente = new Utente(u.getUsername(), passwordEncoder.encode(u.getPassword()), u.getNome(), u.getCognome());
        String token = jwtService.generateToken(utente);
        if (utenteRepository.findByEmail(utente.getUsername()) != null)
            throw new UtenteAlreadyExistsEccezioni();
        utenteRepository.save(utente);
        return new ResponseEntity<>("La registrazione è avvenuta con successo, ecco il token: "+ token, HttpStatus.OK);
    }

    @RequestMapping(value = "/login/{email}/{password}")
    public ResponseEntity<Object> login(@PathVariable("email") String email,@PathVariable("password") String password){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
        Utente utente = utenteRepository.findByEmail(email);
        if (utente == null)
            throw new UsernameNotFoundException("User not found with username");
        String token = jwtService.generateToken(utente);
        return new ResponseEntity<>("Utente loggato correttamente, token: " + token, HttpStatus.OK );
    }

    @RequestMapping(value = "/getUtente")
    public ResponseEntity<Object> getUtente() {
        return new ResponseEntity<>(utenteRepository.findAll(), HttpStatus.OK);
    }
}
