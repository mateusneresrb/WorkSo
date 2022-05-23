package dev.mateusneres.scaling.types;

import java.util.Locale;

public enum SystemType {

    BATCH, INTERATIVO;

    public static boolean isValidSystem(String systemType) {
        switch (valueOf(systemType.toUpperCase(Locale.ROOT))) {
            case BATCH:
            case INTERATIVO:
                return true;
            default:
                return false;
        }
    }

}
