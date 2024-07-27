package it.unicam.IDS.progetto.Controller;

import it.unicam.IDS.progetto.Entita.*;

import java.io.File;

public class ControllerComune {

    private Comune comune;

    public ControllerComune(Comune comune) {
        this.comune = comune;
    }

    public void addContenuti(String nomePDI, Contenuti contenuto){
        comune.addContenuti(nomePDI, contenuto);
    }

    public void addContenutiPending(String nomePDI, Contenuti contenuto){
        comune.addContenutiPending(nomePDI, contenuto);
    }

    public void rimuoviContenuti(String nomePDI, Contenuti contenuto){
        comune.rimuoviContenuti(nomePDI, contenuto);
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

    public void creaContestDiContribuzione(ContestDiContribuzione contestDiContribuzione){
        comune.creaContestDiContribuzione(contestDiContribuzione);
    }

    public void eliminaContestDiContribuzione(ContestDiContribuzione contestDiContribuzione){
        comune.eliminaContestDiContribuzione(contestDiContribuzione);
    }

    public void modificaContestDiContribuzione(String nomeContest, String param, String elemNuovo){
        comune.modificaContestDiContribuzione(nomeContest, param, elemNuovo);
    }

    public void proponiContenuti(String nomeContest, File file){
        comune.proponiContenuti(nomeContest, file);
    }

    public void validaContenuti(String nomeContest, String nomeContenuto, String approv){
        comune.validaContenuti(nomeContest, nomeContenuto, approv);
    }

    public void modificaComune(String param, String elemNuovo) {
        comune.modificaComune(param, elemNuovo);
    }

    public Comune getComune() {
        return comune;
    }
}
