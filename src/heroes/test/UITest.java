package heroes.test;

import heroes.game.global.GlobalState;
import heroes.game.util.*;
import heroes.game.world.objects.Portal;
import heroes.ui.impl.SwingButton;
import heroes.ui.impl.SwingGrid;
import heroes.ui.util.HoldingPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @author Моклев Вячеслав
 */
public class UITest {
    private static final int gridW = 50;
    private static final int gridH = 50;

    public static void main(String[] args) {
        WorldGrid worldGrid = new WorldGrid(gridW, gridH);
        for (int i = 0; i < gridW; i++) {
            for (int j = 0; j < gridH; j++) {
                worldGrid.setCell(i, j, new Cell(CellType.GROUND));
            }
        }

        worldGrid.setCell(5, 1, new Cell(CellType.OBSTACLE));
        worldGrid.setCell(5, 2, new Cell(CellType.OBSTACLE));
        worldGrid.setCell(5, 3, new Cell(CellType.OBSTACLE));
        worldGrid.setCell(5, 4, new Cell(CellType.OBSTACLE));
        worldGrid.setCell(5, 5, new Cell(CellType.OBSTACLE));
        worldGrid.setCell(4, 5, new Cell(CellType.OBSTACLE));
        worldGrid.setCell(3, 5, new Cell(CellType.OBSTACLE));

        worldGrid.getCell(4, 0).emplaceObject(new Portal(new heroes.game.util.Point(8, 7)));

        GameState gameState = new GameState(worldGrid, new Player[] {new Player(0, 0, 0, 0, new Hero[] {null}, 0, "kek", worldGrid)}, null);
        GlobalState.setGameState(gameState);

        // --------------------------------------------------------------------

        JFrame frame = new JFrame("Heroes");
        HoldingPanel panel = new HoldingPanel(15 * 50, 10 * 50);
        panel.add(new SwingGrid(panel));
        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - panel.getSize().width / 2, dim.height / 2 - panel.getSize().height / 2);
        frame.setVisible(true);
    }
}
