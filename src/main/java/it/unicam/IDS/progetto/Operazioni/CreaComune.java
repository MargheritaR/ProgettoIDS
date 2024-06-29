package it.unicam.IDS.progetto.Operazioni;

import it.unicam.IDS.progetto.Entita.Comune;

import java.util.ArrayList;

public class CreaComune {

    public void AggiungiComune(ArrayList<Comune> listaComuni, Comune  comune ){
        if (comune == null)
            System.out.println("Il comune non può essere nullo");
        if (listaComuni.contains(comune))
            System.out.println("Il comune esiste già");

        listaComuni.add(comune);
    }
}
