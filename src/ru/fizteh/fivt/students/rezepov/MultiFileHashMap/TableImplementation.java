package ru.fizteh.fivt.students.rezepov.multifilehashmap;

import ru.fizteh.fivt.storage.strings.Table;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class TableImplementation implements Table {
    public Map<String, String> table = new HashMap<>();
    private String tableName;

    public TableImplementation(File tableFile) throws IllegalArgumentException{
        if (tableFile == null) {
            throw new IllegalArgumentException("Неверное имя файла");
        } else {
            tableName = tableFile.getName();
        }
    }

    public String getName() {
        return tableName;
    }

    public String get(String key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("Некорректный запрос");
        }
        return table.get(key);
    }

    public String put(String key, String value) throws IllegalArgumentException {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Некорректный запрос");
        }
        return table.put(key,value);
    }

    public String remove(String key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("Некорректный запрос");
        }
        return table.remove(key);
    }

    public int size() {
        return table.size();
    }

    public int commit() {
        return 0;
    }

    public int rollback() {
        return 0;
    }

    public Map<String, String> getData() {
        return table;
    }
}
