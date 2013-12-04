package ru.fizteh.fivt.students.rezepov.multifilehashmap;

import ru.fizteh.fivt.storage.strings.Table;
import ru.fizteh.fivt.storage.strings.TableProvider;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class TableProviderImplementation implements TableProvider {
    Map<String, TableImplementation> tables = new HashMap<>();
    Table activeTable = null;
    File sourceDirectory;

    public Table getTable(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("Запрос без параметра");
        }
        return tables.get(name);
    }

    public Table createTable(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("Запрос без параметра");
        }
        return tables.put(name,new TableImplementation(new File(name)));
    }

    public void removeTable(String name) throws IllegalArgumentException, IllegalStateException {
        if (name == null) {
            throw new IllegalArgumentException("Запрос без параметра");
        }
        if (tables.get(name) == null) {
            throw new IllegalStateException("Такого и так нет");
        }
        tables.remove(name);
    }

    public void setActiveTable(String newActiveTableName) {
        activeTable = tables.get(newActiveTableName);
    }

    public void setSourceDirectory(File currentSourceDirectory) {
        sourceDirectory = currentSourceDirectory;
    }

    public File getSourceDirectory() {
        return sourceDirectory;
    }

    public Table getActiveTable() {
        return activeTable;
    }

    public Map[][] getDataBaseToWrite() {
        Map<String, String>[][] dataBase = new HashMap[16][16];
        int hashCode;
        Map<String, String> data;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                dataBase[i][j] = new HashMap<>();
            }
        }

        for (String name: tables.keySet()) {
            data = tables.get(name).getData();
            for (String key: data.keySet()) {
                hashCode = Integer.signum(key.hashCode())*key.hashCode();
                dataBase[hashCode % 16][hashCode / 16 % 16].put(key, data.get(key));
            }
        }
        return dataBase;
    }
}