package it.unicam.IDS.progetto.Service;

import it.unicam.IDS.progetto.Eccezioni.Contenuti.ContenutiNotFoundEccezione;
import it.unicam.IDS.progetto.Entita.*;
import it.unicam.IDS.progetto.Repository.ContenutiListRepository;
import it.unicam.IDS.progetto.Repository.PDIListRepository;
import it.unicam.IDS.progetto.Repository.StatoPendingListPuntoInteresseRepository;
import it.unicam.IDS.progetto.Repository.UtenteListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private UtenteListRepository utenteRepository;

    private StatoPendingListPuntoInteresseRepository statoPendingPuntoInteresseRepository;

    @Autowired
    public ContenutiServiceController(ContenutiListRepository contenutiRepository, PDIListRepository pdiRepository,
                                      UtenteListRepository utenteRepository,
                                      StatoPendingListPuntoInteresseRepository statoPendingPuntoInteresseRepository) {
        this.contenutiRepository = contenutiRepository;
        this.pdiRepository = pdiRepository;
        this.utenteRepository = utenteRepository;
        this.statoPendingPuntoInteresseRepository = statoPendingPuntoInteresseRepository;
    }

    @RequestMapping(value = "/getContenuti")
    public ResponseEntity<Object> getContenuti() {
        return new ResponseEntity<>(contenutiRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/addContenuti/{nomePdi}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> addContenuti(@RequestParam("file") MultipartFile file, @PathVariable String nomePdi) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utente utente = utenteRepository.findByUsername(authentication.getName());
        String ruoloUtente = String.valueOf(utente.getRuolo());
        if (pdiRepository.existsById(nomePdi)) {
            PuntoInteresse appoggio = pdiRepository.findByNomePDI(nomePdi);
            List listaContenuti = appoggio.getListaContenuti();
            if (!appoggio.getListaContenuti().contains(file)) {
                File file1 = new File("/home/daniele-rossi/Scrivania/provaFile" + file.getOriginalFilename());
                file1.createNewFile();
                FileOutputStream fileOut = new FileOutputStream(file1);
                fileOut.write(file.getBytes());
                fileOut.close();
                Contenuti contenuti = new Contenuti(file1);
                listaContenuti.add(contenuti);
                appoggio.setListaContenuti(listaContenuti);
            }
            if (ruoloUtente.equalsIgnoreCase("role_contributori")) {
                pdiRepository.deleteById(nomePdi);
                StatoPendingPuntoInteresse statoPendingPuntoInteresse = new StatoPendingPuntoInteresse(appoggio.getNomePDI(),
                        appoggio.getCoordinate().getX(), appoggio.getCoordinate().getY());
                statoPendingPuntoInteresse.setListaContenuti(appoggio.getListaContenuti());
                statoPendingPuntoInteresseRepository.save(statoPendingPuntoInteresse);
                return new ResponseEntity<>("Il contenuto è stato aggiunto" ,HttpStatus.OK);
            } else {
                pdiRepository.save(appoggio);
                return new ResponseEntity<>("Il contenuto è stato aggiornato", HttpStatus.OK);
            }
        } else throw new ContenutiNotFoundEccezione();
    }

    @DeleteMapping(value = "/deleteContenuti/{nomePdi}/{idContenuto}")
    public ResponseEntity<Object> deleteContenuti(@PathVariable String nomePdi, @PathVariable String idContenuto) {
        if (pdiRepository.existsById(nomePdi)) {
            PuntoInteresse appoggio = pdiRepository.findById(nomePdi).get();
            Contenuti appoggioContenuti = contenutiRepository.findById(idContenuto).get();
            List listaContenuti = appoggio.getListaContenuti();
            if (listaContenuti.contains(appoggioContenuti)) {
                listaContenuti.remove(appoggioContenuti);
                contenutiRepository.deleteById(idContenuto);
                appoggio.setListaContenuti(listaContenuti);
                pdiRepository.save(appoggio);
                return new ResponseEntity<>("Il contenuto è stato eliminato", HttpStatus.OK);
            } else return new ResponseEntity<>("Il contenuto non esiste o è già stato eliminato", HttpStatus.NOT_FOUND);
        } else throw new ContenutiNotFoundEccezione();
    }

    @PutMapping(value = "/editContenuti/{nomePdi}/{idContenuti}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> editContenuti(@PathVariable String nomePdi, @PathVariable String idContenuti,
                                                @RequestParam("file") MultipartFile file) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utente utente = utenteRepository.findByUsername(authentication.getName());
        String ruoloUtente = String.valueOf(utente.getRuolo());
        if (pdiRepository.existsById(nomePdi)) {
            PuntoInteresse appoggio = pdiRepository.findByNomePDI(nomePdi);
            Contenuti appoggioContenuti = contenutiRepository.findByIdContenuto(idContenuti);
            List listaContenuti = appoggio.getListaContenuti();
            if (listaContenuti.contains(appoggioContenuti)) {
                listaContenuti.remove(appoggioContenuti);
                contenutiRepository.deleteById(idContenuti);
                File file1 = new File("/home/daniele-rossi/Scrivania/provaFile" + file.getOriginalFilename());
                file1.createNewFile();
                FileOutputStream fileOut = new FileOutputStream(file1);
                fileOut.write(file.getBytes());
                fileOut.close();
                Contenuti contenuti = new Contenuti(file1);
                listaContenuti.add(contenuti);
                appoggio.setListaContenuti(listaContenuti);
            }else throw new ContenutiNotFoundEccezione();
            if (ruoloUtente.equalsIgnoreCase("role_contributori")) {
                pdiRepository.deleteById(nomePdi);
                StatoPendingPuntoInteresse statoPendingPuntoInteresse = new StatoPendingPuntoInteresse(appoggio.getNomePDI(),
                        appoggio.getCoordinate().getX(), appoggio.getCoordinate().getY());
                statoPendingPuntoInteresse.setListaContenuti(appoggio.getListaContenuti());
                statoPendingPuntoInteresseRepository.save(statoPendingPuntoInteresse);
                return new ResponseEntity<>("Il contenuto è stato aggiunto" ,HttpStatus.OK);
            } else {
                pdiRepository.save(appoggio);
                return new ResponseEntity<>("Il contenuto è stato aggiornato", HttpStatus.OK);
            }
        } else throw new ContenutiNotFoundEccezione();
    }

}
