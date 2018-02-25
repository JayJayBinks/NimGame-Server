package github.jjbinks.nimgame.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets AIDifficulty
 */
public enum AIDifficulty {

    DUMB("dumb"),

    SMART("smart");

    private String value;

    AIDifficulty(String value) {
        this.value = value;
    }

    @JsonCreator
    public static AIDifficulty fromValue(String text) {
        for (AIDifficulty b : AIDifficulty.values()) {
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

