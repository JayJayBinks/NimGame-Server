package github.jjbinks.nimgame.util;

import github.jjbinks.nimgame.model.NimGameMode;

public class GameIdUtil {
    private static Long misereGameCounter = 1L;

    private GameIdUtil() {
        //not the be instantiated
    }

    public static Long generateNewGameId(NimGameMode nimGameMode) {
        switch (nimGameMode) {
            case MISERE:
                return misereGameCounter++;
            default:
                throw new IllegalArgumentException("No Id could be generated for " + nimGameMode);
        }
    }
}
