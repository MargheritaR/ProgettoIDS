package it.unicam.IDS.progetto.Entita;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
public class Itinerario implements Component{

    @Id
    private String nomeItinerario;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PuntoInteresse> listaItinerarioPDI;

    @NotEmpty
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Foto> listaFoto;

    public Itinerario(String nomeItinerario) {
        this.nomeItinerario = nomeItinerario;
        this.listaItinerarioPDI = null;
        this.listaFoto = null;
    }

    public Itinerario() {}

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

    public List<Foto> getListaFoto() {
        return listaFoto;
    }

    public void setListaFoto(List<Foto> listaFoto) {
        this.listaFoto = listaFoto;
    }

    @Override
    public String toString() {
        return "Itinerario" + '\n' +
                "nomeItinerario: " + nomeItinerario + '\n' +
                "listaItinerarioPDI: " + '\n' + listaItinerarioPDI + '\n'+
                "listaFoto: " + '\n' + listaFoto + '\n';
    }

}
