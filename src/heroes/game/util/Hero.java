package heroes.game.util;

/**
 * @author Моклев Вячеслав
 */
public class Hero {
    private float moveScore;
    private int mana;
    private String name;
    private Point location;
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
}
