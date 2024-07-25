package it.unicam.IDS.progetto.Controller;

import it.unicam.IDS.progetto.Entita.Comune;
import it.unicam.IDS.progetto.Entita.Itinerario;
import it.unicam.IDS.progetto.Entita.PuntoInteresse;

public class ControllerComune {

    private Comune comune;

    public ControllerComune(Comune comune) {
        this.comune = comune;
    }

    public void inserimentoPDI(PuntoInteresse puntoPDI) {
        comune.inserimentoPDI(puntoPDI);
    }

    public void inserimentoPendingPDI(PuntoInteresse puntoPDI) {
        comune.inserimentoPendingPDI(puntoPDI);
    }

    public void eliminaPDI(PuntoInteresse puntoPDI) {
        comune.eliminaPDI(puntoPDI);
    }

    public void approvazioneStatoPendingPDI(String pdiScelto, String scelta) {
        comune.approvazioneStatoPendingPDI(pdiScelto, scelta);
    }

    public void approvazioneStatoPendingItinerario(String itinerarioScelto, String scelta) {
        comune.approvazioneStatoPendingItinerario(itinerarioScelto, scelta);
    }

    public void creaItinerario(Itinerario itinerario) {
        comune.creaItinerario(itinerario);
    }

    public void creaItinerarioPending(Itinerario itinerario) {
        comune.creaItinerarioPending(itinerario);
    }

    public void eliminaItinerario(Itinerario itinerario) {
        comune.eliminaItinerario(itinerario);
    }

    public void aggiuntaPdiItinerario(String nomePuntoInteresse, String nomeItinerario) {
        comune.aggiuntaPdiItinerario(nomePuntoInteresse, nomeItinerario);
    }

    public void aggiuntaPendingPdiItinerario(String nomePuntoInteresse, String nomeItinerario) {
        comune.aggiuntaPendingPdiItinerario(nomePuntoInteresse, nomeItinerario);
    }

    public void rimuoviPdiItinerario(String nomePuntoInteresse, String nomeItinerario) {
        comune.rimuoviPdiItinerario(nomePuntoInteresse, nomeItinerario);
    }
}
