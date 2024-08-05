package it.unicam.IDS.progetto.Service;
/*
import it.unicam.IDS.progetto.Eccezioni.Comune.ComuneNotFountEccezione;
import it.unicam.IDS.progetto.Repository.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/comune")
public class ComuneServiceController {

    private ComuneRepository comuneRepository;

    @Autowired
    public ComuneServiceController(ComuneRepository comuneRepository) {
        this.comuneRepository = comuneRepository;
    }

    @RequestMapping(value = "/getComune")
    public ResponseEntity<Object> getComune() {
        return new ResponseEntity<>(comuneRepository.findAll(), HttpStatus.OK);
    }
/*
    @PostMapping(value = "/addComune")
    public ResponseEntity<Object> addComune(@RequestBody ComuneDtos c) {
        if (!comuneRepository.existsById(c.getNomeComune())) {
            Comune comune = new Comune(c.getNomeComune(),c.getAsseX(),c.getAsseY());
            comuneRepository.save(comune);
            return new ResponseEntity<>("Comune creato ", HttpStatus.OK);
        } else throw new ComuneNotFountEccezione();
    }
 */
/*
    @PutMapping(value = "/editComune/{nomeComune}")
    public ResponseEntity<Object> editComune(@RequestBody ComuneDtos c, @PathVariable("nomeComune") String nomeComune){
        if (comuneRepository.existsById(nomeComune)) {
            Comune comune = new Comune(c.getNomeComune(),c.getAsseX(),c.getAsseY());
            comuneRepository.deleteById(nomeComune);
            comuneRepository.save(comune);
            return new ResponseEntity<>("Comune è stato aggiornato con successo", HttpStatus.OK);
        } else throw new ComuneNotFountEccezione();
    }

    @DeleteMapping(value = "/deleteComune/{nomeComune}")
    public ResponseEntity<Object> deleteComune(@PathVariable("nomeComune") String nomeComune){
        if (comuneRepository.existsById(nomeComune)) {
            comuneRepository.deleteById(nomeComune);
            return new ResponseEntity<>("Comune è stato eliminato con successo", HttpStatus.OK);
        } else throw new ComuneNotFountEccezione();
    }
}
*/