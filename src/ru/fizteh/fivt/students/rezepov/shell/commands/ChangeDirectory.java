package ru.fizteh.fivt.students.rezepov.shell.commands;

import ru.fizteh.fivt.students.rezepov.shell.Shell;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

public class ChangeDirectory implements Command {
    public String getName() {
        return "cd";
    }

    public int getArgCount() {
        return 1;
    }

    public void run(String[] args) throws IOException {
        if (args.length != 1) {
            throw new IOException("Command \"cd\" takes one argument.");
        }

        Path newDirectory = Shell.getAbsoluteFile().toPath().resolve(args[0]);
        if (!newDirectory.toFile().isDirectory()) {
            throw new FileNotFoundException("cd: Directory is not exist.");
        }
        Shell.setAbsoluteFile(newDirectory.normalize().toFile());
    }
}