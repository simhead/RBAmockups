package rba.main;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import rba.mockup.util.UtilSet;

import java.sql.ResultSetMetaData;

public class Customer {

	private static String DBname = "MyLocalDerbyDB";
	private static String dbURL = "jdbc:derby:C:\\Users\\simhead\\Documents\\MyLocalDerbyDB;create=true";
    private static String tableName = "CUSTOMERS";
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;
    
	public static void main(String[] args) {
		
		
		Customer customer = new Customer();
		customer.createConnection();
		customer.createCustomerTable();
		customer.insertCustomer();
		customer.getCustomer("06371949861");
		customer.shutdown();

	}

	public static Connection createConnection() {
        try {
        	
        	File DBPath = new File(DBname);
        	System.out.println(
        			"PATH: "+DBPath.getPath()+" ABSPATH: "+DBPath.getAbsolutePath()); 
        	
        	dbURL = "jdbc:derby:"+DBPath.getAbsolutePath()+";create=true";
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL); 
        } catch (Exception except) {
            except.printStackTrace();
            return null;
        }
        
        return conn;
    }
 
	public static Connection getConnection(String fname) {
        try {
        	
        	File DBPath = new File(fname);
        	System.out.println(
        			"PATH: "+DBPath.getPath()+" ABSPATH: "+DBPath.getAbsolutePath()); 
        	
        	dbURL = "jdbc:derby:"+DBPath.getAbsolutePath()+";create=true";
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL); 
        } catch (Exception except) {
            except.printStackTrace();
            return null;
        }
        
        return conn;
    }

	public static Connection createConnection(String fname) {
        try {
        	
        	File DBPath = new File(fname);
        	System.out.println(
        			"PATH: "+DBPath.getPath()+" ABSPATH: "+DBPath.getAbsolutePath()); 
        	
        	dbURL = "jdbc:derby:"+DBPath.getAbsolutePath()+";create=true";
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL); 
        } catch (Exception except) {
            except.printStackTrace();
            return null;
        }
        
        return conn;
    }

	/* 
	 * customerName
	 * customerCountry
	 * customerState
	 * customerCity
	 * customerAddress
	 * customerPostCode
	 * customerPhone
	 * customerEmail
	 * customerIP
	 * storeID
	 */
	static String[] custRec = {"customerName", "customerCountry", "customerState",
			"customerCity", "customerAddress", "customerPostCode", "customerPhone",
			"customerEmail", "customerIP", "storeID"};
	

	public void createCustomerTable() {
        try {
        	String custrow = "";
        	for (int i = 0; i < custRec.length-1; i++) {
        		custrow += custRec[i]+" VARCHAR(50) NOT NULL, ";
        	}
        	custrow += custRec[custRec.length-1]+" VARCHAR(50) NOT NULL)";
        	
        	String sqlCreate = "CREATE TABLE " + tableName
            + "(   CUSTID           VARCHAR(20) NOT NULL,"
            + custrow;

        	Statement stmt = conn.createStatement();
        	
        	DatabaseMetaData dbmd = conn.getMetaData();
        	ResultSet rs = dbmd.getTables(null, null, tableName, null);
        	if(!rs.next()) {
        		stmt.execute(sqlCreate);
        	} 
        	
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

	public static boolean createCustomerTable(Connection dbconn) {
        try {
        	String custrow = "";
        	for (int i = 0; i < custRec.length-1; i++) {
        		custrow += custRec[i]+" VARCHAR(50) NOT NULL, ";
        	}
        	custrow += custRec[custRec.length-1]+" VARCHAR(50) NOT NULL)";
        	
        	String sqlCreate = "CREATE TABLE " + tableName
            + "(   CUSTID           VARCHAR(20) NOT NULL,"
            + custrow;

        	Statement stmt = dbconn.createStatement();
        	
        	DatabaseMetaData dbmd = dbconn.getMetaData();
        	ResultSet rs = dbmd.getTables(null, null, tableName, null);
        	if(!rs.next()) {
        		stmt.execute(sqlCreate);
        	} 
        	
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return false;
        }
        
        return true;
    }
	
	public static String insertCustomer(Connection dbconn,
			String customerName, String customerCountry, String customerState
			, String customerCity, String customerAddress, String customerPostCode
			, String customerPhone, String customerEmail, String customerIP
			, String storeID) {
		String somenum = UtilSet.getSomeNumber();
    	
        try {
        	String custrow = "'";
        	for (int i = 0; i < custRec.length-1; i++) {
        		custrow += custRec[i]+somenum+"','";
        	}
        	custrow += custRec[custRec.length-1]+somenum+"')";
        	
        	String sqlInsert = "insert into " + tableName + " values ('"+ 
        			somenum +"','"+customerName+"','"+customerCountry  
        			+"','"+customerState+"','"+customerCity+"','"+customerAddress 
        			+"','"+customerPostCode+"','"+customerPhone+"','"+customerEmail
        			+"','"+customerIP+"','"+ storeID+"')";
        	
        	System.out.println (sqlInsert);
            stmt = dbconn.createStatement();
            stmt.execute(sqlInsert);
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return null;
        }
        
        return somenum;
    }
	
	public String insertCustomer(
			String customerName, String customerCountry, String customerState
			, String customerCity, String customerAddress, String customerPostCode
			, String customerPhone, String customerEmail, String customerIP
			, String storeID) {
		String somenum = UtilSet.getSomeNumber();
    	
        try {
        	String custrow = "'";
        	for (int i = 0; i < custRec.length-1; i++) {
        		custrow += custRec[i]+somenum+"','";
        	}
        	custrow += custRec[custRec.length-1]+somenum+"')";
        	
        	String sqlInsert = "insert into " + tableName + " values ('"+ 
        			somenum +"','"+customerName+"','"+customerCountry  
        			+"','"+customerState+"','"+customerCity+"','"+customerAddress 
        			+"','"+customerPostCode+"','"+customerPhone+"','"+customerEmail
        			+"','"+customerIP+"','"+ storeID+"')";
        	
        	System.out.println (sqlInsert);
            stmt = conn.createStatement();
            stmt.execute(sqlInsert);
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return null;
        }
        
        return somenum;
    }

	public void insertCustomer() {
        try {
        	String somenum = UtilSet.getSomeNumber();
        	String custrow = "'";
        	for (int i = 0; i < custRec.length-1; i++) {
        		custrow += custRec[i]+somenum+"','";
        	}
        	custrow += custRec[custRec.length-1]+somenum+"')";
        	
        	String sqlInsert = "insert into " + tableName + " values ('"+ 
        			somenum +"',"+custrow;
        	
        	System.out.println (sqlInsert);
            stmt = conn.createStatement();
            stmt.execute(sqlInsert);
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

	public  String[] getCustomers() {
		String[] custRaw = new String[custRec.length+1];
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + tableName);
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i=1; i<=numberCols; i++) {
                //print Column Names
                System.out.print(rsmd.getColumnLabel(i)+"\t\t");  
            }

            System.out.println("\n-------------------------------------------------");

            while(results.next()) {
            	for (int i = 1; i < custRec.length; i++) {
            		custRaw[i-1] = results.getString(i);
            		System.out.print(results.getString(i)+"\t\t");
            	}
            	custRaw[custRec.length] = results.getString(custRec.length);
            	System.out.println(results.getString(custRec.length));
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        
        return custRaw;
    }
 
	public  String[] getCustomer(String custId) {
		String[] custRaw = new String[custRec.length+1];
		String sqlSelect = "select * from "+tableName+" where CUSTID='"+custId+"'";
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery(sqlSelect);
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i=1; i<=numberCols; i++) {
                //print Column Names
                System.out.print(rsmd.getColumnLabel(i)+"\t\t");  
            }

            System.out.println("\n-------------------------------------------------");

            boolean quit = false;
            while(results.next() && !quit) {
            	for (int i = 1; i < custRec.length; i++) {
            		custRaw[i-1] = results.getString(i);
            		System.out.print(results.getString(i)+"\t\t");
            	}
            	custRaw[custRec.length] = results.getString(custRec.length);
            	System.out.println(results.getString(custRec.length));
            	quit = true;
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        
        return custRaw;
    }

	public static String[] getCustomer(Connection dbconn, String custId) {
		String[] custRaw = new String[custRec.length+1];
		String sqlSelect = "select * from "+tableName+" where CUSTID='"+custId+"'";
        try {
            stmt = dbconn.createStatement();
            ResultSet results = stmt.executeQuery(sqlSelect);
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i=1; i<=numberCols; i++) {
                //print Column Names
                System.out.print(rsmd.getColumnLabel(i)+"\t\t");  
            }

            System.out.println("\n-------------------------------------------------");

            boolean quit = false;
            while(results.next() && !quit) {
            	for (int i = 1; i < custRec.length; i++) {
            		custRaw[i-1] = results.getString(i);
            		System.out.print(results.getString(i)+"\t\t");
            	}
            	custRaw[custRec.length] = results.getString(custRec.length);
            	System.out.println(results.getString(custRec.length));
            	quit = true;
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        
        return custRaw;
    }

	public void shutdown() {
        try {
            if (stmt != null) {
                stmt.close();
            } if (conn != null) {
                //DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }           
        } catch (SQLException sqlExcept) {
        	sqlExcept.printStackTrace();
        }

    }

	public static boolean closeConn(Connection dbconn) {
        try {
            if (dbconn != null) {
                //DriverManager.getConnection(dbURL + ";shutdown=true");
            	dbconn.close();
            }           
        } catch (SQLException sqlExcept) {
        	sqlExcept.printStackTrace();
        	return false;
        }
        return true;
    }

}
