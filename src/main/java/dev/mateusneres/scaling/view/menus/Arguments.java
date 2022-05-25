package dev.mateusneres.scaling.view.menus;

import dev.mateusneres.scaling.controllers.ProcessController;
import dev.mateusneres.scaling.controllers.ScallerController;
import dev.mateusneres.scaling.model.Process;
import dev.mateusneres.scaling.model.Scaller;
import dev.mateusneres.scaling.types.AlgorithmType;
import dev.mateusneres.scaling.types.SystemType;
import dev.mateusneres.scaling.utils.FileUtil;
import dev.mateusneres.scaling.utils.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Arguments {

    public static void consoleInit(String[] args) {
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

        int quantum = 50; // in milliseconds;
        if (argsList.contains("-q")) {
            int quantumSelectedPos = (argsList.indexOf("-q") + 1);
            if (quantumSelectedPos > argsList.size()) {
                Logger.error("Você precisa informar o quantum após o argumento '-q' sendo um número inteiro. '-q <quantum>'");
                System.exit(1);
                return;
            }
            try {
                quantum = Integer.parseInt(argsList.get(quantumSelectedPos));
            } catch (NumberFormatException e) {
                Logger.error("O quantum precisa ser um número!");
                System.exit(1);
                return;
            }
        }

        boolean steps = false;
        if (argsList.contains("-steps")) steps = true;

        /*Load Process*/
        List<Process> processList = ProcessController.getProcess(fileNameUtil.getAllLines());
        if (processList.isEmpty()) {
            Logger.error("Não foi possível carregar os processos no arquivo: " + fileName);
            Logger.error("Verifique se o documento está estruturado corretamente!");
            System.exit(1);
            return;
        }

        Scaller scaller = new Scaller(fileNameUtil.getFile(), steps);
        new ScallerController(scaller, algorithmType, systemType, quantum, processList).start();
    }

}
