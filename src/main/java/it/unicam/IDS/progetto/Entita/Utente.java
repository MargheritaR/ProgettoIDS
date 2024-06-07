package it.unicam.IDS.progetto.Entita;

import java.util.ArrayList;

public class Utente {

    private int id;

    private String email;

    private String password;

    private String nome;

    private String cognome;

    private int ruolo;

    public Utente(int id, String email, String password, String nome, String cognome) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.ruolo = 4;
        this.listaMessaggiNonLetti = null;
        this.listaMessaggiLetti = null;
    }

    private ArrayList<Messaggio> listaMessaggiNonLetti = new ArrayList<>();

    private ArrayList<Messaggio> listaMessaggiLetti = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getRuolo() {
        return ruolo;
    }

    public void setRuolo(int ruolo) {
        this.ruolo = ruolo;
    }

    public ArrayList<Messaggio> getListaMessaggiNonLetti() {
        return listaMessaggiNonLetti;
    }

    public void setListaMessaggiNonLetti(ArrayList<Messaggio> listaMessaggiNonLetti) {
        this.listaMessaggiNonLetti = listaMessaggiNonLetti;
    }

    public ArrayList<Messaggio> getListaMessaggiLetti() {
        return listaMessaggiLetti;
    }

    public void setListaMessaggiLetti(ArrayList<Messaggio> listaMessaggiLetti) {
        this.listaMessaggiLetti = listaMessaggiLetti;
    }

    @Override
    public String toString() {
        return "Utente: " + '\n' +
                "id: " + id + '\n' +
                "email: " + email + '\n' +
                "password: " + password + '\n' +
                "nome: " + nome + '\n' +
                "cognome: " + cognome + '\n' +
                "ruolo: " + ruolo + '\n' +
                "listaMessaggiNonLetti: " + listaMessaggiNonLetti + '\n' +
                "listaMessaggiLetti: " + listaMessaggiLetti + '\n';
    }
}
