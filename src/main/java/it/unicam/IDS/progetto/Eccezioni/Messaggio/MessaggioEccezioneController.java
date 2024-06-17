package it.unicam.IDS.progetto.Eccezioni.Messaggio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MessaggioEccezioneController {

    @ExceptionHandler(value = MessaggioNoContentEccezione.class)
    public ResponseEntity<Object> handleException(MessaggioNoContentEccezione e) {
        return new ResponseEntity<>("Messaggio no content", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MessaggioNotFoundEccezione.class)
    public ResponseEntity<Object> handleException(MessaggioNotFoundEccezione e) {
        return new ResponseEntity<>("Messaggio not found", HttpStatus.NOT_FOUND);
    }
}
