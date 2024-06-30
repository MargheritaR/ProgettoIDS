package it.unicam.IDS.progetto.Entita;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
public class PuntoInteresse extends StatoPending {

    @Id
    private String nomePDI;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nomePDI", referencedColumnName = "coordinate_id")
    private Coordinate coordinate;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Contenuti> listaContenuti;

    public PuntoInteresse(String nomePDI,double asseX, double asseY) {
        this.nomePDI = nomePDI;
        this.coordinate = new Coordinate(nomePDI,asseX, asseY);
        this.listaContenuti = null;
    }

    public PuntoInteresse(String nomePDI, double asseX, double asseY, List<Contenuti> listaContenuti) {
        this.nomePDI = nomePDI;
        this.coordinate = new Coordinate(nomePDI,asseX, asseY);
        this.listaContenuti = listaContenuti;
    }

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

    public void setCoordinate(double asseX, double asseY) {
        this.coordinate = new Coordinate(nomePDI,asseX,asseY);
    }

    public void setNomePDI(String nomePDI) {
        this.nomePDI = nomePDI;
    }

    public void setListaContenuti(List<Contenuti> listaContenuti) {
        this.listaContenuti = listaContenuti;
    }

}
