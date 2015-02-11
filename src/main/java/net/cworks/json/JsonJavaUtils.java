package net.cworks.json;

public final class JsonJavaUtils {



    /**
     * Make a clever attempt to convert field names given in any which way
     * to standard Java method names (i.e. lowerCamelCase).
     *
     * Corner Cases:
     *
     * Input is all UPPERCASE
     * In this case we choose what to do based on a global setting.
     *
     * Input is all lowercase
     *
     *
     * @param fieldName name of field in JSON
     * @return the java method name rendered from fieldName
     */
    public static String toMethodName(String fieldName) {
        if (fieldName == null || fieldName.length() == 0) {
            return fieldName;
        }

        if(isAllUppercase(fieldName) || isAllLowercase(fieldName)) {
            return fieldName;
        }

        // if the fieldName contains non alphanumeric characters then
        // we remove them and attempt to render a standard java method name
        for(int i = 0; i < fieldName.length(); i++) {
            if(!Character.isLetterOrDigit(fieldName.codePointAt(i))) {
                fieldName = convertToMethodName(fieldName);
                break;
            }
        }

        return deCapitalize(fieldName);
    }

    /**
     * Attempt to convert a String (fieldName) to the standard lowerCamelCase
     * method naming convention by replacing all non alphanumeric characters
     * with an underscore, then changing each character after an underscore
     * to uppercase and finally removing each underscore.  The first character
     * of the result will be capitalized at this point and should be lowercased
     * if you want the result to reflect a standard java method name.
     * @param fieldName
     * @return
     */
    private static String convertToMethodName(String fieldName) {
        char[] text = fieldName.trim().toLowerCase().replaceAll("[^A-Za-z0-9]+", "_").toCharArray();
        boolean toUpper = false;
        for(int i = 0; i < text.length; i++) {
            char c = text[i];
            if(c == '_') {
                toUpper = true;
            } else if(toUpper) {
                text[i] = Character.toUpperCase(c);
                toUpper = false;
            }
        }

        return String.valueOf(text).replaceAll("_", "");
    }

    /**
     * Convert first letter of text to lowercase
     * @param text any ole text
     * @return text with first letter converted to lowercase
     */
    public static String deCapitalize(String text) {
        if (text == null || text.length() == 0) {
            return text;
        }
        if(Character.isLowerCase(text.charAt(0))){
            return text;
        }
        char chars[] = text.toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);

        return String.valueOf(chars);
    }

    /**
     * Test if a String is all uppercase
     * @param text
     * @return
     */
    public static boolean isAllUppercase(String text) {
        int i;
        for(i = 0; i < text.length(); i++) {
            if(!Character.isUpperCase(text.codePointAt(i))) {
                break;
            }
        }
        return i == text.length();
    }

    /**
     * Test if a String is all lowercase
     * @param text
     * @return
     */
    public static boolean isAllLowercase(String text) {
        int i;
        for(i = 0; i < text.length(); i++) {
            if(!Character.isLowerCase(text.codePointAt(i))) {
                break;
            }
        }
        return i == text.length();
    }

}
