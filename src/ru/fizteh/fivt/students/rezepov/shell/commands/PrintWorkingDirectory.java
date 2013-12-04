package ru.fizteh.fivt.students.rezepov.shell.commands;

import ru.fizteh.fivt.students.rezepov.shell.Shell;

import java.io.IOException;

public class PrintWorkingDirectory implements Command {
    public String getName() {
        return "pwd";
    }

    public int getArgCount() {
        return 0;
    }

    public void run(String[] args) throws IOException {
        if (args.length > 0) {
            throw new IOException("Too many arguments.");
        }
        System.out.println(Shell.getAbsoluteFile());
    }
}