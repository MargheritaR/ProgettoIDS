package it.unicam.IDS.progetto.Eccezioni.Comune;

import it.unicam.IDS.progetto.Eccezioni.Contenuti.ContenutiNotFoundEccezione;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ComuneEccezioniController {
    @ExceptionHandler(value = ContenutiNotFoundEccezione.class)
    public ResponseEntity<Object> handleException(ContenutiNotFoundEccezione e) {
        e.getMessage();
        return new ResponseEntity<>("Contenuto non trovato", HttpStatus.NOT_FOUND);
    }
}
