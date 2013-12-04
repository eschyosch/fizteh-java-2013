package ru.fizteh.fivt.students.rezepov.shell.commands;

import java.io.IOException;

public class Exit implements Command {
    public String getName() {
        return "exit";
    }

    public int getArgCount() {
        return 0;
    }

    public void run(String[] args) throws IOException {
        if (args.length > 0) {
            throw new IOException("Command \"exit\" takes no argument.");
        }
        System.exit(0);
    }
}
