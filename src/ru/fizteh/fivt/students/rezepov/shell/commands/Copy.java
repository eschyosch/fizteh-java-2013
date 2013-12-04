package ru.fizteh.fivt.students.rezepov.shell.commands;

import java.io.*;

public class Copy implements Command {
    public String getName() {
        return "cp";
    }

    public int getArgCount() {
        return 2;
    }

    public void run(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IOException("Command \"cp\" takes two argument.");
        }
        File source = new File(args[0]);
        File destination = new File(args[1]);
        if (!source.exists()) {
            throw new IOException("Cannot copy '" + source.getName() + "': no such file or directory.");
        }
        if (destination.isFile() && source.isDirectory()) {
            throw new IOException("Cannot overwrite non-directory '" + destination.getName() +
                                  "' with directory '" + source.getName() + "'.");
        }
        if (source.equals(destination)) {
            throw new IOException("Do not copy '" + source.getName() + "' to itself.");
        }
        if (!destination.getParentFile().exists()) {
            throw new IOException("Destination '" + destination.getName() + "' doesn't exist.");
        }

        if (source.isFile()) {
            if (!destination.exists() || destination.isFile()) {
                copyFileToFile(source, destination);
            } else {
                copyFileToFolder(source, destination);
            }
        } else {
            copyFolderToFolder(source, destination);
        }
    }

    public static void copyFileToFile(File source, File destination) throws IOException {
        if (destination.exists()) {
            throw new IOException("failed to copy '" + destination.getName() + "': already exists");
        }
        try {
            destination.createNewFile();
            InputStream inputStream = new FileInputStream(source);
            try {
                OutputStream outputStream = new FileOutputStream(destination);
                try {
                    byte[] buffer = new byte[4096];
                    int read;
                    while ((read = inputStream.read(buffer)) > 0) {
                        outputStream.write(buffer, 0, read);
                    }
                } finally {
                    outputStream.close();
                }
            } finally {
                inputStream.close();
            }
        } catch (IOException e) {
            throw new IOException("cannot copy", e);
        }
    }

    public static void copyFileToFolder(File source, File destination) throws IOException {
        File copy = new File(destination, source.getName());
        copyFileToFile(source, copy);

    }

    public static void copyFolderToFolder(File source, File destination) throws IOException {
        File copy;
        if (!destination.exists()) {
            copy = destination;
        } else {
            copy = new File(destination, source.getName());
        }
        if (copy.exists()) {
            throw new IOException("failed to copy '" + copy.getName() + "': already exists");
        }
        copy.mkdirs();
        File[] files = source.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isFile()) {
                    copyFileToFolder(f, copy);
                }
                if (f.isDirectory()) {
                    copyFolderToFolder(f, copy);
                }
            }
        }
    }
}