package it.unicam.IDS.progetto.Entita;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Preferiti{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPreferiti;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Itinerario> preferiti;

    public Preferiti() {
        this.preferiti = new ArrayList<>();
    }

    public List<Itinerario> getPreferiti() {
        return preferiti;
    }

    public void setPreferiti(List<Itinerario> preferiti) {
        this.preferiti = preferiti;
    }

    public int getIdPreferiti() {
        return idPreferiti;
    }

}
