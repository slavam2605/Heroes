package heroes.storage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;

/**
 * @author Моклев Вячеслав
 */
public class IconStorage {
    private static HashMap<String, BufferedImage> images;

    static {
        images = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("res\\config\\icons.cfg"));
            br.lines().forEach(s -> {
                int index = s.indexOf(' ');
                try {
                    images.put(s.substring(0, index),
                            ImageIO.read(new File("res\\icons\\" + s.substring(index).trim())));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage get(String name) {
        return images.get(name);
    }

    public static boolean contains(String name) {
        return images.containsKey(name);
    }
}
