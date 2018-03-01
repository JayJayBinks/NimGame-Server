package github.jjbinks.nimgame.util;

import github.jjbinks.nimgame.api.exceptions.GameModeNotFoundException;
import github.jjbinks.nimgame.model.NimGameMode;

public class GameIdUtil {
    private static Long misereGameCounter = 1L;

    private GameIdUtil() {
        //not the be instantiated
    }

    public static Long generateNewGameId(NimGameMode nimGameMode) throws GameModeNotFoundException {
        switch (nimGameMode) {
            case MISERE:
                return misereGameCounter++;
            default:
                throw new GameModeNotFoundException(nimGameMode);
        }
    }
}
