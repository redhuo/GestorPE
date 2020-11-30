/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author Jimmy
 */
public class EscuelaDao {

  private SQLConnection conexionSqlite;
  private Connection conexion;
  
  public EscuelaDao(){
    conexionSqlite = new SQLConnection();
  }
  
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
          System.out.println(resultado.getString("nombre"));
        }
        resultado.close();
        statement.close();
      } 
      catch (SQLException ex) {
        Logger.getLogger(EscuelaDao.class.getName()).log(Level.SEVERE, null, ex);
      }
      return listaEscuela;
  }

  public static void main (String[] args){
    EscuelaDao escuela = new EscuelaDao();
    Escuela nuevo = new Escuela("IC","Escuela de Ingeniería en Computación");
    //escuela.insertarNuevaEscuela(nuevo);
  }
}
