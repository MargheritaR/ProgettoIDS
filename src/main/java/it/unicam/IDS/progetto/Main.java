package it.unicam.IDS.progetto;

import it.unicam.IDS.progetto.Entita.*;
import it.unicam.IDS.progetto.Operazioni.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Creazione lista per le entità
        ArrayList<PuntoInteresse> listaPDI = new ArrayList<>();
        ArrayList<Contenuti> listaContenuti = new ArrayList<>();
        ArrayList<Comune> listaComuni = new ArrayList<>();
        ArrayList<Itinerario> listaItinerario = new ArrayList<>();
        ArrayList<ContestDiContribuzione> listaContestDiContribuzione = new ArrayList<>();
        ArrayList<StatoPending> listaStatoPending = new ArrayList<>();
        ArrayList<Preferiti> listaPreferiti = new ArrayList<>();
        ArrayList<Foto> listaFoto = new ArrayList<>();
        ArrayList<Contenuti> listaContestContenuti = new ArrayList<>();

        // Creazione azioni
        InserimentoPDI inserimentoPDI = new InserimentoPDI();
        EliminazionePDI ePDI = new EliminazionePDI();
        AggiuntaContenuti aggiuntaContenuti = new AggiuntaContenuti();
        EliminaContenuti eliminaContenuti = new EliminaContenuti();
        ModificaContenuti modificaContenuti = new ModificaContenuti();
        CreaItinerario creaItinerario = new CreaItinerario();
        EliminaItinerari eliminaItinerari = new EliminaItinerari();
        ModificaItinerario modificaItinerario = new ModificaItinerario();
        CreaContestDiContribuzione creaContestDiContribuzione = new CreaContestDiContribuzione();
        EliminaContestDiContribuzione eliminaContestDiContribuzione = new EliminaContestDiContribuzione();
        ApprovazioneStatoPending approvazione = new ApprovazioneStatoPending();
        InserimentoPreferiti inserimentoPreferiti = new InserimentoPreferiti();
        CaricaFoto caricaFoto = new CaricaFoto();
        CreaComune creaComune = new CreaComune();
        ModificaComune modificaComune = new ModificaComune();
        EliminaComune eliminaComune = new EliminaComune();
        ValidazioneContenutiContest validazioneContenutiContest = new ValidazioneContenutiContest();

        //Aggiunta Comune
        Comune x = new Comune("Camerino", 10, 15);
        creaComune.AggiungiComune(listaComuni,x);
        Comune comune = new Comune("Castelraimondo", 12, 17);
        creaComune.AggiungiComune(listaComuni,comune);
        System.out.println("la lista dei comuni è: ");
        System.out.println(listaComuni);

        //Modifica Comune
       // modificaComune.EditComune(listaComuni,comune);
//        System.out.println("La lista dei comuni è:");
//        System.out.println(listaComuni);

        // Aggiunta di un punto di interesse
        PuntoInteresse pI1 = new PuntoInteresse("Daniele", 2, 4);
        inserimentoPDI.AggiungiPDI(listaPDI, pI1);
        PuntoInteresse pI2 = new PuntoInteresse("Margherita", 5, 6);
        inserimentoPDI.AggiungiPDI(listaPDI, pI2);
        PuntoInteresse pI3 = new PuntoInteresse("Domenico", 10, 8);
        inserimentoPDI.AggiungiPDI(listaPDI, pI3);
        PuntoInteresse pI4 = new PuntoInteresse("Nicolo", 7, 3);
        inserimentoPDI.AggiungiPDI(listaPDI, pI4);

        //Creazione contenuto
        ContTesto cont = new ContTesto("1", "storia", 10, "txt");
        ContTesto cont2 = new ContTesto("13", "geografia", 32, "txt");
        aggiuntaContenuti.AddContenuti(listaContenuti, cont);
        aggiuntaContenuti.AddContenuti(listaContenuti, cont2);
//        System.out.println("La lista dei contenuti è: ");
//        System.out.println(listaContenuti);

        // Modifica Contenuto
        ContTesto contM = new ContTesto("3", "matematica", 13, "txt");
        ContTesto contM2 = new ContTesto("23", "geografia", 42, "txt");
//        modificaContenuti.EditContenuti(listaContenuti,cont);
//        System.out.println("La lista dei contenuti è:");
//        System.out.println(listaContenuti);

        // Creazione di intinerario
        List<PuntoInteresse> listaItinerariPDI1 = new ArrayList<>();
        listaItinerariPDI1.add(pI1);
        listaItinerariPDI1.add(pI2);
        List<PuntoInteresse> listaItinerariPDI2 = new ArrayList<>();
        listaItinerariPDI2.add(pI3);
        listaItinerariPDI2.add(pI4);
        Itinerario it1 = new Itinerario("Gruppo 1", listaItinerariPDI1,listaFoto);
        creaItinerario.AggiungiItinerario(listaItinerario, it1);
        Itinerario it2 = new Itinerario("Gruppo 2", listaItinerariPDI2,listaFoto);
        creaItinerario.AggiungiItinerario(listaItinerario, it2);
//        System.out.println("La lista degli itinerari è");
//        System.out.println(listaItinerario);

        // Modifica intinerario
//        modificaItinerario.EditItinerario(it1, listaPDI);
//        System.out.println("La lista degli itinerari è");
//        System.out.println(listaItinerario);

        // Creazione di Contest di Contribuzione
        listaContestContenuti.add(cont);
        listaContestContenuti.add(cont2);
        ContestDiContribuzione contestDiContribuzione1 = new ContestDiContribuzione("springFestival", "bere",
                "informatica", "12/06/2023", 5, "1/03/2023",
                "10/03/2023", "10/06/2023", "20/06/2023", listaContestContenuti);
        creaContestDiContribuzione.AggiungiContest(listaContestDiContribuzione, contestDiContribuzione1);
//        System.out.println("La lista dei contest di contribuzione è");
//        System.out.println(listaContestDiContribuzione);


        // Validazione Contenuti del contest di contribuzione
//        validazioneContenutiContest.ValidaContenutiContest(listaContestDiContribuzione,contestDiContribuzione1);
//        System.out.println("La lista dei contenuti approvati del contest è:");
//        System.out.println(contestDiContribuzione1.getContenutiApprovati());

        // Inserimento dei Preferiti
        // inserimentoPreferiti.AggiungiPreferiti(listaPreferiti,pI4);

        // Caricamento della/e foto
        Foto foto1 = new Foto("12345-fotoprova1","jpeg");
        listaFoto.add(foto1);
        Foto foto2 = new Foto("09876-cnxvbvcnbv","jpeg");
        listaFoto.add(foto2);
//        caricaFoto.InserimentoFoto(listaItinerario,foto1);
//        System.out.println("La lista delle foto di un itinerario è : ");
//        System.out.println(listaItinerario);

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
        }*/

        // Rimozione di un contenuto
        /*eliminaContenuti.RimuoviContenuto(listaContenuti,cont);

        System.out.println("I contenuti aggiornati");
        for (Contenuti appoggio : listaContenuti) {
            System.out.println(appoggio);
        }*/

        // Rimozione di un itinerario
        /*eliminaItinerari.RimuoviItinerari(listaItinerario,it1);

        System.out.println("Gli Itinerari aggiornati ");
        for (Itinerario appoggio : listaItinerario) {
            System.out.println(appoggio);
        }*/

        // Rimozione di un contest di contribuzione
        /*eliminaContestDiContribuzione.RimuoviContest(listaContestDiContribuzione,contestDiContribuzione1);

        System.out.println("I contest di contribuzione aggiornati: ");
        for (ContestDiContribuzione appoggio : listaContestDiContribuzione) {
            System.out.println(appoggio);
        }*/

        // Rimozione di un comune
        eliminaComune.RimuoviComune(listaComuni,x);

        System.out.println("I comuni aggiornati ");
        for (Comune appoggio : listaComuni) {
            System.out.println(appoggio);
        }

        // Inserimento di Stato di Pending
        InserimentoStatoPending inserimentoStatoPending = new InserimentoStatoPending();
        inserimentoStatoPending.AggiungiStatoPending(listaStatoPending,pI1);
        inserimentoStatoPending.AggiungiStatoPending(listaStatoPending,pI2);
        inserimentoStatoPending.AggiungiStatoPending(listaStatoPending,cont);
        inserimentoStatoPending.AggiungiStatoPending(listaStatoPending,cont);
        inserimentoStatoPending.AggiungiStatoPending(listaStatoPending,cont2);
        inserimentoStatoPending.AggiungiStatoPending(listaStatoPending,contestDiContribuzione1);

        // Approvazione di entita nello stato di Pending
//        approvazione.Approvazione(listaStatoPending,listaPDI,pI2);
//        approvazione.Approvazione(listaStatoPending,listaContenuti,cont2);
    }
}