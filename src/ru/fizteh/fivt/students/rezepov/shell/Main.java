package ru.fizteh.fivt.students.rezepov.shell;

import ru.fizteh.fivt.students.rezepov.shell.commands.*;

public class Main {
    public static void main(String[] args) {
        Command[] commands = new Command[] {new ChangeDirectory(), new Copy(), new Exit(), new ListFiles(),
                                            new MakeDirectory(), new Move(), new PrintWorkingDirectory(), new Remove()};
        Shell.createShell(args, commands);
    }
}
