/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Escuela;
import modelo.PlanDeEstudio;
import utils.SQLConnection;

/**
 *
 * @author Jimmy
 */
public class PlanDeEstudioDao {
  private SQLConnection conexionSqlite;
  private Connection conexion;
  
  public PlanDeEstudioDao(){
    conexionSqlite = new SQLConnection();
  }
  public void insertarNuevoPlan(PlanDeEstudio nuevo){
    String sql = "insert into plan_de_estudio(codigo,nombre) values(?,?)";
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
  
}
