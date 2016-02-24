package heroes.util;

/**
 * @author Моклев Вячеслав
 */
public enum CellType {
    GROUND(1), SAND(3), OBSTACLE(1e9f);
    public static final float OBSTACLE_COST = 1e9f;

    public final float moveCost;

    CellType(float moveCost) {
        this.moveCost = moveCost;
    }
}
