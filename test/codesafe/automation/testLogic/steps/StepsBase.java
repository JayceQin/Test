package codesafe.automation.testLogic.steps;

import codesafe.automation.testLogic.TeamSystem;
import codesafe.automation.testLogic.TestConfiguration;
import net.serenitybdd.core.Serenity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;
import java.sql.SQLException;

public class StepsBase {
      protected Logger logger;
      protected TeamSystem testingBox;
      
      public StepsBase() throws SQLException, IOException {
            logger = LogManager.getLogger(StepsBase.class.getName());
            if (Serenity.getCurrentSession().get("TeamSystem") == null) {
                  Serenity.getCurrentSession().put("TeamSystem", new TeamSystem(new TestConfiguration()));
            }
      }
      
}