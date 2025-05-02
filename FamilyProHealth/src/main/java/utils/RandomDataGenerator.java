package utils;

import java.util.Random;

public class RandomDataGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final Random random = new Random();

    public static String generateRandomName(int length) {
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < length; i++) {
            name.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return name.toString();
    }

    public static String generateRandomEmail() {
        return generateRandomName(6).toLowerCase() + "@yopmail.com";
    }

    public static String generateRandomMobile() {
        StringBuilder mobile = new StringBuilder("9"); // starts with 9 (India logic)
        for (int i = 1; i < 10; i++) {
            mobile.append(random.nextInt(10));
        }
        return mobile.toString();
    }
   
}