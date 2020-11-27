/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Curso;
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
    String sql = "insert into plan_de_estudio(numero,fechaVigencia,escuela) values(?,?,?)";
    PreparedStatement statement;
    conexion = conexionSqlite.connect();
    try {
      statement = conexion.prepareStatement(sql);
      statement.setInt(1,nuevo.getNumero());
      statement.setString(2,nuevo.getFecha());
      statement.setString(3,nuevo.getEscuela());
      statement.executeUpdate();
      statement.close();
    } 
    catch (SQLException ex) {
      Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public void insertarCurso(int plan, String curso,int bloque){
    String sql = "insert into bloque(plan,curso,numero) values(?,?,?)";
    PreparedStatement statement;
    conexion = conexionSqlite.connect();
    try {
      statement = conexion.prepareStatement(sql);
      statement.setInt(1,plan);
      statement.setString(2,curso);
      statement.setInt(3,bloque);
      statement.executeUpdate();
      statement.close();
    } 
    catch (SQLException ex) {
      Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public ArrayList<PlanDeEstudio> getPlanesDeEstudioPorEscuela(String codigoEscuela){
    String sql = "select * from plan_de_estudio where escuela = ?";
    PreparedStatement statement;
    String codigoCurso = "";
    ArrayList<PlanDeEstudio> planes = new ArrayList<>();
    conexion = conexionSqlite.connect();
    try {
      statement = conexion.prepareStatement(sql);
      statement.setString(1,codigoCurso);
      ResultSet resultado = statement.executeQuery();
      if (resultado.next()) {
	PlanDeEstudio nuevo = new PlanDeEstudio(resultado.getInt(0), resultado.getString(1), resultado.getString(2));
        planes.add(nuevo);
        System.out.println(nuevo.getNumero());
      }
      resultado.close();
      statement.close();
    } 
    catch (SQLException ex) {
      Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return planes; 
  }
  
  public ObservableList<PlanDeEstudio> getPlanesDeEstudioPorCurso(String curso){
    String sql = "select * from bloque where curso = ?";
    String sql2 = "select * from plan_de_estudio where numero = ?";
    PreparedStatement statement;
    ArrayList<Integer> numeros = new ArrayList<>();
    ObservableList<PlanDeEstudio> planes = FXCollections.observableArrayList();
    conexion = conexionSqlite.connect();
    try {
      statement = conexion.prepareStatement(sql);
      statement.setString(1,curso);
      ResultSet resultado = statement.executeQuery();
      if (resultado.next()) {
	numeros.add(resultado.getInt("plan"));
      }
      resultado.close();
      statement.close();
    } 
    catch (SQLException ex) {
      Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
      statement = conexion.prepareStatement(sql2);
      ResultSet resultado = null;
      for(int numero : numeros){
        statement.setInt(1,numero);
        resultado = statement.executeQuery();
        if (resultado.next()) {
          PlanDeEstudio nuevo = new PlanDeEstudio(resultado.getInt(0), resultado.getString(1), resultado.getString(2));
          planes.add(nuevo);
          System.out.println(nuevo.getNumero());
        }
      }      
      resultado.close();
      statement.close();
    } 
    catch (SQLException ex) {
      Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return planes; 
  }
  
  public boolean eliminarCursoDelPlan(String curso){
    boolean eliminado = false;
    String sql = "DELETE FROM bloque WHERE codigo=?";
    PreparedStatement statement;
    try{
      statement = conexion.prepareStatement(sql);
      statement.setString(1,curso);
      eliminado = statement.executeUpdate() > 0;
      statement.close(); 
    }
    catch(SQLException ex){
      Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return eliminado;
  }
  
}
