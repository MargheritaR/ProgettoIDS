package it.unicam.IDS.progetto.Service;

import it.unicam.IDS.progetto.Dtos.PuntoInteresseDtos;
import it.unicam.IDS.progetto.Eccezioni.PDI.PuntoInteresseNotFoundEccezione;
import it.unicam.IDS.progetto.Entita.PuntoInteresse;
import it.unicam.IDS.progetto.Repository.PDIListRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping(value = "/puntoInteresse")
public class PDIServiceController {

    private PDIListRepository puntiInteresseRepository;

    @Autowired
    public PDIServiceController(PDIListRepository puntiInteresseRepository) {
        this.puntiInteresseRepository = puntiInteresseRepository;
        PuntoInteresse pI1 = new PuntoInteresse("Sotto Corte", 43.1468, 13.063);
        PuntoInteresse pI2 = new PuntoInteresse("Chiesa di Santa Mar", 43.1402, 13.0740);
        PuntoInteresse pI3 = new PuntoInteresse("Polo di Informatica", 43.139, 13.068);
        PuntoInteresse pI4 = new PuntoInteresse("Chiesa di San Venanzio", 43.1420, 13.0768);
        puntiInteresseRepository.save(pI1);
        puntiInteresseRepository.save(pI2);
        puntiInteresseRepository.save(pI3);
        puntiInteresseRepository.save(pI4);
    }

    @RequestMapping(value = "/getPuntoInteresse")
    public ResponseEntity<Object> getPuntoInteresse() {
        return new ResponseEntity<>(puntiInteresseRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getPuntoInteresse/{nomePDI}")
    public ResponseEntity<Object> getPuntoInteresse(@PathVariable("nomePDI") String nomePDI) {
        if (puntiInteresseRepository.existsById(nomePDI))
            return new ResponseEntity<>(puntiInteresseRepository.findById(nomePDI),HttpStatus.OK);
        else throw new PuntoInteresseNotFoundEccezione();
    }

    @PostMapping(value = "/newPuntoInteresse")
    public ResponseEntity<Object> newPDI(@RequestBody PuntoInteresseDtos pdi) {
        if (!puntiInteresseRepository.existsById(pdi.getNomePDI())) {
            PuntoInteresse puntoInteresse = new PuntoInteresse(pdi.getNomePDI(),pdi.getCoordinate().getX(),
                    pdi.getCoordinate().getY());
            puntiInteresseRepository.save(puntoInteresse);
            return new ResponseEntity<>("Punto di Interesse creato ", HttpStatus.OK);
        } else throw new PuntoInteresseNotFoundEccezione();
    }

    @DeleteMapping(value = "/deletePuntoInteresse/{nomePDI}")
    public ResponseEntity<Object> deletePDI(@PathVariable("nomePDI") String nomePDI) {
        if (puntiInteresseRepository.existsById(nomePDI)) {
            puntiInteresseRepository.deleteById(nomePDI);
            return new ResponseEntity<>("Punto di Interesse è stato eliminato con successo", HttpStatus.OK);
        } else throw new PuntoInteresseNotFoundEccezione();
    }

    @PutMapping(value = "/updatePuntoInteresse/{nomePDI}")
    public ResponseEntity<Object> updatePDI(@PathVariable("nomeID") String nomePDI, @RequestBody PuntoInteresseDtos pdi) {
        if (puntiInteresseRepository.existsById(nomePDI)) {
            PuntoInteresse puntoInteresse = new PuntoInteresse(pdi.getNomePDI(),pdi.getCoordinate().getX(),
                    pdi.getCoordinate().getY());
            puntiInteresseRepository.save(puntoInteresse);
            return new ResponseEntity<>("Punto di Interesse è stato aggiornato con successo", HttpStatus.OK);
        } else throw new PuntoInteresseNotFoundEccezione();
    }

    // TODO Sistmare la collection di Contenuti
    @PostMapping(value = "/fileUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        File file1 = new File("/home/margherita/Desktop/"+file.getOriginalFilename());
        file1.createNewFile();
        FileOutputStream fileOut = new FileOutputStream(file1);
        fileOut.write(file.getBytes());
        fileOut.close();
        return new ResponseEntity<>("File uploaded", HttpStatus.OK);
    }
}
