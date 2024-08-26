package it.unicam.IDS.progetto.Eccezioni.ContestDiContribuzione;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ContestDiContribuzioneController {

    @ExceptionHandler(value = ContestDiContribuzioneNotFoundEccezione.class)
    public ResponseEntity<Object> handleException(ContestDiContribuzioneNotFoundEccezione e) {
        return new ResponseEntity<>("Contest di Contribuzione non trovato", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ContestOverTimeLimitEccezione.class)
    public ResponseEntity<Object> handleException(ContestOverTimeLimitEccezione e) {
        return new ResponseEntity<>("Il tempo per inserire un contenuto è finito", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ContestAlreadyExistEccezione.class)
    public ResponseEntity<Object> handleException(ContestAlreadyExistEccezione e) {
        return new ResponseEntity<>("Il contest di contribuzione esiste già", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ContestInvalidDataEccezione.class)
    public ResponseEntity<Object> handleException(ContestInvalidDataEccezione e) {
        return new ResponseEntity<>("La data inserita non è valida", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ContestParamInvalidEccezione.class)
    public ResponseEntity<Object> handleException(ContestParamInvalidEccezione e) {
        return new ResponseEntity<>("Parametro non valido, inserire l'obiettivo " +
                "o la tematica del contest che si vuole modificare",HttpStatus.NOT_FOUND);
    }
}
