package it.unicam.IDS.progetto;

import it.unicam.IDS.progetto.Entita.PuntoInteresse;
import it.unicam.IDS.progetto.Operazioni.ApprovazioneStatoPending;
import it.unicam.IDS.progetto.Operazioni.EliminazionePDI;
import it.unicam.IDS.progetto.Operazioni.InserimentoPDI;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // Creazione lista per memorizzare i punti di interesse
        ArrayList<PuntoInteresse> listaPDI = new ArrayList<>();
        ArrayList<PuntoInteresse> statoPending = new ArrayList<>();

        // Creazione lista di punti di interesse
        InserimentoPDI a = new InserimentoPDI();
        EliminazionePDI e = new EliminazionePDI();
        ApprovazioneStatoPending approvazione = new ApprovazioneStatoPending();

        // Aggiunta di un punto di interesse
        PuntoInteresse pI1 = new PuntoInteresse("Daniele",2, 4);
        a.AggiungiPDI(listaPDI,pI1);
        PuntoInteresse pI2 = new PuntoInteresse("Margherita",5,6);
        a.AggiungiPDI(listaPDI,pI2);

        statoPending.add(pI1);
        statoPending.add(pI2);

        for (PuntoInteresse appoggio : listaPDI) {
            System.out.println(appoggio);
        }

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