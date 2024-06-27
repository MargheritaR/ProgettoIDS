package it.unicam.IDS.progetto.Service;


import it.unicam.IDS.progetto.Dtos.ContestDiContribuzioneDtos;
import it.unicam.IDS.progetto.Dtos.MessaggioDtos;
import it.unicam.IDS.progetto.Eccezioni.ContestDiContribuzione.ContestDiContribuzioneNotFoundEccezione;
import it.unicam.IDS.progetto.Eccezioni.Utente.UtenteNotFoundEccezione;
import it.unicam.IDS.progetto.Entita.Contenuti;
import it.unicam.IDS.progetto.Entita.ContestDiContribuzione;
import it.unicam.IDS.progetto.Entita.Messaggio;
import it.unicam.IDS.progetto.Entita.Utente;
import it.unicam.IDS.progetto.Repository.ContestDiContribuzioneListRepository;
import it.unicam.IDS.progetto.Repository.UtenteListRepository;
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
@RequestMapping(value = "/contestDiContribuzione")
public class ContestDiContribuzioneServiceController {

    private ContestDiContribuzioneListRepository contestDiContribuzioneRepository;

    private UtenteListRepository utenteRepository;

    @Autowired
    public ContestDiContribuzioneServiceController(ContestDiContribuzioneListRepository contestDiContribuzioneRepository,
                                                   UtenteListRepository utenteRepository) {
        this.contestDiContribuzioneRepository = contestDiContribuzioneRepository;
        this.utenteRepository = utenteRepository;
        ContestDiContribuzione contestDiContribuzione1 = new ContestDiContribuzione("springFestival", "bere",
                "informatica", "12/06/2023", 5, "1/03/2023",
                "10/03/2023", "10/06/2023", "20/06/2023");
        contestDiContribuzioneRepository.save(contestDiContribuzione1);
    }

    //TODO modificare nome metodo
    @RequestMapping(value = "/getContest")
    public ResponseEntity<Object> getComune() {
        return new ResponseEntity<>(contestDiContribuzioneRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/addContest")
    public ResponseEntity<Object> addContest(@RequestBody ContestDiContribuzioneDtos c) {
        if (!contestDiContribuzioneRepository.existsById(c.getNomeContest())) {
            ContestDiContribuzione contest = new ContestDiContribuzione(c.getNomeContest(), c.getObiettivo(), c.getTematica(),
                    c.getLimiteMassimoC(), c.getSogliaInviti(), c.getTermineMassimoS(),
                    c.getTermineMassimoR(), c.getTempoInizio(), c.getTempoFine());
            contestDiContribuzioneRepository.save(contest);
            return new ResponseEntity<>("Il contest di contribuzione è stato creato con successo", HttpStatus.OK);
        } else throw new ContestDiContribuzioneNotFoundEccezione();
    }

    @DeleteMapping(value = "/deleteContest/{nomeContest}")
    public ResponseEntity<Object> deleteContest(@PathVariable String nomeContest) {
        if (contestDiContribuzioneRepository.existsById(nomeContest)) {
            contestDiContribuzioneRepository.deleteById(nomeContest);
            return new ResponseEntity<>("Il contest di contribuzione è stato eliminato con successo", HttpStatus.OK);
        } else throw new ContestDiContribuzioneNotFoundEccezione();
    }

    @PutMapping(value = "/editContest/{nomeContest}")
    public ResponseEntity<Object> editContest(@RequestBody ContestDiContribuzioneDtos c, @PathVariable String nomeContest) {
        if (contestDiContribuzioneRepository.existsById(nomeContest)) {
            ContestDiContribuzione contest = new ContestDiContribuzione(c.getNomeContest(), c.getObiettivo(), c.getTematica(),
                    c.getLimiteMassimoC(), c.getSogliaInviti(), c.getTermineMassimoS(),
                    c.getTermineMassimoR(), c.getTempoInizio(), c.getTempoFine());
            contestDiContribuzioneRepository.deleteById(nomeContest);
            contestDiContribuzioneRepository.save(contest);
            return new ResponseEntity<>("Il contest di contribuzione è stato aggiornato con successo", HttpStatus.OK);
        } else throw new ContestDiContribuzioneNotFoundEccezione();

    }

    @RequestMapping(value = "/getVincitore/{nomeContest}")
    public ResponseEntity<Object> getVincitore(@PathVariable("nomeContest") String nomeContest) {
        if (contestDiContribuzioneRepository.existsById(nomeContest))
            return new ResponseEntity<>(contestDiContribuzioneRepository.findById(nomeContest), HttpStatus.OK);
        else throw new ContestDiContribuzioneNotFoundEccezione();
    }

    @RequestMapping(value = "/validaContest/{nomeContest}/{idContenuto}")
    public ResponseEntity<Object> validaContest(@PathVariable String nomeContest, @PathVariable int idContenuto) {
        if (contestDiContribuzioneRepository.existsById(nomeContest)) {
            ContestDiContribuzione contest = contestDiContribuzioneRepository.findById(nomeContest).get();
            Contenuti appoggio = contest.getListaContenuti().get(idContenuto);
            List listaCNonApprovati = contest.getListaContenuti();
            List listaCApprovati = contest.getContenutiApprovati();
            if (listaCNonApprovati.contains(appoggio)) {
                listaCNonApprovati.remove(idContenuto);
                listaCApprovati.add(appoggio);
                contest.setListaContenuti(listaCNonApprovati);
                contest.setContenutiApprovati(listaCApprovati);
                contestDiContribuzioneRepository.save(contest);
            } else
                new ResponseEntity<>("Il contenuto è già stato validato nel contest di contribuzione", HttpStatus.OK);
            return new ResponseEntity<>("Il contenuto del contest di contribuzione è stato validato", HttpStatus.OK);
        } else throw new ContestDiContribuzioneNotFoundEccezione();
    }

    @PostMapping(value = "/proponiContest/{nomeContest}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> ProponiContest(@RequestParam("file") MultipartFile file,
                                                 @PathVariable String nomeContest) throws IOException {
        if (contestDiContribuzioneRepository.existsById(nomeContest)) {
            ContestDiContribuzione appoggio = contestDiContribuzioneRepository.findById(nomeContest).get();
            List listaContenuti = appoggio.getListaContenuti();
            if (!appoggio.getListaContenuti().contains(file)) {
                File file1 = new File("/home/daniele-rossi/Scrivania/provaFile/" + file.getOriginalFilename());
                file1.createNewFile();
                FileOutputStream fileOut = new FileOutputStream(file1);
                fileOut.write(file.getBytes());
                fileOut.close();
                Contenuti contenuti = new Contenuti(file1);
                listaContenuti.add(contenuti);
            } else
                new ResponseEntity<>("Il contenuto è già stato inserito nel contest di contribuzione", HttpStatus.OK);
            appoggio.setListaContenuti(listaContenuti);
            contestDiContribuzioneRepository.save(appoggio);
            return new ResponseEntity<>("Il contenuto è stato aggiornato nel contest di contribuzione", HttpStatus.OK);
        } else throw new ContestDiContribuzioneNotFoundEccezione();
    }

    @PostMapping(value = "/inviaMessaggio")
    public ResponseEntity<Object> inviaMessaggio(@RequestBody MessaggioDtos messaggioDtos) {
        Messaggio appoggio = new Messaggio(messaggioDtos.getMittente(), messaggioDtos.getDestinatario(),
                messaggioDtos.getTitolo(), messaggioDtos.getIntestazione());
        Utente utente = utenteRepository.findByUsername(messaggioDtos.getDestinatario());
        utente.getListaMessaggiNonLetti().add(appoggio);
        utenteRepository.save(utente);
        return new ResponseEntity<>("Messaggio mandato", HttpStatus.OK);
    }


}

