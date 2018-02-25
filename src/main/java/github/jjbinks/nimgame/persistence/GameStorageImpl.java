package github.jjbinks.nimgame.persistence;

import github.jjbinks.nimgame.api.exceptions.GameModeNotFoundException;
import github.jjbinks.nimgame.api.exceptions.NotFoundException;
import github.jjbinks.nimgame.model.NimGameInstance;
import github.jjbinks.nimgame.model.NimGameMode;

import java.util.HashMap;
import java.util.Map;

public class GameStorageImpl implements GameStorage {
    private Map<NimGameMode, Map<Long, NimGameInstance>> gameList = new HashMap<>();

    public GameStorageImpl() {
        for (NimGameMode gameMode : NimGameMode.values()) {
            gameList.put(gameMode, new HashMap<>());
        }
    }

    public NimGameInstance find(NimGameMode gameModeId, Long instanceId) throws NotFoundException {
        NimGameInstance nimGameInstance = gameList.get(gameModeId).get(instanceId);
        if (nimGameInstance == null) {
            throw new NotFoundException(String.format("Game with id %d can not be found.", instanceId));
        }
        return nimGameInstance;
    }

    @Override
    public void store(NimGameInstance nimGameInstance) {
        gameList.get(NimGameMode.fromValue(nimGameInstance.getGameConfiguration().getGameMode()))
                .put(nimGameInstance.getInstanceId(), nimGameInstance);
    }

    @Override
    public void remove(NimGameInstance nimGameInstance) {
        gameList.get(NimGameMode.fromValue(nimGameInstance.getGameConfiguration().getGameMode()))
                .remove(nimGameInstance.getInstanceId());
    }

    @Override
    public Map<Long, NimGameInstance> getAllGames(NimGameMode nimGameMode) throws GameModeNotFoundException {
        Map<Long, NimGameInstance> gameInstanceMap = gameList.get(nimGameMode);
        if (gameInstanceMap == null) {
            throw new GameModeNotFoundException(nimGameMode);
        }
        return gameInstanceMap;
    }


}
