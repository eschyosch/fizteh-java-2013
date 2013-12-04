package ru.fizteh.fivt.students.rezepov.commands;

import ru.fizteh.fivt.storage.strings.Table;
import ru.fizteh.fivt.students.rezepov.multifilehashmap.TableProviderImplementation;

import java.util.List;

public class Put implements Command {
    private TableProviderImplementation tableProvider;

    public Put(TableProviderImplementation currentTableProvider) {
        tableProvider = currentTableProvider;
    }
    public String getName() {
        return "put";
    }

    public int getArgCount() {
        return 2;
    }

    public void run(List<String> args) throws IllegalArgumentException {
        Table table = tableProvider.getActiveTable();
        if (table == null) {
            System.out.println("no table");
        } else {
            String prev = table.put(args.get(1), args.get(2));
            if (prev != null) {
                System.out.println("overwrite");
                System.out.println("old " + prev);
            } else {
                System.out.println("new");
            }
        }
    }
}
