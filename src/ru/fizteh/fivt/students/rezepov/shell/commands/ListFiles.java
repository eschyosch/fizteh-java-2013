package ru.fizteh.fivt.students.rezepov.shell.commands;

import ru.fizteh.fivt.students.rezepov.shell.Shell;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class ListFiles implements Command {
    public String getName() {
        return "dir";
    }

    public int getArgCount() {
        return 0;
    }

    public void run(String[] args) throws IOException {
        if (args.length > 0) {
            throw new IOException("pwd: Too many arguments.");
        }
        PrintStream ps = new PrintStream(System.out);
        File[] entries = Shell.getAbsoluteFile().listFiles();
        if (entries != null) {
            for (File entry : entries) {
                ps.println(entry.getName());
            }
        }
    }
}