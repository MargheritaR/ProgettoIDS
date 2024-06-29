package it.unicam.IDS.progetto.Operazioni;

import it.unicam.IDS.progetto.Entita.Preferiti;

import java.util.ArrayList;

public class EliminaPreferiti<T> {

    public void RimuoviPreferiti(ArrayList<T> listaPreferiti, T preferiti) {
        if (preferiti == null)
            System.out.println("Il preferito scelto non può essere nullo");
        if (!listaPreferiti.contains(preferiti))
            System.out.println("Il preferito scelto è stato già aggiunto");

        listaPreferiti.remove(preferiti);
        System.out.println("Il preferito scelto è stato eliminato");
    }
}
