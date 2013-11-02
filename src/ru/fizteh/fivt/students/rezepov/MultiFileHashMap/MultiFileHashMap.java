package ru.fizteh.fivt.students.rezepov.multifilehashmap;

import ru.fizteh.fivt.students.rezepov.commands.*;

import java.util.HashMap;
import java.util.Map;

public class MultiFileHashMap {
    private static Map<String, Command> commandList = new HashMap<>();

    public static void main(String[] args) {
        try {
            TableProviderFactoryImplementation tableProviderFactory = new TableProviderFactoryImplementation();
            TableProviderImplementation tableProvider =
                    tableProviderFactory.create(System.getProperty("fizteh.db.dir"));
            fillCommandList(tableProvider);
            if (args.length == 0) {
                InteractiveMode.start(commandList);
            } else {
                PackageMode.start(commandList, args);
            }

        } catch (Exception exception) {
            System.err.print(exception.getMessage());
            System.exit(1);
        }
    }
    private static void fillCommandList(TableProviderImplementation tableProvider) {
        commandList.put("put", new Put(tableProvider));
        commandList.put("get", new Get(tableProvider));
        commandList.put("create", new Create(tableProvider));
        commandList.put("drop", new Drop(tableProvider));
        commandList.put("use", new Use(tableProvider));
        commandList.put("remove", new Remove(tableProvider));
        commandList.put("exit", new Exit(tableProvider));
    }
}