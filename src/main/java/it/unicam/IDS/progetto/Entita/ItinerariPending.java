package it.unicam.IDS.progetto.Entita;

import it.unicam.IDS.progetto.Service.ItinerariServiceController;

public class ItinerariPending implements StatoPending{

    @Override
    public ContenutoBase factoryMethod() {
        return new ItinerariServiceController();
    }
}
