package it.unicam.IDS.progetto.Operazioni;

import it.unicam.IDS.progetto.Entita.Itinerario;

import java.util.ArrayList;

public class CreaItinerario {

    public void AggiungiItinerario(ArrayList<Itinerario> listaItinerari, Itinerario itinerario){
        if (itinerario.getNomeItinerario().isEmpty())
            System.out.println("L'Itinerario non può essere nullo");
        if (listaItinerari.contains(itinerario))
            System.out.println("L'Itinerario esiste già");

        listaItinerari.add(itinerario);
    }
}
