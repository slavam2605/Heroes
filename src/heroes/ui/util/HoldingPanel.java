package heroes.ui.util;

import heroes.ui.base.SwingComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Моклев Вячеслав
 */
public class HoldingPanel extends JPanel {
    private List<SwingComponent> layout;
    private BufferedImage bf;

    public HoldingPanel(int w, int h) {
        layout = new ArrayList<>();
        bf = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
        setPreferredSize(new Dimension(w, h));
    }

    public boolean add(SwingComponent swingComponent) {
        return layout.add(swingComponent);
    }

    @Override
    protected void paintComponent(Graphics g) {
        for (SwingComponent component: layout) {
            component.paintComponent(bf);
        }
    }
}
