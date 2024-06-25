package it.unicam.IDS.progetto.Dtos;

import jakarta.persistence.Id;

public class ItinerarioDtos {

    @Id
    private String nomeItinerario;

    public ItinerarioDtos() {}

    public String getNomeItinerario() {
        return nomeItinerario;
    }
}
