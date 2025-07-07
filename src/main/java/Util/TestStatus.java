package Util;

public class TestStatus {
    //Get the status TestStatus.testPassed.get()
    public static ThreadLocal<Boolean> testPassed = ThreadLocal.withInitial(() -> false);
}
