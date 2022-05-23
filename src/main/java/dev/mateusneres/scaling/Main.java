package dev.mateusneres.scaling;

import dev.mateusneres.scaling.model.Menu;
import dev.mateusneres.scaling.utils.Logger;

public class Main {

    /*
    * Instituição: Anhanguera Educacional
    * Alunos: Mateus Neres, Leonardo de Araujo
    * Professor: Gabriel | Disciplina: Sistemas Operacionais
    * */

    public static void main(String[] args) {

        if (args.length < 2) {
            Logger.error("Você não informou o número mínimo de argumentos.");
            Logger.error("Argumentos disponíveis: -p <arquivo> -a <algoritmo> -s <lotes|interativo>");
            return;
        }

        Menu.init(args);
    }

}
