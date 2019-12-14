package be.kdg;

public class Vakje {
    private boolean ingekleurd;

    public Vakje() {
        this.ingekleurd = false;
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

}
