package heroes.algorithm;

import heroes.util.*;
import heroes.world.objects.Portal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import static java.lang.Math.*;

/**
 * @author Моклев Вячеслав
 */
public class AStar {

    private static List<Point> portals;
    private static WorldGrid currentGrid;

    private static float grid_h(Point from, Point to) {
        int Δx = abs(from.x - to.x);
        int Δy = abs(from.y - to.y);
        return 0.414213562f * min(Δx, Δy) + max(Δx, Δy);
    }

    private static float h(Point from, Point to) {
        float straight = grid_h(from, to);
        for (Point portal: portals) {
            float port = grid_h(from, portal) + grid_h(((Portal) currentGrid.getCell(portal.x, portal.y).getObject()).getDest(), to);
            if (port < straight) {
                straight = port;
            }
        }
        return straight;
    }

    public static List<Point> findPath(WorldGrid worldGrid, Point from, Point to) {
        int w = worldGrid.getW();
        int h = worldGrid.getH();
        // initialize static variables
        currentGrid = worldGrid;
        // find all portals on map
        portals = new ArrayList<>();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                WorldObject currentObject = worldGrid.getCell(i, j).getObject();
                if (currentObject != null && currentObject.getType() == WorldObjectType.PORTAL) {
                    portals.add(new Point(i, j));
                }
            }
        }
        boolean[][] used = new boolean[w][h];
        float[][] g = new float[w][h];
        float[][] f = new float[w][h];
        Point[][] parent = new Point[w][h];
        PriorityQueue<Point> Q = new PriorityQueue<>((p1, p2) -> Float.compare(f[p1.x][p1.y], f[p2.x][p2.y]));
        Q.add(from);
        g[from.x][from.y] = 0;
        f[from.x][from.y] = h(from, to);
        used[from.x][from.y] = true;
        while (!Q.isEmpty()) {
            Point current = Q.poll();
            if (current.equals(to)) {
                return formList(from, to, parent);
            }
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if ((i == 0 && j == 0)
                            || current.x + i < 0 || current.x + i >= w
                            || current.y + j < 0 || current.y + j >= h) {
                        continue;
                    }
                    if (worldGrid.getCell(current.x + i, current.y + j).getType().moveCost == CellType.OBSTACLE_COST) {
                        continue;
                    }
                    performEdge(d(worldGrid, current, i, j), to, used, g, f, parent, Q, current, i, j);
                }
            }
            WorldObject currentObject = worldGrid.getCell(current.x, current.y).getObject();
            if (currentObject != null && currentObject.getType() == WorldObjectType.PORTAL) {
                Portal portal = ((Portal) currentObject);
                performEdge(0, to, used, g, f, parent, Q, current, portal.getDest().x - current.x, portal.getDest().y - current.y);
            }
        }
        return null;
    }

    private static void performEdge(float d, Point to, boolean[][] used, float[][] g, float[][] f, Point[][] parent, PriorityQueue<Point> q, Point current, int i, int j) {
        float newLength = g[current.x][current.y] + d;
        if (!used[current.x + i][current.y + j] || newLength < g[current.x + i][current.y + j]) {
            parent[current.x + i][current.y + j] = current;
            g[current.x + i][current.y + j] = newLength;
            f[current.x + i][current.y + j] = newLength + h(current.shift(i, j), to);
            if (!used[current.x + i][current.y + j]) {
                q.add(current.shift(i, j));
                used[current.x + i][current.y + j] = true;
            }
        }
    }

    private static float d(WorldGrid worldGrid, Point current, int i, int j) {
        return 0.5f * (
                worldGrid.getCell(current.x, current.y).getType().moveCost
                        +
                        worldGrid.getCell(current.x + i, current.y + j).getType().moveCost
        ) * (abs(i) + abs(j) == 2 ? 1.414213562f : 1);
    }

    private static List<Point> formList(Point from, Point to, Point[][] parent) {
        List<Point> list = new ArrayList<>();
        Point current = to;
        while (!current.equals(from)) {
            list.add(current);
            current = parent[current.x][current.y];
        }
        list.add(current);
        Collections.reverse(list);
        return list;
    }

}
