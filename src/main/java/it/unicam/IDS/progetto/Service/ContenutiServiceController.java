package it.unicam.IDS.progetto.Service;

import it.unicam.IDS.progetto.Eccezioni.Contenuti.ContenutiNotFoundEccezione;
import it.unicam.IDS.progetto.Eccezioni.PDI.PuntoInteresseNotFoundEccezione;
import it.unicam.IDS.progetto.Entita.Contenuti;
import it.unicam.IDS.progetto.Entita.PuntoInteresse;
import it.unicam.IDS.progetto.Repository.ContenutiListRepository;
import it.unicam.IDS.progetto.Repository.PDIListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/contenuti")
public class ContenutiServiceController {

    private ContenutiListRepository contenutiRepository;

    private PDIListRepository pdiRepository;

    @Autowired
    public ContenutiServiceController(ContenutiListRepository contenutiRepository, PDIListRepository pdiRepository) {
        this.contenutiRepository = contenutiRepository;
        this.pdiRepository = pdiRepository;

    }

    @RequestMapping(value = "/getContenuti")
    public ResponseEntity<Object> getContenuti() {
        return new ResponseEntity<>(contenutiRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/addContenuti/{nomePdi}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> addContenuti(@RequestParam("file") MultipartFile file, @PathVariable String nomePdi) throws IOException {
        if (pdiRepository.existsById(nomePdi)) {
            PuntoInteresse appoggio = pdiRepository.findById(nomePdi).get();
            List listaContenuti = appoggio.getListaContenuti();
            if (!appoggio.getListaContenuti().contains(file)) {
                File file1 = new File("/home/daniele-rossi/Scrivania/provaFile" + file.getOriginalFilename());
                file1.createNewFile();
                FileOutputStream fileOut = new FileOutputStream(file1);
                fileOut.write(file.getBytes());
                fileOut.close();
                Contenuti contenuti = new Contenuti(file1);
                listaContenuti.add(contenuti);
            } else new ResponseEntity<>("Il contenuto è già stato inserito", HttpStatus.OK);
            appoggio.setListaContenuti(listaContenuti);
            pdiRepository.save(appoggio);
            return new ResponseEntity<>("Il contenuto è stato aggiornato", HttpStatus.OK);
        } else throw new ContenutiNotFoundEccezione();
    }

    @DeleteMapping(value = "/deleteContenuti/{nomePdi}/{idContenuto}")
    public ResponseEntity<Object> deleteContenuti(@PathVariable String nomePdi, @PathVariable int idContenuto) {
        if (pdiRepository.existsById(nomePdi)) {
            PuntoInteresse appoggio = pdiRepository.findById(nomePdi).get();
            Contenuti appoggioContenuti = contenutiRepository.findById(String.valueOf(idContenuto)).get();
            List listaContenuti = appoggio.getListaContenuti();
            if (listaContenuti.contains(appoggioContenuti)) {
                listaContenuti.remove(appoggioContenuti);
                contenutiRepository.deleteById(String.valueOf(idContenuto));
                appoggio.setListaContenuti(listaContenuti);
                pdiRepository.save(appoggio);
                return new ResponseEntity<>("Il contenuto è stato eliminato", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Il contenuto non esiste o è già stato eliminato", HttpStatus.NOT_FOUND);
            }
        } else {
            throw new ContenutiNotFoundEccezione();
        }
    }

    @PutMapping(value = "/editContenuti/{nomePdi}/{idContenuti}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> editContenuti(@PathVariable String nomePdi, @PathVariable int idContenuti,
                                                @RequestParam("file") MultipartFile file) throws IOException {
        if (pdiRepository.existsById(nomePdi)) {
            PuntoInteresse appoggio = pdiRepository.findById(nomePdi).get();
            Contenuti appoggioContenuti = contenutiRepository.findById(String.valueOf(idContenuti)).get();
            List listaContenuti = appoggio.getListaContenuti();
            if (listaContenuti.contains(appoggioContenuti)) {
                listaContenuti.remove(appoggioContenuti);
                contenutiRepository.deleteById(String.valueOf(idContenuti));
                File file1 = new File("/home/daniele-rossi/Scrivania/provaFile" + file.getOriginalFilename());
                file1.createNewFile();
                FileOutputStream fileOut = new FileOutputStream(file1);
                fileOut.write(file.getBytes());
                fileOut.close();
                Contenuti contenuti = new Contenuti(file1);
                listaContenuti.add(contenuti);
                appoggio.setListaContenuti(listaContenuti);
                pdiRepository.save(appoggio);
            }else throw new ContenutiNotFoundEccezione();
            return new ResponseEntity<>("Il contenuto è stato modificato", HttpStatus.OK);
        }else {
            throw new ContenutiNotFoundEccezione();
        }
    }
}
