package it.unicam.IDS.progetto.Service;

import it.unicam.IDS.progetto.Eccezioni.Messaggio.MessaggioNoContentEccezione;
import it.unicam.IDS.progetto.Eccezioni.Messaggio.MessaggioNotFoundEccezione;
import it.unicam.IDS.progetto.Entita.Messaggio;
import it.unicam.IDS.progetto.Repository.MessaggioListRepository;
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


/*
public class MessaggioServiceController {
    //TODO sistemare le eccezioni

    private MessaggioListRepository messaggioRepository;

    @Autowired
    public MessaggioServiceController(MessaggioListRepository messaggioRepository) {
        this.messaggioRepository = messaggioRepository;
        Messaggio messaggio = new Messaggio("01","Daniele","Salvatore","Convalida",
                "Complimenti!!! Hai vinto il contest!!");
        messaggioRepository.save(messaggio);
    }

    @RequestMapping(value = "/getMessaggi")
    public ResponseEntity<Object> getMessaggio() {
        return new ResponseEntity<>(messaggioRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value="/getMessaggio/{id}")
    public ResponseEntity<Object> getMessaggioById(@PathVariable("id") String id) {
        return new ResponseEntity<>(messaggioRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value="/newMessaggio")
    public ResponseEntity<Object> newMessaggio(@RequestBody Messaggio messaggio) {
        if (messaggio.getTitolo().isEmpty()) {
            throw new MessaggioNoContentEccezione();
        }
        if (!messaggioRepository.existsById(messaggio.getId())) {
            messaggioRepository.save(messaggio);
            return new ResponseEntity<>("Product Created", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product Already Exists", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value="/deleteMessaggio/{id}")
    public ResponseEntity<Object> deleteMessaggio(@PathVariable("id") String id) {
        if (messaggioRepository.existsById(id)) {
            messaggioRepository.deleteById(id);
            return new ResponseEntity<>("Message succefully deleted", HttpStatus.OK);
        } else
            return new ResponseEntity<>("Message not found", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value="/updateMessaggio")
    public ResponseEntity<Object> updateMessaggio(@PathParam("id") String id, @RequestBody Messaggio messaggio) {
        if (messaggioRepository.existsById(id)) {
            messaggioRepository.save(messaggio);
            return new ResponseEntity<>("Product successfully updated", HttpStatus.OK);
        } else throw new MessaggioNotFoundEccezione();
    }

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
*/