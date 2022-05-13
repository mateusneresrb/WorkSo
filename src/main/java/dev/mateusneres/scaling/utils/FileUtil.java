package dev.mateusneres.scaling.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileUtil {

    private String fileName;

    public FileUtil(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return new File(String.valueOf(Paths.get(fileName)));
    }

    public Stream<String> getAllLines() {
        Stream<String> lines = null;
        try {
            lines = Files.lines(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public boolean isEmpty() {
        return new File(String.valueOf(Paths.get(fileName))).length() == 0;
    }

    public boolean isExists() {
        return new File(String.valueOf(Paths.get(fileName))).exists();
    }

}
