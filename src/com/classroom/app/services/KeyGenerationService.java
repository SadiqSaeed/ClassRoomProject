package com.classroom.app.services;

import java.util.Random;

/**
 * Created by Muhammad Sadiq Saeed on 3/5/2017.
 */
public class KeyGenerationService {

    private static Random random = new Random();

    public static String generateRandomString(int length) {
        char[] values = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
                '4', '5', '6', '7', '8', '9'};
        String out = "";
        for (int i = 0; i < length; i++) {
            int idx = random.nextInt(values.length);
            out += values[idx];
        }
        return out;
    }
}
