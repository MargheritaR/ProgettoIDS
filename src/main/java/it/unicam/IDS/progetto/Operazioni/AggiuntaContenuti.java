package it.unicam.IDS.progetto.Operazioni;

import it.unicam.IDS.progetto.Entita.Contenuti;
import it.unicam.IDS.progetto.Entita.PuntoInteresse;

import java.util.ArrayList;

public class AggiuntaContenuti {

    public void AddContenuti (ArrayList<Contenuti> listaContenuti, Contenuti contenuto){
        if (contenuto.equals(null))
            System.out.println("Il contenuto  non può essere nullo");
        //TODO creare la eccezione e lanciarla
        if (listaContenuti.contains(contenuto))
            System.out.println("Il contenuto esiste già");
        //TODO creare la eccezione e lanciarla

        listaContenuti.add(contenuto);
    }
}
