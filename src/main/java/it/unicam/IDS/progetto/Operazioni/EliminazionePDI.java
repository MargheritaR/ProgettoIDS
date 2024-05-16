package it.unicam.IDS.progetto.Operazioni;

import it.unicam.IDS.progetto.Entita.PuntoInteresse;

import java.util.ArrayList;

public class EliminazionePDI {

    public void RimuoviPDI(ArrayList<PuntoInteresse> listaPDI, PuntoInteresse puntoDI) {
        //TODO creare la eccezione e lanciarla
        if (puntoDI.equals(null))
            System.out.println("Il punto di interesse non può essere nullo");
        //TODO creare la eccezione e lanciarla
        if (listaPDI.contains(puntoDI) != true)
            System.out.println("Il punto di interesse non esiste");

        listaPDI.remove(puntoDI);
    }
}
