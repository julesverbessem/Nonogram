package be.kdg.model;

import javax.swing.text.DateFormatter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GebruikersLijst extends Gebruiker {
    private ArrayList<Gebruiker> lijst;

    public GebruikersLijst() {
        this.lijst = new ArrayList<>();
        initialiseGebruikersLijst();
    }

    private void initialiseGebruikersLijst() {
        File gbrcsv = new File("C:\\Users\\jules\\OneDrive\\Documenten\\KDG\\Java 1\\Gamesproject\\Nonogram\\resources\\Gebruikers.csv");
        try (Scanner fileScanner = new Scanner(gbrcsv)) {
            while (fileScanner.hasNext()) {
               // List<String> gebruikers  = new ArrayList<>();
//                gebruikers.add( fileScanner.nextLine());
                String gebruiker = fileScanner.nextLine();
                String[] data = new String[4];
                data = gebruiker.split(";");
                for (int i = 0; i < data.length; i++) {

                    switch (i){
                        case 0:
                            lijst.add(new Gebruiker(data[i]));
                           System.out.println(data[i]);
                            break;
                        case 1:
                            lijst.get(0).setPasswoord(data[i]);//aanpassen
                           // System.out.println(data[i]+" 1");
                            break;
                        case 2:
                            lijst.get(0).setLevel(Integer.parseInt(data[i]));
                          //  System.out.println(data[i]+" 2");
                            break;
                        case 3:
                            lijst.get(0).setDatum(LocalDateTime.parse(data[i]));
                         //   System.out.println(data[i]+" 3");
                            break;
                    }
                }
            }

        } catch (IOException ioe) {
            System.out.println("Fout bij het inlezen van bestand " + gbrcsv.getName());
        }

    }

    public ArrayList<Gebruiker> getLijst() {
        return lijst;
    }

    public Gebruiker getGebruiker(String gebruikersnaam){
        for (Gebruiker huidigeGebruiker: lijst) {
            if(gebruikersnaam.equals(huidigeGebruiker.getGebruikersnaam())){
                return huidigeGebruiker;
            }
        }
        return null;
    }

    public void setLijst(ArrayList<Gebruiker> lijst) {
        this.lijst = lijst;
    }

    public void setGebruiker(Gebruiker nieweSpeler) {
        this.lijst.add(nieweSpeler);
        //nieweSpeler.opgeslagenSpel.startSpel(nieweSpeler);
    }

    public boolean login(String gebruikersnaam, String passwoord) {
        //later lijst overlopen om user te vinden
        for (Gebruiker huidigeGebruiker : lijst) {
            System.out.println(huidigeGebruiker.getGebruikersnaam().equals(gebruikersnaam));
            System.out.println(".."+huidigeGebruiker.getGebruikersnaam()+"..");
            System.out.println(".."+gebruikersnaam+"..");
            System.out.println(passwoord.equals(huidigeGebruiker.getPasswoord()));
            if (huidigeGebruiker.getGebruikersnaam().equals(gebruikersnaam) && huidigeGebruiker.getPasswoord().equals(passwoord)) {
                if (huidigeGebruiker.getOpgeslagenSpel() == null) {
                    huidigeGebruiker.setLevel(1);
                    System.out.println("Gebruiker heeft nog geen spel, hij start bij 1");
                    return true;
                }
                //huidigeGebruiker.opgeslagenSpel.startSpel(huidigeGebruiker);
                System.out.println("Gebruiker is ingeloged");
                return true;
            } else {
                System.out.println("Foute gebruikersnaam of wachtwoord");
            }
            return false;
        }
        return false;
    }

    public String overzichtSpelers() {
        Collections.sort(lijst);
        StringBuilder stringBuilder = new StringBuilder();
        int counter = 0;
        for (Gebruiker huidigeGebruiker : lijst) {
            counter++;
            DateTimeFormatter format =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String date = format.format(huidigeGebruiker.getDatum());
            System.out.println(String.format("%d. %s ( %s) --------------------- %-10s",counter, huidigeGebruiker.getGebruikersnaam(),date, Integer.toString(huidigeGebruiker.getLevel())));
            stringBuilder.append(String.format("%d. %s ( %s) --------------------- %-10s \n",counter, huidigeGebruiker.getGebruikersnaam(),date, Integer.toString(huidigeGebruiker.getLevel())));
        }
        return stringBuilder.toString();
    }
}

