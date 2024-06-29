package it.unicam.IDS.progetto.Eccezioni.ContestDiContribuzione;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ContestDiContribuzioneController {

    @ExceptionHandler(value = ContestDiContribuzioneNotFoundEccezione.class)
    public ResponseEntity<Object> handleException(ContestDiContribuzioneNotFoundEccezione e) {
        e.getMessage();
        return new ResponseEntity<>("Contest di Contribuzione non trovato", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ContestDiContribuzioneOverTimeLimitEccezione.class)
    public ResponseEntity<Object> handleException(ContestDiContribuzioneOverTimeLimitEccezione e) {
        e.getMessage();
        return new ResponseEntity<>("Il tempo per inserire un contenuto è finito", HttpStatus.NOT_FOUND);
    }
}
