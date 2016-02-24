package heroes.world.objects;

import heroes.util.Point;
import heroes.util.WorldObject;
import heroes.util.WorldObjectType;

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
