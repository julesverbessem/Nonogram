package be.kdg;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
       Scanner keyboard = new Scanner(System.in);
        GebruikersLijst lijst = new GebruikersLijst();

        lijst.startScherm();
        System.out.println("Wilt u het scorebord zien?(y/n)");
        String scorebord = keyboard.nextLine();
        while (!scorebord.toUpperCase().equals("Y") && !scorebord.toUpperCase().equals("N")) {
            System.out.println("Wilt u het scorebord zien?(y/n)");
            scorebord = keyboard.nextLine();
        }
        if (scorebord.toUpperCase().equals("Y")) {
            lijst.overzichtSpelers();
        }
        System.out.println("Heeft u al een account(y/n):");
        String heeftUAlAccount = keyboard.nextLine();
        while (!heeftUAlAccount.toUpperCase().equals("Y") && !heeftUAlAccount.toUpperCase().equals("N")) {
            System.out.println("Heeft u al een account(y/n):");
            heeftUAlAccount = keyboard.nextLine();
        }
        if (heeftUAlAccount.toUpperCase().equals("Y")) {
            System.out.println("Geeft uw gebruikersnaam in:");
            String gebruikersnaam = keyboard.nextLine();
            System.out.println("Geef uw passwoord in:");
            String passwoord = keyboard.nextLine();
            lijst.login(gebruikersnaam, passwoord);

        } else {
            System.out.println("Geef uw gebruikersnaam in:");
            String gebruikersnaam = keyboard.nextLine();
            System.out.println("Geef uw passwoord in:");
            String passwoord = keyboard.nextLine();
            lijst.setGebruiker(gebruikersnaam, passwoord);
        }


        Nonogram test = new Nonogram(5);
        boolean isKlaar = false;
        int opdracht;
        int kolom = 0;
        int rij = 0;
        test.toonGrid();
        test.nonogram1_lijn();

        while (!isKlaar){
            System.out.println("Inkleuren(1) of aanduiden(2):");
            opdracht = keyboard.nextInt();
            if(opdracht == 1){
                System.out.println("Geef een coördinaat in (kolom,rij):");
                kolom = keyboard.nextInt();
                rij = keyboard.nextInt();
                System.out.println(test.kleurIn(rij,kolom));
            }
            else {
                System.out.println("Geef een coördinaat in (kolom,rij):");
                kolom = keyboard.nextInt();
                rij = keyboard.nextInt();
                test.duidAan(rij,kolom);
            }

            if(test.controlleren()){
                isKlaar= true;
            }
        }

        /*
        System.out.println(test.kleurIn(1, 5));

        System.out.println(test.kleurIn(1, 3));

        System.out.println(test.kleurIn(3, 5));

        test.duidAan(1, 2);
        test.duidAan(1, 5);
*/

    }
}
