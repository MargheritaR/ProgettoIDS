package it.unicam.IDS.progetto.Entita;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.File;

@Entity
public class Contenuti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idContenuto;

    @NotNull
    private File contenuto;

    public Contenuti(File contenuto) {
        this.contenuto = contenuto;
    }

    public Contenuti() {
    }

    public int getIdContenuto() {
        return idContenuto;
    }

    public File getContenuto() {
        return contenuto;
    }


    public void setIdContenuto(int idContenuto) {
        this.idContenuto = idContenuto;
    }

    public void setContenuto(File contenuto) {
        this.contenuto = contenuto;
    }


    @Override
    public String toString() {
        return "Contenuti{" +
                "idContenuto='" + idContenuto + '\'' +
                ", nomeContenuto='" + contenuto + '\'' +
                '}';
    }
}
