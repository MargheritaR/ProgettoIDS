package it.unicam.IDS.progetto.Service;

import it.unicam.IDS.progetto.Eccezioni.Preferiti.PreferitiNotFoundEccezione;
import it.unicam.IDS.progetto.Eccezioni.Utente.UtenteNotFoundEccezione;
import it.unicam.IDS.progetto.Entita.Utente;
import it.unicam.IDS.progetto.Repository.UtenteListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/utente")
public class UtentiServiceController {

    private UtenteListRepository utenteRepository;

    @Autowired
    public UtentiServiceController(UtenteListRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
        Utente utente1 = new Utente("margherita@gmail.com", "Valida1@",
                "Margherita", "Ramazzotti");
        Utente utente2 = new Utente("daniele@gmail.com", "Foggia1@",
                "Daniele", "Rossi");
        Utente utente3 = new Utente("domenico@gmail.com", "DaiDai1@",
                "Domenico", "di Silvio");
        Utente utente4 = new Utente("nicolo@gmail.com", "Perugia1@",
                "Nicolò", "Gregori");
        Utente utente5 = new Utente("francesca@gmail.com", "Francè1@",
                "Francesca", "Morici");
        Utente utente6 = new Utente("ilaria@gmail.com", "fellinLove1@",
                "Ilaria", "Gregori");
        utenteRepository.save(utente1);
        utenteRepository.save(utente2);
        utenteRepository.save(utente3);
        utenteRepository.save(utente4);
        utenteRepository.save(utente5);
        utenteRepository.save(utente6);
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
    public ResponseEntity<Object> newUtente(@RequestBody Utente utente) {
        if(!utenteRepository.existsById(String.valueOf(utente.getId()))) {
            utenteRepository.save(utente);
            return new ResponseEntity("Utente creato", HttpStatus.OK);
        } else throw new PreferitiNotFoundEccezione();
    }

    @PutMapping(value = "/assegnamentoRuoli/{id}/{newRuolo}")
    public ResponseEntity<Object> assegnamentoRuoli(@PathVariable int id, @PathVariable int newRuolo) {
        if (utenteRepository.existsById(String.valueOf(id))) {
            Utente utente = utenteRepository.findById(String.valueOf(id)).get();
            utente.setRuolo(newRuolo);
            utenteRepository.save(utente);
            return new ResponseEntity("Nuovo ruolo utente assegnato", HttpStatus.OK);
        } else throw new UtenteNotFoundEccezione();
    }
}
