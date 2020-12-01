package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que se encarga de crear una conexión con la base de datos.
 */
public class SQLConnection {
  
  private Connection conn = null;
  
  /**
   * Método que se encarga de conectar el gestor con la base de datos.
   * Retorna la conexión.
   * @return 
   */
  public Connection connect() {
    
    try {
      // db parameters
      //String url = "jdbc:sqlite:/home/wonmi9501/Documentos/gestorpe";
     String url = "jdbc:sqlite:C:/sqlite/gestorpe.db";
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
