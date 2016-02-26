package heroes.util;

/**
 * @author Моклев Вячеслав
 */
public class Player {
    private int wood;
    private int stone;
    private int crystalls;
    private int gold;
    private Hero[] heroes;
    private boolean[][] knownTerra;
    private int id;
    private String name;

    public Player(int wood, int stone, int crystalls, int gold, Hero[] heroes, int id, String name, WorldGrid worldGrid) {
        this.wood = wood;
        this.stone = stone;
        this.crystalls = crystalls;
        this.gold = gold;
        this.heroes = heroes;
        this.id = id;
        this.name = name;
        knownTerra = new boolean[worldGrid.getW()][worldGrid.getH()];
    }

    public int getWood() {
        return wood;
    }

    public int getStone() {
        return stone;
    }

    public int getCrystalls() {
        return crystalls;
    }

    public int getGold() {
        return gold;
    }

    public Hero[] getHeroes() {
        return heroes;
    }

    public boolean[][] getKnownTerra() {
        return knownTerra;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
// TODO implementation
}
