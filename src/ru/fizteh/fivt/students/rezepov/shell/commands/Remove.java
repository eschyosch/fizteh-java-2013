package ru.fizteh.fivt.students.rezepov.shell.commands;

import ru.fizteh.fivt.students.rezepov.shell.Shell;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Remove implements Command {
    public String getName() {
        return "rm";
    }

    public int getArgCount() {
        return 1;
    }

    private static void removing(Path target) throws IOException {
        File delFile = target.toFile();
        if (delFile.isFile()) {
            if (!delFile.delete()) {
                throw new IOException("rm: Cannot remove " + delFile.getName() + ".");
            }
        } else if (delFile.isDirectory()) {
            File[] filesInDir = delFile.listFiles();
            for (File fileInDir : filesInDir != null ? filesInDir : new File[0]) {
                removing(target.resolve(fileInDir.getName()));
            }
            if (!delFile.delete()) {
                throw new IOException("rm: Cannot remove " + delFile.getName() + ".");
            }
        }
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
        if (args.length != 1) {
            throw new IOException("rm: Command \"rm\" takes one argument.");
        }
        String fileName = args[0];
        Path absolutePath = Shell.getAbsoluteFile().toPath();
        Path target = absolutePath.resolve(fileName);
        if (target.toFile().exists()) {
            removing(target);
        } else {
            throw new IOException("rm: The file doesn't exist.");
        }
        Shell.setAbsoluteFile(validatePath(absolutePath).toFile());
    }
}
