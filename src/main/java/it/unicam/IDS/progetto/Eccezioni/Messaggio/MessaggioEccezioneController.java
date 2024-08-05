package it.unicam.IDS.progetto.Eccezioni.Messaggio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MessaggioEccezioneController {

    @ExceptionHandler(value = MessaggioNotFoundEccezione.class)
    public ResponseEntity<Object> handleException(MessaggioNotFoundEccezione e) {
        return new ResponseEntity<>("Il messaggio non è stato trovato", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MessaggiEmptyEccezione.class)
    public ResponseEntity<Object> handleException(MessaggiEmptyEccezione e) {
        return new ResponseEntity<>("La lista messaggi è vuota", HttpStatus.NOT_FOUND);
    }
}
