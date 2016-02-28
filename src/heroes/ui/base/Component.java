package heroes.ui.base;

import heroes.game.util.Point;
import heroes.ui.event.KeyAction;
import heroes.ui.event.KeyListener;
import heroes.ui.event.MouseAction;
import heroes.ui.event.MouseListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Моклев Вячеслав
 */
public abstract class Component {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected boolean active;
    protected List<KeyListener> keyListeners;
    protected List<MouseListener> mouseListeners;
    protected int keyActionMask;
    protected int mouseActionMask;

    public Component(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        visible = false;
        active = false;
        keyListeners = new ArrayList<>();
        mouseListeners = new ArrayList<>();
        keyActionMask = 0;
        mouseActionMask = 0;
    }

    public void setPosition(Point p) {
        x = p.x;
        y = p.y;
    }

    public void setSize(Point p) {
        width = p.x;
        height = p.y;
    }

    public void performAction(MouseAction mouseAction) {
        for (MouseListener listener: mouseListeners) {
            listener.performAction(mouseAction);
        }
    }

    public void performAction(KeyAction keyAction) {
        for (KeyListener listener: keyListeners) {
            listener.performAction(keyAction);
        }
    }

    public int getKeyActionMask() {
        return keyActionMask;
    }

    public int getMouseActionMask() {
        return mouseActionMask;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isVisible() {
        return visible;
    }

    public abstract void paintComponent();
}
