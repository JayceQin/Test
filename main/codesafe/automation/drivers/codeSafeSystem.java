package codesafe.automation.drivers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface codeSafeSystem {
      
      String getSystemAddress();
      
      Connection getConnection() throws SQLException, IOException;
      
      ResultSet queryDatabase(String query);
      
      ResultSet updateDatabase(String query);
      
      void resetConnection() throws SQLException;
}
