package dev.mateusneres.scaling.model;

import dev.mateusneres.scaling.types.AlgorithmType;
import dev.mateusneres.scaling.types.SystemType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;

@Data
@AllArgsConstructor
public class Scaller {

    private int id;
    private File file;
    private SystemType systemType;
    private AlgorithmType algorithmType;

    public Scaller(int id, File file) {
        this.id = id;
        this.file = file;
        this.systemType = SystemType.BATCH;
        this.algorithmType = AlgorithmType.FIFO;
    }

    public Scaller(int id, File file, SystemType systemType) {
        this.id = id;
        this.file = file;
        this.systemType = systemType;
        if (systemType == SystemType.BATCH) {
            this.algorithmType = AlgorithmType.FIFO;
        } else {
            this.algorithmType = AlgorithmType.ROUNDROBIN;
        }
    }


}
