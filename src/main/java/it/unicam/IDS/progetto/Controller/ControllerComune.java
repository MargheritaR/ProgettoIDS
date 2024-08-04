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

    public void rimuoviContenuti(String nomePDI, String nomeContenuto){
        comune.rimuoviContenuti(nomePDI, nomeContenuto);
    }

    public void inserimentoPDI(PuntoInteresse puntoPDI, String ruolo) {
        comune.inserimentoPDI(puntoPDI,ruolo);
    }

    public void eliminaPDI(String nomePDI) {
        comune.eliminaPDI(nomePDI);
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

    public void eliminaItinerario(String nomeItinerario) {
        comune.eliminaItinerario(nomeItinerario);
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

    public void eliminaContestDiContribuzione(String nomeContest){
        comune.eliminaContestDiContribuzione(nomeContest);
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

    public void aggiungiPreferitiItinerario(String nomeItinerario,String nomeUtente) {
        comune.aggiungiPreferitiItinerario(nomeItinerario,nomeUtente);
    }

    public void aggiungiPreferitiPDI(String nomePdi,String nomeUtente) {
        comune.aggiungiPreferitiPDI(nomePdi,nomeUtente);
    }

    public void rimuoviPreferitiPDI(String nomePdi,String nomeUtente){
        comune.rimuoviPreferitiPDI(nomePdi,nomeUtente);
    }

    public void rimuoviPreferitiItinerari(String nomeItinerari,String nomeUtente) {
        comune.rimuoviPreferitiItinerari(nomeItinerari,nomeUtente);
    }

    public void aggiungiFotoItinerario(Foto foto, String nomeItinerario, String ruolo){
        comune.aggiungiFotoItinerario(foto, nomeItinerario, ruolo);
    }

    public void inviaMessaggi(Messaggio messaggio){
        comune.inviaMessaggi(messaggio);
    }

    public void decidiContenutoVincitore(String nomeContest, String nomeContenuto, Messaggio messaggio){
        comune.decidiContenutoVincitore(nomeContest, nomeContenuto, messaggio);
    }

    public void rimuoviFotoItinerario(int idFoto, String nomeItinerario){
        comune.rimuoviFotoItinerario(idFoto, nomeItinerario);
    }

    public Comune getComune() {
        return comune;
    }
}
