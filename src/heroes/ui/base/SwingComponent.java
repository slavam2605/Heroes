package heroes.ui.base;

import java.awt.image.BufferedImage;

/**
 * @author Моклев Вячеслав
 */
public abstract class SwingComponent extends Component {
    private BufferedImage bf;

    public SwingComponent(int x, int y, int width, int height, BufferedImage bf) {
        super(x, y, width, height);
        this.bf = bf;
    }

    @Override
    public void paintComponent() {
        paintComponent(bf);
    }

    public abstract void paintComponent(BufferedImage bf);
}
