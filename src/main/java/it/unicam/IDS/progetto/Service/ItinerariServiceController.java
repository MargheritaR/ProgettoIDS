package it.unicam.IDS.progetto.Service;

import it.unicam.IDS.progetto.Dtos.ItinerarioDtos;
import it.unicam.IDS.progetto.Eccezioni.Itinerari.ItinerariNotFoundEccezione;
import it.unicam.IDS.progetto.Entita.*;
import it.unicam.IDS.progetto.Repository.StatoPendingListItinerarioRepository;
import it.unicam.IDS.progetto.Repository.ItinerarioListRepository;
import it.unicam.IDS.progetto.Repository.PDIListRepository;
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
@RequestMapping(value = "/itinerari")
public class ItinerariServiceController implements ContenutoBase {

    private ItinerarioListRepository itinerariRepository;
    private PDIListRepository pdiRepository;
    private StatoPendingListItinerarioRepository statoPendingRepository;
    private UtenteListRepository utenteRepository;

    @Autowired
    public ItinerariServiceController(ItinerarioListRepository itinerariRepository, PDIListRepository pdiRepository,
                                      StatoPendingListItinerarioRepository statoPendingRepository,
                                      UtenteListRepository utenteRepository) {
        this.itinerariRepository = itinerariRepository;
        this.pdiRepository = pdiRepository;
        this.statoPendingRepository = statoPendingRepository;
        this.utenteRepository = utenteRepository;
    }

    public ItinerariServiceController() {
    }

    @RequestMapping(value = "/getItinerari")
    public ResponseEntity<Object> getItinerari() {
        return new ResponseEntity<>(itinerariRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getItinerari/{nomeItinerario}")
    public ResponseEntity<Object> getItinerari(@PathVariable("nomeItinerario") String nomeItinerario) {
        if (itinerariRepository.existsById(nomeItinerario)) {
            return new ResponseEntity<>(itinerariRepository.findById(nomeItinerario), HttpStatus.OK);
        } else throw new ItinerariNotFoundEccezione();
    }

    @PostMapping(value = "/newItinerario")
    public ResponseEntity<Object> newItinerario(@RequestBody ItinerarioDtos it) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utente utente = utenteRepository.findByUsername(authentication.getName());
        String ruoloUtente = String.valueOf(utente.getRuolo());
        if (ruoloUtente.equalsIgnoreCase("role_contributori")) {
            StatoPendingItinerario statoPending = new StatoPendingItinerario(it.getNomeItinerario());
            if (!statoPendingRepository.existsById(String.valueOf(statoPending.getId()))) {
                statoPendingRepository.save(statoPending);
                return new ResponseEntity<>("L'Itinerario è stato aggiunto allo Stato Pending", HttpStatus.OK);
            } else throw new ItinerariNotFoundEccezione();
        } else {
            Itinerario itinerario = new Itinerario(it.getNomeItinerario());
            itinerariRepository.save(itinerario);
            return new ResponseEntity<>("Itinerario creato ", HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/deleteItinerario/{nomeItinerario}")
    public ResponseEntity<Object> deleteItinerario(@PathVariable("nomeItinerario") String nomeItinerario) {
        if (itinerariRepository.existsById(nomeItinerario)) {
            itinerariRepository.deleteById(nomeItinerario);
            return new ResponseEntity<>("Itinerario è stato eliminato con successo ", HttpStatus.OK);
        } else throw new ItinerariNotFoundEccezione();
    }

    @PutMapping(value = "AggiungiPdi/{nomeItinerario}/{nomePdi}")
    public ResponseEntity<Object> AggiungiPdi(@PathVariable("nomeItinerario") String nomeItinerario,
                                              @PathVariable("nomePdi") String nomePdi) {
        String ruoloUtente = findRuolo();
        if (itinerariRepository.existsById(nomeItinerario)) {
            Itinerario itinerario = itinerariRepository.findByNomeItinerario(nomeItinerario);
            List listaPdi = itinerario.getListaItinerarioPDI();
            if (!listaPdi.contains(nomePdi)) {
                listaPdi.add(nomePdi);
                itinerario.setListaItinerarioPDI(listaPdi);
            } else throw new ItinerariNotFoundEccezione();
            if (ruoloUtente.equalsIgnoreCase("role_contributori")) {
                itinerariRepository.delete(itinerario);
                StatoPendingItinerario appoggio = new StatoPendingItinerario(itinerario.getNomeItinerario(),
                        itinerario.getListaItinerarioPDI(), itinerario.getListaFoto());
                statoPendingRepository.save(appoggio);
                return new ResponseEntity<>("Itinerario aggiunto allo stato di Pending", HttpStatus.OK);
            } else {
                itinerariRepository.save(itinerario);
                return new ResponseEntity<>("Punto di Interesse aggiunto", HttpStatus.OK);
            }
        } else throw new ItinerariNotFoundEccezione();
    }

    @PutMapping(value = "/AggiungiFoto/{nomeItinerario}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> AggiungiFoto(@RequestParam("file") MultipartFile file,
                                               @PathVariable("nomeItinerario") String nomeItinerario) throws IOException {
        String ruoloUtente = findRuolo();
        if (itinerariRepository.existsById(nomeItinerario)) {
            Itinerario itinerario = itinerariRepository.findByNomeItinerario(nomeItinerario);
            List listaFoto = itinerario.getListaFoto();
            if (!itinerario.getListaFoto().contains(file)) {
                File file1 = new File("/home/margherita/Desktop/" + file.getOriginalFilename());
                file1.createNewFile();
                FileOutputStream fileOut = new FileOutputStream(file1);
                fileOut.write(file.getBytes());
                fileOut.close();
                Foto foto = new Foto(file1);
                listaFoto.add(foto);
            } else throw new ItinerariNotFoundEccezione();
            if (ruoloUtente.equalsIgnoreCase("role_contributori")) {
                itinerariRepository.delete(itinerario);
                StatoPendingItinerario appoggio = new StatoPendingItinerario(itinerario.getNomeItinerario(),
                        itinerario.getListaItinerarioPDI(), itinerario.getListaFoto());
                statoPendingRepository.save(appoggio);
                return new ResponseEntity<>("Itinerario aggiunto allo stato di Pending", HttpStatus.OK);
            } else {
                itinerariRepository.save(itinerario);
                return new ResponseEntity<>("Punto di Interesse aggiunto", HttpStatus.OK);
            }
        } else throw new ItinerariNotFoundEccezione();
    }

    @Override
    @RequestMapping(value = "/getStatoPending")
    public ResponseEntity<Object> getStatoPending() {
        return new ResponseEntity<>(statoPendingRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/approvazioneStatoPending/{idNomeItinearario}")
    public ResponseEntity<Object> approvazioneStatoPending(@PathVariable String idNomeItinerario) {
        StatoPendingItinerario statoPending = statoPendingRepository.findStatoPendingItinerarioByNomeItinerario(idNomeItinerario);
        statoPendingRepository.delete(statoPending);
        Itinerario itinerario = new Itinerario(statoPending.getNomeItinerario());
        itinerario.setListaFoto(statoPending.getListaFoto());
        itinerario.setListaItinerarioPDI(statoPending.getListaItinerarioPDI());
        itinerariRepository.save(itinerario);
        return new ResponseEntity<>("Itinerario approvato", HttpStatus.OK);
    }

    private String findRuolo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utente utente = utenteRepository.findByUsername(authentication.getName());
        return String.valueOf(utente.getRuolo());
    }

}
