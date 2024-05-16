package it.unicam.IDS.progetto;

import it.unicam.IDS.progetto.Entita.Comune;
import it.unicam.IDS.progetto.Entita.ContTesto;
import it.unicam.IDS.progetto.Entita.Contenuti;
import it.unicam.IDS.progetto.Entita.PuntoInteresse;
import it.unicam.IDS.progetto.Operazioni.AggiuntaContenuti;
import it.unicam.IDS.progetto.Operazioni.ApprovazioneStatoPending;
import it.unicam.IDS.progetto.Operazioni.EliminazionePDI;
import it.unicam.IDS.progetto.Operazioni.InserimentoPDI;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // Creazione lista per memorizzare i punti di interesse
        ArrayList<PuntoInteresse> listaPDI = new ArrayList<>();
        //Creazione lista per memorizzare i contenuti
        ArrayList<Contenuti> listaContenuti = new ArrayList<>();
        ArrayList<PuntoInteresse> statoPending = new ArrayList<>();

        //Creazione lista per comuni
        ArrayList<Comune> listaComuni = new ArrayList<>();

        // Creazione lista di punti di interesse
        InserimentoPDI a = new InserimentoPDI();
        EliminazionePDI e = new EliminazionePDI();

        //Creazione lista di contenuti
        AggiuntaContenuti c = new AggiuntaContenuti();

        //Aggiunta Comune
        Comune x = new Comune("Camerino", 10,15);
        System.out.println("la lista dei comuni è");
        System.out.println(listaComuni);



        //Creazione lista di stato di approvazione di punti di punti interesse e contenuti
        ApprovazioneStatoPending approvazione = new ApprovazioneStatoPending();

        // Aggiunta di un punto di interesse
        PuntoInteresse pI1 = new PuntoInteresse("Daniele",2, 4);
        a.AggiungiPDI(listaPDI,pI1);
        PuntoInteresse pI2 = new PuntoInteresse("Margherita",5,6);
        a.AggiungiPDI(listaPDI,pI2);

        //Creazione contenuto
        ContTesto cont = new ContTesto("1","storia",  10, "txt");
        c.AddContenuti(listaContenuti,cont);
        System.out.println("La lista dei contenuti è");
        System.out.println(listaContenuti);


        statoPending.add(pI1);
        statoPending.add(pI2);

//        System.out.println("Tutti i punti di interesse");
//        for (PuntoInteresse appoggio : listaPDI) {
//            System.out.println(appoggio);
//        }

        // Rimozione di un punto di interesse
        /*e.RimuoviPDI(listaPDI,pI1);

        System.out.println("I punti interessi aggiornati");
        for (PuntoInteresse appoggio : listaPDI) {
            System.out.println(appoggio);
        }
         */
        approvazione.Approvazione(statoPending,listaPDI);
    }
}