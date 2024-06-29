package it.unicam.IDS.progetto.Operazioni;

import it.unicam.IDS.progetto.Entita.Itinerario;
import it.unicam.IDS.progetto.Entita.PuntoInteresse;

import java.util.ArrayList;

public class EliminaItinerari {

    public void RimuoviItinerari(ArrayList<Itinerario> listaItinerari, Itinerario itinerario) {
        if (itinerario.getNomeItinerario().isEmpty())
            System.out.println("Il nome dell'itinerario non può essere nullo");
        if (!listaItinerari.contains(itinerario))
            System.out.println("Il nome dell'itinerario non esiste");

        listaItinerari.remove(itinerario);
    }
}
