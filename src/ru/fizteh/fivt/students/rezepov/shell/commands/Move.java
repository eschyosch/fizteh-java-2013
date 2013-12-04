package ru.fizteh.fivt.students.rezepov.shell.commands;

import ru.fizteh.fivt.students.rezepov.shell.Shell;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Move implements Command {
    public String getName() {
        return "mv";
    }

    public int getArgCount() {
        return 2;
    }

    private static void moveFile(File source, File dest) throws IOException {
        Path target = dest.toPath().resolve(source.getName());
        if (source.isFile()) {
            Files.copy(source.toPath(), target);
        } else {
            File[] sourceEntries = source.listFiles();
            target.toFile().mkdir();
            assert sourceEntries != null;
            for (File sourceEntry : sourceEntries) {
                moveFile(sourceEntry, target.toFile());
            }
        }
        source.delete();
    }

    private static Path validatePath(Path path) {
        File f = path.toFile();
        while (!f.isDirectory()) {
            path = path.getParent();
            f = path.toFile();
        }
        return path;
    }

    public void run(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IOException("Command \"mv\" takes one argument.");
        }
        String source = args[0];
        String dest = args[1];
        Path absolutePath = Shell.getAbsoluteFile().toPath();
        Path sourcePath = absolutePath.resolve(source).normalize();
        Path destPath = absolutePath.resolve(dest).normalize();
        if (!destPath.getParent().toFile().exists()) {
            throw new IOException("Cannot mv something on directory that doesn't exist");
        }
        if (destPath.toString().equals(sourcePath.toString())) {
            throw new IOException("Cannot move file on itself.");
        }
        if (sourcePath.toFile().isFile() && !destPath.toFile().isDirectory()) {
            if (destPath.toFile().isFile()) {
                throw new IOException("Destination file is already exist.");
            } else {
                Files.copy(sourcePath, destPath);
                sourcePath.toFile().delete();
            }
        } else if (sourcePath.toFile().isFile() && destPath.toFile().isDirectory()) {
            moveFile(sourcePath.toFile(), destPath.toFile());
        } else if (sourcePath.toFile().isDirectory() && destPath.toFile().isFile()) {
            throw new IOException("Cannot move directory to file");
        } else if (sourcePath.toFile().isDirectory() && destPath.toFile().isDirectory()) {
            if (destPath.startsWith(sourcePath)) {
                throw new IOException("Cannot move directory on itself.");
            }
            moveFile(sourcePath.toFile(), destPath.toFile());
        } else if (sourcePath.toFile().isDirectory()) {
            File[] sourceEntries = sourcePath.toFile().listFiles();
            destPath.toFile().mkdir();
            for (File sourceEntry : sourceEntries != null ? sourceEntries : new File[0]) {
                moveFile(sourceEntry, destPath.toFile());
            }
            sourcePath.toFile().delete();
            Shell.setAbsoluteFile(validatePath(absolutePath).toFile());
        } else {
            throw new IOException("Incorrect file names.");
        }
    }
}
