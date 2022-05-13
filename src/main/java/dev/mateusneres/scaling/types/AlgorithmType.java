package dev.mateusneres.scaling.types;

public enum AlgorithmType {

    FIFO, SJF, ROUNDROBIN, GARANTIDO, LOTERIA;

    public static boolean isBatchAlgorithm(AlgorithmType type) {
        switch (type) {
            case FIFO:
            case SJF:
                return true;
            default:
                return false;
        }
    }

    public static boolean isInteractiveAlgorithm(AlgorithmType type) {
        switch (type) {
            case ROUNDROBIN:
            case GARANTIDO:
            case LOTERIA:
                return true;
            default:
                return false;
        }
    }
}
