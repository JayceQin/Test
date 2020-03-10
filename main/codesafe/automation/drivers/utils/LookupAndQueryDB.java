package codesafe.automation.drivers.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LookupAndQueryDB {
      /**
       * Query the connected database. A connection has to be established
       * before calling this method.
       * <p>
       * Returns a ResultSet object
       *
       * @param s the query string
       * @return an ResultSet object with the results of the query
       * @throws SQLException from executeQuery()
       */
      public ResultSet queryDatabase(Connection conn, String s) throws SQLException {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(s);
            
            return result;
      }
      
      /**
       * Update the connected database. A connection has to be established
       * before calling this method.
       *
       * @param s the query string
       * @throws SQLException from executeQuery()
       */
      public void updateDatabase(Connection conn, String s) throws SQLException {
            Statement statement = conn.createStatement();
            statement.executeUpdate(s);
      }
}