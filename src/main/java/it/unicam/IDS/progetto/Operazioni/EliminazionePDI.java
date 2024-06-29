package it.unicam.IDS.progetto.Operazioni;

import it.unicam.IDS.progetto.Entita.PuntoInteresse;

import java.util.ArrayList;

public class EliminazionePDI {

    public void RimuoviPDI(ArrayList<PuntoInteresse> listaPDI, PuntoInteresse puntoDI) {
        if (puntoDI == null)
            System.out.println("Il punto di interesse non può essere nullo");
        if (!listaPDI.contains(puntoDI))
            System.out.println("Il punto di interesse non esiste");

        listaPDI.remove(puntoDI);
    }

}
