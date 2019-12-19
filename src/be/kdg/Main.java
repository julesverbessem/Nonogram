package be.kdg;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        GebruikersLijst lijst = new GebruikersLijst();

        lijst.startScherm();
        System.out.println("Heeft u al een account(y/n):");
        String heeftUAlAccount = keyboard.nextLine();
        while(!heeftUAlAccount.toUpperCase().equals("Y")&&!heeftUAlAccount.toUpperCase().equals("N")){
            System.out.println("Heeft u al een account(y/n):");
            heeftUAlAccount = keyboard.nextLine();
        }
        if(heeftUAlAccount.toUpperCase().equals("Y")){
            System.out.println("Geeft uw gebruikersnaam in:");
            String gebruikersnaam = keyboard.nextLine();
            System.out.println("Geef uw passwoord in:");
            String passwoord = keyboard.nextLine();
            lijst.login(gebruikersnaam,passwoord);
        }
        else{
            System.out.println("Geef uw gebruikersnaam in:");
            String gebruikersnaam = keyboard.nextLine();
            System.out.println("Geef uw passwoord in:");
            String passwoord = keyboard.nextLine();
            lijst.setGebruiker(gebruikersnaam,passwoord);
        }

	    Nonogram test = new Nonogram(5);
	    test.toonGrid();
	    test.nonogram1_lijn();

        System.out.println(test.kleurIn(1,5));

        System.out.println(test.kleurIn(1,3));

        System.out.println(test.kleurIn(3,5));

        test.duidAan(1,2);
        test.duidAan(1,5);

        test.nonogram2_lijnVerticaal();
    }
}
