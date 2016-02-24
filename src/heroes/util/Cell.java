package heroes.util;

/**
 * @author Моклев Вячеслав
 */
public class Cell {
    private CellType type;
    private WorldObject object;
    // TODO a lot of

    public Cell(CellType type, WorldObject object) {
        this.type = type;
        this.object = object;
    }

    public Cell(CellType type) {
        this(type, null);
    }

    public CellType getType() {
        return type;
    }

    public void emplaceObject(WorldObject object) {
        this.object = object;
    }

    public WorldObject getObject() {
        return object;
    }
}
