package be.kdg.model;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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

    public Vakje[][] getPatroon() {
        return patroon;
    }

    //functionele methodes
    public boolean controlleren(int frij, int fkolom) {//verder afwerken met peerTutor
        boolean rtrn = false;
        int aantaluserVakjses = 0;
        int nietInTeklkeureVakjes=0;

        for (int rij = 0; rij < grootte; rij++) {
            for (int kolom = 0; kolom < grootte; kolom++) {

                if(patroon[rij][kolom].getWaarde().equals(this.achterLiggendPatroon[frij][fkolom].getWaarde())){
                    if(this.achterLiggendPatroon[rij][kolom].getWaarde().equals("O")){
                        System.out.println("Juist");
                        aantaluserVakjses++;
                        if(nietInTeklkeureVakjes==0){
                            rtrn = aantaluserVakjses == aantalIngekleurdeVakjes;
                        }
                    }else{
                        nietInTeklkeureVakjes++;
                        if(nietInTeklkeureVakjes==0){
                            return aantaluserVakjses == aantalIngekleurdeVakjes;
                        }
                    }
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
        String filenaam = "resources/"+gebruikersnaam+"Nonogram.csv";

        Path myFile = Paths.get(filenaam);
        if (!Files.exists(myFile)){
            try {
                Files.createFile(myFile);
            } catch (IOException ioe){
                System.out.println("Er is een fout bij het creeëren van "+filenaam);
            }
        }
        List<String> gebruikersnonogram = new ArrayList<>();
        for (int i = 0; i<5;i++) {
            gebruikersnonogram.add("X;X;X;X;X");
        }
        try {
            Files.write(myFile,gebruikersnonogram);
        } catch (IOException e) {
            System.out.println("Er is een fout bij het resetten van "+filenaam);
        }

    }

    private void leesGebruikerNonogramIn(File csvGebruikernonogram){
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

    public void resetGebruikerNonogram(String gebruikersnaam){
        String filenaam = gebruikersnaam+"Nonogram.csv";
        File csvGebruikernonogram= new File("resources/"+filenaam);
        try(Scanner fileScanner = new Scanner(csvGebruikernonogram)){
            int rowcounter=0;
            while(fileScanner.hasNext()){
                String row = fileScanner.nextLine();
                String[] vakken = new String[grootte];
                vakken = row.split(";");
                for (int i = 0; i < vakken.length; i++) {
                    this.patroon[rowcounter][i].setDefaultWaarde();
                }
                rowcounter++;
            }
        }catch (IOException ex){
            System.out.println("Fout bij het inlezen van bestand " + csvGebruikernonogram.getName());
        }
        List<String> gebruikersnonogram = new ArrayList<>();
            for (Vakje[] vakjes : patroon) {
            StringBuilder rij = new StringBuilder();
            for (Vakje vakje : vakjes) {
                rij.append(vakje.getWaarde()).append(";");
            }
            rij.deleteCharAt(grootte+grootte-1);
            gebruikersnonogram.add(rij.toString());
        }
        try {
            Files.write(csvGebruikernonogram.toPath(),gebruikersnonogram);
        } catch (IOException e) {
            System.out.println("Er is een fout bij het resetten van "+csvGebruikernonogram);
        }
    }

    public void pauzeerGebruikerNonogram(String gebruikersnaam){
        String filenaam = "resources/"+gebruikersnaam+"Nonogram.csv";
        File csvGebruikernonogram= new File(filenaam);

        List<String> gebruikersnonogram = new ArrayList<>();
        for (Vakje[] vakjes : patroon) {
            StringBuilder rij = new StringBuilder();
            for (Vakje vakje : vakjes) {
                rij.append(vakje.getWaarde()).append(";");
            }
            rij.deleteCharAt(grootte+grootte-1);
            gebruikersnonogram.add(rij.toString());
        }
        try {
            Files.write(csvGebruikernonogram.toPath(),gebruikersnonogram);
        } catch (IOException e) {
            System.out.println("Er is een fout bij het opslaan van "+csvGebruikernonogram);
        }
    }

    //Voorgemaakte nonogramen
    public void nonogram1_lijn(String gebruikersnaam) {
        //attributen
        this.naam = "Horizontale lijn";
        this.moeilijkheidsgraad = 1;
        this.aantalIngekleurdeVakjes=5;

        //waarde rij
        Path path = Paths.get("resources/GetallenNonogram1.csv");
        File csvGetallenNonogram = new File(path.toString());
        leesGetallenNonogramIn(csvGetallenNonogram);

        //Achterliggend nonogram
        path = Paths.get("resources/Nonogram1.csv");
        File csvNonogram = new File(path.toString());
        leesNonogramIn(csvNonogram);
        toonAchterLiggendPatroon();

        //Gebruikers reeds opgelsagen nonogram
        String filenaam = gebruikersnaam+"Nonogram.csv";
        File csvGebruikersNonogram = new File("resources/"+filenaam);
        leesGebruikerNonogramIn(csvGebruikersNonogram);
        toonGrid();
    }

    public void nonogram2_lijnVerticaal(String gebruikersnaam) {
        //attributen
        this.naam = "Verticale lijn";
        this.moeilijkheidsgraad = 2;
        this.aantalIngekleurdeVakjes=5;

        //waarde rij
        File csvGetallenNonogram = new File("resources/GetallenNonogram2.csv");
        leesGetallenNonogramIn(csvGetallenNonogram);

        //patroon
        File csvNonogram = new File("resources/Nonogram2.csv");
        leesNonogramIn(csvNonogram);
        toonAchterLiggendPatroon();

        //Gebruikers reeds opgelsagen nonogram
        String filenaam = gebruikersnaam+"Nonogram.csv";
        File csvGebruikersNonogram = new File("resources/"+filenaam);
        leesGebruikerNonogramIn(csvGebruikersNonogram);
        toonGrid();
    }

    public void nonogram3(String gebruikersnaam) {
        this.naam = "Kruis";
        this.moeilijkheidsgraad = 3;
        this.aantalIngekleurdeVakjes = 9;

        File csvGetallenNonogram = new File("resources/GetallenNonogram3.csv");
        leesGetallenNonogramIn(csvGetallenNonogram);

        File csvNonogram = new File("resources/Nonogram3.csv");
        leesNonogramIn(csvNonogram);
        toonAchterLiggendPatroon();

        //Gebruikers reeds opgelsagen nonogram
        String filenaam = gebruikersnaam+"Nonogram.csv";
        File csvGebruikersNonogram = new File("resources/"+filenaam);
        leesGebruikerNonogramIn(csvGebruikersNonogram);
        toonGrid();
    }

    public void nonogram4(String gebruikersnaam) {
        this.naam = "Cirkel";
        this.moeilijkheidsgraad = 4;
        this.aantalIngekleurdeVakjes = 24;

        File csvGetallenNonogram = new File("resources/GetallenNonogram4.csv");
        leesGetallenNonogramIn(csvGetallenNonogram);

        File csvNonogram = new File("resources/Nonogram4.csv");
        leesNonogramIn(csvNonogram);
        toonAchterLiggendPatroon();

        //Gebruikers reeds opgelsagen nonogram
        String filenaam = gebruikersnaam+"Nonogram.csv";
        File csvGebruikersNonogram = new File("resources/"+filenaam);
        leesGebruikerNonogramIn(csvGebruikersNonogram);
        toonGrid();
    }

    public void nonogram5(String gebruikersnaam) {
        this.naam = "Zwaan";
        this.moeilijkheidsgraad = 5;
        this.aantalIngekleurdeVakjes = 18;

        File csvGetallenNonogram = new File("resources/GetallenNonogram5.csv");
        leesGetallenNonogramIn(csvGetallenNonogram);

        File csvNonogram = new File("resources/Nonogram5.csv");
        leesNonogramIn(csvNonogram);
        toonAchterLiggendPatroon();

        //Gebruikers reeds opgelsagen nonogram
        String filenaam = gebruikersnaam+"Nonogram.csv";
        File csvGebruikersNonogram = new File("resources/"+filenaam);
        leesGebruikerNonogramIn(csvGebruikersNonogram);
        toonGrid();
    }

    public void nonogram6(String gebruikersnaam) {
        this.naam = "Hartje";
        this.moeilijkheidsgraad = 6;
        this.aantalIngekleurdeVakjes = 25;

        File csvGetallenNonogram = new File("resources/GetallenNonogram6.csv");
        leesGetallenNonogramIn(csvGetallenNonogram);

        File csvNonogram = new File("resources/Nonogram6.csv");
        leesNonogramIn(csvNonogram);
        toonAchterLiggendPatroon();

        //Gebruikers reeds opgelsagen nonogram
        String filenaam = gebruikersnaam+"Nonogram.csv";
        File csvGebruikersNonogram = new File("resources/"+filenaam);
        leesGebruikerNonogramIn(csvGebruikersNonogram);
        toonGrid();
    }

    public void nonogram7(String gebruikersnaam) {
        this.naam = "Lopendmannetje";
        this.moeilijkheidsgraad = 7;
        this.aantalIngekleurdeVakjes = 37;

        File csvGetallenNonogram = new File("resources/GetallenNonogram7.csv");
        leesGetallenNonogramIn(csvGetallenNonogram);

        File csvNonogram = new File("resources/Nonogram7.csv");
        leesNonogramIn(csvNonogram);
        toonAchterLiggendPatroon();

        //Gebruikers reeds opgelsagen nonogram
        String filenaam = gebruikersnaam+"Nonogram.csv";
        File csvGebruikersNonogram = new File("resources/"+filenaam);
        leesGebruikerNonogramIn(csvGebruikersNonogram);
        toonGrid();
    }

    public void nonogram8(String gebruikersnaam) {
        this.naam = "Schildpad";
        this.moeilijkheidsgraad = 8;
        this.aantalIngekleurdeVakjes = 37;

        File csvGetallenNonogram = new File("resources/GetallenNonogram8.csv");
        leesGetallenNonogramIn(csvGetallenNonogram);

        File csvNonogram = new File("resources/Nonogram8.csv");
        leesNonogramIn(csvNonogram);
        toonAchterLiggendPatroon();

        //Gebruikers reeds opgelsagen nonogram
        String filenaam = gebruikersnaam+"Nonogram.csv";
        File csvGebruikersNonogram = new File("resources/"+filenaam);
        leesGebruikerNonogramIn(csvGebruikersNonogram);
        toonGrid();
    }

    public void nonogram9(String gebruikersnaam) {
        this.naam = "Poes";
        this.moeilijkheidsgraad = 9;
        this.aantalIngekleurdeVakjes = 57;

        File csvGetallenNonogram = new File("resources/GetallenNonogram9.csv");
        leesGetallenNonogramIn(csvGetallenNonogram);

        File csvNonogram = new File("resources/Nonogram9.csv");
        leesNonogramIn(csvNonogram);
        toonAchterLiggendPatroon();

        //Gebruikers reeds opgelsagen nonogram
        String filenaam = gebruikersnaam+"Nonogram.csv";
        File csvGebruikersNonogram = new File("resources/"+filenaam);
        leesGebruikerNonogramIn(csvGebruikersNonogram);
        toonGrid();
    }

    public void nonogram10(String gebruikersnaam) {
        this.naam = "Appel";
        this.moeilijkheidsgraad = 10;
        this.aantalIngekleurdeVakjes = 59;

        File csvGetallenNonogram = new File("resources/GetallenNonogram10.csv");
        leesGetallenNonogramIn(csvGetallenNonogram);

        File csvNonogram = new File("resources/Nonogram10.csv");
        leesNonogramIn(csvNonogram);
        toonAchterLiggendPatroon();

        //Gebruikers reeds opgelsagen nonogram
        String filenaam = gebruikersnaam+"Nonogram.csv";
        File csvGebruikersNonogram = new File("resources/"+filenaam);
        leesGebruikerNonogramIn(csvGebruikersNonogram);
        toonGrid();
    }
}
