package github.jjbinks.nimgame.util;

import github.jjbinks.nimgame.api.exceptions.ApiException;
import github.jjbinks.nimgame.api.exceptions.BadRequestException;
import github.jjbinks.nimgame.model.GameEndedProperties;
import github.jjbinks.nimgame.model.GameProperties;
import github.jjbinks.nimgame.model.NimGameInstance;
import github.jjbinks.nimgame.model.NimGameMode;

public class GameInstanceUtil {

    public void patch(NimGameInstance instanceToUpdate, NimGameInstance gamePatchRequest) throws ApiException {
        validatePatch(instanceToUpdate, gamePatchRequest);
        patchGameProperties(instanceToUpdate, gamePatchRequest.getGameProperties());
    }

    private void validatePatch(NimGameInstance instanceToUpdate, NimGameInstance gameUpdateRequest) throws ApiException {
        switch (NimGameMode.fromValue(instanceToUpdate.getGameConfiguration().getGameMode())) {
            case MISERE:
                validateMiserePatch(instanceToUpdate, gameUpdateRequest);
                break;
        }
    }

    private void patchGameProperties(NimGameInstance instanceToUpdate, GameProperties newGameProperties) {
        switch (NimGameMode.fromValue(instanceToUpdate.getGameConfiguration().getGameMode())) {
            case MISERE:
                patchGamePropertiesMisere(instanceToUpdate, newGameProperties);
                break;
        }

    }

    private void validateMiserePatch(NimGameInstance instanceToUpdate, NimGameInstance gameUpdateRequest) throws BadRequestException {
        if (instanceToUpdate.getGameEndedProperties() != null) {
            throw new BadRequestException("BAD_REQUEST_006", "The game has ended and can not be altered anymore");
        }

        int matchesDiff = instanceToUpdate.getGameProperties().getMatchesRemaining() - gameUpdateRequest.getGameProperties().getMatchesRemaining();
        if (matchesDiff > instanceToUpdate.getGameConfiguration().getMaxMatchesToTake()
                || matchesDiff < instanceToUpdate.getGameConfiguration().getMinMatchesToTake()) {
            throw new BadRequestException("BAD_REQUEST_004", "Illegal removal of matches : " + matchesDiff);
        }
    }

    private void patchGamePropertiesMisere(NimGameInstance instanceToUpdate, GameProperties newGameProperties) {
        newGameProperties.setPlayerOnMove(instanceToUpdate.getGameProperties().getPlayerOnMove().changeTurn());
        instanceToUpdate.setGameProperties(newGameProperties);
        if (newGameProperties.getMatchesRemaining() == 0) {
            instanceToUpdate.setGameEndedProperties(new GameEndedProperties()
                    .winner(instanceToUpdate.getGameProperties().getPlayerOnMove()));
            instanceToUpdate.getGameProperties().setPlayerOnMove(null);
        }
    }
}
