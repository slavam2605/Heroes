package heroes.game.util;

/**
 * @author Моклев Вячеслав
 */
public enum WorldObjectType {
    PORTAL("portal");
    // TODO add WorldObjectTypes
    public final String texture;

    WorldObjectType(String texture) {
        this.texture = texture;
    }
}
