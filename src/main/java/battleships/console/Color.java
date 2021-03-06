package battleships.console;

public class Color {
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";

    public static void red(String msg) {
        System.out.println(RED + msg + RESET);
    }

    public static void printRed(String msg) {
        System.out.print(RED + msg + RESET);
    }

    public static void green(String msg) {
        System.out.println(GREEN + msg + RESET);
    }

    public static void printGreen(String msg) {
        System.out.print(GREEN + msg + RESET);
    }

    public static void yellow(String msg) {
        System.out.println(YELLOW + msg + RESET);
    }

    public static void printYellow(String msg) {
        System.out.print(YELLOW + msg + RESET);
    }

    public static void purple(String msg) {
        System.out.println(PURPLE + msg + RESET);
    }

    public static void printCyan(String msg) {
        System.out.print(CYAN + msg + RESET);
    }

    public static void cyan(String msg) {
        System.out.println(CYAN + msg + RESET);
    }

    public static void printBlue(String msg) {
        System.out.print(BLUE + msg + RESET);
    }

    public static void blue(String msg) {
        System.out.println(BLUE + msg + RESET);
    }
}
