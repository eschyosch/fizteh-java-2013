package ru.fizteh.fivt.students.rezepov.multifilehashmap;

import ru.fizteh.fivt.students.rezepov.commands.Command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class PackageMode {
    static void start(Map<String, Command> commandList, String[] args) {
        String[] tokens;
        List<String> commandsWithArgs;
        List<String> commandWithArgs;
        try {
            StringBuilder pileOfCommands = new StringBuilder();
            for (String commandOrArgument : args) {
                pileOfCommands.append(commandOrArgument).append(" ");
            }
            String inputLine = pileOfCommands.toString();
            tokens = inputLine.trim().split(";");
            commandsWithArgs = new ArrayList<>();
            for (int i = 0; i < tokens.length; i++) {
                if (!tokens[i].equals("") && !tokens[i].matches("\\s+")) {
                    commandsWithArgs.add(tokens[i]);
                }
            }
            for (String commandText: commandsWithArgs) {
                tokens = commandText.trim().split(" ");
                commandWithArgs = new ArrayList<>();
                for (int i = 0; i < tokens.length; i++) {
                    if (!tokens[i].equals("") && !tokens[i].matches("\\s+")) {
                        commandWithArgs.add(tokens[i]);
                    }
                }
                String commandName = commandWithArgs.get(0);
                if (!commandList.containsKey(commandName)) {
                    throw new NoSuchElementException("Неизвестная команда");
                }
                Command command = commandList.get(commandName);
                if (commandWithArgs.size() - 1 != command.getArgCount()) {
                    throw new IOException("Неверное количество аргументов");
                }
                command.run(commandWithArgs);
            }
        } catch (Exception exception) {
            System.err.print(exception.getMessage());
            System.exit(2);
        }
    }
}