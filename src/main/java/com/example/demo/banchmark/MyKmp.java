package com.example.demo.banchmark;

public class MyKmp {

    public static int[] getNext(String pattern) {
        int length = pattern.length();

        int[] result = new int[length];
        result[0] = 0;

        for (int i = 1; i < length; i++) {
            String countString = pattern.substring(0, i + 1);
            int count = 0;
            for (int j = 0; j < i; j++) {
                String frontPart = countString.substring(0, j + 1);
                String backPart = countString.substring(i - j, i + 1);
                if (frontPart.equals(backPart)) {
                    count = count+frontPart.length();
                }
            }
            result[i] = count;
        }
        return result;
    }

    public static int match(String origin, String keyword, int[] indexs) {
        int originLength = origin.length();
        int keywordLength = keyword.length();
        int i = 0;
        int j = 0;
        while (i < originLength && j < keywordLength) {
            if (origin.charAt(i) == keyword.charAt(j)) {
                i++;
                j++;
            } else if (j == 0) {
                i = i - j + 1;
                j = 0;
            }else{
                i = i -indexs[j-1];
                j = 0;
            }
        }
        if (j == keywordLength) {
            return i-j;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(match("BBC ABCDAB ABCDABCDABDE", "ABCDABD", getNext("ABCDABD")));
    }
}
