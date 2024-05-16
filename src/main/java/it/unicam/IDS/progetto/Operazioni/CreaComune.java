package it.unicam.IDS.progetto.Operazioni;

import it.unicam.IDS.progetto.Entita.Comune;

import java.util.ArrayList;

public class CreaComune {

    public void AggiungiComune(ArrayList<Comune> listaComuni, Comune  comune ){
        if (comune.equals(null))
            System.out.println("Il comune non può essere nullo");
        //TODO creare la eccezione e lanciarla
        if (listaComuni.contains(comune))
            System.out.println("Il comune esiste già");
        //TODO creare la eccezione e lanciarla

        listaComuni.add(comune);
    }
}
