package heroes.game.world.objects;

import heroes.game.util.Point;
import heroes.game.util.WorldObject;
import heroes.game.util.WorldObjectType;

/**
 * @author Моклев Вячеслав
 */
public class Portal extends WorldObject {
    private Point dest;

    public Portal(Point dest) {
        this.dest = dest;
    }

    @Override
    public WorldObjectType getType() {
        return WorldObjectType.PORTAL;
    }

    public Point getDest() {
        return dest;
    }
}
