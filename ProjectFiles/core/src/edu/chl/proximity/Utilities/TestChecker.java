package edu.chl.proximity.Utilities;

/**
 * Linda Evaldsson
 * @date 2015-05-19.
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
