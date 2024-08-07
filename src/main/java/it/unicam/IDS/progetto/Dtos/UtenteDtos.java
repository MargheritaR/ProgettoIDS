package it.unicam.IDS.progetto.Dtos;

import jakarta.validation.constraints.NotNull;

public class UtenteDtos {

    @NotNull
    private String password;

    @NotNull
    private String username;

    private String nome;

    private String cognome;

    public UtenteDtos() {}

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }
}
