package it.unicam.IDS.progetto.Operazioni;

import it.unicam.IDS.progetto.Entita.Itinerario;
import it.unicam.IDS.progetto.Entita.PuntoInteresse;

import java.util.ArrayList;

public class EliminaItinerari {

    public void RimuoviItinerari(ArrayList<Itinerario> listaItinerari, Itinerario itinerario) {
        //TODO creare la eccezione e lanciarla
        if (itinerario.getNomeItinerario().equals(null))
            System.out.println("Il punto di interesse non può essere nullo");
        //TODO creare la eccezione e lanciarla
        if (listaItinerari.contains(itinerario) != true)
            System.out.println("Il punto di interesse non esiste");

        listaItinerari.remove(itinerario);
    }
}
