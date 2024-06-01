package it.unicam.IDS.progetto.Entita;

public class Messaggio {
    private String mittente;

    private String destinatario;

    private String titolo;

    private String intestazione;

    public Messaggio(String mittente, String destinatario, String titolo, String intestazione) {
        this.mittente = mittente;
        this.destinatario = destinatario;
        this.titolo = titolo;
        this.intestazione = intestazione;
    }

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
