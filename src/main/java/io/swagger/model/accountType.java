package io.swagger.model;

import java.util.HashMap;
import java.util.Map;

public enum accountType {
    basic("basic"),
    saving("saving");

    private final String name;

    accountType(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
    //region Reverse lookup
    private static final Map<String, accountType> lookup = new HashMap<>();

    static {
        for (accountType status : accountType.values()) {
            lookup.put(status.name, status);
        }
    }

    public static accountType getByDisplayName(String displayName) {
        return lookup.get(displayName);
    }
    //endregion
}

