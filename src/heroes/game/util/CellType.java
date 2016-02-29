package heroes.game.util;

/**
 * @author Моклев Вячеслав
 */
public enum CellType {
    GROUND(1, "grass"), SAND(3, ""), OBSTACLE(1e9f, "stone");
    public static final float OBSTACLE_COST = 1e9f;

    public final float moveCost;
    public final String texture;

    CellType(float moveCost, String texture) {
        this.texture = texture;
        this.moveCost = moveCost;
    }
}
