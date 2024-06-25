package it.unicam.IDS.progetto.Entita;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
public class PuntoInteresse implements Component{

    @Id
    @NotEmpty
    private String nomePDI;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinate_id", referencedColumnName = "IdNomeCoordinate")
    private Coordinate coordinate;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Contenuti> listaContenuti;

    // costruttore
    public PuntoInteresse(String nomePDI,double asseX, double asseY) {
        this.nomePDI = nomePDI;
        this.coordinate = new Coordinate(nomePDI,asseX, asseY);
    }

    public PuntoInteresse() {}

    @Override
    public String toString() {
        return "Punti di Interesse" + '\n' +
                "nomePDI: " + nomePDI + '\n' +
                "coordinate: " + coordinate + '\n';
    }

    public String getNomePDI() {
        return nomePDI;
    }

    public List<Contenuti> getListaContenuti() {
        return listaContenuti;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(int asseX, int asseY) {
        this.coordinate = new Coordinate(nomePDI,asseX,asseY);
    }

    public void setNomePDI(String nomePDI) {
        this.nomePDI = nomePDI;
    }

    public void setListaContenuti(List<Contenuti> listaContenuti) {
        this.listaContenuti = listaContenuti;
    }
}
