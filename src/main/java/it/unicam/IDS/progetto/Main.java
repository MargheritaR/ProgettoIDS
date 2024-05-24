package it.unicam.IDS.progetto;

import it.unicam.IDS.progetto.Entita.*;
import it.unicam.IDS.progetto.Operazioni.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Creazione lista per le entitò
        ArrayList<PuntoInteresse> listaPDI = new ArrayList<>();
        ArrayList<Contenuti> listaContenuti = new ArrayList<>();
        ArrayList<PuntoInteresse> statoPending = new ArrayList<>();
        ArrayList<Comune> listaComuni = new ArrayList<>();
        ArrayList<Itinerario> listaItinerario = new ArrayList<>();

        // Creazione azioni
        InserimentoPDI inserimentoPDI = new InserimentoPDI();
        EliminazionePDI ePDI = new EliminazionePDI();
        AggiuntaContenuti aggiuntaContenuti = new AggiuntaContenuti();
        EliminaContenuti eliminaContenuti = new EliminaContenuti();
        CreaItinerario creaItinerario = new CreaItinerario();
        EliminaItinerari eliminaItinerari = new EliminaItinerari();
        ModificaItinerario modificaItinerario = new ModificaItinerario();

        //Aggiunta Comune
        Comune x = new Comune("Camerino", 10,15);
//        System.out.println("la lista dei comuni è: ");
//        System.out.println(listaComuni);

        // Aggiunta di un punto di interesse
        PuntoInteresse pI1 = new PuntoInteresse("Daniele",2, 4);
        inserimentoPDI.AggiungiPDI(listaPDI,pI1);
        PuntoInteresse pI2 = new PuntoInteresse("Margherita",5,6);
        inserimentoPDI.AggiungiPDI(listaPDI,pI2);
        PuntoInteresse pI3 = new PuntoInteresse("Domenico",10,8);
        inserimentoPDI.AggiungiPDI(listaPDI,pI3);
        PuntoInteresse pI4 = new PuntoInteresse("Nicolo",7,3);
        inserimentoPDI.AggiungiPDI(listaPDI,pI4);

        //Creazione contenuto
        ContTesto cont = new ContTesto("1","storia",  10, "txt");
        ContTesto cont2 = new ContTesto("13","geografia",  32, "txt");
        aggiuntaContenuti.AddContenuti(listaContenuti,cont);
        aggiuntaContenuti.AddContenuti(listaContenuti,cont2);
//        System.out.println("La lista dei contenuti è: ");
//        System.out.println(listaContenuti);

        // Creazione di intinerario
        List<PuntoInteresse> listaItinerariPDI1 = new ArrayList<>();
        listaItinerariPDI1.add(pI1);
        listaItinerariPDI1.add(pI2);
        List<PuntoInteresse> listaItinerariPDI2 = new ArrayList<>();
        listaItinerariPDI2.add(pI3);
        listaItinerariPDI2.add(pI4);
        Itinerario it1 = new Itinerario("Gruppo 1", listaItinerariPDI1);
        creaItinerario.AggiungiItinerario(listaItinerario,it1);
        Itinerario it2 = new Itinerario("Gruppo 2", listaItinerariPDI2);
        creaItinerario.AggiungiItinerario(listaItinerario,it2);
//        System.out.println("La lista degli itinerari è");
//        System.out.println(listaItinerario);

        // Modifica intinerario
        modificaItinerario.EditItinerario(listaItinerario,it1,listaPDI);

//        for (PuntoInteresse appoggio : listaPDI) {
//            System.out.println(appoggio);
//        }

        /*for (Contenuti appoggio : listaContenuti) {
            System.out.println(appoggio);
        }*/

        // Rimozione di un punto di interesse
        /*ePDI.RimuoviPDI(listaPDI,pI1);

        System.out.println("I punti interessi aggiornati");
        for (PuntoInteresse appoggio : listaPDI) {
            System.out.println(appoggio);
        }
         */

        // Rimozione di un contenuto
        /*eliminaContenuti.RimuoviContenuto(listaContenuti,cont);

        System.out.println("I contenuti aggiornati");
        for (Contenuti appoggio : listaContenuti) {
            System.out.println(appoggio);
        }*/

        /*
        // Rimozione di un punto di interesse
        eliminaItinerari.RimuoviItinerari(listaItinerario,it1);

        System.out.println("Gli Itinerari aggiornati ");
        for (Itinerario appoggio : listaItinerario) {
            System.out.println(appoggio);
        }
         */

        /*
        // Aggiunta di entita nello stato di Pending
        ApprovazioneStatoPending approvazione = new ApprovazioneStatoPending();
        statoPending.add(pI1);
        statoPending.add(pI2);
        approvazione.Approvazione(statoPending,listaPDI);
         */
    }
}