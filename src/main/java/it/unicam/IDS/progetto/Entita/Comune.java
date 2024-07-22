package it.unicam.IDS.progetto.Entita;

import it.unicam.IDS.progetto.Eccezioni.PDI.PuntoInteresseNotFoundEccezione;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
public class Comune {

    @Id
    private String nomeComune;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nomeComune", referencedColumnName = "coordinate_id")
    private Coordinate coordinate;

    private String cap;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<PuntoInteresse> listaPDI;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<PuntoInteresse> listaPendingPDI;

    public Comune(String nomeComune, double asseX, double asseY, String cap) {
        this.nomeComune = nomeComune;
        this.coordinate = new Coordinate(nomeComune, asseX, asseY);
        this.cap = cap;
        this.listaPDI = null;
        this.listaPendingPDI = null;
    }

    public String getNomeComune() {
        return nomeComune;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setNomeComune(String nomeComune) {
        this.nomeComune = nomeComune;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public List<PuntoInteresse> getListaPDI() {
        return listaPDI;
    }

    public void setListaPDI(List<PuntoInteresse> listaPDI) {
        this.listaPDI = listaPDI;
    }

    public List<PuntoInteresse> getListaPendingPDI() {
        return listaPendingPDI;
    }

    public void setListaPendingPDI(List<PuntoInteresse> listaPendingPDI) {
        this.listaPendingPDI = listaPendingPDI;
    }

    public void addContenuti(String nomePDI,Contenuti contenuto) {
        PuntoInteresse puntoInteresse = findPDI(nomePDI);
//        if (puntoInteresse == null)
//            System.out.println("Il punto di interesse non esiste");
        if (puntoInteresse.getListaContenuti().contains(contenuto))
            System.out.println("Il nome del contenuto inserito è già presente nella piattaforma");

        puntoInteresse.getListaContenuti().add(contenuto);
        System.out.println("Il contenuto è stato aggiunto al punto di interesse");
    }

    public void addContenutiPending(String nomePDI, Contenuti contenuto) {
        PuntoInteresse puntoInteresse = findPDI(nomePDI);
//        if (puntoInteresse == null)
//            System.out.println("Il punto di interesse non esiste");
        if (puntoInteresse.getListaContenuti().contains(contenuto))
            System.out.println("Il nome del contenuto inserito è già presente nella piattaforma");

        puntoInteresse.getListaContenuti().add(contenuto);
        listaPDI.remove(puntoInteresse);
        listaPendingPDI.add(puntoInteresse);
        System.out.println("Il contenuto è stato aggiunto allo stato pending");
    }

    public void rimuoviContenuti(String nomePDI, Contenuti contenuto) {
        PuntoInteresse puntoInteresse = findPDI(nomePDI);
//        if (puntoInteresse == null)
//            System.out.println("Il punto di interesse non esiste");
        if (!puntoInteresse.getListaContenuti().contains(contenuto))
            System.out.println("Il nome del contenuto da eliminare non è presente nella piattaforma");

        puntoInteresse.getListaContenuti().remove(contenuto);
        System.out.println("Il contenuto è stato eliminato dal punto di interesse");
    }

    private PuntoInteresse findPDI(String nomePdi){
        for(PuntoInteresse pdi:listaPDI){
            if(pdi.getNomePDI().equalsIgnoreCase(nomePdi))
                return pdi;
        }
        return throw new PuntoInteresseNotFoundEccezione();
    }

    public void inserimentoPDI(PuntoInteresse puntoPDI) {
        if (listaPDI.contains(puntoPDI))
            System.out.println("Il nome del punto di interesse è già presente nella piattaforma");

        listaPDI.add(puntoPDI);
        System.out.println("Il punto di interesse è stato aggiunto");
    }

    public void inserimentoPendingPDI(PuntoInteresse puntoPDI) {
        if (listaPendingPDI.contains(puntoPDI))
            System.out.println("Il nome del punto di interesse è già presente nella piattaforma");

        listaPendingPDI.add(puntoPDI);
        System.out.println("Il punto di interesse è stato aggiunto allo stato di pending");
    }

    public void eliminaPDI(PuntoInteresse puntoPDI) {
        if (!listaPDI.contains(puntoPDI))
            System.out.println("Il nome del punto di interesse da eliminare non è presente nella piattaforma");

        listaPDI.remove(puntoPDI);
        System.out.println("il punto di interesse è stato eliminato");
    }

    public void approvazioneStatoPendingPDI(PuntoInteresse pdiScelto, String scelta) {
        if (!listaPendingPDI.contains(pdiScelto))
            throw new PuntoInteresseNotFoundEccezione();
        if (scelta.equalsIgnoreCase("Y")) {
            listaPendingPDI.remove(pdiScelto);
            listaPDI.add(pdiScelto);
            System.out.println("Il punto di interesse è stato approvato");
        } else {
            listaPendingPDI.remove(pdiScelto);
            System.out.println("Il punto di interesse non è stato approvato quindi è stato eliminato");
        }
    }
}
