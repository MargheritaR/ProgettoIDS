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

    public void leggiMessaggi(String titoloMessaggio, String nomeUtente){
        utente.leggiMessaggi(titoloMessaggio, nomeUtente);
    }

    public void registrazione(Utente u){
        utente.registrazione(u);
    }
}
