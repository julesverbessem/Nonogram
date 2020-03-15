package be.kdg.model;

import javafx.scene.shape.Path;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Nonogram {
    private int moeilijkheidsgraad;
    private String naam;
    private int grootte;
    private Vakje[][] patroon;
    private Vakje[][] achterLiggendPatroon;
    private String[] rij;
    private String[] kolom;
    private int aantalIngekleurdeVakjes;

    public Nonogram(int grootte) {
        this.patroon = new Vakje[grootte][grootte];
        this.achterLiggendPatroon = new Vakje[grootte][grootte];
        this.rij = new String[grootte];
        this.kolom = new String[grootte];
        this.grootte = grootte;
        this.aantalIngekleurdeVakjes = 0;

        for (int rij = 1; rij <= grootte; rij++) {//aan te passe patroon waarde geven
            for (int kolom = 1; kolom <= grootte; kolom++) {
                int r = rij;
                int k = kolom;
                this.patroon[r - 1][k - 1] = new Vakje();
            }
        }
        for (int rij = 1; rij <= grootte; rij++) {//nonogram patroon waarde geven
            for (int kolom = 1; kolom <= grootte; kolom++) {
                int r = rij;
                int k = kolom;
                this.achterLiggendPatroon[r - 1][k - 1] = new Vakje();
            }
        }
    }

    public int getGrootte(){ return this.grootte;}

    //functionele methodes
    public boolean controlleren(int frij, int fkolom) {//verder afwerken met peerTutor
        boolean rtrn = false;
        int aantaluserVakjses = 0;

        for (int rij = 0; rij < grootte; rij++) {
            for (int kolom = 0; kolom < grootte; kolom++) {

                if(patroon[frij][fkolom].getWaarde().equals(this.achterLiggendPatroon[rij][kolom].getWaarde())){
                    if(!patroon[frij][fkolom].getWaarde().equals("X")){
                        System.out.println("Juist");
                        aantaluserVakjses++;
                        rtrn = true;
                    }
                }else{

                    rtrn = false;
                }
            }
        }
        return rtrn;//&&aantaluserVakjses==aantalIngekleurdeVakjes
    }

    public String feliciteren() {
        return "Gefeliciteerd, een " + this.naam + "!";
    }

    public void toonGrid() {
        StringBuilder builder = new StringBuilder();
        for (int rij = 1; rij <= grootte; rij++) {
            for (int kolom = 1; kolom <= grootte; kolom++) {
                int r = rij;
                int k = kolom;
                if (k % grootte == 0) {

                    builder.append(patroon[r - 1][k - 1].getWaarde()).append(" ").append("\n");
                } else {
                    builder.append(patroon[r - 1][k - 1].getWaarde()).append(" ");
                }
            }
        }
        System.out.println(builder.append("\n").toString());
    }

    public void toonAchterLiggendPatroon() {
        StringBuilder builder = new StringBuilder();
        for (int rij = 1; rij <= grootte; rij++) {
            for (int kolom = 1; kolom <= grootte; kolom++) {
                int r = rij;
                int k = kolom;
                if (k % grootte == 0) {

                    builder.append(achterLiggendPatroon[r - 1][k - 1].getWaarde()).append(" ").append("\n");
                } else {
                    builder.append(achterLiggendPatroon[r - 1][k - 1].getWaarde()).append(" ");
                }
            }
        }
        System.out.println(builder.append("\n").toString());
    }

    public boolean kleurIn(int rij, int kolom) {
        boolean rtrn = false;
        if (rij <= grootte && kolom <= grootte) {
            if(!patroon[rij][kolom].isIngekleurd()){
                patroon[rij][kolom].kleurIn();
                rtrn = true;
            }else{
                patroon[rij][kolom].cancelKleurIn();
            }
            toonGrid();
        }
        return rtrn;
    }

    public boolean duidAan(int rij, int kolom){
        boolean rtrn = false;
        if (rij <= grootte && kolom <= grootte) {
            if(!patroon[rij][kolom].getWaarde().equals("+")){
                patroon[rij][kolom].duidVakAan();
                rtrn = true;
            }else{
                patroon[rij][kolom].cancelKleurIn();
            }
            toonGrid();
        }
        return rtrn;
    }

    public String[] getRij() {
        return rij;
    }

    public String[] getKolom() {
        return kolom;
    }

    public void schrijfGebruikerNonogramWeg(String gebruikersnaam){
        String filenaam = "C:\\Users\\jules\\OneDrive\\Documenten\\KDG\\Java 1\\Gamesproject\\Nonogram\\resources\\"+gebruikersnaam+"Nonogram.csv";
        File file = new File(filenaam);

/*
        List<String> gebruikersnonogram = new ArrayList<>();
        for (Vakje[] vakjes : patroon) {
            for (Vakje vakje : vakjes) {
                gebruikersnonogram.add(vakje.getWaarde());
            }
        }

        try {
            Files.write(file.toPath(),gebruikersnonogram);
        } catch (IOException e) {
            System.out.println("Er is een fout bij het creeëren van "+filenaam);
        }*/
    }

    public void leesGebruikerNonogramIn(File csvGebruikernonogram){
        try(Scanner fileScanner = new Scanner(csvGebruikernonogram)){
            int rowcounter=0;
            while(fileScanner.hasNext()){
                String row = fileScanner.nextLine();
                String[] vakken = new String[grootte];
                vakken = row.split(";");
                for (int i = 0; i < vakken.length; i++) {
                    if(vakken[i].equals("O")){
                        this.patroon[rowcounter][i].kleurIn();
                    }else if(vakken[i].equals("+")){
                        this.patroon[rowcounter][i].duidVakAan();
                    }
                    else {
                        this.patroon[rowcounter][i].setDefaultWaarde();
                    }
                }
                rowcounter++;
            }
        }catch (IOException ex){
            System.out.println("Fout bij het inlezen van bestand " + csvGebruikernonogram.getName());
        }
    }

    private void leesNonogramIn(File csvnonogram){
        try(Scanner fileScanner = new Scanner(csvnonogram)){
            int rowcounter=0;
            while(fileScanner.hasNext()){
                String row = fileScanner.nextLine();
                String[] vakken = new String[grootte];
                vakken = row.split(";");
                for (int i = 0; i < vakken.length; i++) {
                    if(vakken[i].equals("O")){
                        this.achterLiggendPatroon[rowcounter][i].kleurIn();
                    }else {
                        this.achterLiggendPatroon[rowcounter][i].setDefaultWaarde();
                    }
                }
                rowcounter++;
            }
        }catch (IOException ex){
            System.out.println("Fout bij het inlezen van bestand " + csvnonogram.getName());
        }
    }

    private void leesGetallenNonogramIn(File csvGetallenNonogram) {
        System.out.println(csvGetallenNonogram.toPath());
        try {
            //inlzen bestand
            String[] lijstVakken = new String[0];
            List<String> vakjes = Files.readAllLines(csvGetallenNonogram.toPath());
            for (String vak : vakjes) {
                lijstVakken = vak.split(";");
            }

            for (int i = 0; i < lijstVakken.length; i++) {
                if(i<=grootte-1){
                    kolom[i]=lijstVakken[i];
                }
                else{
                    rij[i-grootte]=lijstVakken[i];
                }

            }


        } catch (IOException e) {
            System.out.println("Fout bij het inlezen van bestand " + csvGetallenNonogram.getName());
        }
    }

    //Voorgemaakte nonogramen
    public void nonogram1_lijn() {
        //attributen
        this.naam = "Horizontale lijn";
        this.moeilijkheidsgraad = 1;
        this.aantalIngekleurdeVakjes=5;

        //waarde rij

        File csvGetallenNonogram = new File("GetallenNonogram1.csv"); //https://www.stevebreese.com/Relative-Path-Calculator    ..\..\..\..\resources\GetallenNonogram1.csv
        leesGetallenNonogramIn(csvGetallenNonogram);

        //patroon
        File csvNonogram = new File("C:\\Users\\jules\\OneDrive\\Documenten\\KDG\\Java 1\\Gamesproject\\Nonogram\\resources\\Nonogram1.csv");
        leesNonogramIn(csvNonogram);

        toonAchterLiggendPatroon();
    }

    public void nonogram2_lijnVerticaal() {
        //attributen
        this.naam = "Verticale lijn";
        this.moeilijkheidsgraad = 2;
        this.aantalIngekleurdeVakjes=5;

        //waarde rij
        File csvGetallenNonogram = new File("C:\\Users\\jules\\OneDrive\\Documenten\\KDG\\Java 1\\Gamesproject\\Nonogram\\resources\\GetallenNonogram2.csv");
        leesGetallenNonogramIn(csvGetallenNonogram);

        //patroon
        File csvNonogram = new File("C:\\Users\\jules\\OneDrive\\Documenten\\KDG\\Java 1\\Gamesproject\\Nonogram\\resources\\Nonogram2.csv");
        leesNonogramIn(csvNonogram);

        toonAchterLiggendPatroon();
    }

    public void nonogram3() {
        this.naam = "Kruis";
        this.moeilijkheidsgraad = 3;
        this.aantalIngekleurdeVakjes = 9;

        File csvGetallenNonogram = new File("C:\\Users\\jules\\OneDrive\\Documenten\\KDG\\Java 1\\Gamesproject\\Nonogram\\resources\\GetallenNonogram3.csv");
        leesGetallenNonogramIn(csvGetallenNonogram);

        File csvNonogram = new File("C:\\Users\\jules\\OneDrive\\Documenten\\KDG\\Java 1\\Gamesproject\\Nonogram\\resources\\Nonogram3.csv");
        leesNonogramIn(csvNonogram);

        toonAchterLiggendPatroon();
    }

    public void nonogram4() {
        this.naam = "Cirkel";
        this.moeilijkheidsgraad = 4;
        this.aantalIngekleurdeVakjes = 24;

        File csvGetallenNonogram = new File("C:\\Users\\jules\\OneDrive\\Documenten\\KDG\\Java 1\\Gamesproject\\Nonogram\\resources\\GetallenNonogram4.csv");
        leesGetallenNonogramIn(csvGetallenNonogram);

        File csvNonogram = new File("C:\\Users\\jules\\OneDrive\\Documenten\\KDG\\Java 1\\Gamesproject\\Nonogram\\resources\\Nonogram4.csv");
        leesNonogramIn(csvNonogram);

        toonAchterLiggendPatroon();
    }

    public void nonogram5() {
        this.naam = "Zwaan";
        this.moeilijkheidsgraad = 5;
        this.aantalIngekleurdeVakjes = 18;

        File csvGetallenNonogram = new File("C:\\Users\\jules\\OneDrive\\Documenten\\KDG\\Java 1\\Gamesproject\\Nonogram\\resources\\GetallenNonogram5.csv");
        leesGetallenNonogramIn(csvGetallenNonogram);

        File csvNonogram = new File("C:\\Users\\jules\\OneDrive\\Documenten\\KDG\\Java 1\\Gamesproject\\Nonogram\\resources\\Nonogram5.csv");
        leesNonogramIn(csvNonogram);

        toonAchterLiggendPatroon();
    }

    public void nonogram6() {
        this.naam = "Hartje";
        this.moeilijkheidsgraad = 6;
        this.aantalIngekleurdeVakjes = 25;

        File csvGetallenNonogram = new File("C:\\Users\\jules\\OneDrive\\Documenten\\KDG\\Java 1\\Gamesproject\\Nonogram\\resources\\GetallenNonogram6.csv");
        leesGetallenNonogramIn(csvGetallenNonogram);

        File csvNonogram = new File("C:\\Users\\jules\\OneDrive\\Documenten\\KDG\\Java 1\\Gamesproject\\Nonogram\\resources\\Nonogram6.csv");
        leesNonogramIn(csvNonogram);

        toonAchterLiggendPatroon();
    }

    public void nonogram7() {
        this.naam = "Lopendmannetje";
        this.moeilijkheidsgraad = 7;
        this.aantalIngekleurdeVakjes = 37;

        File csvGetallenNonogram = new File("C:\\Users\\jules\\OneDrive\\Documenten\\KDG\\Java 1\\Gamesproject\\Nonogram\\resources\\GetallenNonogram7.csv");
        leesGetallenNonogramIn(csvGetallenNonogram);

        File csvNonogram = new File("C:\\Users\\jules\\OneDrive\\Documenten\\KDG\\Java 1\\Gamesproject\\Nonogram\\resources\\Nonogram7.csv");
        leesNonogramIn(csvNonogram);

        toonAchterLiggendPatroon();
    }

    public void nonogram8() {
        this.naam = "Schildpad";
        this.moeilijkheidsgraad = 8;
        this.aantalIngekleurdeVakjes = 37;

        File csvGetallenNonogram = new File("C:\\Users\\jules\\OneDrive\\Documenten\\KDG\\Java 1\\Gamesproject\\Nonogram\\resources\\GetallenNonogram8.csv");
        leesGetallenNonogramIn(csvGetallenNonogram);

        File csvNonogram = new File("C:\\Users\\jules\\OneDrive\\Documenten\\KDG\\Java 1\\Gamesproject\\Nonogram\\resources\\Nonogram8.csv");
        leesNonogramIn(csvNonogram);

        toonAchterLiggendPatroon();
    }

    public void nonogram9() {
        this.naam = "Poes";
        this.moeilijkheidsgraad = 9;
        this.aantalIngekleurdeVakjes = 57;

        File csvGetallenNonogram = new File("C:\\Users\\jules\\OneDrive\\Documenten\\KDG\\Java 1\\Gamesproject\\Nonogram\\resources\\GetallenNonogram9.csv");
        leesGetallenNonogramIn(csvGetallenNonogram);

        File csvNonogram = new File("C:\\Users\\jules\\OneDrive\\Documenten\\KDG\\Java 1\\Gamesproject\\Nonogram\\resources\\Nonogram9.csv");
        leesNonogramIn(csvNonogram);

        toonAchterLiggendPatroon();
    }

    public void nonogram10() {
        this.naam = "Appel";
        this.moeilijkheidsgraad = 10;
        this.aantalIngekleurdeVakjes = 59;

        File csvGetallenNonogram = new File("C:\\Users\\jules\\OneDrive\\Documenten\\KDG\\Java 1\\Gamesproject\\Nonogram\\resources\\GetallenNonogram10.csv");
        leesGetallenNonogramIn(csvGetallenNonogram);

        File csvNonogram = new File("C:\\Users\\jules\\OneDrive\\Documenten\\KDG\\Java 1\\Gamesproject\\Nonogram\\resources\\Nonogram10.csv");
        leesNonogramIn(csvNonogram);

        toonAchterLiggendPatroon();
    }
}
