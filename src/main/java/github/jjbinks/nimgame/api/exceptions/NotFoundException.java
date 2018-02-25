package github.jjbinks.nimgame.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends ApiException {

    public NotFoundException(String errorMessage) {
        super("NOT_FOUND_404", errorMessage);
    }
}
