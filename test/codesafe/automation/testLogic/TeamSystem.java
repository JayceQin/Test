package codesafe.automation.testLogic;

import codesafe.automation.drivers.selenium.CodeSafeDriver;
import codesafe.automation.drivers.utils.TestSystem;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamSystem extends TestSystem {
      private CodeSafeDriver selectedBrowser;
      
      public TeamSystem(String targetAddress, String url, String sqlUser, String sqlPass, String sqlClass
              , String serverUser, String serverPass) throws IOException, SQLException {
            super(targetAddress, url, sqlUser, sqlPass, sqlClass, serverUser, serverPass);
      }
      
      public TeamSystem() throws IOException, SQLException {
            this(null, null, null, null, null, null, null);
      }
      
      public TeamSystem(String targetAddress) throws IOException, SQLException {
            this(targetAddress, null, null, null, null, null, null);
      }
      
      public TeamSystem(TestConfiguration runConfig) throws IOException, SQLException {
            this(runConfig.getTargetAddress(), runConfig.getTargetUrl(),
                    runConfig.getTargetSqlUser(), runConfig.getTargetSqlPass(), runConfig.getTargetSqlClass(), runConfig.getServerUser(),
                    runConfig.getServerPass());
      }
      
      public CodeSafeDriver useViews() throws Exception {
            if (selectedBrowser == null) {
                  selectedBrowser = new CodeSafeDriver(this);
            }
            return selectedBrowser;
      }
      
}
