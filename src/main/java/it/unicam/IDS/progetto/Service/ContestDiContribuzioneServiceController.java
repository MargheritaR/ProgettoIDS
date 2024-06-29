package it.unicam.IDS.progetto.Service;

import it.unicam.IDS.progetto.Dtos.ContestDiContribuzioneDtos;
import it.unicam.IDS.progetto.Dtos.MessaggioDtos;
import it.unicam.IDS.progetto.Eccezioni.Contenuti.ContenutiNotFoundEccezione;
import it.unicam.IDS.progetto.Eccezioni.ContestDiContribuzione.ContestDiContribuzioneNotFoundEccezione;
import it.unicam.IDS.progetto.Eccezioni.ContestDiContribuzione.ContestDiContribuzioneOverTimeLimitEccezione;
import it.unicam.IDS.progetto.Entita.Contenuti;
import it.unicam.IDS.progetto.Entita.ContestDiContribuzione;
import it.unicam.IDS.progetto.Entita.Messaggio;
import it.unicam.IDS.progetto.Entita.Utente;
import it.unicam.IDS.progetto.Repository.ContenutiListRepository;
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
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/contestDiContribuzione")
public class ContestDiContribuzioneServiceController {

    private ContestDiContribuzioneListRepository contestRepository;
    private ContenutiListRepository contenutiRepository;
    private UtenteListRepository utenteRepository;

    @Autowired
    public ContestDiContribuzioneServiceController(ContestDiContribuzioneListRepository contestRepository,
                                                   UtenteListRepository utenteRepository,
                                                   ContenutiListRepository contenutiRepository) {
        this.contestRepository = contestRepository;
        this.utenteRepository = utenteRepository;
        this.contenutiRepository = contenutiRepository;
        ContestDiContribuzione contestDiContribuzione1 = new ContestDiContribuzione("springFestival", "bere",
                "informatica", "12/06/2023", 5, "1/03/2023",
                "10/03/2023", "10/06/2023", "20/06/2023");
        contestRepository.save(contestDiContribuzione1);
    }

    @RequestMapping(value = "/getContest")
    public ResponseEntity<Object> getContest() {
        return new ResponseEntity<>(contestRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/addContest")
    public ResponseEntity<Object> addContest(@RequestBody ContestDiContribuzioneDtos c) {
        if (!contestRepository.existsByNomeContest(c.getNomeContest())) {
            ContestDiContribuzione contest = new ContestDiContribuzione(c.getNomeContest(), c.getObiettivo(), c.getTematica(),
                    c.getLimiteMassimoC(), c.getSogliaInviti(), c.getTermineMassimoS(),
                    c.getTermineMassimoR(), c.getTempoInizio(), c.getTempoFine());
            contestRepository.save(contest);
            return new ResponseEntity<>("Il contest di contribuzione è stato creato con successo", HttpStatus.OK);
        } else throw new ContestDiContribuzioneNotFoundEccezione();
    }

    @DeleteMapping(value = "/deleteContest/{nomeContest}")
    public ResponseEntity<Object> deleteContest(@PathVariable("nomeContest") String nomeContest) {
        if (contestRepository.existsByNomeContest(nomeContest)) {
            contestRepository.deleteById(nomeContest);
            return new ResponseEntity<>("Il contest di contribuzione è stato eliminato con successo", HttpStatus.OK);
        } else throw new ContestDiContribuzioneNotFoundEccezione();
    }

    @PutMapping(value = "/editContest/{nomeContest}")
    public ResponseEntity<Object> editContest(@RequestBody ContestDiContribuzioneDtos c,
                                              @PathVariable("nomeContest") String nomeContest) {
        if (contestRepository.existsByNomeContest(nomeContest)) {
            ContestDiContribuzione contest = new ContestDiContribuzione(c.getNomeContest(), c.getObiettivo(), c.getTematica(),
                    c.getLimiteMassimoC(), c.getSogliaInviti(), c.getTermineMassimoS(),
                    c.getTermineMassimoR(), c.getTempoInizio(), c.getTempoFine());
            contestRepository.deleteById(nomeContest);
            contestRepository.save(contest);
            return new ResponseEntity<>("Il contest di contribuzione è stato aggiornato con successo", HttpStatus.OK);
        } else throw new ContestDiContribuzioneNotFoundEccezione();

    }

    @RequestMapping(value = "/getVincitore/{nomeContest}")
    public ResponseEntity<Object> getVincitore(@PathVariable("nomeContest") String nomeContest) {
        if (contestRepository.existsByNomeContest(nomeContest))
            return new ResponseEntity<>("Il Vincitore è: " +
                    contestRepository.findByNomeContest(nomeContest).getVincitore(), HttpStatus.OK);
        else throw new ContestDiContribuzioneNotFoundEccezione();
    }

    @PostMapping(value = "/decidiVincitore/{nomeContest}/{idContenuto}")
    public ResponseEntity<Object> decidiVincitore(@PathVariable("nomeContest") String nomeContest,
                                                  @PathVariable("idContenuto") int idContenuto) {
        if (contestRepository.existsByNomeContest(nomeContest)) {
            ContestDiContribuzione appoggio = contestRepository.findByNomeContest(nomeContest);
            if (appoggio.getContenutiApprovati().contains(idContenuto)) {
                Contenuti contenuti = contenutiRepository.findByIdContenuto(idContenuto);
                appoggio.setVincitore(contenuti.getContenuto());
                return new ResponseEntity<>("Il vincitore è stato deciso", HttpStatus.OK);
            } else throw new ContenutiNotFoundEccezione();
        } else throw new ContestDiContribuzioneNotFoundEccezione();
    }

    @RequestMapping(value = "/validaContest/{nomeContest}/{idContenuto}")
    public ResponseEntity<Object> validaContest(@PathVariable("nomeContest") String nomeContest,
                                                @PathVariable("idContenuto") int idContenuto) {
        if (contestRepository.existsByNomeContest(nomeContest)) {
            ContestDiContribuzione contest = contestRepository.findByNomeContest(nomeContest);
            Contenuti appoggio = contest.getListaContenuti().get(idContenuto);
            List listaCNonApprovati = contest.getListaContenuti();
            List listaCApprovati = contest.getContenutiApprovati();
            if (listaCNonApprovati.contains(appoggio)) {
                listaCNonApprovati.remove(idContenuto);
                listaCApprovati.add(appoggio);
                contest.setListaContenuti(listaCNonApprovati);
                contest.setContenutiApprovati(listaCApprovati);
                contestRepository.save(contest);
            } else new ResponseEntity<>("Il contenuto è già stato validato nel contest di contribuzione", HttpStatus.OK);
            return new ResponseEntity<>("Il contenuto del contest di contribuzione è stato validato", HttpStatus.OK);
        } else throw new ContestDiContribuzioneNotFoundEccezione();
    }

    @PostMapping(value = "/proponiContest/{nomeContest}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> ProponiContest(@RequestParam("file") MultipartFile file,
                                                 @PathVariable("nomeContest") String nomeContest) throws IOException {
        if (contestRepository.existsByNomeContest(nomeContest)) {
            ContestDiContribuzione appoggio = contestRepository.findByNomeContest(nomeContest);
            List listaContenuti = appoggio.getListaContenuti();
            if (!appoggio.getLimiteMassimoC().isAfter(LocalDate.now()))
                throw new ContestDiContribuzioneOverTimeLimitEccezione();
            if (!appoggio.getListaContenuti().contains(file)) {
                File file1 = new File("/home/margherita/Desktop/ProvaFile/" + file.getOriginalFilename());
                file1.createNewFile();
                FileOutputStream fileOut = new FileOutputStream(file1);
                fileOut.write(file.getBytes());
                fileOut.close();
                Contenuti contenuti = new Contenuti(file1);
                listaContenuti.add(contenuti);
            } else new ResponseEntity<>("Il contenuto è già stato inserito nel contest di contribuzione", HttpStatus.OK);
            appoggio.setListaContenuti(listaContenuti);
            contestRepository.save(appoggio);
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

