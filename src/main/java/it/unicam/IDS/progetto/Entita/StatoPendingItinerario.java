package it.unicam.IDS.progetto.Entita;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class StatoPendingItinerario extends StatoPending{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String nomeItinerario;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ArrayList<PuntoInteresse> listaItinerarioPDI = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ArrayList<Foto> listaFoto = new ArrayList<>();

    public StatoPendingItinerario(String nomeItinerario) {
        this.nomeItinerario = nomeItinerario;
        this.listaItinerarioPDI = null;
        this.listaFoto = null;
    }

    public StatoPendingItinerario(String nomeItinerario, ArrayList<PuntoInteresse> listaItinerarioPDI,
                                  ArrayList<Foto> listaFoto) {
        this.nomeItinerario = nomeItinerario;
        this.listaItinerarioPDI = listaItinerarioPDI;
        this.listaFoto = listaFoto;
    }

    public int getId() {
        return id;
    }

    public String getNomeItinerario() {
        return nomeItinerario;
    }

    public void setNomeItinerario(String nomeItinerario) {
        this.nomeItinerario = nomeItinerario;
    }

    public void setId(int id) {
        this.id = id;
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
        return "StatoPendingItinerario{" +
                "id=" + id +
                ", nomeItinerario='" + nomeItinerario + '\'' +
                ", listaItinerarioPDI=" + listaItinerarioPDI +
                ", listaFoto=" + listaFoto +
                '}';
    }
}
