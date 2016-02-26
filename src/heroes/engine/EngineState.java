package heroes.engine;

/**
 * @author Моклев Вячеслав
 */
public class EngineState {
    private StateKind stateKind;
    // TODO a lot

    public EngineState() {
        stateKind = StateKind.TERRA;
        // TODO
    }

    public StateKind getStateKind() {
        return stateKind;
    }
}
