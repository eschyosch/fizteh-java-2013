package ru.fizteh.fivt.students.rezepov.commands;

import ru.fizteh.fivt.storage.strings.Table;
import ru.fizteh.fivt.students.rezepov.multifilehashmap.TableProviderImplementation;

import java.util.List;

public class Get implements Command {
    private TableProviderImplementation tableProvider;

    public Get(TableProviderImplementation currentTableProvider) {
        tableProvider = currentTableProvider;
    }

    public String getName() {
        return "get";
    }

    public int getArgCount() {
        return 1;
    }

    public void run(List<String> args) throws Exception {
        Table table = tableProvider.getActiveTable();
        if (table == null) {
            System.out.println("no table");
        } else {
            String value = table.get(args.get(1));
            if (value != null) {
                System.out.println("found");
                System.out.println(value);
            } else {
                System.out.println("not found");
            }
        }
    }
}
