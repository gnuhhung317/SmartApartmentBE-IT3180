package com.hust.smart_apartment.utils;

import lombok.experimental.UtilityClass;

import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.StringJoiner;

@UtilityClass
public class StringUtil {

    private final SecureRandom RD = new SecureRandom();

    private static final DecimalFormat decimalFormat = new DecimalFormat("000000");

    public String camelToSnake(String str) {

        // Empty String
        StringBuilder result = new StringBuilder();

        // Append first character(in lower case)
        // to result string
        char c = str.charAt(0);
        result.append(Character.toLowerCase(c));

        // Traverse the string from
        // ist index to last index
        for (int i = 1; i < str.length(); i++) {

            char ch = str.charAt(i);

            // Check if the character is upper case
            // then append '_' and such character
            // (in lower case) to result string
            if (Character.isUpperCase(ch)) {
                result.append('_');
                result.append(Character.toLowerCase(ch));
            }

            // If the character is lower case then
            // add such character into result string
            else {
                result.append(ch);
            }
        }

        // return the result
        return result.toString();
    }

    public String generateRandomStr(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        return RD.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(length).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }

    public String generateOTP() {
        return decimalFormat.format(RD.nextInt(999999));
    }

    public String generateOrderCode() {
        return decimalFormat.format(RD.nextInt(999999999));
    }

    public static String buildLikeOperator(String value) {
        if (value == null || "".equals(value.trim())) return null;
        return "%".concat(value.trim().toUpperCase()).concat("%");
    }

    public static String buildLikeOperatorLower(String value) {
        if (value == null || "".equals(value.trim())) return null;
        return "%".concat(value.trim().toLowerCase()).concat("%");
    }

    public static boolean isNullOrBlank(String str) {
        return str == null || str.isBlank();
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isNotBlank(String str) {
        return str != null && !str.trim().isEmpty();
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static String safeToString(Object obj) {
        if (isNull(obj)) {
            return "";
        }
        return String.valueOf(obj);
    }

    public static String safeToString(String obj) {
        if (isBlank(obj)) {
            return null;
        }
        return obj;
    }

    public static String convertFullNameToUsername(String fullName) {
        if (fullName == null || fullName.isBlank()) return null;

        String[] names = fullName.trim().toLowerCase().split("\\s+");
        if (names.length == 0) return null;

        StringJoiner username = new StringJoiner("");
        username.add(names[names.length - 1]);

        // Lấy chữ cái đầu của các tên còn lại
        for (int i = 0; i < names.length - 1; i++) {
            username.add(String.valueOf(names[i].charAt(0)));
        }

        return username.toString();
    }
}

