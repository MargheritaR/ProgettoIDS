package it.unicam.IDS.progetto.Dtos;

import jakarta.validation.constraints.NotNull;

public class UtenteDtos {

    @NotNull
    private String password;

    @NotNull
    private String username;

    public UtenteDtos() {}

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }


}
