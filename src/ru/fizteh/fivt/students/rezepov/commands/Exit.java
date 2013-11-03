package ru.fizteh.fivt.students.rezepov.commands;

import ru.fizteh.fivt.students.rezepov.multifilehashmap.TableInputOutput;
import ru.fizteh.fivt.students.rezepov.multifilehashmap.TableProviderImplementation;

import java.io.IOException;
import java.util.List;

public class Exit implements Command {
    private TableProviderImplementation tableProvider;

    public Exit(TableProviderImplementation currentTableProvider) {
        tableProvider = currentTableProvider;
    }
    public String getName() {
        return "exit";
    }

    public int getArgCount() {
        return 0;
    }

    public void run(List<String> args) throws IllegalArgumentException, IOException {
        TableInputOutput.writeTheTables(tableProvider.getDataBaseToWrite(),tableProvider.getSourceDirectory());
        System.exit(0);
    }
}
