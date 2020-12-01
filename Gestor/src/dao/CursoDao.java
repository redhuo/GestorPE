package dao;

import java.sql.Array;
import utils.SQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import modelo.Curso;

/**
 * Clase que se encarga de accesar a a la base de datos y adquirir toda la información
 * relacionada a los cursos.
 * 
 */
public class CursoDao {
    
  private SQLConnection conexionSqlite;
  private Connection conexion;
  
  /**
   * Contructor de la clase CursoDao.
   * No recibe nada como parámetro. Se encarga de inicializar la conexión con la base de datos.
   */
  public CursoDao(){
    conexionSqlite = new SQLConnection();
  }
  
  /**
   * Método que se escarga de insertar nuevos cursos a la base de datos.
   * Recibe como parámetro el codigo del nuevo curso y el codigo de la escuela 
   * al que pertenece el curso.
   * @param nuevo
   * @param codigoEscuela 
   */
  public void insertarNuevoCurso(Curso nuevo,String codigoEscuela){
    String sql = "insert into curso(codigo,nombre,creditos,horas_lectivas,escuela) values(?,?,?,?,?)";
    conexion = conexionSqlite.connect();
    PreparedStatement statement;
    try {
      statement = conexion.prepareStatement(sql);
      statement.setString(1,nuevo.getCodigo());
      statement.setString(2,nuevo.getNombre());
      statement.setInt(3,nuevo.getCreditos());
      statement.setInt(4,nuevo.getHorasLectivas());
      statement.setString(5,codigoEscuela);
      statement.executeUpdate();
      statement.close();
    } 
    catch (SQLException ex) {
      Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  /**
   * Método que se encarga de ingresar a la base de datos los requisitos de un curso.
   * Recibe como parámetro el código del curso al cual se le esta relacionando un requisito y 
   * el codigo del curso requisito.
   * @param curso
   * @param requisito 
   */
  public void insertarRequisito(String curso,String requisito){
    String sql = "insert into curso_requisito(curso,requisitos) values(?,?)";
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
  /**
   * Método que se encarga de ingresar a la base de datos los requisitos de un curso.
   * Recibe como parámetro el código del curso al cual se le esta relacionando un requisito y 
   * el codigo del curso correquisito.
   * @param curso
   * @param correquisito 
   */
  public void insertarCorrequisito(String curso,String correquisito){
    String sql = "insert into curso_correquisito(curso,correquisito) values(?,?)";
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
  
  /**
   * Método que se encarga de obtener todos los cursos que se han ingresado en el gestor.
   * Retorna un ArrayList con todos los cursos existentes.
   * @return 
   */
  public ArrayList<Curso> getCursos(){
    String sql = "select * from curso";
    PreparedStatement statement;
    ArrayList<Curso> cursos = new ArrayList<Curso>();
    conexion = conexionSqlite.connect();
    try {
      statement = conexion.prepareStatement(sql);
      ResultSet resultado = statement.executeQuery();
      while(resultado.next()) {
        Curso nuevo = new Curso(resultado.getString("codigo"), resultado.getString("nombre"), 
            resultado.getInt("creditos"), resultado.getInt("horas_lectivas"), resultado.getString("escuela"));
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
  
  /**
   * Método que se encarga de obtener todos los cursos de una escuela en específico.
   * Recibe como parámetro el codigo de a escuela a consultar.
   * Retorna un ArrayList con todos los cursos pertenecientes a la escuela dada.
   * @param codigoEscuela
   * @return 
   */
  public ArrayList<Curso> getCursosPorEscuela(String codigoEscuela){
    String sql = "select * from curso where escuela = ?";
    PreparedStatement statement;
    ArrayList<Curso> cursos = new ArrayList<Curso>();
    conexion = conexionSqlite.connect();
    try {
      statement = conexion.prepareStatement(sql);
      statement.setString(1,codigoEscuela);
      ResultSet resultado = statement.executeQuery();
      while(resultado.next()) {
        Curso nuevo = new Curso(resultado.getString("codigo"), resultado.getString("nombre"), 
            resultado.getInt("creditos"), resultado.getInt("horas_lectivas"), codigoEscuela);
        cursos.add(nuevo);
      }
      resultado.close();
      statement.close();
    } 
    catch (SQLException ex) {
      Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return cursos; 
  }
  
  /**
   * Método que se encarga de obtner todos los cursos de un plan dado.
   * Recibe como parámetro el número del plan.
   * Retorna un ObservableList con todos los cursos de un determinado plan.
   * @param plan
   * @return 
   */
  public ObservableList<Curso> getCursosPorPlan(int plan){
    String sql = "select * from bloque where plan = ?";
    String sql2 = "select * from curso where codigo = ?";
    PreparedStatement statement;
    ArrayList<String[]> codigos = new ArrayList<>();
    ObservableList<Curso> cursos = FXCollections.observableArrayList();
    conexion = conexionSqlite.connect();
    try {
      statement = conexion.prepareStatement(sql);
      statement.setInt(1,plan);
      ResultSet resultado = statement.executeQuery();
      while(resultado.next()) {
        String[] conjunto = {resultado.getString("curso"),resultado.getString("numero")};
	codigos.add(conjunto);
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
      for(String[] codigo : codigos){
        statement.setString(1,codigo[0]);
        resultado = statement.executeQuery();
        while(resultado.next()) {
          Curso nuevo = new Curso(resultado.getString("codigo"), resultado.getString("nombre"), 
              resultado.getInt("creditos"), resultado.getInt("horas_lectivas"), 
              codigo[1], resultado.getString("escuela"));
          cursos.add(nuevo);
          System.out.println(nuevo.getNombre());
        }
      }      
      try{
          resultado.close();
      }
      catch(Exception e){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Mensade del plan");
        alert.setHeaderText(null);
        alert.setContentText("Plan seleccionado no presenta cursos");
        alert.showAndWait();
      }
      statement.close();
    } 
    catch (SQLException ex) {
      Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return cursos; 
  }
  
  /**
   * Método que se encarga de obtener todos los requisitos de un curso dado.
   * Recibe como parámetro el codigo del curso.
   * Retorna un ArrayList con todos los requisitos del curso.
   * @param curso
   * @return 
   */
  public ArrayList<Curso> getCursosRequisitos(String curso){
    String sql = "select * from curso_requisito where curso = ?";
    String sql2 = "select * from curso where codigo = ?";
    PreparedStatement statement;
    ArrayList<String> codigos = new ArrayList<>();
    ArrayList<Curso> cursos = new ArrayList<>();
    conexion = conexionSqlite.connect();
    try {
      statement = conexion.prepareStatement(sql);
      statement.setString(1,curso);
      ResultSet resultado = statement.executeQuery();
      while(resultado.next()) {
	codigos.add(resultado.getString("requisitos"));
      }
    //  resultado.close();
      statement.close();
    } 
    catch (SQLException ex) {
      Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
      statement = conexion.prepareStatement(sql2);
      ResultSet resultado = null;
      
      for(String codigo : codigos){
        statement.setString(1,codigo);
        resultado = statement.executeQuery();
        while(resultado.next()) {
          Curso nuevo = new Curso(resultado.getString("codigo"), resultado.getString("nombre"), 
              resultado.getInt("creditos"), resultado.getInt("horas_lectivas"), resultado.getString("escuela"));
          cursos.add(nuevo);
          System.out.println("aqui j2 : " + nuevo.getNombre());
        }
      }      
//      resultado.close();
      statement.close();
    } 
    catch (SQLException ex) {
      Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return cursos; 
  }
  
  /**
   * Método que se encarga de obtener todos los correquisitos de un curso dado.
   * Recibe como parámetro el codigo del curso.
   * Retorna un ArrayList con todos los correquisitos del curso.
   * @param curso
   * @return 
   */
  public ArrayList<Curso> getCursosCorrequisitos(String curso){
    String sql = "select * from curso_correquisito where curso = ? or correquisito = ?";
    String sql2 = "select * from curso where codigo = ?";
    PreparedStatement statement;
    ArrayList<String> codigos = new ArrayList<>();
    ArrayList<Curso> cursos = new ArrayList<>();
    conexion = conexionSqlite.connect();
    try {
      statement = conexion.prepareStatement(sql);
      statement.setString(1,curso);
      statement.setString(2,curso);
      ResultSet resultado = statement.executeQuery();
      if (resultado.next()) {
        if(resultado.getString("curso").equals(curso)){
  	  codigos.add(resultado.getString("correquisito"));
        }
        else{
          codigos.add(resultado.getString("curso"));
        }
      }
      //resultado.close();
      statement.close();
    } 
    catch (SQLException ex) {
      Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
      statement = conexion.prepareStatement(sql2);
      ResultSet resultado = null;
      for(String codigo : codigos){
        statement.setString(1,codigo);
        resultado = statement.executeQuery();
        if (resultado.next()) {
          Curso nuevo = new Curso(resultado.getString("codigo"), resultado.getString("nombre"), 
              resultado.getInt("creditos"), resultado.getInt("horas_lectivas"), resultado.getString("escuela"));
          cursos.add(nuevo);
         // System.out.println(nuevo.getNombre());
          System.out.println("aqui j3 : " + nuevo.getNombre());
        }
      }      
      //resultado.close();
      statement.close();
    } 
    catch (SQLException ex) {
      Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return cursos; 
  }
  
  /**
   * Método que se encarga de eliminar un requisito de un curso.
   * Recibe como parámetro el codigo del requisito y el codigo del curso.
   * Retorna true si se pudo eliminar el requisito y false si no se puedo eliminar
   * el requisito.
   * @param requisito
   * @param curso
   * @return 
   */
  public boolean eliminarRequisito(String requisito, String curso){
    boolean eliminado = false;
    String sql = "DELETE FROM curso_requisito WHERE curso = ? and requisitos = ?";
    PreparedStatement statement;
    try{
      statement = conexion.prepareStatement(sql);
      statement.setString(1,curso);
      statement.setString(2,requisito);
      eliminado = statement.executeUpdate() > 0;
      statement.close(); 
      eliminado = true;
    }
    catch(SQLException ex){
      Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return eliminado;
  }
  
  /**
   * Método que se encarga de eliminar un correquisito de un curso.
   * Recibe como parámetro el codigo del correquisito y el codigo del curso.
   * Retorna true si se pudo eliminar el correquisito y false si no se puedo eliminar
   * el correquisito.
   * @param correquisito
   * @param curso
   * @return 
   */
  public boolean eliminarCorrequisito(String correquisito, String curso){
    boolean eliminado = false;
    String sql = "DELETE FROM curso_correquisito WHERE curso = ? and correquisito = ?";
    PreparedStatement statement;
    try{
      statement = conexion.prepareStatement(sql);
      statement.setString(1,curso);
      statement.setString(2,correquisito);
      eliminado = statement.executeUpdate() > 0;
      if(!eliminado){
        statement.setString(1,correquisito);
        statement.setString(2,curso);
        eliminado = statement.executeUpdate() > 0;
      }
      statement.close(); 
      eliminado = true;
    }
    catch(SQLException ex){
      Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return eliminado;
  }
  
  /**
   * Método que se encarga de eliminar un curso.
   * Recibe como parámetro el codigo del curso.
   * Retorna true si el curso se puedo eliminar y false si el curso no se pudo 
   * eliminar.
   * @param curso
   * @return 
   */
  public boolean eliminarCurso(String curso){
    String sql = "select count(*) as total from bloque where curso=?";
    String sql2 = "DELETE FROM curso WHERE codigo=?";
    PreparedStatement statement;
    
    try{
      statement = conexion.prepareStatement(sql);
      statement.setString(1,curso);
      ResultSet resultado = statement.executeQuery();
      if (resultado.next()) {
        if(resultado.getInt("total")>0){
          System.out.println("Curso se encuentra en un plan de estudio");
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
