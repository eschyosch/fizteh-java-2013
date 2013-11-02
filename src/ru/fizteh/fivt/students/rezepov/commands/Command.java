package ru.fizteh.fivt.students.rezepov.commands;

import java.util.List;

public interface Command {
    String getName();

    int getArgCount();

    void run(List<String> args) throws Exception;
}
