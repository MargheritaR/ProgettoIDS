package it.unicam.IDS.progetto.Controller;

import it.unicam.IDS.progetto.Entita.*;

import java.io.File;

public class ControllerComune {

    private Comune comune;

    public ControllerComune(Comune comune) {
        this.comune = comune;
    }

    public void addContenuti(String nomePDI, Contenuti contenuto, String ruolo){
        comune.addContenuti(nomePDI, contenuto, ruolo);
    }

    public void rimuoviContenuti(String nomePDI, Contenuti contenuto){
        comune.rimuoviContenuti(nomePDI, contenuto);
    }

    public void inserimentoPDI(PuntoInteresse puntoPDI, String ruolo) {
        comune.inserimentoPDI(puntoPDI,ruolo);
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

    public void creaItinerario(Itinerario itinerario,String ruolo) {
        comune.creaItinerario(itinerario, ruolo);
    }

    public void eliminaItinerario(Itinerario itinerario) {
        comune.eliminaItinerario(itinerario);
    }

    public void aggiuntaPdiItinerario(String nomePuntoInteresse, String nomeItinerario, String ruolo) {
        comune.aggiuntaPdiItinerario(nomePuntoInteresse, nomeItinerario, ruolo);
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

    public void aggiungiPreferitiItinerario(String nomeItinerario) {
        comune.aggiungiPreferitiItinerario(nomeItinerario);
    }

    public void aggiungiPreferitiPDI(String nomePdi) {
        comune.aggiungiPreferitiPDI(nomePdi);
    }

    public void rimuoviPreferitiPDI(String nomePdi){
        comune.rimuoviPreferitiPDI(nomePdi);
    }

    public void rimuoviPreferitiItinerari(String nomeItinerari) {
        comune.rimuoviPreferitiItinerari(nomeItinerari);
    }

    public void aggiungiFotoItinerario(Foto foto, String nomeItinerario, String ruolo){
        comune.aggiungiFotoItinerario(foto, nomeItinerario, ruolo);
    }

    public Comune getComune() {
        return comune;
    }
}
