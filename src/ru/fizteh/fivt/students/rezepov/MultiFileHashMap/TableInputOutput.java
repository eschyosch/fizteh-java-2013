package ru.fizteh.fivt.students.rezepov.multifilehashmap;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class TableInputOutput {
    public static void readTheTables(File sourceDirectory, Map<String, TableImplementation> tables) throws IOException {
        if (!sourceDirectory.exists() || sourceDirectory.isFile()) {
            throw new IOException(sourceDirectory + " не является существующим каталогом.");
        }
        for (int index = 0; index < 16; ++index) {
            File subDirectory = new File(sourceDirectory, index + ".dir");
            if(subDirectory.isFile()) {
                throw new IOException(subDirectory + " не является существующим каталогом.");
            }
            if (!subDirectory.exists()) {
                for (int fileIndex = 0; fileIndex < 16; ++fileIndex) {
                    File tableFile = new File(subDirectory, fileIndex + ".dat");
                    if (tableFile.exists()) {
                        tables.put(tableFile.getName(), fillTheTable(tableFile));
                    }
                }
            }
        }
    }

    private static TableImplementation fillTheTable(File inputFile) throws IOException {
        TableImplementation currentTable = new TableImplementation(inputFile);
        RandomAccessFile inputStream = new RandomAccessFile(inputFile, "r");
        try {
            byte[] buffer;
            long length = inputFile.length();
            int keyLength;
            int valueLength;

            while (length > 0) {
                keyLength = inputStream.readInt();
                length -= 4;
                valueLength = inputStream.readInt();
                length -= 4;

                buffer = new byte[keyLength];
                inputStream.readFully(buffer);
                length -= buffer.length;
                String key = new String(buffer, StandardCharsets.UTF_8);

                buffer = new byte[valueLength];
                inputStream.readFully(buffer);
                length -= buffer.length;
                String value = new String(buffer, StandardCharsets.UTF_8);

                currentTable.put(key, value);
            }
        } catch(Exception exception) {
            inputStream.close();
            throw exception;
        }
        inputStream.close();
        return currentTable;
    }

    public static void writeTheTables(Map<String, String>[][] dataBase, File sourceDirectory) throws IOException {

        if (!sourceDirectory.exists() || sourceDirectory.isFile()) {
            throw new IOException(sourceDirectory + " не является существующим каталогом.");
        }
        for (int index = 0; index < 16; ++index) {
            File subDirectory = new File(sourceDirectory, index + ".dir");
            if (!subDirectory.exists() && !subDirectory.mkdir()) {
                throw new IOException("Запись в " + subDirectory + " не удалась.");
            }
            for (int fileIndex = 0; fileIndex < 16; ++fileIndex) {
                File tableFile = new File(subDirectory, fileIndex + ".dat");
                RandomAccessFile outputStream = new RandomAccessFile(tableFile, "rw");
                try {
                    outputStream.setLength(0);
                    for (String key: dataBase[index][fileIndex].keySet()) {
                        byte[] bufferKey = key.getBytes(StandardCharsets.UTF_8);
                        outputStream.writeInt(bufferKey.length);
                        byte[] bufferValue = dataBase[index][fileIndex].get(key).getBytes(StandardCharsets.UTF_8);
                        outputStream.writeInt(bufferValue.length);
                        outputStream.write(bufferKey);
                        outputStream.write(bufferValue);
                    }
                } catch (Exception exception) {
                    throw exception;
                } finally {
                    outputStream.close();
                }
            }
        }
       deleteEmptyDirectories(sourceDirectory);
    }

    public static void deleteEmptyDirectories(File directory) throws IOException {
        for (File subDirectory: directory.listFiles()) {
            if (subDirectory.length() == 0) {
                subDirectory.delete();
            } else {
                deleteEmptyDirectories(subDirectory);
            }
        }
    }
}
