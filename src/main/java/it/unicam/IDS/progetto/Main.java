package it.unicam.IDS.progetto;

import it.unicam.IDS.progetto.Controller.ControllerComune;
import it.unicam.IDS.progetto.Controller.ControllerUtenti;
import it.unicam.IDS.progetto.Entita.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@SpringBootApplication
@RestController
public class Main {

    public static void main(String[] args) {
        /*Utente u = new Utente();
        ControllerUtenti utenti = new ControllerUtenti(u);
        Utente utente1 = new Utente("margherita@gmail.com","Valida1","Margherita","Ramazzotti");
        Utente utente2 = new Utente("daniele@gmail.com","Valida2","Daniele","Rossi");
        utenti.registrazione(utente1);
        utenti.registrazione(utente2);
        //utenti.assegnamentoRuoli("Margherita","ROLE_CONTRIBUTORI");
        //utenti.assegnamentoRuoli("Daniele","ROLE_CONTRIBUTORI");

        Comune c = new Comune(u.getListaUtenti());
        ControllerComune comune = new ControllerComune(c);
        // /home/daniele-rossi/Scrivania/provaFile/prova1  /home/daniele-rossi/Scrivania/provaFile/prova2
        // /home/margherita/Desktop/ProvaFile/contenuto1   /home/margherita/Desktop/ProvaFile/contenuto2
        File file1 = new File("/home/margherita/Desktop/ProvaFile/contenuto1");
        File file2 = new File("/home/margherita/Desktop/ProvaFile/contenuto2");
        Contenuti contenuto = new Contenuti("Contenuto1", file1);
        Contenuti contenuto2 = new Contenuti("Contenuto2", file2);
        PuntoInteresse pdi = new PuntoInteresse("Chiesa di San Venanzio", 43.1, 34.2);
        PuntoInteresse pdi2 = new PuntoInteresse("Sotto Corte", 44.1, 35.2);
        Itinerario it1 = new Itinerario("Festa della Spada");
        Itinerario it2 = new Itinerario("Maratona");
        ContestDiContribuzione cont = new ContestDiContribuzione("Progetto", "Piattaforma", "valorizzazione comune",
                "12/03/2024", true, "11/03/2024", "16/03/2024");
        ContestDiContribuzione cont2 = new ContestDiContribuzione("Estate", "Mare", "vacanze estive",
                "10/07/2024", true, "25/08/2024", "28/08/2024");
        Foto foto = new Foto(new File("/home/margherita/Desktop/ProvaFile/2480X1000_camerino_destination.jpg"));
        Messaggio messaggio1 = new Messaggio("Daniele","Margherita","Prova","prova");

/*
        //ROLE_CONTRIBUTORI   ROLE_CONTRIBUTORIAUTORIZZATI
        comune.modificaComune("nome","prova");
        comune.modificaComune("cap","prova");
        comune.inserimentoPDI(pdi,"ROLE_CONTRIBUTORI");
        comune.inserimentoPDI(pdi,"ROLE_CONTRIBUTORIAUTORIZZATI");
        comune.inserimentoPDI(pdi2,"ROLE_CONTRIBUTORIAUTORIZZATI");
        comune.eliminaPDI("Sotto Corte");
        comune.addContenuti("Chiesa di San Venanzio", contenuto,"ROLE_CONTRIBUTORIAUTORIZZATI");
        comune.addContenuti("Chiesa di San Venanzio", contenuto2,"ROLE_CONTRIBUTORIAUTORIZZATI");
        comune.addContenuti("Sotto Corte", contenuto2,"ROLE_CONTRIBUTORI");
        comune.approvazioneStatoPendingPDI("Chiesa di San Venanzio","Y");
        comune.approvazioneStatoPendingPDI("Sotto Corte","Y");
        comune.rimuoviContenuti("Chiesa di San Venanzio", "Contenuto2");
        comune.creaItinerario(it1,"ROLE_CONTRIBUTORI");
        comune.creaItinerario(it1,"ROLE_CONTRIBUTORIAUTORIZZATI");
        comune.creaItinerario(it2,"ROLE_CONTRIBUTORIAUTORIZZATI");
        comune.eliminaItinerario("Maratona");
        comune.aggiuntaPdiItinerario("Sotto Corte", "Festa della Spada","ROLE_CONTRIBUTORI");
        comune.aggiuntaPdiItinerario("Sotto Corte", "Festa della Spada","ROLE_CONTRIBUTORIAUTORIZZATI");
        comune.aggiuntaPdiItinerario("Chiesa di San venanzio", "Maratona","ROLE_CONTRIBUTORI");
        comune.aggiuntaPdiItinerario("Chiesa di San venanzio", "Maratona","ROLE_CONTRIBUTORIAUTORIZZATI");
        comune.aggiungiFotoItinerario(foto, "Festa della Spada","ROLE_CONTRIBUTORIAUTORIZZATI");
        comune.aggiungiFotoItinerario(foto, "Maratona","ROLE_CONTRIBUTORI");
        comune.aggiungiFotoItinerario(foto, "Festa della Spada","ROLE_CONTRIBUTORI");
        comune.approvazioneStatoPendingItinerario("Maratona","Y");
        comune.approvazioneStatoPendingItinerario("Festa della Spada","Y");
        comune.rimuoviPdiItinerario("Sotto Corte", "Festa della Spada");
        comune.rimuoviFotoItinerario(0,"Festa della Spada");
        comune.aggiungiPreferitiItinerario("Maratona","Daniele");
        comune.aggiungiPreferitiPDI("Chiesa di San Venanzio","Daniele");
        comune.rimuoviPreferitiPDI("Chiesa di San Venanzio","Daniele");
        comune.rimuoviPreferitiItinerari("Maratona","Daniele");
        comune.creaContestDiContribuzione(cont);
        comune.eliminaContestDiContribuzione("Progetto");
        comune.modificaContestDiContribuzione("Progetto","obiettivo","prova");
        comune.modificaContestDiContribuzione("Progetto","tematica","prova");
        comune.modificaContestDiContribuzione("Progetto","dataInizio","prova");
        comune.proponiContenuti("Progetto",file1);
        comune.proponiContenuti("Progetto",file2);
        comune.validaContenuti("Progetto","contenuto1","Y");
        comune.validaContenuti("Progetto","contenuto2","Y");
        comune.decidiContenutoVincitore("Progetto","contenuto1",messaggio1);
        comune.inviaMessaggi(messaggio1);
        utenti.leggiMessaggi("Prova","Margherita");
*/
        SpringApplication.run(Main.class, args);
    }
}