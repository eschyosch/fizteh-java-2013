package ru.fizteh.fivt.students.rezepov.commands;

import ru.fizteh.fivt.storage.strings.Table;
import ru.fizteh.fivt.students.rezepov.multifilehashmap.TableProviderImplementation;

import java.util.List;

public class Remove implements Command {
    private TableProviderImplementation tableProvider;

    public Remove(TableProviderImplementation currentTableProvider) {
        tableProvider = currentTableProvider;
    }

    public String getName() {
        return "remove";
    }

    public int getArgCount() {
        return 1;
    }

    public void run(List<String> args) throws Exception {
        if (args.size() != 1) {
            throw new IllegalArgumentException("Не жадничай, три по одному.");
        }
        Table table = tableProvider.getActiveTable();
        if (table == null) {
            System.out.println("no table");
        } else {
            String value = table.remove(args.get(1));
            if (value != null) {
                System.out.println("removed");
            } else {
                System.out.println("not found");
            }
        }
    }
}
