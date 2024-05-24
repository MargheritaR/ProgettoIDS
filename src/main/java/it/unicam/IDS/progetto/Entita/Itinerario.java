package it.unicam.IDS.progetto.Entita;

import java.util.List;

public class Itinerario {

    private String nomeItinerario;

    private List<PuntoInteresse> listaItinerarioPDI;

    public Itinerario(String nomeItinerario, List<PuntoInteresse> listaItinerarioPDI) {
        this.nomeItinerario = nomeItinerario;
        this.listaItinerarioPDI = listaItinerarioPDI;
    }

    public String getNomeItinerario() {
        return nomeItinerario;
    }

    public void setNomeItinerario(String nomeItinerario) {
        this.nomeItinerario = nomeItinerario;
    }

    public List<PuntoInteresse> getListaItinerarioPDI() {
        return listaItinerarioPDI;
    }

    public void setListaItinerarioPDI(List<PuntoInteresse> listaItinerarioPDI) {
        this.listaItinerarioPDI = listaItinerarioPDI;
    }

    @Override
    public String toString() {
        return "Itinerario" + '\n' +
                "nomeItinerario: " + nomeItinerario + '\n' +
                "listaItinerarioPDI: " + '\n' + listaItinerarioPDI + '\n';
    }
}
