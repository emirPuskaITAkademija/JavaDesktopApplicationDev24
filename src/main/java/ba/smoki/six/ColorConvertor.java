package ba.smoki.six;

import java.awt.*;

public class ColorConvertor {
    public Color toColor(String color){
        //155,0,155
        String[] rgb = color.split(",");
        int red = Integer.parseInt(rgb[0]);
        int green = Integer.parseInt(rgb[1]);
        int blue = Integer.parseInt(rgb[2]);
        Color colorObject = new Color(red, green, blue);
        return colorObject;
    }

    public String toColorString(Color color){
        //155,0,155
        String colorValue = color.getRed()+","+color.getGreen()+","+color.getBlue();
        return colorValue;
    }
}
