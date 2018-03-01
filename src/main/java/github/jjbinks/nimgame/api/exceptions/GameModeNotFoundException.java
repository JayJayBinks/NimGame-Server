package github.jjbinks.nimgame.api.exceptions;

import github.jjbinks.nimgame.model.NimGameMode;

public class GameModeNotFoundException extends NotFoundException {
    public static final String GAME_MODE_NOT_FOUND = "NOT_FOUND_002";

    public GameModeNotFoundException(NimGameMode nimGameMode) {
        super(GAME_MODE_NOT_FOUND, "Game mode could not be found " + nimGameMode);
    }
}
