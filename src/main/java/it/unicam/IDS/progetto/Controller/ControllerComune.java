package it.unicam.IDS.progetto.Controller;

import it.unicam.IDS.progetto.Dtos.ContestDiContribuzioneDtos;
import it.unicam.IDS.progetto.Dtos.ItinerarioDtos;
import it.unicam.IDS.progetto.Dtos.MessaggioDtos;
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

    @PostMapping(value = "/aggiungiContenuti/{nomePDI}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> aggiungiContenuti(@PathVariable("nomePDI") String nomePDI,
                                                    @RequestParam("file") MultipartFile file) throws IOException {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Utente utente = utenteRepository.findByUsername(authentication.getName());
        //String ruolo = String.valueOf(utente.getRuolo());
        //ROLE_CONTRIBUTORI   ROLE_CONTRIBUTORIAUTORIZZATI
        File file1 = new File("/home/margherita/Desktop/ProvaFile/" + file.getOriginalFilename());
        file1.createNewFile();
        FileOutputStream fileOut = new FileOutputStream(file1);
        fileOut.write(file.getBytes());
        fileOut.close();
        getComune().aggiungiContenuti(nomePDI, file1, "ROLE_CONTRIBUTORIAUTORIZZATI");
        comuneRepository.save(getComune());
        return new ResponseEntity<>("Il contenuto è stato aggiunto al punto di interesse", HttpStatus.OK);
    }

    @DeleteMapping(value = "/rimuoviContenuti/{nomePDI}/{nomeContenuto}")
    public ResponseEntity<Object> rimuoviContenuti(@PathVariable("nomePDI") String nomePDI,
                                                   @PathVariable("nomeContenuto") String nomeContenuto) {
        getComune().rimuoviContenuti(nomePDI, nomeContenuto);
        comuneRepository.save(getComune());
        return new ResponseEntity<>("Il contenuto è stato eliminato dal punto di interesse", HttpStatus.OK);
    }

    @RequestMapping(value = "/getComune")
    public ResponseEntity<Object> getAllComune() {
        return new ResponseEntity<>(comuneRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/inserimentoPDI")
    public ResponseEntity<Object> inserimentoPDI(@RequestBody PuntoInteresseDtos pdi) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Utente utente = utenteRepository.findByUsername(authentication.getName());
        //String ruolo = String.valueOf(utente.getRuolo());
        //ROLE_CONTRIBUTORI   ROLE_CONTRIBUTORIAUTORIZZATI
        getComune().inserimentoPDI(pdi, "ROLE_CONTRIBUTORI");
        comuneRepository.save(getComune());
        return new ResponseEntity<>("Il punto di interesse è stato aggiunto alla piattaforma", HttpStatus.OK);
    }

    @DeleteMapping(value = "/elimaPDI/{nomePDI}")
    public ResponseEntity<Object> eliminaPDI(@PathVariable("nomePDI") String nomePDI) {
        getComune().eliminaPDI(nomePDI);
        comuneRepository.save(getComune());
        return new ResponseEntity<>("Il contenuto è stato eliminato dal punto di interesse", HttpStatus.OK);
    }

    @PutMapping(value = "/approvStatoPendingPDI/{pdiScelto}/{scelta}")
    public ResponseEntity<Object> approvazioneStatoPendingPDI(@PathVariable("pdiScelto") String pdiScelto,
                                                              @PathVariable("scelta") String scelta) {
        getComune().approvazioneStatoPendingPDI(pdiScelto, scelta);
        comuneRepository.save(getComune());
        if (scelta.equalsIgnoreCase("Y"))
            return new ResponseEntity<>("Il punto di interesse è stato approvato", HttpStatus.OK);
        else return new ResponseEntity<>("Il punto di interesse è stato eliminato", HttpStatus.OK);
    }

    @PutMapping(value = "/approvStatoPendingItinerario/{itinerarioScelto}/{scelta}")
    public ResponseEntity<Object> approvazioneStatoPendingItinerario(@PathVariable("itinerarioScelto") String itinerarioScelto,
                                                                     @PathVariable("scelta") String scelta) {
        getComune().approvazioneStatoPendingItinerario(itinerarioScelto, scelta);
        comuneRepository.save(getComune());
        if (scelta.equalsIgnoreCase("Y"))
            return new ResponseEntity<>("L'itinerario è stato approvato", HttpStatus.OK);
        else return new ResponseEntity<>("L'itinerario è stato eliminato", HttpStatus.OK);
    }

    @PostMapping(value = "/creaItinerario")
    public ResponseEntity<Object> creaItinerario(@RequestBody ItinerarioDtos itinerario) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Utente utente = utenteRepository.findByUsername(authentication.getName());
        //String ruolo = String.valueOf(utente.getRuolo());
        //ROLE_CONTRIBUTORI   ROLE_CONTRIBUTORIAUTORIZZATI
        getComune().creaItinerario(itinerario, "ROLE_CONTRIBUTORI");
        comuneRepository.save(getComune());
        return new ResponseEntity<>("L'itinerario è stato aggiunto alla piattaforma", HttpStatus.OK);
    }

    @DeleteMapping(value = "/eliminaItinerario/{nomeItinerario}")
    public ResponseEntity<Object> eliminaItinerario(@PathVariable("nomeItinerario") String nomeItinerario) {
        getComune().eliminaItinerario(nomeItinerario);
        comuneRepository.save(getComune());
        return new ResponseEntity<>("L'itinerario è stato eliminato dalla piattaforma", HttpStatus.OK);
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
        return new ResponseEntity<>("Il punto di interesse è stato aggiunto all'itinerario", HttpStatus.OK);
    }

    @DeleteMapping(value = "/rimuoviPdiItinerario/{nomePuntoInteresse}/{nomeItinerario}")
    public ResponseEntity<Object> rimuoviPdiItinerario(@PathVariable("nomePuntoInteresse") String nomePuntoInteresse,
                                                       @PathVariable("nomeItinerario") String nomeItinerario) {
        getComune().rimuoviPdiItinerario(nomePuntoInteresse, nomeItinerario);
        comuneRepository.save(getComune());
        return new ResponseEntity<>("Il punto di interesse è stato rimosso dall'itinerario", HttpStatus.OK);
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
        return new ResponseEntity<>("La foto è stata aggiunta all'itinerario", HttpStatus.OK);
    }

    @DeleteMapping(value = "/rimuoviFotoItinerario/{idFoto}/{nomeItinerario}")
    public ResponseEntity<Object> rimuoviFotoItinerario(@PathVariable("idFoto") int idFoto,
                                                        @PathVariable("nomeItinerario") String nomeItinerario) {
        getComune().rimuoviFotoItinerario(idFoto-1, nomeItinerario);
        comuneRepository.save(getComune());
        return new ResponseEntity<>("La foto è stata rimossa dall'itinerario", HttpStatus.OK);
    }


    @PostMapping(value = "/creaContest")
    public ResponseEntity<Object> creaContestDiContribuzione(@RequestBody ContestDiContribuzioneDtos contestDiContribuzioneDtos) {
        getComune().creaContestDiContribuzione(contestDiContribuzioneDtos);
        comuneRepository.save(getComune());
        return new ResponseEntity<>("Il contest di contribuzione è stato aggiunto alla piattaforma", HttpStatus.OK);
    }

    @DeleteMapping(value = "/eliminaContest/{nomeContest}")
    public ResponseEntity<Object> eliminaContestDiContribuzione(@PathVariable("nomeContest") String nomeContest) {
        getComune().eliminaContestDiContribuzione(nomeContest);
        comuneRepository.save(getComune());
        return new ResponseEntity<>("Il contest di contribuzione è stato eliminato dalla piattaforma", HttpStatus.OK);
    }

    @PutMapping(value = "/modificaContest/{nomeContest}/{param}/{elemNuovo}")
    public ResponseEntity<Object> modificaContestDiContribuzione(@PathVariable("nomeContest") String nomeContest,
                                                                 @PathVariable("param") String param,
                                                                 @PathVariable("elemNuovo") String elemNuovo) {
        getComune().modificaContestDiContribuzione(nomeContest, param, elemNuovo);
        comuneRepository.save(getComune());
        return new ResponseEntity<>("La modifica dell'/della " + param + " del contesti di contribuzione è avvenuta", HttpStatus.OK);
    }

    @PostMapping(value = "/proponiContest/{nomeContest}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> proponiContenuti(@PathVariable("nomeContest") String nomeContest,
                                                   @RequestParam("file") MultipartFile file) throws IOException {
        File file1 = new File("/home/daniele-rossi/Scrivania/provaFile" + file.getOriginalFilename());
        file1.createNewFile();
        FileOutputStream fileOut = new FileOutputStream(file1);
        fileOut.write(file.getBytes());
        fileOut.close();
        getComune().proponiContenuti(nomeContest, file1);
        comuneRepository.save(getComune());
        return new ResponseEntity<>("Il contenuto è stato proposto al contest di contribuzione", HttpStatus.OK);
    }

    @PutMapping(value = "/validaContenuti/{nomeContest}/{nomeContenuto}/{approv}")
    public ResponseEntity<Object> validaContenuti(@PathVariable("nomeContest") String nomeContest,
                                                  @PathVariable("nomeContenuto") String nomeContenuto,
                                                  @PathVariable("approv") String approv) {
        getComune().validaContenuti(nomeContest, nomeContenuto, approv);
        comuneRepository.save(getComune());
        if (approv.equalsIgnoreCase("Y"))
            return new ResponseEntity<>("Il contenuto è stato approvato", HttpStatus.OK);
        else return new ResponseEntity<>("Il contenuto è stato eliminato", HttpStatus.OK);
    }

    @PutMapping(value = "/modificaComune/{param}/{elemNuovo}")
    public ResponseEntity<Object> modificaComune(@PathVariable("param") String param,
                                                 @PathVariable("elemNuovo") String elemNuovo) {
        //
        getComune().modificaComune(param, elemNuovo);
        comuneRepository.save(getComune());
        return new ResponseEntity<>("La modifica del " + param + " del comune è avvenuta", HttpStatus.OK);
    }

    @PostMapping(value = "/aggiungiPreferitiItinerario/{nomeItinerario}")
    public ResponseEntity<Object> aggiungiPreferitiItinerario(@PathVariable("nomeItinerario") String nomeItinerario) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Utente utente = utenteRepository.findByUsername(authentication.getName());
        getComune().aggiungiPreferitiItinerario(nomeItinerario, "Daniele");
        comuneRepository.save(getComune());
        return new ResponseEntity<>("L'itinerario è stato aggiunto ai preferiti", HttpStatus.OK);
    }

    @PostMapping(value = "/aggiungiPreferitiPDI/{nomePdi}")
    public ResponseEntity<Object> aggiungiPreferitiPDI(@PathVariable("nomePdi") String nomePdi) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Utente utente = utenteRepository.findByUsername(authentication.getName());
        getComune().aggiungiPreferitiPDI(nomePdi, "Daniele");
        comuneRepository.save(getComune());
        return new ResponseEntity<>("Il punto di interesse è stato aggiunto ai preferiti", HttpStatus.OK);
    }

    @DeleteMapping(value = "/rimuoviPreferitiPDI/{nomePdi}")
    public ResponseEntity<Object> rimuoviPreferitiPDI(@PathVariable("nomePdi") String nomePdi) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Utente utente = utenteRepository.findByUsername(authentication.getName());
        getComune().rimuoviPreferitiPDI(nomePdi, "Daniele");
        comuneRepository.save(getComune());
        return new ResponseEntity<>("Il punto di interesse è stato rimosso dai preferiti", HttpStatus.OK);
    }

    @DeleteMapping(value = "/rimuoviPreferitiItinerari/{nomeItinerari}")
    public ResponseEntity<Object> rimuoviPreferitiItinerari(String nomeItinerari) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Utente utente = utenteRepository.findByUsername(authentication.getName());
        getComune().rimuoviPreferitiItinerari(nomeItinerari, "Daniele");
        comuneRepository.save(getComune());
        return new ResponseEntity<>("L'itinerario è stato rimosso dai preferiti", HttpStatus.OK);
    }

    @PostMapping(value = "/inviaMessaggi")
    public ResponseEntity<Object> inviaMessaggi(@RequestBody Messaggio messaggio) {
        getComune().inviaMessaggi(messaggio);
        comuneRepository.save(getComune());
        return new ResponseEntity<>("Messaggio inviato a: " + messaggio.getDestinatario(), HttpStatus.OK);
    }

    @PutMapping(value = "/decidiContenuto/{nomeContest}/{nomeContenuto}")
    public ResponseEntity<Object> decidiContenutoVincitore(@PathVariable("nomeContest") String nomeContest,
                                                           @PathVariable("nomeContenuto") String nomeContenuto,
                                                           @RequestBody MessaggioDtos messaggio) {
        getComune().decidiContenutoVincitore(nomeContenuto, nomeContest, messaggio);
        comuneRepository.save(getComune());
        return new ResponseEntity<>("Il vincitore è stato scelto", HttpStatus.OK);
    }

    public Comune getComune() {
        return comuneRepository.findByNomeComune("Camerino");
    }
}
