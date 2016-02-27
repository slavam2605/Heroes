package heroes.ui.event;

/**
 * @author Моклев Вячеслав
 */
public class KeyAction {
    public static final int PRESSED = 1;
    public static final int RELEASED = 2;

    private int action;
    private int keyCode;

    public KeyAction(int action, boolean pressed) {
        this.action = action;
        keyCode = pressed ? PRESSED : RELEASED;
    }

    public int getAction() {
        return action;
    }

    public int getKeyCode() {
        return keyCode;
    }
}
