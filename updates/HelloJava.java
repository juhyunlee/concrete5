package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;

public class HelloJava {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		  try
		  {
		      // Load the database driver
		      Class.forName( "com.microsoft.sqlserver.jdbc.SQLServerDriver" ) ;

		      // Get a connection to the database
		      //conn = DriverManager.getConnection( "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sampleDB;SelectMethod=cursor;user=sa;password=MJS369mjs" ) ;
		      conn = DriverManager.getConnection( "jdbc:sqlserver://p2v4cwd7ua.database.windows.net:1433;database=auth-gate-admin-db-dev;user=mjsadmin@p2v4cwd7ua;password=MJS369mjs;encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;" ) ;

		      // Print all warnings
		      for( SQLWarning warn = conn.getWarnings(); warn != null; warn = warn.getNextWarning() )
		      {
		          System.out.println( "SQL Warning:" ) ;
		          System.out.println( "State  : " + warn.getSQLState()  ) ;
		          System.out.println( "Message: " + warn.getMessage()   ) ;
		          System.out.println( "Error  : " + warn.getErrorCode() ) ;
		      }

		      // Prepare a statement
		      ps = conn.prepareStatement( "SELECT * FROM Content WHERE content_shiki_id = ?" ) ;

		      // Set the first parameter of the statement
		      ps.setString( 1, "salesslip2" ) ;

		      // Execute the query
		      rs = ps.executeQuery() ;

		      // Loop through the result set
		      while( rs.next() )
		         System.out.println( rs.getString("client_id") + " " + rs.getString("client_secret") ) ;

		      // Close the result set, statement and the connection
//		      rs.close() ;
//		      ps.close() ;
//		      conn.close() ;
		  }
		  catch( SQLException se )
		  {
		      System.out.println( "SQL Exception:" ) ;

		      // Loop through the SQL Exceptions
		      while( se != null )
		      {
		          System.out.println( "State  : " + se.getSQLState()  ) ;
		          System.out.println( "Message: " + se.getMessage()   ) ;
		          System.out.println( "Error  : " + se.getErrorCode() ) ;

		          se = se.getNextException() ;
		      }
		  }
		  catch( Exception e )
		  {
		      System.out.println( e ) ;
		  }finally{
	            // return resources!!
	            if (rs != null) { try { rs.close(); } catch (Exception ignored) {} }
	            if (ps != null) { try { ps.close(); } catch (Exception ignored) {} }
	            if (conn != null) { try { conn.close(); } catch (Exception ignored) {} }
		  }
	}

}
