package ru.fizteh.fivt.students.rezepov.shell;

import java.util.Scanner;

public class Mode {

    public static void interactive() {
        Scanner scan = new Scanner(System.in);
        do {
            System.out.print("$ ");
            String input = scan.nextLine().trim();
            String[] commands = input.split("\\s*;\\s*");
            try {
                for (String command : commands) {
                    Shell.launch(command);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } while (!Thread.currentThread().isInterrupted());
    }

    public static void batch(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            sb.append(arg).append(" ");
        }
        String input = sb.toString();
        String[] commands = input.split("\\s*;\\s*");
        for (String command : commands) {
            command = command.trim();
            if (command.equals("exit")) {
                break;
            }
            try {
                Shell.launch(command);
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.exit(1);
            }
        }
    }
}
