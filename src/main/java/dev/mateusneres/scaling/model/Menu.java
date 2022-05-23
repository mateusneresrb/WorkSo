package dev.mateusneres.scaling.model;

import dev.mateusneres.scaling.types.AlgorithmType;
import dev.mateusneres.scaling.types.SystemType;
import dev.mateusneres.scaling.utils.ConsoleColors;
import dev.mateusneres.scaling.utils.FileUtil;
import dev.mateusneres.scaling.utils.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Menu {

    public static void init(String[] args) {
        List<String> argsList = Arrays.asList(args);

        if (argsList.contains("-menu")) {
            menuInteractive(args);
            return;
        }

        consoleArguments(args);
    }

    private static void menuInteractive(String[] args) {
        headerMenuInformation();

    }

    private static void consoleArguments(String[] args) {
        List<String> argsList = Arrays.asList(args);

        if (!argsList.contains("-p")) {
            Logger.error("Você precisa utilizar o parâmetro '-p' para informar o arquivo!");
            System.exit(1);
            return;
        }

        int fileNameIndex = (argsList.indexOf("-p") + 1);
        if (fileNameIndex > argsList.size()) {
            Logger.error("Você precisa informar o nome do arquivo após o argumento '-p'");
            System.exit(1);
            return;
        }

        String fileName = argsList.get(fileNameIndex).toLowerCase(Locale.ROOT);
        if (!fileName.endsWith(".txt")) {
            Logger.error("O programa reconhece apenas arquivos com a extensão '.txt'");
            System.exit(1);
            return;
        }

        FileUtil fileNameUtil = new FileUtil(fileName);
        if (!fileNameUtil.isExists() || fileNameUtil.isEmpty()) {
            Logger.error("O arquivo informado não existe ou está vázio!");
            System.exit(1);
            return;
        }

        SystemType systemType = SystemType.BATCH;
        if (argsList.contains("-s")) {

            int systemSelectedPos = (argsList.indexOf("-s") + 1);
            if (systemSelectedPos > argsList.size()) {
                Logger.error("Você precisa informar o nome do sistema após o argumento '-s'");
                System.exit(1);
                return;
            }

            String systemSelect = argsList.get(systemSelectedPos);
            if (!SystemType.isValidSystem(systemSelect)) {
                Logger.error("Você informou um sistema inválido! Use: BATCH OU INTERATIVO");
                System.exit(1);
                return;
            }

            systemType = SystemType.valueOf(systemSelect.toUpperCase(Locale.ROOT));
        }

        AlgorithmType algorithmType = AlgorithmType.getDefaultBySystem(systemType);

        if (argsList.contains("-a")) {
            int algorithmSelectedPos = (argsList.indexOf("-a") + 1);
            if (algorithmSelectedPos > argsList.size()) {
                Logger.error("Você precisa informar o nome do algoritmo após o argumento '-a'");
                System.exit(1);
                return;
            }

            String algorithmSelect = argsList.get(algorithmSelectedPos);
            if (!AlgorithmType.isValidAlgorithm(algorithmSelect)) {
                Logger.error("Você informou um algoritmo inválido! Use: FIFO, SJF, ROUNDROBIN, GARANTIDO, LOTERIA");
                System.exit(1);
                return;
            }

            AlgorithmType algorithmSelectType = AlgorithmType.valueOf(algorithmSelect.toUpperCase(Locale.ROOT));
            if (!AlgorithmType.isValidAlgorithmBySystem(systemType, algorithmSelectType)) {
                Logger.error("O algoritmo informado não está disponível para o sistema em questão.");
                System.exit(1);
                return;
            }

            algorithmType = algorithmSelectType;
        }

        System.out.println("ARQUIVO: " + fileName);
        System.out.println("SISTEMA: " + systemType);
        System.out.println("ALGORITMO: " + algorithmType);
    }


    public static void headerMenuInformation() {
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
