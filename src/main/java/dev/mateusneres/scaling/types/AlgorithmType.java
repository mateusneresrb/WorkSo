package dev.mateusneres.scaling.types;

import java.util.Locale;

public enum AlgorithmType {

    FIFO, SJF, ROUNDROBIN, GARANTIDO, LOTERIA;
    
    public static boolean isValidAlgorithm(String algorithm) {
        switch (valueOf(algorithm.toUpperCase(Locale.ROOT))) {
            case FIFO:
            case SJF:
            case ROUNDROBIN:
            case GARANTIDO:
            case LOTERIA:
                return true;
            default:
                return false;
        }
    }

    public static boolean isValidAlgorithmBySystem(SystemType systemType, AlgorithmType algorithmType) {
        if (systemType == SystemType.BATCH) {
            if (isBatchAlgorithm(algorithmType)) {
                return true;
            }
        } else {
            if (isInteractiveAlgorithm(algorithmType)) {
                return true;
            }
        }
        return false;
    }


    private static boolean isBatchAlgorithm(AlgorithmType type) {
        switch (type) {
            case FIFO:
            case SJF:
                return true;
            default:
                return false;
        }
    }

    private static boolean isInteractiveAlgorithm(AlgorithmType type) {
        switch (type) {
            case ROUNDROBIN:
            case GARANTIDO:
            case LOTERIA:
                return true;
            default:
                return false;
        }
    }

    public static AlgorithmType getDefaultBySystem(SystemType systemType) {
        if (systemType == SystemType.BATCH) {
            return AlgorithmType.FIFO;
        }

        return ROUNDROBIN;
    }

}
