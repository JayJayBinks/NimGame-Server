package github.jjbinks.nimgame.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents one game mode of the Nim game
 */
public enum NimGameMode {

    MISERE("misere");

    private String value;

    NimGameMode(String value) {
        this.value = value;
    }

    @JsonCreator
    public static NimGameMode fromValue(String text) {
        for (NimGameMode b : NimGameMode.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }
}

