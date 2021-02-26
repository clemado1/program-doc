package com.mac.doc.util;

import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class DocumentUtil {

    private DocumentUtil() { throw new IllegalStateException("Utility class"); }

    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char SPACE = ' ';

    public static boolean equalsWithLength(String s1, String s2) {
        return s1.length() == s2.length() && s1.equals(s2);
    }

    public static String diffContents(String contents1, String contents2) {
        LinkedList<String> output = new LinkedList<>();

        LinkedList<String> contentsL = contents1.lines().collect(Collectors.toCollection(LinkedList::new));
        LinkedList<String> contentsR = contents2.lines().collect(Collectors.toCollection(LinkedList::new));

        int cursorL = 0;
        int cursorR = 0;

        boolean conflict = false;

        while (true) {
            if (contentsL.isEmpty() || contentsR.isEmpty()) {
                output.addAll(contentsL.stream().map(s -> MINUS + SPACE + s).collect(Collectors.toList()));
                output.addAll(contentsR.stream().map(s -> PLUS + SPACE + s).collect(Collectors.toList()));

                break;
            }

            cursorL = Math.min(cursorL, contentsL.size() - 1);
            cursorR = Math.min(cursorR, contentsR.size() - 1);

            String currL = contentsL.get(cursorL);
            String currR = contentsR.get(cursorR);

            if (conflict) {
                String atL = contentsL.getFirst();
                String atR = contentsR.getFirst();

                if (equalsWithLength(currL, atR)) {
                    // for startIdx ~ cursorL set - string
                    acceptTimes(output, contentsL, cursorL, MINUS);
                    contentsR.removeFirst();

                    cursorL = 0;
                    cursorR = 0;
                    conflict = false;
                } else if (equalsWithLength(currR, atL)) {
                    // for startId ~ cursorR set + string
                    acceptTimes(output, contentsR, cursorR, PLUS);
                    contentsL.removeFirst();

                    cursorL = 0;
                    cursorR = 0;
                    conflict = false;
                } else if (cursorL == contentsL.size() - 1 && cursorR == contentsR.size() - 1) {
                    output.add(MINUS + SPACE + contentsL.removeFirst());
                    output.add(PLUS + SPACE + contentsR.removeFirst());

                    cursorL = 0;
                    cursorR = 0;
                    conflict = false;
                } else {
                    cursorL++;
                    cursorR++;
                }
            } else if (equalsWithLength(currL, currR)) {
                output.add(contentsL.removeFirst());
                contentsR.removeFirst();
            } else {
                conflict = true;
                cursorL++;
                cursorR++;
            }
        }

        return String.join("\n", output);
    }

    private static void acceptTimes(Deque<String> i, Deque<String> d, int times, char prefix) {
        for (int t = 0; t < times; t++) {
            i.add(prefix + SPACE + d.removeFirst());
        }
        i.add(d.removeFirst());
    }

}
