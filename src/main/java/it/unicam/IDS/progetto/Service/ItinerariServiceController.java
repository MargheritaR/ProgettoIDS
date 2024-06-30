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
public class ItinerariServiceController {

    private ItinerarioListRepository itinerariRepository;
    private StatoPendingListItinerarioRepository statoPendingRepository;
    private PDIListRepository pdiRepository;
    private UtenteListRepository utenteRepository;

    @Autowired
    public ItinerariServiceController(ItinerarioListRepository itinerariRepository,
                                      StatoPendingListItinerarioRepository statoPendingRepository,
                                      UtenteListRepository utenteRepository, PDIListRepository pdiRepository) {
        this.itinerariRepository = itinerariRepository;
        this.statoPendingRepository = statoPendingRepository;
        this.utenteRepository = utenteRepository;
        this.pdiRepository = pdiRepository;
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

    @PostMapping(value = "/newItinerario/{nomeItinerario}")
    public ResponseEntity<Object> newItinerario(@PathVariable String nomeItinerario) {
        IStatoPendingFactory factory = new StatoPendingPIFactory();
        String ruoloUtente = findRuolo();
        IStatoPending appoggio = factory.newStatoPending(ruoloUtente);
        if (appoggio instanceof StatoPendingItinerario) {
            StatoPendingItinerario statoPending = new StatoPendingItinerario(nomeItinerario);
            if (!statoPendingRepository.existsById(String.valueOf(statoPending.getId()))) {
                statoPendingRepository.save(statoPending);
                return new ResponseEntity<>("L'Itinerario è stato aggiunto allo Stato Pending", HttpStatus.OK);
            } else throw new ItinerariNotFoundEccezione();
        } else {
            Itinerario itinerario = new Itinerario(nomeItinerario);
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

    @PutMapping(value = "aggiungiPdi/{nomeItinerario}/{nomePDI}")
    public ResponseEntity<Object> aggiungiPdi(@PathVariable("nomeItinerario") String nomeItinerario,
                                              @PathVariable("nomePDI") String nomePDI) {
        IStatoPendingFactory factory = new StatoPendingPIFactory();
        String ruoloUtente = findRuolo();
        IStatoPending app = factory.newStatoPending(ruoloUtente);
        if (itinerariRepository.existsById(nomeItinerario)) {
            Itinerario itinerario = itinerariRepository.findByNomeItinerario(nomeItinerario);
            List listaPdi = itinerario.getListaItinerarioPDI();
            if (!listaPdi.contains(nomePDI)) {
                PuntoInteresse puntoInteresse = pdiRepository.findByNomePDI(nomePDI);
                listaPdi.add(puntoInteresse);
                itinerario.setListaItinerarioPDI(listaPdi);
            } else throw new ItinerariNotFoundEccezione();
            if (app instanceof StatoPendingItinerario) {
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

    @PutMapping(value = "/aggiungiFoto/{nomeItinerario}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> aggiungiFoto(@RequestParam("file") MultipartFile file,
                                               @PathVariable("nomeItinerario") String nomeItinerario) throws IOException {
        IStatoPendingFactory factory = new StatoPendingPIFactory();
        String ruoloUtente = findRuolo();
        IStatoPending app = factory.newStatoPending(ruoloUtente);
        if (itinerariRepository.existsById(nomeItinerario)) {
            Itinerario itinerario = itinerariRepository.findByNomeItinerario(nomeItinerario);
            List listaFoto = itinerario.getListaFoto();
            if (!itinerario.getListaFoto().contains(file)) {
                File file1 = new File("/home/margherita/Desktop/ProvaFile/" + file.getOriginalFilename());
                file1.createNewFile();
                FileOutputStream fileOut = new FileOutputStream(file1);
                fileOut.write(file.getBytes());
                fileOut.close();
                Foto foto = new Foto(file1);
                listaFoto.add(foto);
            } else throw new ItinerariNotFoundEccezione();
            if (app instanceof StatoPendingItinerario) {
                itinerariRepository.delete(itinerario);
                StatoPendingItinerario appoggio = new StatoPendingItinerario(itinerario.getNomeItinerario(),
                        itinerario.getListaItinerarioPDI(), itinerario.getListaFoto());
                statoPendingRepository.save(appoggio);
                return new ResponseEntity<>("Itinerario aggiunto allo stato di Pending", HttpStatus.OK);
            } else {
                itinerariRepository.save(itinerario);
                return new ResponseEntity<>("File aggiunto con successo", HttpStatus.OK);
            }
        } else throw new ItinerariNotFoundEccezione();
    }


    @RequestMapping(value = "/getStatoPending")
    public ResponseEntity<Object> getStatoPending() {
        return new ResponseEntity<>(statoPendingRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/approvStatoPending/{nomeItinerario}")
    public ResponseEntity<Object> approvazioneStatoPending(@PathVariable("nomeItinerario") String nomeItinerario) {
        StatoPendingItinerario statoPending = statoPendingRepository.findStatoPendingItinerarioByNomeItinerario(nomeItinerario);
        statoPendingRepository.delete(statoPending);
        Itinerario itinerario = new Itinerario(statoPending.getNomeItinerario(),statoPending.getListaItinerarioPDI(),
                statoPending.getListaFoto());
        itinerariRepository.save(itinerario);
        return new ResponseEntity<>("Itinerario approvato", HttpStatus.OK);
    }

    private String findRuolo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utente utente = utenteRepository.findByUsername(authentication.getName());
        return String.valueOf(utente.getRuolo());
    }

}
