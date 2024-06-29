package it.unicam.IDS.progetto.Eccezioni.Contenuti;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ContenutiEccezioniController {

    @ExceptionHandler(value = ContenutiNotFoundEccezione.class)
    public ResponseEntity<Object> handleException(ContenutiNotFoundEccezione e) {
        e.getMessage();
        return new ResponseEntity<>("Contenuto non trovato", HttpStatus.NOT_FOUND);
    }
}
