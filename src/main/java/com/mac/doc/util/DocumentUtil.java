package com.mac.doc.util;

public class DocumentUtil {

    public static final char PLUS = '+';
    public static final char MINUS = '-';

    public static boolean equalsWithLength(String s1, String s2) {
        return s1.length() == s2.length() && s1.equals(s2);
    }
}
