package dev.mateusneres.scaling.view;

import dev.mateusneres.scaling.view.menus.Arguments;
import dev.mateusneres.scaling.view.menus.Interactive;

import java.util.Arrays;
import java.util.List;

public class Menu {

    public static void init(String[] args) {
        List<String> argsList = Arrays.asList(args);

        if (argsList.contains("-menu")) {
            Interactive.menuInit(args);
            return;
        }

        Arguments.consoleInit(args);
    }
}
