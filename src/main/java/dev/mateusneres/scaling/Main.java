package dev.mateusneres.scaling;

import dev.mateusneres.scaling.model.Menu;
import dev.mateusneres.scaling.utils.Logger;

public class Main {

    public static void main(String[] args) {
        if (args.length < 2) {
            Logger.error("Você não informou o número mínimo de argumentos.");
            Logger.error("Argumentos disponíveis: -p <arquivo> -a <algoritmo> -s <lotes|interativo>");
            return;
        }

        Menu.init(args);
    }


}

//ALGORITMOS:
// - FIFO (SO LOTE)
// - SJF (SO LOTE)
// - RR (SO INTERATIVO) [ROUND ROBIN]
// - GARANTIDO (SO INTERATIVO)
// - LOTERIA (SO INTERATIVO)

//DEFAULT = FIFO - LOTES | ROUND-ROBIN = INTERATIVO