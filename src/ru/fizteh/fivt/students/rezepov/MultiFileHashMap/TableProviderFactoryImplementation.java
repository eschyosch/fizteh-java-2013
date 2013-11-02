package ru.fizteh.fivt.students.rezepov.multifilehashmap;

import ru.fizteh.fivt.storage.strings.TableProviderFactory;

import java.io.File;

public class TableProviderFactoryImplementation implements TableProviderFactory {

    public TableProviderImplementation create(String dir) {
        TableProviderImplementation newTableProvider = new TableProviderImplementation();
        File sourceDirectory = new File(dir);

        try {
        TableInputOutput.readTheTables(sourceDirectory, newTableProvider.tables);
        } catch (Exception exception) {
            System.err.print(exception.getMessage());
            System.exit(1);
        }
        newTableProvider.setSourceDirectory(sourceDirectory);
        return newTableProvider;
    }
}
