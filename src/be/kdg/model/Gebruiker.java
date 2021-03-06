package be.kdg.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

public class Gebruiker implements Comparable<Gebruiker>{
    private String gebruikersnaam;
    private String passwoord;
    public Spel opgeslagenSpel=new Spel();
    private int level;
    private LocalDateTime datum;

    //constructors
    public Gebruiker(String gebruikersnaam, String passwoord) {
        this.gebruikersnaam = gebruikersnaam;
        this.passwoord = passwoord;
        this.level = 1;//aanpassen !!!!!!
        this.datum = LocalDateTime.now();

        schrijfGebruikerWeg(gebruikersnaam,passwoord,level,datum);
    }
    public Gebruiker(){};
    public Gebruiker(String gebruikersnaam){
        this.gebruikersnaam = gebruikersnaam;
    }

    //Gebruiker in csv zetten
    private void schrijfGebruikerWeg(String gebruikersnaam, String passwoord, int level, LocalDateTime datum) {
        File gbrfile = new File("resources/Gebruikers.csv");
        String data = "\n;"+gebruikersnaam+";"+passwoord+";"+level+";"+datum;
        System.out.println(data);
        try {
            Files.writeString(gbrfile.toPath(),data, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Een fout bij het wegschrijven van gebruiker "+gebruikersnaam);
        }
    }

    //Getters en setters
    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public String getPasswoord() {
        return passwoord;
    }

    public void setPasswoord(String passwoord) {
        this.passwoord = passwoord;
    }

    public Spel getOpgeslagenSpel() {
        return opgeslagenSpel;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    //Methodes om gebruikers te vergelijken
    public int compareTo(Gebruiker gbrkr1){
        if(this.getLevel()<gbrkr1.getLevel()){
            return 1;
        }else{
            return -1;
        }
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
