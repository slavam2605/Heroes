package heroes.ui.impl;

import heroes.game.util.CellType;
import heroes.game.util.Point;
import heroes.game.util.WorldGrid;
import heroes.storage.IconStorage;
import heroes.ui.base.SwingComponent;
import heroes.ui.event.MouseAction;
import heroes.ui.util.HoldingPanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Моклев Вячеслав
 */
public class SwingGrid extends SwingComponent {
    private static final int cellSize = 50;
    private static final int gridW = 15;
    private static final int gridH = 10;
    private static final int α = 4;
    private static final int β = 1;

    private Point mousePos;
    private WorldGrid worldGrid;

    public SwingGrid(WorldGrid worldGrid, HoldingPanel hp) {
        super(0, 0, cellSize * gridW, cellSize * gridH, hp);
        this.worldGrid = worldGrid;
        mousePos = new Point(-1, -1);
        mouseActionMask = MouseAction.MOUSE_MOVE;
        mouseListeners.add(ma -> mousePos.set(ma.getX(), ma.getY()));
    }

    @Override
    public void paintComponent(BufferedImage bf) {
        Graphics2D g = bf.createGraphics();
        for (int i = 0; i < gridW; i++) {
            for (int j = 0; j < gridH; j++) {
                g.drawImage(IconStorage.get(worldGrid.getCell(/* TODO */ i, j).getType() == CellType.GROUND ? "grass" : "stone"), i * cellSize, j * cellSize, cellSize, cellSize, null);
                if (mousePos.inRectangle(i * cellSize, j * cellSize, cellSize, cellSize)) {
                    int[] pixel = new int[4];
                    int[] mixColor = new int[] {0, 0, 255, 255};
                    for (int dx = 0; dx < cellSize; dx++) {
                        for (int dy = 0; dy < cellSize; dy++) {
                            pixel = bf.getRaster().getPixel(i * cellSize + dx, j * cellSize + dy, pixel);
                            for (int c = 0; c < 4; c++) {
                                pixel[c] = (α * pixel[c] + β * mixColor[c]) / (α + β);
                            }
                            bf.getRaster().setPixel(i * cellSize + dx, j * cellSize + dy, pixel);
                        }
                    }
                }
            }
        }
    }
}
