package costimator;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *
 * @author Jobair_Joty
 */
public class sqliteJDBC {
    public static final String dbname = "costimator.db";

    
    public static void connect(String cfilename) {
       Connection conn = null;
        try {
            String url = "jdbc:sqlite:"+cfilename;
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connected to the database");
        } catch (Exception e) {
            
            System.out.println(e.getMessage()+"Its not working");
        }finally{
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage()+"i am from ex");
            }
        }
       
    }
    
    public static void createDatabase(String dfilename) {
        
        String url = "jdbc:sqlite:"+dfilename;
        
        try (Connection conn = DriverManager.getConnection(url)){
            if(conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.err.println("The driver name is  "+meta.getDriverName());
                System.out.println("a new database has been created.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    

    public static void createTable(String databasename,String tableName,String typeName,String typeAmount) {
        //String url = "jdbc:sqlite:C:/sqlite/"+databasename;
        String url = "jdbc:sqlite:"+databasename;
        
        String sql ="CREATE TABLE IF NOT EXISTS "+tableName+" (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+typeName+" TEXT, "+typeAmount+" INTEGER,date TEXT);";
        
        
        try (Connection conn = DriverManager.getConnection(url)){
            Statement stml = conn.createStatement();
            
            stml.execute(sql);
            System.out.println("Table Created");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static Connection conn() {
        
        String url = "jdbc:sqlite:costimator.db";
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection(url);
            
        } catch (Exception e) {
        }
        
        return conn;
    }
    public static void costinsert(String costType,int costAmount,String date) {
        String sql = "INSERT INTO costManager(costType,costAmount,date) VALUES(?,?,?);";
        try (Connection conn = sqliteJDBC.conn();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setString(1,costType);
            String cAmount = String.valueOf(costAmount);
            pstmt.setString(2,cAmount);
            pstmt.setString(3,date);
     
            pstmt.executeUpdate();
            
            
        } catch (Exception e) {
        }
    }
   
        public static void incomeinsert(String incomeType,int incomeAmount,String date) {
        String sql = "INSERT INTO incomeManager(incomeType,incomeAmount,date) VALUES(?,?,?);";
        try (Connection conn = sqliteJDBC.conn();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setString(1,incomeType);
            String cAmount = String.valueOf(incomeAmount);
            pstmt.setString(2,cAmount);
            pstmt.setString(3,date);
     
            pstmt.executeUpdate();
            
            
        } catch (Exception e) {
        }
    }
    
    public static void selectAll() {
        String sql = "SELECT id,costType,costName,costAmount FROM costManager";
        
        try(Connection conn = sqliteJDBC.conn();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            
            while(rs.next()) {
                System.out.println(rs.getInt("id")+ "\t"+ rs.getString("costType"+"\t"+rs.getString("costName")+"\t"+rs.getString("costAmount")));
               
               
            }
        }catch(Exception e){
            
        }
    }

    
    
   

}
