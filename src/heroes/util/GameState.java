package heroes.util;

/**
 * @author Моклев Вячеслав
 */
public class GameState {
    // global storage state
    private WorldGrid worldGrid;
    private Player[] players;
    private DateState dateState;
    // current state
    private int currentPlayerId;
    private Hero currentHero;

    public GameState(WorldGrid worldGrid, Player[] players, DateState dateState) {
        this.worldGrid = worldGrid;
        this.players = players;
        this.dateState = dateState;
        currentPlayerId = 0;
        currentHero = players[currentPlayerId].getHeroes()[0];
    }

    public WorldGrid getWorldGrid() {
        return worldGrid;
    }

    public Player[] getPlayers() {
        return players;
    }

    public DateState getDateState() {
        return dateState;
    }

    public int getCurrentPlayerId() {
        return currentPlayerId;
    }

    public Hero getCurrentHero() {
        return currentHero;
    }

    public void setCurrentHero(Hero currentHero) {
        this.currentHero = currentHero;
    }

    public void nextTurn() {
        currentPlayerId++;
        if (currentPlayerId > players.length) {
            currentPlayerId = 0;
        }
        currentHero = players[currentPlayerId].getHeroes()[0];
        // TODO a lot
    }

    // TODO implementation
}
