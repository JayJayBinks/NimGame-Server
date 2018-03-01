package github.jjbinks.nimgame.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends ApiException {

    public static final String AI_NOT_FOUND = "NOT_FOUND_001";
    public static final String GAME_NOT_FOUND = "NOT_FOUND_002";

    public NotFoundException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
