package it.unicam.IDS.progetto.Operazioni;

import java.util.ArrayList;

public class InserimentoPreferiti<T> {

    public void AggiungiPreferiti(ArrayList<T> listaPreferiti, T preferiti){
        if (preferiti == null)
            System.out.println("errore");
        if (listaPreferiti.contains(preferiti))
            System.out.println("errore");
        listaPreferiti.add(preferiti);
        System.out.println("L'informazione è stata aggiunta ai preferiti");
    }
}
