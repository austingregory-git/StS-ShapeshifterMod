package StSShapeShifter.util;

public class BloomCountUtils {
    public static int bloomCount;
    public static int getBloomCount() {
        return bloomCount;
    }

    public static void setBloomCount(int amount) {
        bloomCount = amount;
    }

    public static void addBloomCount(int amount) {
        bloomCount += amount;
    }
}
