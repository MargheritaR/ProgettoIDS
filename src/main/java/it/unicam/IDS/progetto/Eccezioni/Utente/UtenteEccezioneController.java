package it.unicam.IDS.progetto.Eccezioni.Utente;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UtenteEccezioneController {

    @ExceptionHandler(value = UtenteNotFoundEccezione.class)
    public ResponseEntity<Object> handleException(UtenteNotFoundEccezione e) {
        return new ResponseEntity<>("l'utente non è stato trovato", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UtenteAlreadyExistsEccezioni.class)
    public ResponseEntity<Object> handleException(UtenteAlreadyExistsEccezioni e) {
        return new ResponseEntity<>("L'utente esiste già", HttpStatus.NOT_FOUND);
    }
}
