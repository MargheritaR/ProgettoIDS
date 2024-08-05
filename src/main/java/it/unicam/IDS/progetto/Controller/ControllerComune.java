package it.unicam.IDS.progetto.Controller;

import it.unicam.IDS.progetto.Dtos.ContenutiDtos;
import it.unicam.IDS.progetto.Entita.*;
import it.unicam.IDS.progetto.Repository.ComuneRepository;
import it.unicam.IDS.progetto.Repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping(value = "/comune")
public class ControllerComune {

    private ComuneRepository comuneRepository;

    private UtenteRepository utenteRepository;

   @Autowired
    public ControllerComune(ComuneRepository comuneRepository, UtenteRepository utenteRepository) {
        this.comuneRepository = comuneRepository;
        this.utenteRepository = utenteRepository;
        /*Comune comune = new Comune("Camerino", 43.5, 32.1, "62032");
        this.comuneRepository.save(comune);*/
    }

   /* @PostMapping(value = "/aggiungiContenuti/{nomePDI}")
    public ResponseEntity<Object> aggiungiContenuti(@PathVariable("nomePDI") String nomePDI, @RequestBody ContenutiDtos contenutiDtos){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utente utente = utenteRepository.findByUsername(authentication.getName());
        String ruolo = String.valueOf(utente.getRuolo());
        getComune().aggiungiContenuti(nomePDI, contenutiDtos, ruolo);

        return new ResponseEntity<>("Il contenuto è stato aggiunto al punto di interesse",HttpStatus.OK);
    }*/

   /* @DeleteMapping(value = "/rimuoviContenuti/{nomePDI}/{nomeContenuto}")
    public ResponseEntity<Object> rimuoviContenuti(@PathVariable("nomePDI") String nomePDI,
                                                   @PathVariable("nomeContenuto") String nomeContenuto) {
        getComune().rimuoviContenuti(nomePDI, nomeContenuto);

        return new ResponseEntity<>("Il contenuto è stato eliminato dal punto di interesse", HttpStatus.OK);
    }

    @RequestMapping(value = "/getContenuti")
    public ResponseEntity<Object> getContenuti() {
        return new ResponseEntity<>(comuneRepository.findAll(), HttpStatus.OK);
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
        return comuneRepository.findByNomeComune("Camerino");
    }*/
}
