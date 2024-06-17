package it.unicam.IDS.progetto.Eccezioni.PDI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PuntoInteresseEccezioneController {

    @ExceptionHandler(value = PuntoInteresseNotFoundEccezione.class)
    public ResponseEntity<Object> hanldeException(PuntoInteresseNotFoundEccezione e) {
        return new ResponseEntity<>("Punto Interesse non trovato", HttpStatus.NOT_FOUND);
    }
}
