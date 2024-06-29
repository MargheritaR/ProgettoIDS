package it.unicam.IDS.progetto.Eccezioni.Utente;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class UtenteEccezioneController {

    @ExceptionHandler(value = UtenteNotFoundEccezione.class)
    public ResponseEntity<Object> handleException(UtenteNotFoundEccezione e) {
        return new ResponseEntity<>("Utente non trovato", HttpStatus.NOT_FOUND);
    }
}
