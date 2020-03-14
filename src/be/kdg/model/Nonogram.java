package be.kdg.model;

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

    public int getMoeilijkheidsgraad() {
        return moeilijkheidsgraad;
    }

    public String getNaam() {
        return naam;
    }

    public int getGrootte(){ return this.grootte;}

    //functionele methodes
    public boolean controlleren(int frij, int fkolom) {//verder afwerken met peerTutor
        boolean rtrn = false;
        int aantaluserVakjses = 0;
        int aantalspelvkajes = 0;

        for (int rij = 1; rij <= grootte; rij++) {
            for (int kolom = 1; kolom <= grootte; kolom++) {
                int r = rij;
                int k = kolom;




                if(this.patroon[r - 1][k - 1].getWaarde().equals(this.achterLiggendPatroon[r - 1][k - 1].getWaarde())&&this.achterLiggendPatroon[r - 1][k - 1].getWaarde().equals("O")){
                    System.out.println("Juist vakje");

                    if(this.patroon[r - 1][k - 1].isIngekleurd()){
                        aantaluserVakjses++;
                        System.out.println(aantaluserVakjses);
                    }
                    if(aantaluserVakjses==this.aantalIngekleurdeVakjes){
                        System.out.println(aantaluserVakjses+"ej");
                        rtrn = true;
                        return rtrn;
                    }
                }else{
                    if(this.patroon[r - 1][k - 1].isIngekleurd()){
                        aantaluserVakjses++;
                        System.out.println(aantaluserVakjses);
                    }
                    rtrn = false;
                }

            }
        }
        return rtrn;
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

    public String[] getRij() {
        return rij;
    }

    public String[] getKolom() {
        return kolom;
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
        File csvGetallenNonogram = new File("..\\..\\..\\..\\resources\\GetallenNonogram1.csv");
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
        File csvGetallenNonogram = new File("..\\..\\..\\..\\resources\\GetallenNonogram2.csv");
        leesGetallenNonogramIn(csvGetallenNonogram);

        //patroon
        File csvNonogram = new File("C:\\Users\\jules\\OneDrive\\Documenten\\KDG\\Java 1\\Gamesproject\\Nonogram\\resources\\Nonogram2.csv");
        leesNonogramIn(csvNonogram);

        toonAchterLiggendPatroon();
    }

    //aanvullen tot 10 nonogrammen

}
