package it.unicam.IDS.progetto.Operazioni;

import it.unicam.IDS.progetto.Entita.PuntoInteresse;

import java.util.ArrayList;

public class InserimentoPDI {

    public void AggiungiPDI(ArrayList<PuntoInteresse> listaPDI, PuntoInteresse puntoDI){
        if (puntoDI == null)
            System.out.println("Il punto di interesse non può essere nullo");
        if (listaPDI.contains(puntoDI))
            System.out.println("Il punto di interesse esiste già");

        listaPDI.add(puntoDI);
    }
    
    // Metodo che invia il punto di interesse verso lo stato di Pending
    public void InviaPending() {
    }

}
