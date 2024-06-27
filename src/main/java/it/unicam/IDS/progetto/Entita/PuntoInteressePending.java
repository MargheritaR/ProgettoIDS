package it.unicam.IDS.progetto.Entita;

import it.unicam.IDS.progetto.Service.PDIServiceController;

public class PuntoInteressePending implements StatoPending{

    @Override
    public ContenutoBase factoryMethod() {
        return new PDIServiceController();
    }
}
