package battleships.console;

import org.graalvm.compiler.replacements.nodes.PureFunctionMacroNode;

public class Color {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public void red(String msg) {
        System.out.println(RED + msg + RESET);
    }

    public void black(String msg) {
        System.out.println(GREEN + msg + RESET);
    }

    public void yellow(String msg) {
        System.out.println(YELLOW + msg + RESET);
    }

    public void blue(String msg) {
        System.out.println(BLUE + msg + RESET);
    }

    public void purple(String msg) {
        System.out.println(PURPLE + msg + RESET);
    }
}
