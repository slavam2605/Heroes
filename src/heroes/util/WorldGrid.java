package heroes.util;

/**
 * @author Моклев Вячеслав
 */
public class WorldGrid {
    private Cell[][] a;
    private int w;
    private int h;

    public WorldGrid(int w, int h) {
        a = new Cell[w][h];
        this.w = w;
        this.h = h;
        // TODO a lot of
    }

    public Cell getCell(int i, int j) {
        return a[i][j];
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    // TODO remove
    public void setCell(int i, int j, Cell cell) {
        a[i][j] = cell;
    }

}
