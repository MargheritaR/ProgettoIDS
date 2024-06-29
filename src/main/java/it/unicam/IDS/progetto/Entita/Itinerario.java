package it.unicam.IDS.progetto.Entita;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
public class Itinerario {

    @Id
    private String nomeItinerario;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PuntoInteresse> listaItinerarioPDI;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Foto> listaFoto;

    public Itinerario(String nomeItinerario) {
        this.nomeItinerario = nomeItinerario;
        this.listaItinerarioPDI = null;
        this.listaFoto = null;
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
                "listaItinerarioPDI: " + '\n' + listaItinerarioPDI + '\n' +
                "listaFoto: " + '\n' + listaFoto + '\n';
    }

}
