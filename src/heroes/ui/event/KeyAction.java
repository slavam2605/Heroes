package heroes.ui.event;

/**
 * @author Моклев Вячеслав
 */
public class KeyAction {
    public static final int PRESSED = 1;
    public static final int RELEASED = 2;

    private int action;
    private int keyCode;

    public KeyAction(int keyCode, boolean pressed) {
        this.keyCode = keyCode;
        action = pressed ? PRESSED : RELEASED;
    }

    public KeyAction(int keyCode, int action) {
        this.keyCode = keyCode;
        this.action = action;
    }

    public int getAction() {
        return action;
    }

    public int getKeyCode() {
        return keyCode;
    }
}
