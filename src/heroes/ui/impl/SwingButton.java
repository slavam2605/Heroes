package heroes.ui.impl;

import heroes.ui.base.SwingComponent;
import heroes.ui.event.MouseAction;
import heroes.ui.util.HoldingPanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Моклев Вячеслав
 */
public class SwingButton extends SwingComponent {
    public SwingButton(int x, int y, int width, int height, HoldingPanel hp) {
        super(x, y, width, height, hp);
    }

    @Override
    public void paintComponent(BufferedImage bf) {
        Graphics2D g = bf.createGraphics();
        if (active) {
            g.setColor(new Color(200, 200, 200));
            g.fillRect(x, y, width, height);
        }
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }
}
