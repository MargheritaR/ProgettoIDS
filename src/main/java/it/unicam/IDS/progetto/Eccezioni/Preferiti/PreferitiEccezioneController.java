package it.unicam.IDS.progetto.Eccezioni.Preferiti;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class PreferitiEccezioneController {

    @ExceptionHandler(value = PreferitiNotFoundEccezione.class)
    public ResponseEntity<Object> handleException(PreferitiNotFoundEccezione e) {
        return new ResponseEntity<>("Preferiti non trovato", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = PreferitiAlreadyExist.class)
    public ResponseEntity<Object> handleException(PreferitiAlreadyExist e) {
        return new ResponseEntity<>("Preferiti esiste già", HttpStatus.NOT_FOUND);
    }
}
