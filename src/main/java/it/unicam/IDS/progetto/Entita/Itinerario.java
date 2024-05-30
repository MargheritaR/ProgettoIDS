package it.unicam.IDS.progetto.Entita;

import java.util.ArrayList;
import java.util.List;

public class Itinerario extends StatoPending{

    private String nomeItinerario;

    private List<PuntoInteresse> listaItinerarioPDI;

    private ArrayList<Foto> listaFoto;

    public Itinerario(String nomeItinerario, List<PuntoInteresse> listaItinerarioPDI, ArrayList<Foto> listaFoto) {
        this.nomeItinerario = nomeItinerario;
        this.listaItinerarioPDI = listaItinerarioPDI;
        this.listaFoto = listaFoto;
    }

    public String getNomeItinerario() {
        return nomeItinerario;
    }

    public void setNomeItinerario(String nomeItinerario) {
        this.nomeItinerario = nomeItinerario;
    }

    public List<PuntoInteresse> getListaItinerarioPDI() {
        return listaItinerarioPDI;
    }

    public void setListaItinerarioPDI(List<PuntoInteresse> listaItinerarioPDI) {
        this.listaItinerarioPDI = listaItinerarioPDI;
    }

    public ArrayList<Foto> getListaFoto() {
        return listaFoto;
    }

    public void setListaFoto(ArrayList<Foto> listaFoto) {
        this.listaFoto = listaFoto;
    }

    @Override
    public String toString() {
        return "Itinerario" + '\n' +
                "nomeItinerario: " + nomeItinerario + '\n' +
                "listaItinerarioPDI: " + '\n' + listaItinerarioPDI + '\n'+
                "listaFoto: " + '\n' + listaFoto + '\n';
    }

}
