package it.unicam.IDS.progetto.Entita;

import java.util.ArrayList;

public abstract class Utente {

    private int id;

    private String username;

    private String password;

    private String nome;

    private String cognome;

    private ArrayList<Messaggio> listaMessaggiNonLetti = new ArrayList<>();

    private ArrayList<Messaggio> listaMessaggiLetti = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
