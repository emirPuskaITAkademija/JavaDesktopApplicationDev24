package ba.smoki.three;

public class ChangeModel {
    private int index;
    private char slovo;

    public ChangeModel() {
        this(0, 'c');
    }

    public ChangeModel(int index, char slovo) {
        this.index = index;
        this.slovo = slovo;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public char getSlovo() {
        return slovo;
    }

    public void setSlovo(char slovo) {
        this.slovo = slovo;
    }
}
