package com.mt.majiang;

public class MJ {
    private final static String[] mahjong = { "1T", "2T", "3T", "4T", "5T", "6T", "7T", "8T", "9T", "1S", "2S", "3S",
            "4S", "5S", "6S", "7S", "8S", "9S", "1W", "2W", "3W", "4W", "5W", "6W", "7W", "8W", "9W", "DONG", "NAN",
            "XI", "BEI", "ZHONG", "FA", "BAI" };
    private static int[] c = new int[36];

    public static void main(String[] args) {
        String[] shoupai = { "1T", "2T", "2T", "3T", "5T", "6T", "4S", "4S", "5S", "6S", "8S", "8S", "9S", "9S" };

        for (int i = 0; i < shoupai.length; i++) {
            for (int k = 0; k < mahjong.length; k++) {
                if (mahjong[k].equals(shoupai[i])) {
                    c[k] = k;
                }
                System.out.println("分隔符======");
            }
        }
    }
}
