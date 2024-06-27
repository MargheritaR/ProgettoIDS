package it.unicam.IDS.progetto.Service;

import it.unicam.IDS.progetto.Dtos.ComuneDtos;
import it.unicam.IDS.progetto.Eccezioni.Comune.ComuneNotFountEccezione;
import it.unicam.IDS.progetto.Entita.Comune;
import it.unicam.IDS.progetto.Repository.ComuneListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/comune")
public class ComuneServiceController {

    private ComuneListRepository comuneRepository;


    @Autowired
    public ComuneServiceController(ComuneListRepository comuneRepository) {
        this.comuneRepository = comuneRepository;
    }

    @RequestMapping(value = "/getComune")
    public ResponseEntity<Object> getComune() {
        return new ResponseEntity<>(comuneRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/addComune")
    public ResponseEntity<Object> addComune(@RequestBody ComuneDtos c) {
        if (!comuneRepository.existsById(c.getNomeComune())) {
            Comune comune1 = new Comune(c.getNomeComune(),c.getCoordinate().getX(),c.getCoordinate().getY());
            comuneRepository.save(comune1);
            return new ResponseEntity<>("Comune creato ", HttpStatus.OK);
        } else throw new ComuneNotFountEccezione();
    }

    @PutMapping(value = "/editComune/{nomeComune}")
    public ResponseEntity<Object> editComune(@RequestBody ComuneDtos c, @PathVariable String nomeComune){
        if (comuneRepository.existsById(nomeComune)) {
            Comune comune1 = new Comune(c.getNomeComune(),c.getCoordinate().getX(),c.getCoordinate().getY());
            comuneRepository.deleteById(nomeComune);
            comuneRepository.save(comune1);
            return new ResponseEntity<>("Comune è stato aggiornato con successo", HttpStatus.OK);
        } else throw new ComuneNotFountEccezione();
    }

    @DeleteMapping(value = "/deleteComune/{nomeComune}")
    public ResponseEntity<Object> deleteComune(@PathVariable String nomeComune){
        if (comuneRepository.existsById(nomeComune)) {
            comuneRepository.deleteById(nomeComune);
            return new ResponseEntity<>("Comune è stato eliminato con successo", HttpStatus.OK);
        } else throw new ComuneNotFountEccezione();
    }
}