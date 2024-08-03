package it.unicam.IDS.progetto.Service;

import it.unicam.IDS.progetto.Dtos.PuntoInteresseDtos;
import it.unicam.IDS.progetto.Eccezioni.PDI.PuntoInteresseNotFoundEccezione;
import it.unicam.IDS.progetto.Entita.*;
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

@RestController
@RequestMapping(value = "/puntoInteresse")
public class PDIServiceController {

    private PDIListRepository puntiInteresseRepository;
    private StatoPendingListPuntoInteresseRepository statoPendingListPuntoInteresseRepository;
    UtenteListRepository utenteRepository;

    public PDIServiceController() {
    }

    @Autowired
    public PDIServiceController(PDIListRepository puntiInteresseRepository,
                                StatoPendingListPuntoInteresseRepository statoPendingListPuntoInteresseRepository,
                                UtenteListRepository utenteRepository) {
        this.puntiInteresseRepository = puntiInteresseRepository;
        this.statoPendingListPuntoInteresseRepository = statoPendingListPuntoInteresseRepository;
        this.utenteRepository = utenteRepository;
        PuntoInteresse pI1 = new PuntoInteresse("Sotto Corte", 43.1468, 13.063);
        PuntoInteresse pI2 = new PuntoInteresse("Chiesa di Santa Mar", 43.1402, 13.0740);
        StatoPendingPuntoInteresse pI3 = new StatoPendingPuntoInteresse("Polo di Informatica", 43.139, 13.068);
        StatoPendingPuntoInteresse pI4 = new StatoPendingPuntoInteresse("Chiesa di San Venanzio", 43.1420, 13.0768);
        puntiInteresseRepository.save(pI1);
        puntiInteresseRepository.save(pI2);
        statoPendingListPuntoInteresseRepository.save(pI3);
        statoPendingListPuntoInteresseRepository.save(pI4);
    }

    @RequestMapping(value = "/getPuntoInteresse")
    public ResponseEntity<Object> getPuntoInteresse() {
        return new ResponseEntity<>(puntiInteresseRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getPuntoInteresseById/{nomePDI}")
    public ResponseEntity<Object> getPuntoInteresse(@PathVariable("nomePDI") String nomePDI) {
        if (puntiInteresseRepository.existsById(nomePDI))
            return new ResponseEntity<>(puntiInteresseRepository.findById(nomePDI), HttpStatus.OK);
        else throw new PuntoInteresseNotFoundEccezione();
    }

    @PostMapping(value = "/newPuntoInteresse")
    public ResponseEntity<Object> newPDI(@RequestBody PuntoInteresseDtos pdi) {
        IStatoPendingFactory factory = new StatoPendingPDIFactory();
        String ruoloUtente = findRuolo();
        IStatoPending appoggio = factory.newStatoPending(ruoloUtente);
        if (appoggio instanceof StatoPendingPuntoInteresse) {
            StatoPendingPuntoInteresse statoPending = new StatoPendingPuntoInteresse(pdi.getNomePDI(), pdi.getAsseX(), pdi.getAsseY());
            if (!statoPendingListPuntoInteresseRepository.existsById(String.valueOf(statoPending.getIdNome()))) {
                statoPendingListPuntoInteresseRepository.save(statoPending);
                return new ResponseEntity<>("Punto di Interesse aggiunto allo Stato di Pending", HttpStatus.OK);
            } else throw new PuntoInteresseNotFoundEccezione();
        } else {
            PuntoInteresse puntoInteresse = new PuntoInteresse(pdi.getNomePDI(), pdi.getAsseX(), pdi.getAsseY());
            puntiInteresseRepository.save(puntoInteresse);
            return new ResponseEntity<>("Punto di Interesse creato", HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/deletePuntoInteresse/{nomePDI}")
    public ResponseEntity<Object> deletePDI(@PathVariable("nomePDI") String nomePDI) {
        if (puntiInteresseRepository.existsById(nomePDI)) {
            puntiInteresseRepository.deleteById(nomePDI);
            return new ResponseEntity<>("Punto di Interesse è stato eliminato con successo", HttpStatus.OK);
        } else throw new PuntoInteresseNotFoundEccezione();
    }

    @PutMapping(value = "/updatePuntoInteresse/{nomePDI}")
    public ResponseEntity<Object> updatePDI(@PathVariable("nomePDI") String nomePDI, @RequestBody PuntoInteresseDtos pdi) {
        IStatoPendingFactory factory = new StatoPendingPDIFactory();
        String ruoloUtente = findRuolo();
        IStatoPending appoggio = factory.newStatoPending(ruoloUtente);
        if (appoggio instanceof StatoPendingPuntoInteresse) {
            if (statoPendingListPuntoInteresseRepository.existsStatoPendingPuntoInteresseByNomePDI(nomePDI)) {
                StatoPendingPuntoInteresse statoPending = statoPendingListPuntoInteresseRepository.findStatoPendingPuntoInteresseByNomePDI(nomePDI);
                statoPending.setNomePDI(pdi.getNomePDI());
                statoPending.setLatitudine(pdi.getAsseX());
                statoPending.setLongitudine(pdi.getAsseY());
                statoPendingListPuntoInteresseRepository.save(statoPending);
            } else throw new PuntoInteresseNotFoundEccezione();
        } else {
            if (puntiInteresseRepository.existsById(nomePDI)) {
                PuntoInteresse puntoInteresse = puntiInteresseRepository.findById(nomePDI).get();
                puntoInteresse.setNomePDI(pdi.getNomePDI());
                //puntoInteresse.setCoordinate(pdi.getAsseX(), pdi.getAsseY());
                puntiInteresseRepository.save(puntoInteresse);
            } else throw new PuntoInteresseNotFoundEccezione();
        }
        return new ResponseEntity<>("Punto di Interesse aggiunto con successo", HttpStatus.OK);
    }

    @PostMapping(value = "/fileUpload/{nomePDI}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> fileUpload(@PathVariable("nomePDI") String nomePDI,
                                             @RequestParam("file") MultipartFile file) throws IOException {
        IStatoPendingFactory factory = new StatoPendingPDIFactory();
        String ruoloUtente = findRuolo();
        IStatoPending appoggio = factory.newStatoPending(ruoloUtente);
        File file1 = new File("/home/margherita/Desktop/ProvaFile/" + file.getOriginalFilename());
        file1.createNewFile();
        FileOutputStream fileOut = new FileOutputStream(file1);
        fileOut.write(file.getBytes());
        fileOut.close();
        PuntoInteresse puntoInteresse = puntiInteresseRepository.findByNomePDI(nomePDI);
        //Contenuti contenuti = new Contenuti(file1);
        //puntoInteresse.getListaContenuti().add(contenuti);
        if (appoggio instanceof StatoPendingPuntoInteresse) {
            StatoPendingPuntoInteresse statoPending = new StatoPendingPuntoInteresse(puntoInteresse.getNomePDI(),
                    puntoInteresse.getCoordinate().getLatitudine(),puntoInteresse.getCoordinate().getLongitudine(),
                    puntoInteresse.getListaContenuti());
            puntiInteresseRepository.delete(puntoInteresse);
            statoPendingListPuntoInteresseRepository.save(statoPending);
            return new ResponseEntity<>("Punto di Interesse aggiunto allo statoPending", HttpStatus.OK);
        } else {
            puntiInteresseRepository.save(puntoInteresse);
            return new ResponseEntity<>("Punto di Interesse aggiunto con successo", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/getStatoPending")
    public ResponseEntity<Object> getStatoPending() {
        return new ResponseEntity<>(statoPendingListPuntoInteresseRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/approvazioneStatoPending/{nomePDI}/{approv}")
    public ResponseEntity<Object> approvazioneStatoPending(@PathVariable("nomePDI") String nomePDI,
                                                           @PathVariable("approv") String approv) {
        StatoPendingPuntoInteresse statoPending = statoPendingListPuntoInteresseRepository
                .findStatoPendingPuntoInteresseByNomePDI(nomePDI);
        if (approv.equalsIgnoreCase("Y")) {
            statoPendingListPuntoInteresseRepository.delete(statoPending);
            /*PuntoInteresse puntoInteresse = new PuntoInteresse(statoPending.getNomePDI(), statoPending.getAsseX(),
                    statoPending.getAsseY(), statoPending.getListaContenuti());
            puntiInteresseRepository.save(puntoInteresse);*/
            return new ResponseEntity<>("Punto di interesse approvato", HttpStatus.OK);
        } else {
            statoPendingListPuntoInteresseRepository.delete(statoPending);
            return new ResponseEntity<>("Punto di interesse non approvato", HttpStatus.OK);
        }
    }

    private String findRuolo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utente utente = utenteRepository.findByUsername(authentication.getName());
        return String.valueOf(utente.getRuolo());
    }
}
