package util;

import org.picocontainer.classname.ClassName;

import java.util.logging.Logger;

public class LoggerHelper {
    private static Logger logger = Logger.getLogger(ClassName.class.getName());

    private LoggerHelper() {

    }

    public static void navigating(String name, Runnable function) {
            logger.info("Navigating " + name);
            function.run();
            logger.info("Navigated " + name);
    }

    public static void asserting(String name, Runnable function) {
            logger.info("Asserting " + name);
            function.run();
            logger.info("Asserted " + name);
        }

    public static void entering(String name, Runnable function) throws Exception {
        logger.info("Entering " + name);
        function.run();
        logger.info("Entered " + name);
    }

    public static void selecting(String name, Runnable function) throws Exception {
        logger.info("Selecting " + name);
        function.run();
        logger.info("Selected " + name);
    }

    public static void skipping(String name, Runnable function) throws Exception {
        logger.info("Skipping " + name);
        function.run();
        logger.info("Skipped " + name);
    }

    public static void logging(String name, Runnable function) throws Exception {
        logger.info("Logging in " + name);
        function.run();
        logger.info("Logged in " + name);
    }
}
