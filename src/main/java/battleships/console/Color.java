package battleships.console;

public class Color {
    public static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";

    public static void red(String msg) {
        System.out.println(RED + msg + RESET);
    }

    public static void green(String msg) {
        System.out.println(GREEN + msg + RESET);
    }

    public static void yellow(String msg) {
        System.out.println(YELLOW + msg + RESET);
    }

    public static void blue(String msg) {
        System.out.println(BLUE + msg + RESET);
    }

    public static void purple(String msg) {
        System.out.println(PURPLE + msg + RESET);
    }

    public static void cyan(String msg) {
        System.out.println(CYAN + msg + RESET);
    }
}
