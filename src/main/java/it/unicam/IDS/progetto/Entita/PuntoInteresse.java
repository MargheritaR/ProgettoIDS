package it.unicam.IDS.progetto.Entita;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
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
    private ArrayList<Contenuti> listaContenuti = new ArrayList<>();

    public PuntoInteresse(String nomePDI,double latitudine, double longitudine) {
        this.nomePDI = nomePDI;
        this.coordinate = new Coordinate(nomePDI,latitudine, longitudine);
        //this.listaContenuti = null;
    }

    /*
    public PuntoInteresse(String nomePDI, double asseX, double asseY, List<Contenuti> listaContenuti) {
        this.nomePDI = nomePDI;
        this.coordinate = new Coordinate(nomePDI,asseX, asseY);
        this.listaContenuti = listaContenuti;
    }
*/

    @Override
    public String toString() {
        return "PuntoInteresse{" +
                "nomePDI='" + nomePDI + '\n' +
                ", coordinate=" + coordinate + '\n' +
                ", listaContenuti=" + listaContenuti +
                '}';
    }

    public String getNomePDI() {
        return nomePDI;
    }

    public void setNomePDI(String nomePDI) {
        this.nomePDI = nomePDI;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public ArrayList<Contenuti> getListaContenuti() {
        return listaContenuti;
    }

    public void setListaContenuti(ArrayList<Contenuti> listaContenuti) {
        this.listaContenuti = listaContenuti;
    }
}
