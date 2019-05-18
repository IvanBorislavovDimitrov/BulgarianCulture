package com.bulgarian.culture.util;

import java.util.Random;

public final class RandomGenerator {

    public static int generateRandomInt(int min, int max){
        Random random = new Random();
        return min + random.nextInt(max - min +1);
    }
}
