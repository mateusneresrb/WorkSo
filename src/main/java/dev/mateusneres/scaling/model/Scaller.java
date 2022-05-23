package dev.mateusneres.scaling.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;

@Data
@AllArgsConstructor
public class Scaller {

    private File file;
    private boolean steps;

}
