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
            int usercounter =0;
            while (fileScanner.hasNext()) {
               // List<String> gebruikers  = new ArrayList<>();
//                gebruikers.add( fileScanner.nextLine());
                String gebruiker = fileScanner.nextLine();
                String[] data = new String[4];
                data = gebruiker.split(";");
                for (int i = 0; i < data.length; i++) {

                    switch (i){
                        case 1:
                            lijst.add(new Gebruiker(data[i]));
                           System.out.println(data[i]);
                            break;
                        case 2:
                            lijst.get(usercounter).setPasswoord(data[i]);
                            break;
                        case 3:
                            lijst.get(usercounter).setLevel(Integer.parseInt(data[i]));
                            break;
                        case 4:
                            lijst.get(usercounter).setDatum(LocalDateTime.parse(data[i]));
                            break;
                    }
                }
                usercounter++;
            }

        } catch (IOException ioe) {
            System.out.println("Fout bij het inlezen van bestand " + gbrcsv.getName());
        }

    }

    public String inlezenSpelrgels(){
        File csvSpelregels = new File("C:\\Users\\jules\\OneDrive\\Documenten\\KDG\\Java 1\\Gamesproject\\Nonogram\\resources\\Spelregels.csv");
        try(Scanner fileScanner = new Scanner(csvSpelregels)){
            StringBuilder builder = new StringBuilder();
            while(fileScanner.hasNext()){
                builder.append(fileScanner.nextLine()).append("\n");
            }
            return builder.toString();
        }catch (IOException ex){
            System.out.println("Fout bij het inlezen van bestand " + csvSpelregels.getName());
        }
        return null;
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
    }

    public boolean login(String gebruikersnaam, String passwoord) {
        //later lijst overlopen om user te vinden
        boolean rtn = false;
        for (Gebruiker huidigeGebruiker : lijst) {
            if (huidigeGebruiker.getGebruikersnaam().equals(gebruikersnaam) && huidigeGebruiker.getPasswoord().equals(passwoord)) {
                System.out.println("Gebruiker is ingeloged");
                return true;
            } else {
                System.out.println("Foute gebruikersnaam of wachtwoord");
                rtn = false;
            }

        }
        return rtn;
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

    public void updateGebruikers(){
        File gbrfile = new File("C:\\Users\\jules\\OneDrive\\Documenten\\KDG\\Java 1\\Gamesproject\\Nonogram\\resources\\Gebruikers.csv");

        List<String> gebruikers = new ArrayList<>();

        for (Gebruiker gebruiker : lijst) {
            gebruikers.add(";"+gebruiker.getGebruikersnaam()+";"+gebruiker.getPasswoord()+";"+gebruiker.getLevel()+";"+gebruiker.getDatum());
        }

        try{
            Files.write(gbrfile.toPath(),gebruikers);
        }catch (IOException ex){
            System.out.println("Er is een fout gebeurd bij het updaten van de gebruikers");
        }
    }
}

