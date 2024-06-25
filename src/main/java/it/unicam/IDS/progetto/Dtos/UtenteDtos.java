package it.unicam.IDS.progetto.Dtos;

import jakarta.validation.constraints.NotEmpty;

public class UtenteDtos {

    @NotEmpty
    private String password;

    @NotEmpty
    private String username;

    public UtenteDtos() {}

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }


}
