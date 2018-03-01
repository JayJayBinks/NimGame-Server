package github.jjbinks.nimgame.util;

import github.jjbinks.nimgame.api.exceptions.ApiException;
import github.jjbinks.nimgame.api.exceptions.BadRequestException;
import github.jjbinks.nimgame.model.GameEndedProperties;
import github.jjbinks.nimgame.model.GameProperties;
import github.jjbinks.nimgame.model.NimGameInstance;

import static github.jjbinks.nimgame.model.NimGameMode.MISERE;

import static github.jjbinks.nimgame.api.exceptions.ApiException.GAME_ALREADY_ENDED;

public class GameInstanceUtil {


    public void patch(NimGameInstance instanceToUpdate, NimGameInstance gamePatchRequest) throws ApiException {
        validatePatch(instanceToUpdate, gamePatchRequest);
        patchGameProperties(instanceToUpdate, gamePatchRequest.getGameProperties());
    }

    private void validatePatch(NimGameInstance instanceToUpdate, NimGameInstance gameUpdateRequest) throws ApiException {
        switch (instanceToUpdate.getGameConfiguration().getGameMode()) {
            case MISERE:
                validateMiserePatch(instanceToUpdate, gameUpdateRequest);
                break;
        }
    }

    private void patchGameProperties(NimGameInstance instanceToUpdate, GameProperties newGameProperties) {
        switch (instanceToUpdate.getGameConfiguration().getGameMode()) {
            case MISERE:
                patchGamePropertiesMisere(instanceToUpdate, newGameProperties);
                break;
        }

    }

    private void validateMiserePatch(NimGameInstance instanceToUpdate, NimGameInstance gameUpdateRequest) throws BadRequestException {
        if (instanceToUpdate.getGameEndedProperties() != null) {
            throw new BadRequestException(GAME_ALREADY_ENDED, "The game has ended and can not be altered anymore");
        }

        int matchesDiff = instanceToUpdate.getGameProperties().getMatchesRemaining() - gameUpdateRequest.getGameProperties().getMatchesRemaining();
        if (matchesDiff > instanceToUpdate.getGameConfiguration().getMaxMatchesToTake()
                || matchesDiff < instanceToUpdate.getGameConfiguration().getMinMatchesToTake()) {
            throw new BadRequestException(ApiException.ILLEGAL_GAME_MOVE, "Illegal removal of matches : " + matchesDiff);
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
