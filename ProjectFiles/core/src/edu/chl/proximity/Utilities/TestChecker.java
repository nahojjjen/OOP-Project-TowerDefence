package edu.chl.proximity.Utilities;

/**
 * @author Linda Evaldsson (code taken from method in Image)
 * @date 2015-05-19
 *
 * A class for testing whether the current run is a test
 */
public class TestChecker {

    public static boolean isJUnitTest() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : stackTrace) {
            if (element.getClassName().startsWith("org.junit.")) {
                return true;
            }
        }
        return false;
    }
}
