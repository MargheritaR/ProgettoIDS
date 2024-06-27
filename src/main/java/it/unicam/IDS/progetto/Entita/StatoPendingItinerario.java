package it.unicam.IDS.progetto.Entita;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
public class StatoPendingItinerario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String nomeItinerario;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PuntoInteresse> listaItinerarioPDI;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Foto> listaFoto;

    public StatoPendingItinerario(String nomeItinerario) {
        this.nomeItinerario = nomeItinerario;
        this.listaItinerarioPDI = null;
        this.listaFoto = null;
    }

    public StatoPendingItinerario(String nomeItinerario, List<PuntoInteresse> listaItinerarioPDI, List<Foto> listaFoto) {
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

    public void setNomeItinerario(@NotNull String nomeItinerario) {
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
}
