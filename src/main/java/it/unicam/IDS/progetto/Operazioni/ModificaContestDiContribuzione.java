package it.unicam.IDS.progetto.Operazioni;
/*
import it.unicam.IDS.progetto.Entita.ContestDiContribuzione;

import java.time.LocalDate;
import java.util.Scanner;

public class ModificaContestDiContribuzione {
    Scanner scanner = new Scanner(System.in);
    String continua = "Y";

    public void EditContest(ContestDiContribuzione contestDiContribuzione) {
        while (continua.equalsIgnoreCase("Y")) {
            System.out.println("Quale parametro vuoi modificare?" +'\n'
                    + "1- Nome del contest" + '\n'
                    + "2- Obiettivo" + '\n'
                    + "3- Tematica" + '\n'
                    + "4- Data inizio del contest" + '\n'
                    + "5- Data fine del contest" + '\n'
                    + "6- Limite massimo dei contenuti aggiungibili al contest" + '\n'
                    + "7- Termine massimo di spedizione degli inviti" + '\n'
                    +"8- Termine massimo di ricezione degli inviti" + '\n'
                    + "9- Sogli degli inviti" + '\n'
                    + "10- Esci");

            int scelta = scanner.nextInt();

            switch (scelta) {
                case 1:
                    System.out.println("Inserisci il nuovo nome del contest:");
                    String nome = scanner.nextLine();
                    ControlliContest(nome);
                    contestDiContribuzione.setNomeContest(nome);
                    break;
                case 2:
                    System.out.println("Inserisci il nuovo obiettivo del contest:");
                    String obiettivo = scanner.nextLine();
                    ControlliContest(obiettivo);
                    contestDiContribuzione.setObiettivo(obiettivo);
                    break;
                case 3:
                    System.out.println("Inserisci la nuova tematica del contest:");
                    String tematica = scanner.nextLine();
                    ControlliContest(tematica);
                    contestDiContribuzione.setTematica(tematica);
                    break;
                case 4:
                    System.out.println("Inserisci la nuova data di inizio del contest:");
                    String dataInizio = scanner.nextLine();
                    ControlliContest(dataInizio);
                    ControlliValoreDataInizio(contestDiContribuzione,dataInizio);
                    contestDiContribuzione.setTempoInizio(LocalDate.parse(dataInizio));
                    break;
                case 5:
                    System.out.println("Inserisci la nuova data di fine del contest:");
                    String dataFine = scanner.nextLine();
                    ControlliContest(dataFine);
                    ControlliValoreDataFine(contestDiContribuzione,dataFine);
                    contestDiContribuzione.setTempoFine(LocalDate.parse(dataFine));
                    break;
                case 6:
                    System.out.println("Inserisci il nuovo limite massimo di contenuti aggiungibili:");
                    String limiteMaxContenuto = scanner.nextLine();
                    ControlliContest(limiteMaxContenuto);
                    ControlliValoreLimiteMCont(contestDiContribuzione,limiteMaxContenuto);
                    contestDiContribuzione.setLimiteMassimoC(LocalDate.parse(limiteMaxContenuto));
                    break;
                case 7:
                    System.out.println("Inserisci il nuovo termine massimo di spedizione inviti");
                    String termineMaxS = scanner.nextLine();
                    ControlliContest(termineMaxS);
                    ControlliValoreTermineMax(termineMaxS);
                    contestDiContribuzione.setTermineMassimoS(termineMaxS);
                    break;
                case 8:
                    System.out.println("Inserisci il nuovo termine massimo di ricezione inviti");
                    String termineMaxR = scanner.nextLine();
                    ControlliContest(termineMaxR);
                    ControlliValoreTermineMax(termineMaxR);
                    contestDiContribuzione.setTermineMassimoR(termineMaxR);
                    break;
                case 9:
                    System.out.println("inserisci la nuova sogli degli inviti");
                    int soglia = scanner.nextInt();
                    ControlliContest(soglia);
                    contestDiContribuzione.setSogliaInviti(soglia);
                    break;
                case 10:
                    System.out.println("Vuoi uscire?: Y/N");
                    continua = scanner.nextLine();
                    break;
                default:
                    System.out.println("Scelta non valida." + '\n' + "Riprovare.");
            }
        }
    }

    private boolean ControlliContest(String valore) {
        if (valore.isEmpty()) {
            System.out.println("Il nome non può essere nullo");
            return false;
        }
        return true;
    }

    private boolean ControlliContest(int valore){
        if (valore == 0) {
            System.out.println("Il valore inserito non può essere nullo");
            return false;
        }
        return true;
    }

    private boolean ControlliValoreDataFine(ContestDiContribuzione contestDiContribuzione, String dataFine) {
        if (!LocalDate.parse(dataFine).isAfter(contestDiContribuzione.getTempoInizio())) {
            System.out.println("Il tempo di fine deve essere temporalmente dopo del tempo di inizio");
            return false;
        }
        return true;
    }

    private boolean ControlliValoreLimiteMCont(ContestDiContribuzione contestDiContribuzione,String limiteMaxCont){
        if (ControlloLassoDiTempo(contestDiContribuzione,limiteMaxCont)) {
            System.out.println("Il limite massimo di aggiunta contenuti del contest non può essere al di fuori " +
                    "del lasso di tempo del contest di contribuzione");
            return false;
        }
        return true;
    }

    private boolean ControlliValoreDataInizio(ContestDiContribuzione contestDiContribuzione, String dataInizio){
        if (!LocalDate.parse(dataInizio).isBefore(contestDiContribuzione.getTempoFine())) {
            System.out.println("Il tempo di inizio deve essere temporalmente prima del tempo di fine");
            return false;
        }
        return true;
    }

    private boolean ControlloLassoDiTempo(ContestDiContribuzione contestDiContribuzione,String limiteMaxCont) {
        return !contestDiContribuzione.getTempoInizio().isBefore(LocalDate.parse(limiteMaxCont)) &&
                !contestDiContribuzione.getTempoFine().isAfter(LocalDate.parse(limiteMaxCont));
    }

    private boolean ControlliValoreTermineMax(String termineMax) {
        if (termineMax.isEmpty()) {
            System.out.println("Il termine massimo di spedizione o risposta  agli inviti non può essere nullo");
            return false;
        }
        return true;
    }

}
*/