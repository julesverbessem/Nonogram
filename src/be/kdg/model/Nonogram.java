package be.kdg.model;

import java.util.Arrays;

public class Nonogram {
    private int moeilijkheidsgraad;
    private String naam;
    private int grootte;
    private Vakje[][] patroon;
    private Vakje[][] achterLiggendPatroon;
    private String[] rij;
    private String[] kolom;

    public Nonogram(int grootte) {
        this.patroon = new Vakje[grootte][grootte];
        this.achterLiggendPatroon = new Vakje[grootte][grootte];
        this.rij = new String[grootte];
        this.kolom = new String[grootte];
        this.grootte = grootte;

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

        for (int rij = 1; rij <= grootte; rij++) {
            for (int kolom = 1; kolom <= grootte; kolom++) {
                int r = rij;
                int k = kolom;
                userPatroon = this.achterLiggendPatroon[r - 1][k - 1].getWaarde();
                spelPatroon = this.patroon[r - 1][k - 1].getWaarde();

                if (userPatroon.equals(spelPatroon)) {//vast
                    rtrn = true;
                } else {
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

    public String kleurIn(int rij, int kolom) {
        String message;
        if (rij <= grootte && kolom <= grootte) {
            if (achterLiggendPatroon[rij - 1][kolom - 1].getWaarde().equals("O")) {
                message = "juist";
                patroon[rij - 1][kolom - 1].kleurIn();
                toonGrid();
            } else {
                message = "fout";
                patroon[rij - 1][kolom - 1].kleurIn();
                toonGrid();

            }
        } else {
            message = "Ingevoerd getal is te groot!";
        }

        return message;
    }

    public void duidAan(int rij, int kolom) {
        if (!patroon[rij - 1][kolom - 1].getWaarde().equals("O")) {
            patroon[rij - 1][kolom - 1].duidAan();
            toonGrid();
        } else {
            System.out.println("U heeft dit al ingekleurd");
            toonGrid();
        }

    }


    //Voorgemaakte nonogramen
    public void nonogram1_lijn() {
        //attributen
        this.naam = "Horizontale lijn";
        this.moeilijkheidsgraad = 1;

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
