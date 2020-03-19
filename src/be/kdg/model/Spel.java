package be.kdg.model;

import javafx.scene.image.Image;

import java.time.LocalDateTime;

public class Spel {
    Nonogram mijnNonogram;

    public int startSpel(Gebruiker speler) {
        switch (speler.getLevel()){
            case 1:
                mijnNonogram = new Nonogram(5);
                mijnNonogram.nonogram1_lijn(speler.getGebruikersnaam());
                break;
            case 2:
                mijnNonogram = new Nonogram(5);
                mijnNonogram.nonogram2_lijnVerticaal(speler.getGebruikersnaam());
                break;
            case 3:
                mijnNonogram = new Nonogram(5);
                mijnNonogram.nonogram3(speler.getGebruikersnaam());
                break;
            case 4:
                mijnNonogram = new Nonogram(6);
                mijnNonogram.nonogram4(speler.getGebruikersnaam());
                break;
            case 5:
                mijnNonogram = new Nonogram(10);
                mijnNonogram.nonogram5(speler.getGebruikersnaam());
                break;
            case 6:
                mijnNonogram = new Nonogram(10);
                mijnNonogram.nonogram6(speler.getGebruikersnaam());
                break;
            case 7:
                mijnNonogram = new Nonogram(10);
                mijnNonogram.nonogram7(speler.getGebruikersnaam());
                break;
            case 8:
                mijnNonogram = new Nonogram(10);
                mijnNonogram.nonogram8(speler.getGebruikersnaam());
                break;
            case 9:
                mijnNonogram = new Nonogram(10);
                mijnNonogram.nonogram9(speler.getGebruikersnaam());
                break;
            case 10:
                mijnNonogram = new Nonogram(10);
                mijnNonogram.nonogram10(speler.getGebruikersnaam());
                break;
        }
        return  mijnNonogram.getGrootte();
    }
    public void startVolgendSpel(Gebruiker speler){
        int lvl = speler.getLevel();
        int lvlUp = lvl+1;

        speler.setLevel(lvlUp);
        speler.setDatum(LocalDateTime.now());
        mijnNonogram.resetGebruikerNonogram(speler.getGebruikersnaam());
    }

    public Image toonNonogramFoto(Gebruiker speler){
        Image foto = null;
        switch (speler.getLevel()-1){
            case 1:
                foto = new Image("foto/VerticaleLijn.jpg");
                break;
            case 2:
                foto = new Image("foto/horizontaleLijn.png");
                break;
            case 3:
                foto = new Image("foto/Kruis.png");
                break;
            case 4:
                foto = new Image("foto/Zwaan.jpg");
                break;
            case 5:
                foto = new Image("foto/Cirkel.png");
                break;
            case 6:
                foto = new Image("foto/Hartje.jpg");
                break;
            case 7:
                foto = new Image("Hardloper.jpg");
                break;
            case 8:
                foto = new Image("foto/Schildpad.jpg");
                break;
            case 9:
                foto = new Image("foto/Kat.jpg");
                break;
            case 10:
                foto = new Image("foto/Appel.jpg");
                break;
        }
        return  foto;

    }

    public Nonogram getMijnNonogram(){return mijnNonogram;}
}
