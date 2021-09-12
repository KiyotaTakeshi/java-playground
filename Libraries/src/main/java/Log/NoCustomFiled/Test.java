package Log.NoCustomFiled;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Test {

    public void output() {
        Logger logger = LogManager.getLogger("NoCustomFiled");
        logger.info("this is info log");
        logger.debug("this is debug log");
    }
}
