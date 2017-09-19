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

    public static void clicking(String name, Runnable function) throws Exception {
            logger.info("Clicking " + name);
            function.run();
            logger.info("Clicked " + name);
    }

    public static void entering(String name, Runnable function) throws Exception {
        logger.info("Entering " + name);
        function.run();
        logger.info("Entered " + name);
    }
}
