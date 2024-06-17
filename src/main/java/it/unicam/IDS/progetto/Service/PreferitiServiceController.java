package it.unicam.IDS.progetto.Service;

import it.unicam.IDS.progetto.Eccezioni.Preferiti.PreferitiNotFoundEccezione;
import it.unicam.IDS.progetto.Entita.Preferiti;
import it.unicam.IDS.progetto.Repository.PreferitiListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/preferiti")
public class PreferitiServiceController {

    private PreferitiListRepository preferitiRepository;

    @Autowired
    public PreferitiServiceController(PreferitiListRepository preferitiRepository) {
        this.preferitiRepository = preferitiRepository;
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

    @PostMapping(value = "/newPreferiti")
    public ResponseEntity<Object> newPreferiti(@RequestBody Preferiti preferiti) {
        if (!preferitiRepository.existsById(String.valueOf(preferiti.getIdPreferiti()))){
            preferitiRepository.save(preferiti);
            return new ResponseEntity("Preferiti creato", HttpStatus.OK);
        } else throw new PreferitiNotFoundEccezione();
    }

    @DeleteMapping(value = "/deletePreferiti/{idPreferiti}")
    public ResponseEntity<Object> deletePreferiti(@PathVariable("idPreferiti") int idPreferiti) {
        if (preferitiRepository.existsById(String.valueOf(idPreferiti))) {
            preferitiRepository.deleteById(String.valueOf(idPreferiti));
            return new ResponseEntity("Preferiti eliminato", HttpStatus.OK);
        } else throw new PreferitiNotFoundEccezione();
    }


}
