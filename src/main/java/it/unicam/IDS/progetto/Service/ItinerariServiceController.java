package it.unicam.IDS.progetto.Service;

import it.unicam.IDS.progetto.Eccezioni.Itinerari.ItinerariNotFoundEccezione;
import it.unicam.IDS.progetto.Eccezioni.PDI.PuntoInteresseNotFoundEccezione;
import it.unicam.IDS.progetto.Entita.Foto;
import it.unicam.IDS.progetto.Entita.Itinerario;
import it.unicam.IDS.progetto.Repository.ItinerarioListRepository;
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
@RequestMapping(value = "/itinerari")
public class ItinerariServiceController {
    //TODO controllare se sono stati fatti tutti i controlli

    private ItinerarioListRepository itinerariRepository;

    private PDIListRepository pdiRepository;

    @Autowired
    public ItinerariServiceController(ItinerarioListRepository itinerariRepository, PDIListRepository pdiRepository) {
        this.itinerariRepository = itinerariRepository;
        this.pdiRepository = pdiRepository;
    }

    @RequestMapping(value = "/getItinerari")
    public ResponseEntity<Object> getItinerari() {
        return new ResponseEntity<>(itinerariRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getItinerari/{nomeItinerario}")
    public ResponseEntity<Object> getItinerari(@PathVariable("nomeItinerario") String nomeItinerario) {
        if (itinerariRepository.existsById(nomeItinerario)) {
            return new ResponseEntity<>(itinerariRepository.findById(nomeItinerario),HttpStatus.OK);
        } else throw new ItinerariNotFoundEccezione();
    }

    @PostMapping(value = "/newItinerario")
    public ResponseEntity<Object> newItinerario(@RequestBody Itinerario itinerario) {
        if (!itinerariRepository.existsById(itinerario.getNomeItinerario())) {
            itinerariRepository.save(itinerario);
            return new ResponseEntity<>("Itinerario creato ", HttpStatus.OK);
        } else throw new ItinerariNotFoundEccezione();
    }

    @DeleteMapping(value = "/deleteItinerario/{nomeItinerario}")
    public ResponseEntity<Object> deleteItinerario(@PathVariable("nomeItinerario") String nomeItinerario) {
        if (itinerariRepository.existsById(nomeItinerario)) {
            itinerariRepository.deleteById(nomeItinerario);
            return new ResponseEntity<>("Itinerario è stato eliminato con successo ", HttpStatus.OK);
        } else throw new ItinerariNotFoundEccezione();
    }

    // TODO capire cosa farci, non funziona perchè non voglio mettere id
    /*
    @PutMapping(value = "/updateItinerario/{nomeItinerario}/{nomeItinerarioNuovo}")
    public ResponseEntity<Object> updateItinerario(@PathVariable("nomeItinerario") String nomeItinerario,
                                                   @PathVariable("nomeItinerarioNuovo") String nomeItinerarioNuovo) {
        if (itinerariRepository.existsById(nomeItinerario)) {
            Itinerario itinerarioEsistente = itinerariRepository.findById(nomeItinerario).get();
            Itinerario nuovoItinerario = new Itinerario(nomeItinerarioNuovo, itinerarioEsistente.getListaItinerarioPDI(), itinerarioEsistente.getListaFoto());
            itinerariRepository.save(nuovoItinerario);
            itinerariRepository.delete(itinerarioEsistente);
            return new ResponseEntity<>("Itinerario è stato aggiornato con successo", HttpStatus.OK);
        } else throw new ItinerariNotFoundEccezione();
    }
    */

    //TODO funziona ma da controllare se funziona con la sintassi giusta di PathVariable
    @PutMapping(value = "AggiungiPdi/{nomeItinerario}/{nomePdi}")
    public ResponseEntity<Object> AggiungiPdi(@PathVariable String nomeItinerario, @PathVariable String nomePdi) {
        if (itinerariRepository.existsById(nomeItinerario)) {
            Itinerario appoggio = itinerariRepository.findById(nomeItinerario).get();
            List listaPdi = appoggio.getListaItinerarioPDI();
            if (!appoggio.getListaItinerarioPDI().contains(nomePdi))
                if (pdiRepository.existsById(nomePdi))
                    listaPdi.add(pdiRepository.findById(nomePdi).get());
                else throw new PuntoInteresseNotFoundEccezione();
            else return new ResponseEntity<>("Punto Interesse già inserito",HttpStatus.NOT_ACCEPTABLE);
            appoggio.setListaItinerarioPDI(listaPdi);
            itinerariRepository.save(appoggio);
            return new ResponseEntity<>("Itinerario è stato aggiornato con successo", HttpStatus.OK);
        } else throw new ItinerariNotFoundEccezione();
    }

    @PostMapping(value = "/AggiungiFoto/{nomeItinerario}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> AggiungiFoto(@RequestParam("file") MultipartFile file,
                                               @PathVariable("nomeItinerario") String nomeItinerario) throws IOException {
        if (itinerariRepository.existsById(nomeItinerario)) {
            Itinerario appoggio = itinerariRepository.findById(nomeItinerario).get();
            List listaFoto = appoggio.getListaFoto();
            if (!appoggio.getListaFoto().contains(file)) {
                File file1 = new File("/home/margherita/Desktop/"+file.getOriginalFilename());
                file1.createNewFile();
                FileOutputStream fileOut = new FileOutputStream(file1);
                fileOut.write(file.getBytes());
                fileOut.close();
                Foto foto = new Foto(file1);
                listaFoto.add(foto);
            } else return new ResponseEntity<>("Foto già inserita",HttpStatus.NOT_ACCEPTABLE);
            appoggio.setListaFoto(listaFoto);
            itinerariRepository.save(appoggio);
            return new ResponseEntity<>("Itinerario Aggiornato", HttpStatus.OK);
        } else throw new ItinerariNotFoundEccezione();
    }
}
