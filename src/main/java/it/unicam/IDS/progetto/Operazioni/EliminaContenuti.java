package it.unicam.IDS.progetto.Operazioni;

import it.unicam.IDS.progetto.Entita.Contenuti;

import java.util.ArrayList;

public class EliminaContenuti {

    public void RimuoviContenuto(ArrayList<Contenuti> listaContenuti, Contenuti contenuto) {
        //TODO creare la eccezione e lanciarla
        if (contenuto == null)
            System.out.println("Il Contenuto non può essere nullo");
        //TODO creare la eccezione e lanciarla
        if (!listaContenuti.contains(contenuto))
            System.out.println("Il Contenuto non esiste");

        listaContenuti.remove(contenuto);
    }

}
