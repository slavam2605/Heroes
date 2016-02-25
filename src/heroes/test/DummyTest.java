package heroes.test;

import java.util.List;

import heroes.algorithm.AStar;
import heroes.util.*;
import heroes.util.Point;
import heroes.world.objects.Portal;

import javax.swing.*;
import java.awt.*;

/**
 * @author Моклев Вячеслав
 */
public class DummyTest extends JPanel {
    private static final int cellSize = 50;
    private static final int gridW = 10;
    private static final int gridH = 10;

    private WorldGrid worldGrid;

    public void start() {
        worldGrid = new WorldGrid(gridW, gridH);
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

        worldGrid.getCell(4, 0).emplaceObject(new Portal(new Point(8, 7)));

        setPreferredSize(new Dimension(cellSize * gridW, cellSize * gridH));
        JFrame frame = new JFrame("Heroes");
        frame.add(this);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        for (int i = 0; i < worldGrid.getW(); i++) {
            for (int j = 0; j < worldGrid.getH(); j++) {
                g.drawRect(cellSize * i, cellSize * j, cellSize, cellSize);
                if (worldGrid.getCell(i, j).getType().moveCost > 1) {
                    g.setColor(new Color(150, 150, 150));
                    g.fillRect(cellSize * i, cellSize * j, cellSize, cellSize);
                    g.setColor(Color.BLACK);
                }
                if (worldGrid.getCell(i, j).getType().moveCost > 10) {
                    g.fillRect(cellSize * i, cellSize * j, cellSize, cellSize);
                }
                if (worldGrid.getCell(i, j).getObject() != null && worldGrid.getCell(i, j).getObject().getType() == WorldObjectType.PORTAL) {
                    g.setColor(new Color(0, 150, 255));
                    g.fillRect(cellSize * i, cellSize * j, cellSize, cellSize);
                    g.setColor(Color.BLACK);
                }
            }
        }
        List<Point> list = new AStar(worldGrid).findPath(new Point(4, 4), new Point(9, 9));
        if (list == null) {
            return;
        }
        for (int i = 1; i < list.size(); i++) {
            Point p = list.get(i);
            Point pp = list.get(i - 1);
            g.setColor(new Color(0, 180, 0));
            g.fillRect(cellSize * p.x + cellSize / 5, cellSize * p.y + cellSize / 5, 3 * cellSize / 5, 3 * cellSize / 5);
            g.setColor(Color.BLACK);
            g.drawLine(cellSize * p.x + cellSize / 2, cellSize * p.y + cellSize / 2, cellSize * pp.x + cellSize / 2, cellSize * pp.y + cellSize / 2);
        }
    }

    public static void main(String[] args) {
        new DummyTest().start();
    }
}
