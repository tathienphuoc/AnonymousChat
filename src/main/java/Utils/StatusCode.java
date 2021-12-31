package Utils;

public class StatusCode {
    //    public static final String EXIT_CODE=String.valueOf(System.currentTimeMillis());
    public static final String EXIT_CODE = "exit";
    public static final String CHAT_CODE = "connect";
    public static final String REFUSE_CODE = "refuse";

    public static boolean isExitCode(String message) {
        return message.equalsIgnoreCase(EXIT_CODE);
    }

    public static boolean isRefuseCode(String message) {
        return message.equalsIgnoreCase(REFUSE_CODE);
    }

    public static boolean isChatCode(String message) {
        return message.equalsIgnoreCase(CHAT_CODE);
    }
}
