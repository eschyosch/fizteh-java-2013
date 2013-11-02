package ru.fizteh.fivt.students.rezepov.commands;

import ru.fizteh.fivt.students.rezepov.multifilehashmap.TableProviderImplementation;

import java.util.List;

public class Use implements Command {
    TableProviderImplementation tableProvider;

    public Use(TableProviderImplementation currentTableProvider) {
        tableProvider = currentTableProvider;
    }

    public String getName() {
        return "use";
    }

    public int getArgCount() {
        return 1;
    }

    public void run(List<String> args) {
        if (args.size() != 1) {
            throw new IllegalArgumentException("Нельзя объять всё необъятное, используй только одну.");
        }
        if (tableProvider.getTable(args.get(1)) != null) {
            tableProvider.setActiveTable(args.get(1));
            System.out.println("using " + args.get(1));
        } else {
            System.err.println(args.get(1) + " not exists");
        }
    }
}
