package heroes.game.util;

/**
 * @author Моклев Вячеслав
 */
public class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        this(0, 0);
    }

    public Point shift(int dx, int dy) {
        return new Point(x + dx, y + dy);
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean inRectangle(int x, int y, int w, int h) {
        return this.x >= x && this.x < x + w && this.y >= y && this.y < y + h;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
