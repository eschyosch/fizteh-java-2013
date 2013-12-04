package ru.fizteh.fivt.students.rezepov.shell;

import ru.fizteh.fivt.students.rezepov.shell.commands.Command;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Shell {
    private static File absoluteFile;
    private static HashMap<String, Command> commands = new HashMap<>();

    public static void createShell(String[] args, Command[] newCommands) {
        absoluteFile = new File("").getAbsoluteFile();
        for(Command command: newCommands) {
            commands.put(command.getName(), command);
        }
        if (args.length == 0) {
            Mode.interactive();
        } else {
            Mode.batch(args);
        }
    }

    public static void launch(String input) throws IOException {
        String command = getCommand(input.trim());
        String[] args = getArgs(input.trim());
        if (command.isEmpty()) {
            throw new IOException("Empty input.");
        }
        if (!commands.containsKey(command)) {
            throw new IOException("Wrong command.");
        }
        if (args.length != commands.get(command).getArgCount()) {
            if (commands.get(command).getArgCount() != -1) {
                throw new IOException("Incorrect argument count.");
            }
        }
        commands.get(command).run(args);
    }

    private static String getCommand(String input) {
        int start = 0;
        int finish = input.indexOf(" ");
        if (finish == -1) {
            finish = input.length();
        }
        return input.substring(start, finish);
    }

    private static String[] getArgs(String input) {
        int start = input.indexOf(" ");
        if (start == -1) {
            return new String[0];
        }
        String[] result = input.substring(start + 1, input.length()).trim().split("\\s+");
        for (int i = 0; i < result.length; ++i) {
            result[i] = result[i].trim();
        }
        return result;
    }

    public static void setAbsoluteFile(File newAbsoluteFile) {
        absoluteFile = newAbsoluteFile;
    }

    public static File getAbsoluteFile() {
        return absoluteFile;
    }
}
