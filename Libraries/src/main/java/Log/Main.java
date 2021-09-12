package Log;

import Log.NoCustomFiled.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    public static void main(String[] args) {
        Logger logger = LogManager.getLogger("Logger");
        // src/main/resources/log4j2.xml の level に合わせて出力される
        // log4j2.xml がない場合は fatal,error が出力される
        // 2021-09-12 12:01:37,041 main WARN No Root logger was configured, creating default ERROR-level Root logger with Console appender
        logger.fatal("this is fatal log");
        logger.error("this is error log");
        logger.warn("this is warn log");
        logger.info("this is info log");
        logger.debug("this is debug log");
        logger.trace("this is trace log");

        Test test = new Test();
        test.output();
    }
}
