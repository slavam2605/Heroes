package heroes.ui.event;

/**
 * @author Моклев Вячеслав
 */
public class MouseAction {
    public static final int LEFT_BUTTON = 1;
    public static final int RIGHT_BUTTON = 2;
    public static final int WHEEL_BUTTON = 4;
    public static final int MOUSE_MOVE = 8;
    public static final int MOUSE_DOWN = 16;
    public static final int MOUSE_UP = 32;

    private int action;
    private int x;
    private int y;

    public MouseAction(int action, int x, int y) {
        this.action = action;
        this.x = x;
        this.y = y;
    }

    public int getAction() {
        return action;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
