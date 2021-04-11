/**
 * Created by zengxiaoning on 2021/4/8.
 */
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpringApplicationContext {
	static Logger logger=LogManager.getLogger("Console");

	public static void main(String[] args) {
		logger.debug("this is the debug message");
		logger.info("this is the debug message");
		logger.error("this is the debug message:{}", 5);

	}
}