package it.unicam.IDS.progetto;

import it.unicam.IDS.progetto.Controller.ControllerComune;
import it.unicam.IDS.progetto.Entita.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@SpringBootApplication
@RestController
public class Main {

    public static void main(String[] args) {
        Comune c = new Comune();
        ControllerComune comune = new ControllerComune(c);
        // /home/daniele-rossi/Scrivania/provaFile/prova1  /home/daniele-rossi/Scrivania/provaFile/prova2
        // /home/margherita/Desktop/ProvaFile/contenuto1   /home/margherita/Desktop/ProvaFile/contenuto2
        Contenuti contenuto = new Contenuti("Contenuto1",new File("/home/margherita/Desktop/ProvaFile/contenuto1"));
        Contenuti contenuto2 = new Contenuti("Contenuto2",new File("/home/margherita/Desktop/ProvaFile/contenuto2"));
        PuntoInteresse pdi = new PuntoInteresse("Chiesa di San Venanzio", 43.1, 34.2);
        PuntoInteresse pdi2 = new PuntoInteresse("Sotto Corte", 44.1, 35.2);
        Itinerario it1 = new Itinerario("Festa della Spada");
        Itinerario it2 = new Itinerario("Maratona");
        ContestDiContribuzione cont = new ContestDiContribuzione("Progetto", "Piattaforma", "valorizzazione comune",
                "12/03/2024", true, "11/03/2024", "16/03/2024");
        ContestDiContribuzione cont2 = new ContestDiContribuzione("Estate", "Mare", "vacanze estive",
                "10/07/2024", true, "25/08/2024", "28/08/2024");

        /*
        comune.inserimentoPDI(pdi);
        comune.inserimentoPDI(pdi2);
        comune.addContenuti("Chiesa di San Venanzio", contenuto);
        comune.addContenutiPending("Sotto Corte", contenuto);
        comune.inserimentoPendingPDI(pdi2);
        comune.creaItinerario(it1);
        comune.creaItinerario(it2);
        comune.creaItinerarioPending(it2);
        comune.aggiuntaPdiItinerario("Sotto Corte", "Festa della Spada");
        comune.aggiuntaPendingPdiItinerario("Chiesa di San Venanzio", "Maratona");
        comune.aggiuntaPdiItinerario("Chiesa di San venanzio", "Maratona");
        comune.creaContestDiContribuzione(cont);
        comune.eliminaContestDiContribuzione(cont);
        comune.eliminaItinerario(it2);
        comune.eliminaPDI(pdi2);
        comune.rimuoviContenuti("Chiesa di San Venanzio", contenuto);
        comune.rimuoviPdiItinerario("Sotto Corte", "Festa della Spada");
        */

        //da provare
        /*
        comune.modificaComune();
        comune.modificaContestDiContribuzione();
        comune.proponiContenuti();
        comune.validaContenuti();
        comune.aggiungiPreferitiItinerario();
        comune.aggiungiPreferitiPDI();
        comune.rimuoviPreferitiPDI();
        comune.rimuoviPreferitiItinerari();
        comune.aggiungiFotoItinerario();
        comune.aggiungiFotoItinerarioPending();
        */

        System.out.print(c);

//        SpringApplication.run(Main.class, args);


    }
}