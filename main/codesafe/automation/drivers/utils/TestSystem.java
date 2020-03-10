package codesafe.automation.drivers.utils;

import codesafe.automation.drivers.codeSafeSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestSystem implements codeSafeSystem {
      private Logger logger;
      private String systemAddress;
      protected Connection connection;
      protected String url;
      protected String sqlUser;
      protected String sqlPass;
      protected String sqlClass;
      protected String serverUser;
      protected String serverPass;
      
      public TestSystem(String address, String sqlUrl, String sqlUser, String sqlPass, String sqlClass
              , String serverUser, String serverPass) {
            logger = LogManager.getLogger(TestSystem.class.getName());
            if (address == null) {
                  logger.error("- Could not determine address for codeSafe System");
            } else {
                  this.systemAddress = address;
            }
            
            if (sqlUrl == null) {
                  logger.error("- Could not determine password for codeSafe System");
            } else {
                  this.url = sqlUrl;
            }
            
            if (sqlUser == null) {
                  logger.error("- Could not determine password for codeSafe System");
            } else {
                  this.sqlUser = sqlUser;
            }
            
            if (sqlPass == null) {
                  logger.error("- Could not determine password for codeSafe System");
            } else {
                  this.sqlPass = sqlPass;
            }
            
            if (sqlClass == null) {
                  logger.error("- Could not determine password for codeSafe System");
            } else {
                  this.sqlClass = sqlClass;
            }
            
            if (serverUser == null) {
                  logger.error("- Could not determine password for codeSafe System");
            } else {
                  this.serverUser = serverUser;
            }
            
            if (serverPass == null) {
                  logger.error("- Could not determine password for codeSafe System");
            } else {
                  this.serverPass = serverPass;
            }
      }
      
      public void setSystemAddress(String systemAddress) {
            this.systemAddress = systemAddress;
      }
      
      public void setConnection(Connection connection) {
            this.connection = connection;
      }
      
      public void setUrl(String url) {
            this.url = url;
      }
      
      public void setSqlUser(String sqlUser) {
            this.sqlUser = sqlUser;
      }
      
      public void setSqlPass(String sqlPass) {
            this.sqlPass = sqlPass;
      }
      
      public void setSqlClass(String sqlClass) {
            this.sqlClass = sqlClass;
      }
      
      public void setServerUser(String serverUser) {
            this.serverUser = serverUser;
      }
      
      public void setServerPass(String serverPass) {
            this.serverPass = serverPass;
      }
      
      public String getSystemAddress() {
            return systemAddress;
      }
      
      public String getUrl() {
            return url;
      }
      
      public String getSqlUser() {
            return sqlUser;
      }
      
      public String getSqlPass() {
            return sqlPass;
      }
      
      public String getSqlClass() {
            return sqlClass;
      }
      
      public String getServerUser() {
            return serverUser;
      }
      
      public String getServerPass() {
            return serverPass;
      }
      
      public Connection getConnection() throws SQLException, IOException {
            if (connection == null) {
                  try {
                        Class.forName(this.getSqlClass());
                        connection = DriverManager.getConnection(this.getUrl(), this.getSqlUser(), this.getSqlPass());
                  } catch (Exception e) {
                        logger.error("Empty");
                  }
            }
            return connection;
      }
      
      
      public ResultSet queryDatabase(String query) {
            try {
                  return new LookupAndQueryDB().queryDatabase(getConnection(), query);
            } catch (Exception e) {
                  logger.error("Error in database query: (" + query + "): " + e.getMessage());
            }
            return null;
      }
      
      
      public ResultSet updateDatabase(String query) {
            try {
                  new LookupAndQueryDB().updateDatabase(getConnection(), query);
            } catch (Exception e) {
                  logger.error("Error in database update: (" + query + "): " + e.getMessage());
            }
            return null;
      }
      
      
      public synchronized void resetConnection() throws SQLException {
            if (connection != null && !connection.isClosed()) connection.close();
            connection = null;
      }
}