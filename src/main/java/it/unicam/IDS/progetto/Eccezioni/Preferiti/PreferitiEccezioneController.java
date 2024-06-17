package it.unicam.IDS.progetto.Eccezioni.Preferiti;

import it.unicam.IDS.progetto.Eccezioni.Itinerari.ItinerariNotFoundEccezione;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class PreferitiEccezioneController {

    @ExceptionHandler(value = PreferitiNotFoundEccezione.class)
    public ResponseEntity<Object> handleException(PreferitiNotFoundEccezione e) {
        e.getMessage();
        return new ResponseEntity<>("Preferiti non trovato", HttpStatus.NOT_FOUND);
    }
}
