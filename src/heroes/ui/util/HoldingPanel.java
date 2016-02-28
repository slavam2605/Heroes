package heroes.ui.util;

import heroes.ui.base.SwingComponent;
import heroes.ui.event.KeyAction;
import heroes.ui.event.MouseAction;
import heroes.game.util.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Моклев Вячеслав
 */
public class HoldingPanel extends JPanel {
    private List<SwingComponent> layout;
    private BufferedImage bf;

    public HoldingPanel(int w, int h) {
        layout = new ArrayList<>();
        bf = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR);
        setPreferredSize(new Dimension(w, h));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point mousePoint = new Point(e.getX(), e.getY());
                for (SwingComponent component: layout) {
                    int action = 1 << (e.getButton() - 1) | MouseAction.MOUSE_DOWN;
                    if ((component.getMouseActionMask() & action) != 0) {
                        if (mousePoint.inRectangle(component.getX(), component.getY(), component.getWidth(), component.getHeight())) {
                            component.performAction(new MouseAction(action, e.getX() - component.getX(), e.getY() - component.getY()));
                        }
                    }
                }
                // TODO lol wut
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Point mousePoint = new Point(e.getX(), e.getY());
                for (SwingComponent component: layout) {
                    int action = 1 << (e.getButton() - 1) | MouseAction.MOUSE_UP;
                    if ((component.getMouseActionMask() & action) != 0) {
                        if (mousePoint.inRectangle(component.getX(), component.getY(), component.getWidth(), component.getHeight())) {
                            component.performAction(new MouseAction(action, e.getX() - component.getX(), e.getY() - component.getY()));
                        }
                    }
                }
                // TODO lol wut
                repaint();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Point mousePoint = new Point(e.getX(), e.getY());
                for (SwingComponent component: layout) {
                    if (mousePoint.inRectangle(component.getX(), component.getY(), component.getWidth(), component.getHeight())) {
                        if ((component.getMouseActionMask() & MouseAction.MOUSE_MOVE) != 0) {
                            component.performAction(new MouseAction(1 << (e.getButton() - 1), e.getX() - component.getX(), e.getY() - component.getY()));
                        }
                        component.setActive(true);
                    } else {
                        component.setActive(false);
                    }
                }
                // TODO lol wut
                repaint();
            }
        });
        addKeyListener(new KeyAdapter() {
            private void sendAction(KeyEvent e, int action) {
                for (SwingComponent component: layout) {
                    if ((component.getKeyActionMask() & action) != 0) {
                        component.performAction(new KeyAction(e.getKeyCode(), action));
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                sendAction(e, KeyAction.PRESSED);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                sendAction(e, KeyAction.RELEASED);
            }
        });
    }

    public boolean add(SwingComponent swingComponent) {
        return layout.add(swingComponent);
    }

    public BufferedImage getBf() {
        return bf;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D bfGraphics = ((Graphics2D) bf.getGraphics());
        bfGraphics.setBackground(Color.WHITE);
        bfGraphics.clearRect(0, 0, bf.getWidth(), bf.getHeight());
        for (SwingComponent component: layout) {
            component.paintComponent(bf);
        }
        g.drawImage(bf, 0, 0, null);
    }
}
