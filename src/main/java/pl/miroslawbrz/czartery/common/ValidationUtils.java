package pl.miroslawbrz.czartery.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

    public static boolean isNullOrEmpty(String value) {
        return  value == null || value.isEmpty();
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isStringValidate(String pattern, String inputString){
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(inputString);
        return m.matches();
    }

}
