package heroes.game.global;

import heroes.game.engine.EngineState;
import heroes.game.util.GameState;

/**
 * @author Моклев Вячеслав
 */
public class GlobalState {
    private static EngineState engineState;
    private static GameState gameState;

    public static EngineState getEngineState() {
        return engineState;
    }

    public static void setEngineState(EngineState engineState) {
        GlobalState.engineState = engineState;
    }

    public static GameState getGameState() {
        return gameState;
    }

    public static void setGameState(GameState gameState) {
        GlobalState.gameState = gameState;
    }
}
