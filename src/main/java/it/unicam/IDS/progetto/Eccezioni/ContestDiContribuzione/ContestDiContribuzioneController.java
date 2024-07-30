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

    @ExceptionHandler(value = ContestOverTimeLimitEccezione.class)
    public ResponseEntity<Object> handleException(ContestOverTimeLimitEccezione e) {
        e.getMessage();
        return new ResponseEntity<>("Il tempo per inserire un contenuto è finito", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ContestAlreadyExistEccezione.class)
    public ResponseEntity<Object> handleException(ContestAlreadyExistEccezione e) {
        e.getMessage();
        return new ResponseEntity<>("Il contest di contribuzione esiste già", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ContestInvalidDataEccezione.class)
    public ResponseEntity<Object> handleException(ContestInvalidDataEccezione e) {
        e.getMessage();
        return new ResponseEntity<>("La data inserita non è valida", HttpStatus.NOT_FOUND);
    }
}
