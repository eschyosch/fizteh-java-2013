package ru.fizteh.fivt.students.rezepov.commands;

import ru.fizteh.fivt.students.rezepov.multifilehashmap.TableProviderImplementation;

import java.util.List;

public class Drop implements Command {
    private TableProviderImplementation tableProvider;

    public Drop(TableProviderImplementation currentTableProvider) {
        tableProvider = currentTableProvider;
    }

    public String getName() {
        return "drop";
    }

    public int getArgCount() {
        return 1;
    }

    public void run(List<String> args) throws IllegalArgumentException, IllegalStateException {
        if (args.size() != 1) {
            throw new IllegalArgumentException("Попробуйте в следующий раз передать ровно один аругмент.");
        }
        String tableName = args.get(1);
        if (tableProvider.getTable(tableName) == null) {
            System.err.println(tableName + " not exists");
        } else {
            tableProvider.removeTable(tableName);
            System.out.println("dropped");
        }
    }
}
