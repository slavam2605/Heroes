package heroes.ui.impl;

import heroes.game.global.GlobalState;
import heroes.game.util.CellType;
import heroes.game.util.Point;
import heroes.game.util.WorldGrid;
import heroes.storage.IconStorage;
import heroes.ui.base.SwingComponent;
import heroes.ui.event.KeyAction;
import heroes.ui.event.MouseAction;
import heroes.ui.util.HoldingPanel;

import java.awt.*;
import java.awt.event.KeyEvent;
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
    private int cx;
    private int cy;

    public SwingGrid(HoldingPanel hp) {
        super(0, 0, cellSize * gridW, cellSize * gridH, hp);
        cx = 0;
        cy = 0;
        worldGrid = GlobalState.getGameState().getWorldGrid();
        mousePos = new Point(-1, -1);
        mouseActionMask = MouseAction.MOUSE_MOVE;
        mouseListeners.add(ma -> mousePos.set(ma.getX(), ma.getY()));
        keyActionMask = KeyAction.PRESSED;
        keyListeners.add(ka -> {
            System.out.println(ka.getKeyCode());
            switch (ka.getKeyCode()) {
                case KeyEvent.VK_RIGHT:
                    if (cx + 1 < worldGrid.getW() - gridW) {
                        cx++;
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if (cx > 0) {
                        cx--;
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (cy > 0) {
                        cy--;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (cy + 1 < worldGrid.getH() - gridH) {
                        cy++;
                    }
                    break;
            }
        });
    }

    @Override
    public void paintComponent(BufferedImage bf) {
        Graphics2D g = bf.createGraphics();
        for (int i = 0; i < gridW; i++) {
            for (int j = 0; j < gridH; j++) {
                g.drawImage(IconStorage.get(worldGrid.getCell(i + cx, j + cy).getType().texture), i * cellSize, j * cellSize, cellSize, cellSize, null);
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
