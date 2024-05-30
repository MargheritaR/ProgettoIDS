package it.unicam.IDS.progetto.Operazioni;

import it.unicam.IDS.progetto.Entita.Foto;
import it.unicam.IDS.progetto.Entita.Itinerario;

import java.util.ArrayList;
import java.util.Scanner;

public class CaricaFoto {

    Scanner scanner = new Scanner(System.in);

    String continua = "Y";

    public void InserimentoFoto(ArrayList<Itinerario> listaItinerari,Foto foto){
        if (foto == null)
            System.out.println("La foto è nulla");
        //TODO creare la eccezione e lanciarla

        while (continua.equalsIgnoreCase("Y")) {
            Itinerario appoggio = ScegliFoto(listaItinerari,foto);
            AggiungiFoto(appoggio,foto);

            System.out.println("Vuoi aggiungere altre foto: (Y/N)");
            continua = scanner.nextLine();
        }
    }

    private String Mostra(ArrayList<Itinerario> listaItinerari) {
        System.out.println("Lista degli itinerari");
        System.out.println(listaItinerari);

        System.out.println('\n' + "Quale itinerario vuoi aggiungere la foto: (nome degli itinerari)");
        String itinerarioSelezionato = scanner.nextLine();

        return itinerarioSelezionato;
    }

    private Itinerario FindItinerario(ArrayList<Itinerario> listaItinerari, String itinerarioSelezionato) {
        for (int i = 0; i < listaItinerari.size(); i++)
            if (listaItinerari.get(i).getNomeItinerario().equals(itinerarioSelezionato))
                return listaItinerari.get(i);
        return null;
    }

    private void AggiungiFoto(Itinerario itinerarioScelto, Foto foto) {
        ArrayList<Foto> newListaFoto = new ArrayList<>(itinerarioScelto.getListaFoto());
        newListaFoto.add(foto);
        itinerarioScelto.setListaFoto(newListaFoto);
        System.out.println("La foto è stata aggiunta");
    }

    private Itinerario ScegliFoto(ArrayList<Itinerario> listaItinerari, Foto foto) {
        String appoggio = Mostra(listaItinerari);
        Itinerario itinerarioScelto = FindItinerario(listaItinerari,appoggio);
        if (itinerarioScelto == null)
            System.out.println("L'itinerario scelto non è stato trovato");
        if (!itinerarioScelto.getListaFoto().contains(foto))
            System.out.println("La foto esiste già nell'itinerario scelto");
        //TODO creare la eccezione e lanciarla
        return itinerarioScelto;
    }
}
