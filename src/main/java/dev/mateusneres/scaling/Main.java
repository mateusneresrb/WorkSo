package dev.mateusneres.scaling;

import dev.mateusneres.scaling.view.Menu;
import dev.mateusneres.scaling.utils.AutoRunFromConsole;

public class Main {

    /*
     * Instituição: Anhanguera Educacional
     * Alunos: Mateus Neres, Leonardo de Araujo
     * Professor: Gabriel | Disciplina: Sistemas Operacionais
     * */

    public static void main(String[] args) {
        if (args.length == 0) {
            AutoRunFromConsole.runYourselfInConsole(true);
            return;
        }
        
        Menu.init(args);
    }

}
