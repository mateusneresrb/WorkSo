package dev.mateusneres.scaling;

import dev.mateusneres.scaling.utils.ConsoleColors;
import dev.mateusneres.scaling.utils.Logger;

public class Main {

    public static void main(String[] args) {
        menu();
        if (args.length < 2) {
            Logger.error("Você não informou o número mínimo de argumentos.");
            System.out.println("Use: ");
            return;
        }
        System.out.println("Hello world!");

    }


    /*MENU CLASS IF DOUBLE CLICK || USE CLASS AUTORUN TO OPEN CONSOLE.*/
    public static void menu() {
        Logger.info("  __  __ ____            _ _ _             ");
        Logger.info(" |  \\/  / ___|  ___ __ _| | (_)_ __   __ _ ");
        Logger.info(" | |\\/| \\___ \\ / __/ _` | | | | '_ \\ / _` |");
        Logger.info(" | |  | |___) | (_| (_| | | | | | | | (_| |");
        Logger.info(" |_|  |_|____/ \\___\\__,_|_|_|_|_| |_|\\__, |");
        Logger.info("                                     |___/");
        Logger.info("--------------------------------------------");
        Logger.info("1. Selecionar arquivo e escalonar");
        Logger.info("2. Listar Sistemas ");
        Logger.info("3. Listar algoritmos ");
        Logger.info("4. Sair do programa!");
        Logger.info(" ");
        Logger.info(ConsoleColors.BLUE + "Escolha uma das opções acima:");
    }

}

//ALGORITMOS:
// - FIFO (SO LOTE)
// - SJF (SO LOTE)
// - RR (SO INTERATIVO) [ROUND ROBIN]
// - GARANTIDO (SO INTERATIVO)
// - LOTERIA (SO INTERATIVO)

//DEFAULT = FIFO - LOTES | ROUND-ROBIN = INTERATIVO