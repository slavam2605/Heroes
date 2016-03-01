package heroes.game.util;

import java.util.List;

/**
 * @author Моклев Вячеслав
 */
public class Hero {
    private float moveScore;
    private int mana;
    private String name;
    private Point location;
    private List<Point> path;
    // TODO private SkillState skillState;
    // TODO private SpellBook spellBook;
    // TODO private int power, magic, ...;
    // TODO private BattleUnit[] units;
    // TODO private HeroEquipment equipment;
    // TODO private TemporaryEffects[] effects;
    // TODO private Fraction fraction;


    public Hero(float moveScore, int mana, String name, Point location) {
        this.moveScore = moveScore;
        this.mana = mana;
        this.name = name;
        this.location = location;
    }

    public float getMoveScore() {
        return moveScore;
    }

    public int getMana() {
        return mana;
    }

    public String getName() {
        return name;
    }

    public Point getLocation() {
        return location;
    }

    public List<Point> getPath() {
        return path;
    }

    public void setPath(List<Point> path) {
        this.path = path;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
