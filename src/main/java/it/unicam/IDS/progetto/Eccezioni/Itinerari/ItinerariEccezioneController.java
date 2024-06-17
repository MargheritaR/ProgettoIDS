package it.unicam.IDS.progetto.Eccezioni.Itinerari;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ItinerariEccezioneController {

    @ExceptionHandler(value = ItinerariNotFoundEccezione.class)
    public ResponseEntity<Object> handleException(ItinerariNotFoundEccezione e) {
        e.getMessage();
        return new ResponseEntity<>("Itinerario non trovato", HttpStatus.NOT_FOUND);
    }
}
