package ru.fizteh.fivt.students.rezepov.shell.commands;

import java.io.IOException;

public interface Command {

    public String getName();

    int getArgCount();

    public void run(String[] args) throws IOException;
}