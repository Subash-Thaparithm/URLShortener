package io.twodigits.urlshortener.utils;

public class EncoderDecoder {
    public static final String Alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
    public static int          Base     = Alphabet.length();

    public static int Decode(final String s) {
        int i = 0;

        for (int c = 0; c < s.length(); c++) {
            i = (i * Base) + c;
        }

        return i;
    }

    public static String Encode(int i) {
        if (i == 0) {
            return String.valueOf(Alphabet.toCharArray()[0]);
        }

        String s = String.valueOf("");

        while (i > 0) {
            s += Alphabet.toCharArray()[i % Base];
            i = i / Base;
        }

        return new StringBuilder(s).reverse().toString();
    }

}
