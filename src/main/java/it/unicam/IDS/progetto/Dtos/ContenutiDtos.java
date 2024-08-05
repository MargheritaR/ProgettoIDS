package it.unicam.IDS.progetto.Dtos;

import jakarta.persistence.Id;

import java.io.File;

public class ContenutiDtos {

    @Id
    private String nomeContenuto;

    private File contenuto;

    public ContenutiDtos(String nomeContenuto, File contenuto) {
        this.nomeContenuto = nomeContenuto;
        this.contenuto = contenuto;
    }

    public File getContenuto() {
        return contenuto;
    }

    public String getNomeContenuto() {
        return nomeContenuto;
    }

    public void setContenuto(File contenuto) {
        this.contenuto = contenuto;
    }

    public void setNomeContenuto(String nomeContenuto) {
        this.nomeContenuto = nomeContenuto;
    }
}

