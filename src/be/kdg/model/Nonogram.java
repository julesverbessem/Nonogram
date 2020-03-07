package be.kdg.model;

import java.util.ArrayList;
import java.util.Arrays;

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
    public boolean controlleren() {//verder afwerken met peerTutor
        boolean rtrn = false;
        String userPatroon = "";
        String spelPatroon = "";
        int aantalspeVakjes = this.aantalIngekleurdeVakjes;
        int aantaluserVakjses = 0;
        System.out.println("ingekleurd patroon:"+aantalspeVakjes);

        for (int rij = 1; rij <= grootte; rij++) {
            for (int kolom = 1; kolom <= grootte; kolom++) {
                int r = rij;
                int k = kolom;
                spelPatroon = this.achterLiggendPatroon[r - 1][k - 1].getWaarde();
                userPatroon = this.patroon[r - 1][k - 1].getWaarde();


                if(this.patroon[r - 1][k - 1].isIngekleurd()){
                    aantaluserVakjses++;
                    System.out.println(aantaluserVakjses);
                    if(aantaluserVakjses==aantalspeVakjes){
                        System.out.println("x");
                        rtrn = true;
                    }
                }else{
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




    //Voorgemaakte nonogramen
    public void nonogram1_lijn() {
        //attributen
        this.naam = "Horizontale lijn";
        this.moeilijkheidsgraad = 1;
        this.aantalIngekleurdeVakjes=5;

        //waarde rij
        Arrays.fill(this.kolom, "");//ieder element heeft waarde ""
        this.rij[grootte - 1] = String.valueOf(this.kolom.length);
        //waarde kolom
        Arrays.fill(this.rij, "1");


        //patroon
        for (int rij = 1; rij <= grootte; rij++) {
            for (int kolom = 1; kolom <= grootte; kolom++) {
                int r = rij;
                int k = kolom;
                this.achterLiggendPatroon[r - 1][k - 1].setDefaultWaarde();
                if (k % grootte == 0) {
                    this.achterLiggendPatroon[r - 1][k - 1].kleurIn();
                }

            }
        }

        toonAchterLiggendPatroon();
    }

    public void nonogram2_lijnVerticaal() {
        //attributen
        this.naam = "Verticale lijn";
        this.moeilijkheidsgraad = 2;
        this.aantalIngekleurdeVakjes=5;

        //waarde rij
        Arrays.fill(this.rij, "1");
        //waarde kolom
        Arrays.fill(this.kolom, "");
        this.rij[3] = String.valueOf(this.rij.length);//derde rij

        //patroon
        for (int rij = 1; rij <= grootte; rij++) {
            for (int kolom = 1; kolom <= grootte; kolom++) {
                int r = rij;
                int k = kolom;
                this.achterLiggendPatroon[r - 1][k - 1].setDefaultWaarde();
                if (r == 3) {
                    this.achterLiggendPatroon[r - 1][k - 1].kleurIn();
                }

            }
        }
        toonAchterLiggendPatroon();

    }

    //aanvullen tot 10 nonogrammen

}
