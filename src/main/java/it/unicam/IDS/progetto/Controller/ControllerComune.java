package it.unicam.IDS.progetto.Controller;

import it.unicam.IDS.progetto.Dtos.ContenutiDtos;
import it.unicam.IDS.progetto.Dtos.ItinerarioDtos;
import it.unicam.IDS.progetto.Dtos.PuntoInteresseDtos;
import it.unicam.IDS.progetto.Entita.*;
import it.unicam.IDS.progetto.Repository.ComuneRepository;
import it.unicam.IDS.progetto.Repository.UtenteRepository;
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
@RequestMapping(value = "/comune")
public class ControllerComune {

    private ComuneRepository comuneRepository;

    private UtenteRepository utenteRepository;

    @Autowired
    public ControllerComune(ComuneRepository comuneRepository, UtenteRepository utenteRepository) {
        this.comuneRepository = comuneRepository;
        this.utenteRepository = utenteRepository;
        Comune comune = new Comune("Camerino", 43.5, 32.1, "62032");
        comuneRepository.save(comune);
    }

    @PostMapping(value = "/aggiungiContenuti/{nomePDI}")
    public ResponseEntity<Object> aggiungiContenuti(@PathVariable("nomePDI") String nomePDI,
                                                    @RequestBody ContenutiDtos contenutiDtos) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Utente utente = utenteRepository.findByUsername(authentication.getName());
        //String ruolo = String.valueOf(utente.getRuolo());
        //ROLE_CONTRIBUTORI   ROLE_CONTRIBUTORIAUTORIZZATI
        getComune().aggiungiContenuti(nomePDI, contenutiDtos, "ROLE_CONTRIBUTORIAUTORIZZATI");
        return new ResponseEntity<>("Il contenuto è stato aggiunto al punto di interesse", HttpStatus.OK);
    }

    @DeleteMapping(value = "/rimuoviContenuti/{nomePDI}/{nomeContenuto}")
    public ResponseEntity<Object> rimuoviContenuti(@PathVariable("nomePDI") String nomePDI,
                                                   @PathVariable("nomeContenuto") String nomeContenuto) {
        getComune().rimuoviContenuti(nomePDI, nomeContenuto);
        return new ResponseEntity<>("Il contenuto è stato eliminato dal punto di interesse", HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllComune")
    public ResponseEntity<Object> getAllComune() {
        return new ResponseEntity<>(comuneRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/inserimentoPDI")
    public ResponseEntity<Object> inserimentoPDI(@RequestBody PuntoInteresseDtos pdi) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Utente utente = utenteRepository.findByUsername(authentication.getName());
        //String ruolo = String.valueOf(utente.getRuolo());
        //ROLE_CONTRIBUTORI   ROLE_CONTRIBUTORIAUTORIZZATI
        getComune().inserimentoPDI(pdi, "ROLE_CONTRIBUTORIAUTORIZZATI");
        comuneRepository.save(getComune());
        return new ResponseEntity<>("Il punto di interesse è stato aggiunto alla piattaforma", HttpStatus.OK);
    }

    @DeleteMapping(value = "/elimaPDI/{nomePDI}")
    public ResponseEntity<Object> eliminaPDI(@PathVariable("nomePDI") String nomePDI) {
        getComune().eliminaPDI(nomePDI);
        comuneRepository.save(getComune());
        return new ResponseEntity<>("Il contenuto è stato eliminato dal punto di interesse", HttpStatus.OK);
    }

    /*
        public void approvazioneStatoPendingPDI(String pdiScelto, String scelta) {
            comune.approvazioneStatoPendingPDI(pdiScelto, scelta);
        }

        public void approvazioneStatoPendingItinerario(String itinerarioScelto, String scelta) {
            comune.approvazioneStatoPendingItinerario(itinerarioScelto, scelta);
        }
    */

    @PostMapping(value = "/creaItinerario")
    public ResponseEntity<Object> creaItinerario(@RequestBody ItinerarioDtos itinerario) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Utente utente = utenteRepository.findByUsername(authentication.getName());
        //String ruolo = String.valueOf(utente.getRuolo());
        //ROLE_CONTRIBUTORI   ROLE_CONTRIBUTORIAUTORIZZATI
        getComune().creaItinerario(itinerario, "ROLE_CONTRIBUTORIAUTORIZZATI");
        comuneRepository.save(getComune());
        return new ResponseEntity<>("L'itinerario è stato aggiunto alla piattaforma",HttpStatus.OK);
    }

    @DeleteMapping(value = "/eliminaItinerario/{nomeItinerario}")
    public ResponseEntity<Object> eliminaItinerario(@PathVariable("nomeItinerario") String nomeItinerario){
        getComune().eliminaItinerario(nomeItinerario);
        comuneRepository.save(getComune());
        return new ResponseEntity<>("L'itinerario è stato eliminato dalla piattaforma",HttpStatus.OK);
    }

    @PostMapping(value = "/aggiungiPdiItinerario/{nomePuntoInteresse}/{nomeItinerario}")
    public ResponseEntity<Object> aggiuntaPdiItinerario(@PathVariable("nomePuntoInteresse") String nomePuntoInteresse,
                                                        @PathVariable("nomeItinerario") String nomeItinerario) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Utente utente = utenteRepository.findByUsername(authentication.getName());
        //String ruolo = String.valueOf(utente.getRuolo());
        //ROLE_CONTRIBUTORI   ROLE_CONTRIBUTORIAUTORIZZATI
        getComune().aggiuntaPdiItinerario(nomePuntoInteresse, nomeItinerario, "ROLE_CONTRIBUTORIAUTORIZZATI");
        comuneRepository.save(getComune());
        return new ResponseEntity<>("Il punto di interesse è stato aggiunto all'itinerario",HttpStatus.OK);
    }

    @DeleteMapping(value = "/rimuoviPdiItinerario/{nomePuntoInteresse}/{nomeItinerario}")
    public ResponseEntity<Object> rimuoviPdiItinerario(@PathVariable("nomePuntoInteresse") String nomePuntoInteresse,
                                                       @PathVariable("nomeItinerario") String nomeItinerario) {
        getComune().rimuoviPdiItinerario(nomePuntoInteresse, nomeItinerario);
        comuneRepository.save(getComune());
        return new ResponseEntity<>("Il punto di interesse è stato rimosso dall'itinerario",HttpStatus.OK);
    }

    @PostMapping(value = "/aggiungiFotoItinerario/{nomeItinerario}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> aggiungiFotoItinerario(@RequestParam("file") MultipartFile file,
                                                         @PathVariable("nomeItinerario") String nomeItinerario) throws IOException {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Utente utente = utenteRepository.findByUsername(authentication.getName());
        //String ruolo = String.valueOf(utente.getRuolo());
        //ROLE_CONTRIBUTORI   ROLE_CONTRIBUTORIAUTORIZZATI
        File file1 = new File("/home/margherita/Desktop/ProvaFile/" + file.getOriginalFilename());
        file1.createNewFile();
        FileOutputStream fileOut = new FileOutputStream(file1);
        fileOut.write(file.getBytes());
        fileOut.close();
        Foto foto = new Foto(file1);
        getComune().aggiungiFotoItinerario(foto, nomeItinerario, "ROLE_CONTRIBUTORIAUTORIZZATI");
        comuneRepository.save(getComune());
        return new ResponseEntity<>("La foto è stata aggiunta all'itinerario",HttpStatus.OK);
    }

    @DeleteMapping(value = "/rimuoviFotoItinerario/{idFoto}/{nomeItinerario}")
    public ResponseEntity<Object> rimuoviFotoItinerario(@PathVariable("idFoto") int idFoto,
                                                        @PathVariable("nomeItinerario") String nomeItinerario) {
        getComune().rimuoviFotoItinerario(idFoto, nomeItinerario);
        comuneRepository.save(getComune());
        return new ResponseEntity<>("La foto è stata rimossa dall'itinerario",HttpStatus.OK);
    }

/*
    public void creaContestDiContribuzione(ContestDiContribuzione contestDiContribuzione) {
        comune.creaContestDiContribuzione(contestDiContribuzione);
    }

    public void eliminaContestDiContribuzione(String nomeContest) {
        comune.eliminaContestDiContribuzione(nomeContest);
    }

    public void modificaContestDiContribuzione(String nomeContest, String param, String elemNuovo) {
        comune.modificaContestDiContribuzione(nomeContest, param, elemNuovo);
    }

    public void proponiContenuti(String nomeContest, File file) {
        comune.proponiContenuti(nomeContest, file);
    }

    public void validaContenuti(String nomeContest, String nomeContenuto, String approv) {
        comune.validaContenuti(nomeContest, nomeContenuto, approv);
    }

    public void modificaComune(String param, String elemNuovo) {
        comune.modificaComune(param, elemNuovo);
    }

    public void aggiungiPreferitiItinerario(String nomeItinerario, String nomeUtente) {
        comune.aggiungiPreferitiItinerario(nomeItinerario, nomeUtente);
    }

    public void aggiungiPreferitiPDI(String nomePdi, String nomeUtente) {
        comune.aggiungiPreferitiPDI(nomePdi, nomeUtente);
    }

    public void rimuoviPreferitiPDI(String nomePdi, String nomeUtente) {
        comune.rimuoviPreferitiPDI(nomePdi, nomeUtente);
    }

    public void rimuoviPreferitiItinerari(String nomeItinerari, String nomeUtente) {
        comune.rimuoviPreferitiItinerari(nomeItinerari, nomeUtente);
    }

    public void inviaMessaggi(Messaggio messaggio) {
        comune.inviaMessaggi(messaggio);
    }

    public void decidiContenutoVincitore(String nomeContest, String nomeContenuto, Messaggio messaggio) {
        comune.decidiContenutoVincitore(nomeContest, nomeContenuto, messaggio);
    }

    */

    public Comune getComune() {
        return comuneRepository.findByNomeComune("Camerino");
    }
}
