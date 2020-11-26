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
public class PlanDeEstudioDAO {
  private SQLConnection conexionSqlite;
  private Connection conexion;
  
  public PlanDeEstudioDAO(){
    conexionSqlite = new SQLConnection();
  }
  public void insertarNuevoPlan(PlanDeEstudio nuevo,String escuela){
    String sql = "insert into plan_de_estudio(numero,fechaVigencia) values(?,?)";
    String sql2 = "insert into escuela_plandeestudio(numero_plan,codigo_escuela) values(?,?)";
    PreparedStatement statement;
    conexion = conexionSqlite.connect();
    try {
      statement = conexion.prepareStatement(sql);
      statement.setInt(1,nuevo.getNumero());
      statement.setString(2,nuevo.getFecha());
      statement.executeUpdate();
      statement.close();
    } 
    catch (SQLException ex) {
      Logger.getLogger(EscuelaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
      statement = conexion.prepareStatement(sql2);
      statement.setInt(1,nuevo.getNumero());
      statement.setString(2,escuela);
      statement.executeUpdate();
      statement.close();
    } 
    catch (SQLException ex) {
      Logger.getLogger(EscuelaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public void insertarCurso(int plan, String curso){
    String sql = "insert into plandeestudio_curso(plan,curso) values(?,?)";
    PreparedStatement statement;
    conexion = conexionSqlite.connect();
    try {
      statement = conexion.prepareStatement(sql);
      statement.setInt(1,plan);
      statement.setString(2,curso);
      statement.executeUpdate();
      statement.close();
    } 
    catch (SQLException ex) {
      Logger.getLogger(EscuelaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public boolean eliminarCursoDelPlan(String curso){
    boolean eliminado = false;
    String sql = "DELETE FROM plandeestudio_curso WHERE codigo=?";
    PreparedStatement statement;
    try{
      statement = conexion.prepareStatement(sql);
      statement.setString(1,curso);
      eliminado = statement.executeUpdate() > 0;
      statement.close(); 
    }
    catch(SQLException ex){
      Logger.getLogger(EscuelaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return eliminado;
  }
  
}
