package ba.smoki.three.image;

import javax.swing.*;
import java.net.URL;

/**
 * Generički tipovi:
 * <li>1. Unbounded </li>
 * <li>2. Bounded </li>
 */
public class IconLoader {

    private final Class clazz;

    public IconLoader(Class clazz) {
        this.clazz = clazz;
    }

    public Icon loadIcon(String imagePath) {
        ClassLoader classLoader = clazz.getClassLoader();
        URL iconAddressUrl = classLoader.getResource(imagePath);
        if (iconAddressUrl != null) {
            Icon imageIcon = new ImageIcon(iconAddressUrl);
            return imageIcon;
        }
        System.err.println("No image with path " + imagePath);
        return null;
    }
}
