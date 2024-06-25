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
        admin.setRuolo(Ruoli.ROLE_CONTRIBUTORI);
        Utente utente1 = new Utente("margherita@gmail.com", passwordEncoder.encode("Valida1@"));
        utente1.setRuolo(Ruoli.ROLE_CURATORI);
        Utente utente2 = new Utente("daniele@gmail.com", passwordEncoder.encode("Foggia2@"));
        utente2.setRuolo(Ruoli.ROLE_CONTRIBUTORIAUTORIZZATI);
        utenteRepository.save(admin);
        utenteRepository.save(utente1);
        utenteRepository.save(utente2);
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

    /*
    @PutMapping(value = "/assegnamentoRuoli/{id}/{newRuolo}")
    public ResponseEntity<Object> assegnamentoRuoli(@PathVariable int id, @PathVariable String newRuolo) {
        if (utenteRepository.existsById(String.valueOf(id)) && ruoliRepository.existsByNome(newRuolo)) {
            Utente utente = utenteRepository.findById(String.valueOf(id)).get();
            Ruoli ruoli = ruoliRepository.findByNome(newRuolo);
            utente.setRuolo(ruoli);
            utenteRepository.save(utente);
            return new ResponseEntity("Nuovo ruolo utente assegnato", HttpStatus.OK);
        } else throw new UtenteNotFoundEccezione();
    }
     */

    @RequestMapping(value = "/login")
    public ResponseEntity<Object> login(@RequestBody UtenteDtos u){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(u.getUsername(),u.getPassword()
                )
        );
        Utente utente = utenteRepository.findByUsername(u.getUsername());
        if (utente == null) {
            throw new UsernameNotFoundException("User not found with username");
        }
        String token = jwtService.generateToken(utente);

        return new ResponseEntity<>("Utente loggato correttamente, token: " + token, HttpStatus.OK );
    }

}
