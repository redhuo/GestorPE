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
import modelo.PlanDeEstudio;
import utils.SQLConnection;

/**
 * Clase que se encarga de accesar a a la base de datos y adquirir toda la información
 * relacionada a los planes de estudio.
 */
public class PlanDeEstudioDao {
  private SQLConnection conexionSqlite;
  private Connection conexion;
  
  /**
   * Método constructor de la clase.
   * Se encarga de establecer conexion con la base de datos.
   */
  public PlanDeEstudioDao(){
    conexionSqlite = new SQLConnection();
  }
  
  /**
   * Método que se encarga de insertar un nuevo plan de estudio al gestor.
   * Recibe como párametro un objeto de tipo PlanDeEstudio.
   * @param nuevo 
   */
  public void insertarNuevoPlan(PlanDeEstudio nuevo){
    String sql = "insert into plan_de_estudio(numero,fechaVigencia,escuela) values(?,?,?)";
    PreparedStatement statement;
    conexion = conexionSqlite.connect();
    try {
      statement = conexion.prepareStatement(sql);
      statement.setInt(1,nuevo.getNumero());
      statement.setString(2,nuevo.getFechaVigencia());
      statement.setString(3,nuevo.getEscuela());
      statement.executeUpdate();
      statement.close();
    } 
    catch (SQLException ex) {
      Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  /**
   * Método que se encarga de insertar un curso a un plan de estudio en el gestor.
   * Recibe como párametro el codigo del plan, el codigo del curso y el bloque en 
   * la que se encuentra el curso.
   * @param plan
   * @param curso
   * @param bloque 
   */
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
  
  /**
   * Método que se encarga de obtener todos los planes de estudio de una escuela.
   * Recibe como parámetro el codigo de la escuela.
   * Retorna un ArrayList con todos los planes de estudio de una escuela.
   * @param codigoEscuela
   * @return 
   */
  public ArrayList<PlanDeEstudio> getPlanesDeEstudioPorEscuela(String codigoEscuela){
    String sql = "select * from plan_de_estudio where escuela = ?";
    PreparedStatement statement;
    String codigoCurso = "";
    ArrayList<PlanDeEstudio> planes = new ArrayList<>();
    conexion = conexionSqlite.connect();
    try {
      statement = conexion.prepareStatement(sql);
      statement.setString(1,codigoEscuela);
      ResultSet resultado = statement.executeQuery();
      while (resultado.next()) {
	PlanDeEstudio nuevo = new PlanDeEstudio(resultado.getInt("numero"), 
            resultado.getString("fechaVigencia"), resultado.getString("escuela"));
        planes.add(nuevo);
      }
      resultado.close();
      statement.close();
    } 
    catch (SQLException ex) {
      Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return planes; 
  }
  
  /**
   * Método que se encarga de obtener todos los planes de estudio al que un curso pertenece.
   * Recibe como parámetro el codigo del curso.
   * Retorna un ObservableList con todos los planes de estudios.
   * @param curso
   * @return 
   */
  public ObservableList<PlanDeEstudio> getPlanesDeEstudioPorCurso(String curso){
    String sql = "select * from bloque where curso = ?";
    String sql2 = "select * from plan_de_estudio where numero = ?";
    PreparedStatement statement;
    ArrayList<Integer> numeros = new ArrayList<Integer>();
    ObservableList<PlanDeEstudio> planes = FXCollections.observableArrayList();
    conexion = conexionSqlite.connect();
    try {
      statement = conexion.prepareStatement(sql);
      statement.setString(1,curso);
      ResultSet resultado = statement.executeQuery();
      while (resultado.next()) {
	numeros.add(resultado.getInt("plan"));
        System.out.println("busca plan "+resultado.getInt("plan"));
      }
      resultado.close();
      statement.close();
    } 
    catch (SQLException ex) {
      Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
      for(int numero : numeros){
        statement = conexion.prepareStatement(sql2);
        ResultSet resultado = null;
        System.out.println("es numero es "+numero);
        statement.setInt(1,numero);
        resultado = statement.executeQuery();
        while (resultado.next()) {
          System.out.println("esto es b "+ resultado.getString("fechaVigencia")+ " ---");
          PlanDeEstudio nuevo = new PlanDeEstudio(resultado.getInt("numero"), resultado.getString("fechaVigencia"), resultado.getString("escuela"));
          planes.add(nuevo);
          System.out.println(nuevo.getNumero());
        }
        resultado.close();
        statement.close();
      }      
      
    } 
    catch (SQLException ex) {
      Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return planes; 
  }
  
  /**
   * Método que se encarga eliminar un curso de un plan de estudio.
   * Recibe como parámetro el número del plan y el codigo del curso.
   * Retorna true si se pudo eliminar y falso si no se pudo eliminar.
   * @param plan
   * @param curso
   * @return 
   */
  public boolean eliminarPlanCurso(int plan, String curso){
    boolean eliminado = false;
    String sql = "DELETE FROM bloque WHERE plan = ? and curso = ?";
    PreparedStatement statement;
    try{
      statement = conexion.prepareStatement(sql);
      statement.setInt(1,plan);
      statement.setString(2,curso);
      eliminado = statement.executeUpdate() > 0;
      statement.close(); 
    }
    catch(SQLException ex){
      Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return eliminado;
  }
  
}
