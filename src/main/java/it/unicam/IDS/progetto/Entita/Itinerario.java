package it.unicam.IDS.progetto.Entita;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Itinerario extends StatoPending {

    @Id
    private String nomeItinerario;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ArrayList<PuntoInteresse> listaItinerarioPDI = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ArrayList<Foto> listaFoto = new ArrayList<>();

    public Itinerario(String nomeItinerario) {
        this.nomeItinerario = nomeItinerario;
        //this.listaItinerarioPDI = null;
        //this.listaFoto = null;
    }

    public Itinerario(String nomeItinerario, ArrayList<PuntoInteresse> listaItinerarioPDI, ArrayList<Foto> listaFoto) {
        this.nomeItinerario = nomeItinerario;
        this.listaItinerarioPDI = listaItinerarioPDI;
        this.listaFoto = listaFoto;
    }


    public String getNomeItinerario() {
        return nomeItinerario;
    }

    public void setNomeItinerario(String nomeItinerario) {
        this.nomeItinerario = nomeItinerario;
    }

    public ArrayList<PuntoInteresse> getListaItinerarioPDI() {
        return listaItinerarioPDI;
    }

    public void setListaItinerarioPDI(ArrayList<PuntoInteresse> listaItinerarioPDI) {
        this.listaItinerarioPDI = listaItinerarioPDI;
    }

    public ArrayList<Foto> getListaFoto() {
        return listaFoto;
    }

    public void setListaFoto(ArrayList<Foto> listaFoto) {
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
