/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import utils.SQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Curso;

/**
 *
 * @author Jimmy
 */
public class CursoDao {
    
  private SQLConnection conexionSqlite;
  private Connection conexion;
  
  public CursoDao(){
    conexionSqlite = new SQLConnection();
  }
  
  public void insertarNuevoCurso(Curso nuevo,String codigoEscuela){
    String sql = "insert into curso(codigo,nombre,creditos,horas_lectivas) values(?,?,?,?)";
    String sql2 = "insert into escuela_curso(codigo_escuela,codigo_curso) values(?,?)";
    conexion = conexionSqlite.connect();
    PreparedStatement statement;
    try {
      statement = conexion.prepareStatement(sql);
      statement.setString(1,nuevo.getCodigo());
      statement.setString(2,nuevo.getNombre());
      statement.setInt(3,nuevo.getCreditos());
      statement.setInt(4,nuevo.getHorasLectivas());
      statement.executeUpdate();
      statement.close();
    } 
    catch (SQLException ex) {
      Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
      statement = conexion.prepareStatement(sql2);
      statement.setString(1,codigoEscuela);
      statement.setString(2,nuevo.getCodigo());
      statement.executeUpdate();
      statement.close();
    } 
    catch (SQLException ex) {
      Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
      
  }
  
  public void insertarRequisito(String curso,String requisito){
    String sql = "insert into curso_requisito(codigo_curso,codigo_requisito) values(?,?)";
    conexion = conexionSqlite.connect();
    PreparedStatement statement;
    try {
        statement = conexion.prepareStatement(sql);
        statement.setString(1,curso);
        statement.setString(2,requisito);
        statement.executeUpdate();
        statement.close();
      } 
      catch (SQLException ex) {
        Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  public void insertarCorrequisito(String curso,String correquisito){
    String sql = "insert into curso_requisito(codigo_curso,codigo_requisito) values(?,?)";
    conexion = conexionSqlite.connect();
    PreparedStatement statement;
    try {
      statement = conexion.prepareStatement(sql);
      statement.setString(1,curso);
      statement.setString(2,correquisito);
      statement.executeUpdate();
      statement.close();
      } 
      catch (SQLException ex) {
        Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  
  public LinkedList<Curso> getCursosPorEscuela(String codigoEscuela){
    String sql = "select * from escuela_curso where codigo_escuela = ?";
    String sql2 = "select * from curso where codigo= ?";
    PreparedStatement statement;
    String codigoCurso = "";
    LinkedList<Curso> cursos = new LinkedList<Curso>();
    conexion = conexionSqlite.connect();
    try {
      statement = conexion.prepareStatement(sql);
      statement.setString(1,codigoEscuela);
      ResultSet resultado = statement.executeQuery();
      if (resultado.next()) {
	codigoCurso = resultado.getString("codigo_curso");
      }
      resultado.close();
      statement.close();
    } 
    catch (SQLException ex) {
      Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
      statement = conexion.prepareStatement(sql2);
      statement.setString(1,codigoCurso);
      ResultSet resultado = statement.executeQuery();
      if (resultado.next()) {
	Curso nuevo = new Curso(resultado.getString("codigo"), resultado.getString("nombre"), 
            resultado.getInt("creditos"), resultado.getInt("hora_lectivas"), codigoEscuela);
        cursos.add(nuevo);
        System.out.println(nuevo.getNombre());
      }
      resultado.close();
      statement.close();
    } 
    catch (SQLException ex) {
      Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return cursos; 
  }
  
  public boolean eliminarRequisito(String curso){
    boolean eliminado = false;
    String sql = "DELETE FROM curso_requisito WHERE codigo_curso=?";
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
  
  public boolean eliminarCorrequisito(String curso){
    boolean eliminado = false;
    String sql = "DELETE FROM curso_correquisito WHERE codigo_curso=?";
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
  
  public boolean eliminarCurso(String curso){
    
    String sql = "select count(*) as total from plandeestudio_curso where codigo=?";
    String sql2 = "DELETE FROM curso WHERE codigo=?";
    PreparedStatement statement;
    
    try{
      statement = conexion.prepareStatement(sql);
      statement.setString(1,curso);
      ResultSet resultado = statement.executeQuery();
      if (resultado.next()) {
        if(resultado.getInt("total")>0){
          System.out.println("Curso se encuenra en un plan de estudio");
          return false;
        }
      }
      statement.close(); 
    }
    catch(SQLException ex){
      Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    try{
      statement = conexion.prepareStatement(sql2);
      statement.setString(1,curso);
      if(statement.executeUpdate() > 0){
          return true;
      };
      statement.close(); 
    }
    catch(SQLException ex){
      Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }
}
