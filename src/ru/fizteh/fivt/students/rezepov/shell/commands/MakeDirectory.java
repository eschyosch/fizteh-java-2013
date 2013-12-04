package ru.fizteh.fivt.students.rezepov.shell.commands;

import ru.fizteh.fivt.students.rezepov.shell.Shell;

import java.io.File;
import java.io.IOException;

public class MakeDirectory implements Command {
    public String getName() {
        return "mkdir";
    }

    public int getArgCount() {
        return 1;
    }

    public void run(String[] args) throws IOException {
        if (args.length != 1) {
            throw new IOException("mkdir: Command \"mkdir\" takes one argument.");
        }
        File newDirectory = new File(args[0]);
        if (!newDirectory.isAbsolute()) {
            newDirectory = new File(Shell.getAbsoluteFile(), args[0]);
        }
        if (newDirectory.exists()) {
            throw new IOException("mkdir: Directory already exists.");
        }
        if (!newDirectory.getParentFile().exists()) {
            throw new IOException("parent directory '" + newDirectory.getParent() + "' doesn't exist");
        }

        boolean success = newDirectory.mkdir();
        if (!success) {
            throw new IOException("cannot create '" + args[0] + "': directory already exists");
        }

    }
}
