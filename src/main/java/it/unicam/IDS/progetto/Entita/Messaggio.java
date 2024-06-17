package it.unicam.IDS.progetto.Entita;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Messaggio {

    @Id
    private String id;

    private String mittente;

    private String destinatario;

    private String titolo;

    private String intestazione;

    public Messaggio() {}

    public Messaggio(String id,String mittente, String destinatario, String titolo, String intestazione) {
        this.id = id;
        this.mittente = mittente;
        this.destinatario = destinatario;
        this.titolo = titolo;
        this.intestazione = intestazione;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getMittente() {
        return mittente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getIntestazione() {
        return intestazione;
    }

    public void setMittente(String mittente) {
        this.mittente = mittente;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setIntestazione(String intestazione) {
        this.intestazione = intestazione;
    }
}
