package heroes.ui.base;

import heroes.ui.util.HoldingPanel;

import java.awt.image.BufferedImage;

/**
 * @author Моклев Вячеслав
 */
public abstract class SwingComponent extends Component {
    private HoldingPanel hp;

    public SwingComponent(int x, int y, int width, int height, HoldingPanel hp) {
        super(x, y, width, height);
        this.hp = hp;
    }

    @Override
    public void paintComponent() {
        paintComponent(hp.getBf());
    }

    public abstract void paintComponent(BufferedImage bf);
}
