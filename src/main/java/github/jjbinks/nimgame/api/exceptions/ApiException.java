package github.jjbinks.nimgame.api.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"cause", "localizedMessage", "stackTrace", "suppressed"})
public class ApiException extends Exception {

    public static final String ILLEGAL_GAME_MOVE = "BAD_REQUEST_001";
    public static final String GAME_CONFIG_NOT_SUPPORTED = "BAD_REQUEST_002";
    public static final String GAME_ALREADY_ENDED = "BAD_REQUEST_003";


    private String errorCode;

    public ApiException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }
}
