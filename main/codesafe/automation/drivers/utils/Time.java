package codesafe.automation.drivers.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Time {
      private Logger logger;
      
      public Time() {
            logger = LogManager.getLogger(Time.class.getName());
      }
      
      public void waitForSeconds(int duration, String explanation) {
            logger.debug("- Waiting " + duration + " seconds for " + explanation);
            try {
                  while (duration > 0) {
                        logger.debug("- " + duration + " seconds left");
                        int waitSeconds = duration > 60 ? 60 : duration;
                        Thread.sleep(waitSeconds * 1000);
                        duration -= waitSeconds;
                  }
            } catch (InterruptedException ie) {
                  logger.warn("- Early exit from interrupted sleep");
                  Thread.currentThread().interrupt();
            }
      }
}
