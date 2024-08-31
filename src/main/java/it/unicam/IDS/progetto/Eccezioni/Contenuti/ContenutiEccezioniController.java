package it.unicam.IDS.progetto.Eccezioni.Contenuti;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ContenutiEccezioniController {

    @ExceptionHandler(value = ContenutiNotFoundEccezione.class)
    public ResponseEntity<Object> handleException(ContenutiNotFoundEccezione e) {
        return new ResponseEntity<>("Il contenuto non è stato trovato", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ContentAlreadyExistEccezione.class)
    public ResponseEntity<Object> handleException(ContentAlreadyExistEccezione e) {
        return new ResponseEntity<>("Il contenuto esiste già", HttpStatus.NOT_FOUND);
    }
}
