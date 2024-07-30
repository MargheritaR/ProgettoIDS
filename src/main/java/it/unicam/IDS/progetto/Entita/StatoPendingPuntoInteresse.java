package it.unicam.IDS.progetto.Entita;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class StatoPendingPuntoInteresse  extends StatoPending{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNome;

    @NotNull
    private String nomePDI;

    @NotNull
    private double asseX;

    @NotNull
    private double asseY;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private ArrayList<Contenuti> listaContenuti;

    public StatoPendingPuntoInteresse(String nomePDI, double asseX, double asseY) {
        this.nomePDI = nomePDI;
        this.asseX = asseX;
        this.asseY = asseY;
        this.listaContenuti = null;
    }

    public StatoPendingPuntoInteresse(String nomePDI, double asseX, double asseY, ArrayList<Contenuti> listaContenuti) {
        this.nomePDI = nomePDI;
        this.asseX = asseX;
        this.asseY = asseY;
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

    public double getAsseX() {
        return asseX;
    }

    public void setAsseX(double asseX) {
        this.asseX = asseX;
    }

    public double getAsseY() {
        return asseY;
    }

    public void setAsseY(double asseY) {
        this.asseY = asseY;
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
                ", asseX=" + asseX +
                ", asseY=" + asseY +
                ", listaContenuti=" + listaContenuti +
                '}';
    }
}
