package it.unicam.IDS.progetto.Entita;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Entity
@NoArgsConstructor
public class StatoPendingPuntoInteresse  extends StatoPending{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNome;

    @NotNull
    private String nomePDI;

    @NotNull
    private double latitudine;

    @NotNull
    private double longitudine;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private ArrayList<Contenuti> listaContenuti;

    public StatoPendingPuntoInteresse(String nomePDI, double latitudine, double longitudine) {
        this.nomePDI = nomePDI;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.listaContenuti = null;
    }

    public StatoPendingPuntoInteresse(String nomePDI, double latitudine, double longitudine, ArrayList<Contenuti> listaContenuti) {
        this.nomePDI = nomePDI;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.listaContenuti = listaContenuti;
    }

    public int getIdNome() {
        return idNome;
    }

    public String getNomePDI() {
        return nomePDI;
    }

    public void setNomePDI(String nomePDI) {
        this.nomePDI = nomePDI;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(double asseX) {
        this.latitudine = asseX;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(double asseY) {
        this.longitudine = asseY;
    }

    public ArrayList<Contenuti> getListaContenuti() {
        return listaContenuti;
    }

    public void setListaContenuti(ArrayList<Contenuti> listaContenuti) {
        this.listaContenuti = listaContenuti;
    }

    @Override
    public String toString() {
        return "StatoPendingPuntoInteresse{" +
                "idNome=" + idNome +
                ", nomePDI='" + nomePDI + '\'' +
                ", asseX=" + latitudine +
                ", asseY=" + longitudine +
                ", listaContenuti=" + listaContenuti +
                '}';
    }
}
