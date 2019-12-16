package be.kdg;

public class Vakje {
    private boolean ingekleurd;
    private String waarde;

    public Vakje() {
        this.ingekleurd = false;
        this.waarde = "X";
    }

    public String getWaarde() {
        return waarde;
    }

    public void setWaarde(String waarde) {
        this.waarde = waarde;
    }

    public boolean isIngekleurd() {
        return ingekleurd;
    }

    public void setIngekleurd(boolean ingekleurd) {
        this.ingekleurd = ingekleurd;
    }

    public void kleurIn(){
        this.ingekleurd = true;
    }

    public void veranderWaarde(){
        this.waarde = "O";
    }

}
