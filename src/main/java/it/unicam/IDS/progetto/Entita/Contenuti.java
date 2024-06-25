package it.unicam.IDS.progetto.Entita;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.io.File;

// Foglia per il design pattern Composite
@Entity
public class Contenuti implements Component{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idContenuto;

    @NotEmpty
    private File contenuto;

    /*
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idContenuto", referencedColumnName = "id")
    private Utente autore;
     */

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


    public void setIdContenuto(int  idContenuto) {
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
