package github.jjbinks.nimgame.api.exceptions;

import github.jjbinks.nimgame.model.NimGameMode;

public class GameModeNotFoundException extends NotFoundException {
    public GameModeNotFoundException(NimGameMode nimGameMode) {
        super("Game mode could not be found " + nimGameMode);
    }
}
