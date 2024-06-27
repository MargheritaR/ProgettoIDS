package it.unicam.IDS.progetto.Entita;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.io.File;

// Foglia per il design pattern Composite
@Entity
public class Contenuti {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String idContenuto;

    @NotEmpty
    private File contenuto;

    public Contenuti(File contenuto) {
        this.contenuto = contenuto;
    }

    public Contenuti() {

    }

    public String getIdContenuto() {
        return idContenuto;
    }

    public File getContenuto() {
        return contenuto;
    }


    public void setIdContenuto(String idContenuto) {
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
