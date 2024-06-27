package it.unicam.IDS.progetto.Dtos;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public class MessaggioDtos {

    @NotNull
    private String mittente;

    @NotNull
    private String destinatario;

    @NotNull
    private String titolo;

    @NotNull
    private String intestazione;

    public MessaggioDtos(String mittente, String destinatario, String titolo, String intestazione) {
        this.mittente = mittente;
        this.destinatario = destinatario;
        this.titolo = titolo;
        this.intestazione = intestazione;
    }

    public String getMittente() {
        return mittente;
    }

    public void setMittente(String mittente) {
        this.mittente = mittente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getIntestazione() {
        return intestazione;
    }

    public void setIntestazione(String intestazione) {
        this.intestazione = intestazione;
    }
}
