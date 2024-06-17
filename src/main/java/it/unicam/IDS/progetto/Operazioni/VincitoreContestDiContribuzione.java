package it.unicam.IDS.progetto.Operazioni;

import it.unicam.IDS.progetto.Entita.Messaggio;
import it.unicam.IDS.progetto.Entita.Utente;

import java.util.ArrayList;

public class VincitoreContestDiContribuzione {

    public void Vincitore(ArrayList<Utente> listaPartecipanti, Utente utente) {
        if (utente.getId() == 0)
            System.out.println("Il nome dell'utente non può essere nullo");
        //TODO creare la eccezione e lanciarla
        if (listaPartecipanti.contains(utente))
            System.out.println("L'utente esiste già");
        //TODO creare la eccezione e lanciarla
        String vincitore = ScegliVincitore(listaPartecipanti,utente);
        String intestazione ="Complimenti!!! Hai vinto il contest!!";
        Messaggio messaggio = new Messaggio("02","Daniele", "Salvatore","Convalida",intestazione);
        //TODO implementare messaggio

    }
    private String CercaV(ArrayList<Utente> listaPartecipanti, Utente utente) {
        for (int i = 0; i < listaPartecipanti.size(); i++)
            if (listaPartecipanti.get(i).getId() == utente.getId())
                return utente.getNome();
        return null;
    }
    private String ScegliVincitore(ArrayList<Utente> listaPartecipanti, Utente utente){
        String vincitore = CercaV(listaPartecipanti,utente);
        if (vincitore.isEmpty())
            System.out.println("eccezione");
        return vincitore;
    }
}
