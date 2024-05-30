package it.unicam.IDS.progetto.Operazioni;

import java.util.ArrayList;

public class InserimentoStatoPending<T> {

    public void AggiungiStatoPending(ArrayList<T> listaStatoPending,T informazione) {
        listaStatoPending.add(informazione);
    }
}
