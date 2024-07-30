package it.unicam.IDS.progetto.Controller;

import it.unicam.IDS.progetto.Entita.Utente;

public class ControllerUtenti {

    private Utente utente;

    public ControllerUtenti(Utente utente) {
        this.utente = utente;
    }

    public void assegnamentoRuoli(String nomeUtente, String ruolo){
        utente.assegnamentoRuoli(nomeUtente,ruolo);
    }

    public void leggiMessaggi(String titoloMessaggio){
        utente.leggiMessaggi(titoloMessaggio);
    }

    public void registrazione(Utente utente){
        utente.registrazione(utente);
    }
}
