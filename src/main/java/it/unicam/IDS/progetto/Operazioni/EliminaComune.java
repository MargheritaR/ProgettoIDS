package it.unicam.IDS.progetto.Operazioni;

import it.unicam.IDS.progetto.Entita.Comune;

import java.util.ArrayList;

public class EliminaComune {

    public void RimuoviComune(ArrayList<Comune> listaComuni, Comune comune) {
        if (comune == null)
            System.out.println("Il nome del comune non può essere nullo");
        //TODO creare la eccezione e lanciarla
        if (listaComuni.contains(comune))
            System.out.println("Il comune esiste già");
        //TODO creare la eccezione e lanciarla

        listaComuni.remove(comune);
    }
}
