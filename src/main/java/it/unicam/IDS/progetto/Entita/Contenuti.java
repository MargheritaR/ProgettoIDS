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
    private String nomeContenuto;

    @NotNull
    private File contenuto;

    public Contenuti(String nomeContenuto, File contenuto) {
        this.nomeContenuto = nomeContenuto;
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

    public String getNomeContenuto() {
        return nomeContenuto;
    }

    public void setIdContenuto(int idContenuto) {
        this.idContenuto = idContenuto;
    }

    public void setContenuto(File contenuto) {
        this.contenuto = contenuto;
    }

    public void setNomeContenuto(String nomeContenuto) {
        this.nomeContenuto = nomeContenuto;
    }

    @Override
    public String toString() {
        return "Contenuti{" +
                "idContenuto=" + idContenuto + '\n' +
                ", nomeContenuto='" + nomeContenuto + '\n' +
                ", contenuto=" + contenuto +
                '}';
    }
}
