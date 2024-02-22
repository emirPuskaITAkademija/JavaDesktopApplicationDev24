package ba.smoki.four.demo;

import javax.swing.*;
import java.io.Serializable;

public class PetModel implements Serializable {
    private String petName;
    private Icon imageIcon;

    public PetModel() {
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public Icon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(Icon imageIcon) {
        this.imageIcon = imageIcon;
    }

    @Override
    public String toString() {
        return petName;
    }
}
