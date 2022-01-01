package Utils;

public class StatusCode {
    //    public static final String EXIT_CODE=String.valueOf(System.currentTimeMillis());
    public static final String EXIT_CODE = "e0mOVHY807";
    public static final String CHAT_CODE = "cQJH6Nj8yB";
    public static final String REFUSE_CODE = "l997BWqaqC";
    public static final String NAME_IS_USED_CODE = "3nirhyJ9eE";
    public static final String NAME_INVALID_CODE = "JHvv9fq148";


    public static boolean isExitCode(String message) {
        return message.equalsIgnoreCase(EXIT_CODE);
    }

    public static boolean isChatCode(String message) {
        return message.equalsIgnoreCase(CHAT_CODE);
    }

    public static boolean isRefuseCode(String message) {
        return message.equalsIgnoreCase(REFUSE_CODE);
    }

    public static boolean isNameIsUsedCode(String message) {
        return message.equalsIgnoreCase(NAME_IS_USED_CODE);
    }

    public static boolean isNameInvalidCode(String message) {
        return message.equalsIgnoreCase(NAME_INVALID_CODE);
    }

}
