/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Arco Iris
 */
public class SQLConnection {
  
  private Connection conn = null;
  
  public Connection connect() {
    
    try {
      // db parameters
      String url = "jdbc:sqlite:/home/wonmi9501/Documentos/gestorpe";
      // create a connection to the database
      conn = DriverManager.getConnection(url);
            
      System.out.println("Connection to SQLite has been established.");
      System.out.println(conn.getMetaData());
      return conn;
            
    } 
    catch (SQLException e) {
      System.out.println(e.getMessage());
      
    } 
   /* finally {
      try {
        if(conn != null) {
           conn.close();
        }
      } 
      catch (SQLException ex) {
        System.out.println(ex.getMessage());
      }
    }*/
    return null;
  }
  
  public static void main(String[] args){   
    SQLConnection con = new SQLConnection();
    con.connect();
  }
}