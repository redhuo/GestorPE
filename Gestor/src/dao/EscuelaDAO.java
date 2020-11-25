/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.Escuela;
import utils.SQLConnection;
import java.util.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Jimmy
 */
public class EscuelaDAO {

  private SQLConnection conexionSqlite;
  private Connection conexion;
  
  public EscuelaDAO(){
    conexionSqlite = new SQLConnection();
  }
  
  public void insertar(Escuela nuevo){
    String sql = "insert into escuela(codigo,nombre) values(?,?)";
    PreparedStatement statement;
    conexion = conexionSqlite.connect();
      try {
        statement = conexion.prepareStatement(sql);
        statement.setString(1,nuevo.getCodigo());
        statement.setString(2,nuevo.getNombre());
        statement.executeUpdate();
        statement.close();
      } 
      catch (SQLException ex) {
        Logger.getLogger(EscuelaDAO.class.getName()).log(Level.SEVERE, null, ex);
      }
  }

  public static void main (String[] args){
    EscuelaDAO escuela = new EscuelaDAO();
    Escuela nuevo = new Escuela("IC","Escuela de Ingeniería en Computación");
    escuela.insertar(nuevo);
  }
}
