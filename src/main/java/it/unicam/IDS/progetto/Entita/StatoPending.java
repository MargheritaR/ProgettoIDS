package it.unicam.IDS.progetto.Entita;

import java.util.ArrayList;

public class StatoPending<T> {

    private ArrayList<T> statoPending;

    public ArrayList<T> getStatoPending() {
        return statoPending;
    }

    public void setStatoPending(ArrayList<T> statoPending) {
        this.statoPending = statoPending;
    }
}
