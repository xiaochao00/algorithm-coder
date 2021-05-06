package org.example.algo.coder.top100;

import java.util.Arrays;

/**
 * 1720
 *
 * @author shichao
 * @since 1.0.0
 * 2021/5/6 23:35
 */
public class Item1720 {
    public static void main(String[] args) {
        int[] encoded;
        int first;
        int[] arr;
        //
        encoded = new int[]{1, 2, 3};
        first = 1;
        arr = decode(encoded, first);
        System.out.println(Arrays.toString(arr));
        //
        encoded = new int[]{6, 2, 7, 3};
        first = 4;
        arr = decode(encoded, first);
        System.out.println(Arrays.toString(arr));
    }

    public static int[] decode(int[] encoded, int first) {
        //A^B=C --> B=A^C
        int[] arr = new int[encoded.length + 1];
        arr[0] = first;
        for (int i = 0; i < encoded.length; i++) {
            arr[i + 1] = encoded[i] ^ arr[i];
        }
        return arr;
    }
}
