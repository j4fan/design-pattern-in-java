package com.example.demo.banchmark;

public class ViolentMatch {

    public static int match(String origin, String keyword) {
        int originLength = origin.length();
        int keywordLength = keyword.length();
        int i = 0;
        int j = 0;
        while (i < originLength && j < keywordLength) {
            if (origin.charAt(i) == keyword.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == keywordLength - 1) {
            return i - j;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println(ViolentMatch.match("asdfghj", "asd"));
    }
}
