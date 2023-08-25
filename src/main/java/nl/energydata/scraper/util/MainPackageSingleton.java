package nl.energydata.scraper.util;

public class MainPackageSingleton {
    private static MainPackageSingleton instance;
    private String mainPackageName;

    private MainPackageSingleton() {
        // Get stack trace of thread that created singleton
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        // Get fully-qualified class name of caller (should be third element in stack trace)
        String callerClassName = stackTraceElements[2].getClassName();

        // Get main package name from fully-qualified class name
        this.mainPackageName = getMainPackageName(callerClassName);
    }

    public static synchronized MainPackageSingleton getInstance() {
        if (instance == null) {
            instance = new MainPackageSingleton();
        }
        return instance;
    }

    public String getMainPackageName() {
        return this.mainPackageName;
    }

    private String getMainPackageName(String fullyQualifiedClassName) {
        return fullyQualifiedClassName.split("\\.")[0];
    }
}


