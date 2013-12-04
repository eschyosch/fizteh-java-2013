package ru.fizteh.fivt.students.rezepov.commands;

import ru.fizteh.fivt.students.rezepov.multifilehashmap.TableProviderImplementation;

import java.util.List;

public class Create implements Command {
    private TableProviderImplementation tableProvider;

    public Create(TableProviderImplementation currentTableProvider) {
        tableProvider = currentTableProvider;
    }

    public String getName() {
        return "create";
    }

    public int getArgCount() {
        return 1;
    }

    public void run(List<String> args) throws IllegalArgumentException {
        String tableName = args.get(1);
        if (tableProvider.createTable(tableName) != null) {
            System.err.println(tableName + " exists");
        } else {
            System.out.println("created");
        }
    }
}
