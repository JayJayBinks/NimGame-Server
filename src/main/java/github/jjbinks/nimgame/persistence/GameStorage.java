package github.jjbinks.nimgame.persistence;

import github.jjbinks.nimgame.api.exceptions.GameModeNotFoundException;
import github.jjbinks.nimgame.api.exceptions.NotFoundException;
import github.jjbinks.nimgame.model.NimGameInstance;
import github.jjbinks.nimgame.model.NimGameMode;

import java.util.Map;

public interface GameStorage {
    NimGameInstance find(NimGameMode gameModeId, Long instanceId) throws NotFoundException;
    void store(NimGameInstance nimGameInstance);
    void remove(NimGameInstance nimGameInstance);

    Map<Long, NimGameInstance> getAllGames(NimGameMode nimGameMode) throws GameModeNotFoundException;
}
