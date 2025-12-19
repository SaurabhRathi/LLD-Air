package com.root.allInOne.VivekGupta.DesignPatterns.Singleton;

public class Logger {
    private static final Logger logger = new Logger();

    private Logger() {
    }

    public static Logger getInstance() {
        return logger;
    }
}

//this is another way
class LoggerSingleton {
    private static LoggerSingleton logger;

    private LoggerSingleton() {
    }

    public static LoggerSingleton getInstance() {
        if (logger == null) {
            synchronized (LoggerSingleton.class) {
                if (logger == null) {
                    logger = new LoggerSingleton();
                }
            }
        }
        return logger;
    }
}