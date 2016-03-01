package heroes.ui.impl;

import heroes.game.algorithm.AStar;
import heroes.game.global.GlobalState;
import heroes.game.util.Hero;
import heroes.game.util.Point;
import heroes.game.util.WorldGrid;
import heroes.game.util.WorldObject;
import heroes.storage.IconStorage;
import heroes.ui.base.SwingComponent;
import heroes.ui.event.KeyAction;
import heroes.ui.event.MouseAction;
import heroes.ui.util.HoldingPanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        mouseActionMask = MouseAction.MOUSE_MOVE | MouseAction.MOUSE_DOWN;
        mouseListeners.add(ma -> {
            if (ma.getAction() == MouseAction.MOUSE_MOVE) {
                mousePos.set(ma.getX(), ma.getY());
            }
        });
        mouseListeners.add(ma -> {
            if ((ma.getAction() & MouseAction.MOUSE_DOWN) != 0) {
                Hero currentHero = GlobalState.getGameState().getCurrentHero();
                if (currentHero == null) return;
                List<Point> list = currentHero.getPath();
                Point mousePoint = new Point(ma.getX() / cellSize, ma.getY() / cellSize);
                if (list == null || !list.get(list.size() - 1).equals(mousePoint)) {
                    list = new AStar(worldGrid).findPath(
                            currentHero.getLocation(),
                            mousePoint
                    );
                    currentHero.setPath(list);
                } else {
                    // TODO not immediately
                    currentHero.setLocation(mousePoint);
                    currentHero.setPath(null);
                }
            }
        });
        keyActionMask = KeyAction.PRESSED;
        keyListeners.add(ka -> {
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
        List<Hero> heroes = new ArrayList<>();
        Arrays.stream(GlobalState.getGameState().getPlayers())
                .forEach(p -> Arrays.stream(p.getHeroes())
                        .forEach(heroes::add));
        Graphics2D g = bf.createGraphics();
        for (int i = 0; i < gridW; i++) {
            for (int j = 0; j < gridH; j++) {
                g.drawImage(IconStorage.get(worldGrid.getCell(i + cx, j + cy).getType().texture), i * cellSize, j * cellSize, cellSize, cellSize, null);
                WorldObject object = worldGrid.getCell(i + cx, j + cy).getObject();
                if (object != null) {
                    g.drawImage(IconStorage.get(object.getType().texture), i * cellSize, j * cellSize, cellSize, cellSize, null);
                }
            }
        }
        g.setFont(new Font("Times New Roman", Font.PLAIN, cellSize));
        heroes.stream().forEach(hero -> {
            FontMetrics fm = g.getFontMetrics();
            Rectangle2D r = fm.getStringBounds("X", g);
            g.drawString("X",
                    (hero.getLocation().x - cx) * cellSize + (cellSize - (int) r.getWidth()) / 2,
                    (hero.getLocation().y - cy) * cellSize + (cellSize - (int) r.getHeight()) / 2 + fm.getAscent());
        });
        int mi = mousePos.x / cellSize;
        int mj = mousePos.y / cellSize;
        int[] pixel = new int[4];
        int[] mixColor = new int[]{0, 0, 255, 255};
        for (int dx = 0; dx < cellSize; dx++) {
            for (int dy = 0; dy < cellSize; dy++) {
                pixel = bf.getRaster().getPixel(mi * cellSize + dx, mj * cellSize + dy, pixel);
                for (int c = 0; c < 4; c++) {
                    pixel[c] = (α * pixel[c] + β * mixColor[c]) / (α + β);
                }
                bf.getRaster().setPixel(mi * cellSize + dx, mj * cellSize + dy, pixel);
            }
        }
        Hero currentHero = GlobalState.getGameState().getCurrentHero();
        List<Point> list = currentHero.getPath();
        if (list != null) {
            for (int i = 1; i < list.size(); i++) {
                Point p = list.get(i);
                Point pp = list.get(i - 1);
                g.setColor(new Color(0, 180, 0));
                g.fillRect(cellSize * (p.x - cx) + cellSize / 5, cellSize * (p.y - cy) + cellSize / 5, 3 * cellSize / 5, 3 * cellSize / 5);
                g.setColor(Color.BLACK);
                g.drawLine(cellSize * (p.x - cx) + cellSize / 2, cellSize * (p.y - cy) + cellSize / 2, cellSize * (pp.x - cx) + cellSize / 2, cellSize * (pp.y - cy) + cellSize / 2);
            }
        }
    }
}
