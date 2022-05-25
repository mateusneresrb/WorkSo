package dev.mateusneres.scaling.view.menus;

import dev.mateusneres.scaling.controllers.ProcessController;
import dev.mateusneres.scaling.controllers.ScallerController;
import dev.mateusneres.scaling.model.Process;
import dev.mateusneres.scaling.model.Scaller;
import dev.mateusneres.scaling.types.AlgorithmType;
import dev.mateusneres.scaling.types.SystemType;
import dev.mateusneres.scaling.utils.FileUtil;
import dev.mateusneres.scaling.utils.Logger;
import dev.mateusneres.scaling.utils.SystemUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Interactive {

    public static void menuInit(String[] args) {
        boolean finished = false;
        int option;

        do {
            headerMenuDefault();

            Scanner input = new Scanner(System.in);
            try {
                option = input.nextInt();
            } catch (NumberFormatException e) {
                Logger.error("§cA opção informada precisa ser um número!");
                return;
            }

            switch (option) {
                case 1:
                    SystemUtil.clearConsole();
                    finished = true;
                    FileUtil fileUtil = chooseFile();
                    SystemType systemType = chooseSystemType();
                    AlgorithmType algorithmType = chooseAlgorithmType(systemType);

                    List<Process> processList = ProcessController.getProcess(fileUtil.getAllLines());
                    if (processList.isEmpty()) {
                        Logger.error("Não foi possível carregar os processos no arquivo: " + fileUtil.getFile().getName());
                        Logger.error("Verifique se o documento está estruturado corretamente!");
                        System.exit(1);
                        return;
                    }

                    Scaller scaller = new Scaller(fileUtil.getFile(), false);
                    new ScallerController(scaller, algorithmType, systemType, 50, processList).start();
                    break;
                case 2:
                    SystemUtil.clearConsole();
                    Logger.info("Sistemas disponíveis:");
                    Logger.info(Arrays.stream(SystemType.values()).collect(Collectors.toList()).toString());
                    break;
                case 3:
                    SystemUtil.clearConsole();
                    Logger.info("Algoritmos disponíveis:");
                    Logger.info(Arrays.stream(AlgorithmType.values()).collect(Collectors.toList()).toString());
                    break;
                case 4:
                    Logger.info("Você decidiu fechar o programa de escalonamento!");
                    finished = true;
                    System.exit(0);
                    break;
                default:
                    Logger.error("Você escolheu uma opção inexistente!");
            }

        } while (!finished);
    }

    private static FileUtil chooseFile() {
        FileUtil fileUtil = null;
        String fileName;

        while (fileUtil == null) {
            headerMenuFileChoose();

            Scanner scanner = new Scanner(System.in);
            fileName = scanner.nextLine().toLowerCase(Locale.ROOT);

            if (!fileName.endsWith(".txt")) {
                SystemUtil.clearConsole();
                Logger.error("O programa reconhece apenas arquivos com a extensão '.txt'");
                continue;
            }

            FileUtil fileNameUtil = new FileUtil(fileName);
            if (!fileNameUtil.isExists() || fileNameUtil.isEmpty()) {
                SystemUtil.clearConsole();
                Logger.error("O arquivo informado não existe ou está vázio!");
                continue;
            }

            Logger.info("Encontramos o arquivo: " + fileName + "!");
            fileUtil = fileNameUtil;
        }

        return fileUtil;
    }

    private static SystemType chooseSystemType() {
        SystemType systemType = SystemType.BATCH;
        boolean selected = false;
        int option = 0;

        do {
            headerMenuSystemType();
            Scanner input = new Scanner(System.in);

            try {
                option = input.nextInt();
            } catch (NumberFormatException e) {
                Logger.error("§cA opção informada precisa ser um número!");
                continue;
            }

            if (option == 2) {
                SystemUtil.clearConsole();
                Logger.info("Sistema selecionado: INTERATIVO");
                systemType = SystemType.INTERATIVO;
            } else {
                Logger.info("Sistema selecionado: LOTES");
            }

            selected = true;

        } while (!selected);


        return systemType;
    }

    private static AlgorithmType chooseAlgorithmType(SystemType systemType) {
        AlgorithmType algorithmType = null;
        boolean selected = false;

        do {
            headerMenuAlgorithmType();
            Scanner input = new Scanner(System.in);
            String algorithmString = input.nextLine();

            if (!AlgorithmType.isValidAlgorithm(algorithmString)) {
                SystemUtil.clearConsole();
                Logger.error("Você informou um algoritmo inválido! Use: FIFO, SJF, ROUNDROBIN, GARANTIDO, LOTERIA");
                continue;
            }

            AlgorithmType algorithmSelectType = AlgorithmType.valueOf(algorithmString.toUpperCase(Locale.ROOT));
            if (!AlgorithmType.isValidAlgorithmBySystem(systemType, algorithmSelectType)) {
                SystemUtil.clearConsole();
                Logger.error("O algoritmo informado não está disponível para o sistema em questão.");
                continue;
            }

            algorithmType = algorithmSelectType;
            selected = true;

        } while (!selected);

        return algorithmType;
    }

    private static void headerMenuInformation() {
        Logger.info("  ___     _           _      _ _           ");
        Logger.info(" / __| __| |_  ___ __| |_  _| (_)_ _  __ _ ");
        Logger.info(" \\__ \\/ _| ' \\/ -_) _` | || | | | ' \\/ _` |");
        Logger.info(" |___/\\__|_||_\\___\\__,_|\\_,_|_|_|_||_\\__, |");
        Logger.info("                                     |___/ ");
        Logger.info("--------------------------------------------");
    }

    private static void headerMenuDefault() {
        headerMenuInformation();
        Logger.info("1. Selecionar arquivo e escalonar");
        Logger.info("2. Listar Sistemas ");
        Logger.info("3. Listar algoritmos ");
        Logger.info("4. Sair do programa!");
        Logger.info(" ");
        Logger.info("Escolha uma das opções acima:");
    }

    private static void headerMenuFileChoose() {
        headerMenuInformation();
        Logger.info(" * Digite o nome do arquivo onde os processos estão listados:");
    }

    private static void headerMenuSystemType() {
        headerMenuInformation();
        Logger.info("1. LOTES");
        Logger.info("2. INTERATIVO");
        Logger.info(" ");
        Logger.info("Escolha uma das opções acima:");
    }

    private static void headerMenuAlgorithmType() {
        headerMenuInformation();
        Logger.info(Arrays.stream(AlgorithmType.values()).collect(Collectors.toList()).toString());
        Logger.info(" * Escolha um dos algoritmos listados acima:");
    }

}
