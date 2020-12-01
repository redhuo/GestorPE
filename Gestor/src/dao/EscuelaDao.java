package dao;

import modelo.Escuela;
import utils.SQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Clase que se encarga de accesar a la base de datos y adquirir toda la información
 * relacionada a las escuelas.
 */
public class EscuelaDao {

  private SQLConnection conexionSqlite;
  private Connection conexion;
  
  /**
   * Método constructor de la clase.
   * Se encarga de establecer conexion con la base de datos.
   */
  public EscuelaDao(){
    conexionSqlite = new SQLConnection();
  }
  
  /**
   * Método que se encarga de insertar una nueva escuela al gestor.
   * Recibe como párametro un objeto de tipo Escuela.
   * @param nuevo 
   */
  public void insertarNuevaEscuela(Escuela nuevo){
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
        Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  
  /**
   * Método que se encarga de obtener todas las escuelas del gestor.
   * Retorna un ArrayList con todas las escuelas en el gestor.
   * @return 
   */
  public ArrayList<Escuela> getEscuelas(){
    String sql = "select * from escuela";
    Statement statement;
    ArrayList<Escuela> listaEscuela = new ArrayList<>();
    conexion = conexionSqlite.connect();
      try {
        statement = conexion.createStatement();
        ResultSet resultado = statement.executeQuery(sql);
        while(resultado.next()) {
	  Escuela escuela = new Escuela(resultado.getString("codigo"), resultado.getString("nombre"));
          listaEscuela.add(escuela);
        }
        resultado.close();
        statement.close();
      } 
      catch (SQLException ex) {
        Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
      }
      return listaEscuela;
  }
}
