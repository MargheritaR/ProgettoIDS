package it.unicam.IDS.progetto.Service;
/*
import it.unicam.IDS.progetto.Eccezioni.Preferiti.PreferitiNotFoundEccezione;
import it.unicam.IDS.progetto.Entita.Itinerario;
import it.unicam.IDS.progetto.Repository.ItinerarioListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/preferiti")
public class PreferitiServiceController {

    private PreferitiListRepository preferitiRepository;
    private ItinerarioListRepository itinerarioRepository;

    @Autowired
    public PreferitiServiceController(PreferitiListRepository preferitiRepository,
                                      ItinerarioListRepository itinerarioRepository) {
        this.preferitiRepository = preferitiRepository;
        this.itinerarioRepository = itinerarioRepository;
    }

    @RequestMapping(value = "/getPreferiti")
    public ResponseEntity<Object> getPreferiti() {
        return new ResponseEntity(preferitiRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getPreferiti/{idPreferiti}")
    public ResponseEntity<Object> getPreferiti(@PathVariable("idPreferiti") int idPreferiti) {
        if (preferitiRepository.existsById(String.valueOf(idPreferiti))) {
            return new ResponseEntity(preferitiRepository.findById(String.valueOf(idPreferiti)).get(), HttpStatus.OK);
        } else throw new PreferitiNotFoundEccezione();
    }

    @PostMapping(value = "/newPreferiti/{nomeItinerario}")
    public ResponseEntity<Object> newPreferiti(@PathVariable("nomeItinerario") String nomeItinerario) {
        Itinerario it = itinerarioRepository.findByNomeItinerario(nomeItinerario);
        Preferiti preferiti = new Preferiti();
        List lista = preferiti.getPreferiti();
        lista.add(it);
        preferiti.setPreferiti(lista);
        preferitiRepository.save(preferiti);
        return new ResponseEntity("Preferiti creato", HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletePreferiti/{idPreferiti}")
    public ResponseEntity<Object> deletePreferiti(@PathVariable("idPreferiti") int idPreferiti) {
        if (preferitiRepository.existsById(String.valueOf(idPreferiti))) {
            preferitiRepository.deleteById(String.valueOf(idPreferiti));
            return new ResponseEntity("Preferiti eliminato", HttpStatus.OK);
        } else throw new PreferitiNotFoundEccezione();
    }


}
*/