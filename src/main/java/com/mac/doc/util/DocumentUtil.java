package com.mac.doc.util;

import java.util.Deque;

public class DocumentUtil {

    public static final char pls = '+';
    public static final char mns = '-';

    public static boolean equalsWithLength(String s1, String s2) {
        return s1.length() == s2.length() && s1.equals(s2);
    }
}
