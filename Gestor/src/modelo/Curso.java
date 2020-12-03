/**
 * Clase Curso
 * @author WonMi Lim y Jimmy Tsang
 */

package modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Curso {
  
  private StringProperty codigo = new SimpleStringProperty("");
  private StringProperty nombre = new SimpleStringProperty("");
  private StringProperty escuela = new SimpleStringProperty("");
  private StringProperty bloque = new SimpleStringProperty("");
  private IntegerProperty creditos = new SimpleIntegerProperty(0);
  private IntegerProperty horasLectivas = new SimpleIntegerProperty(0);
  
  /**
   * Contructor Curso default vacío
   */
  public Curso(){ }
  
  /**
   * Contructor Curso sin bloque especificado
   * @param pCodigo
   * @param pNombre
   * @param pCreditos
   * @param pHorasLectivas
   * @param pEscuela 
   */
  public Curso(String pCodigo,String pNombre, int pCreditos, int pHorasLectivas, String pEscuela){
    setCodigo(pCodigo);
    setNombre(pNombre);
    setCreditos(pCreditos);
    setHorasLectivas(pHorasLectivas);
    setEscuela(pEscuela);
  }
  
  /**
   * Contructor Curso con bloque especificado
   * @param pCodigo
   * @param pNombre
   * @param pCreditos
   * @param pHorasLectivas
   * @param pBloque
   * @param pEscuela 
   */
  public Curso(String pCodigo,String pNombre, int pCreditos, int pHorasLectivas, String pBloque, String pEscuela){
    setCodigo(pCodigo);
    setNombre(pNombre);
    setCreditos(pCreditos);
    setHorasLectivas(pHorasLectivas);
    setEscuela(pEscuela);
    setBloque(pBloque);
  }

  public String getCodigo() {
    return codigo.get();
  }

  public void setCodigo(String codigo) {
    this.codigo.set(codigo);
  }

  public String getNombre() {
    return nombre.get();
  }

  public void setNombre(String nombre) {
    this.nombre.set(nombre);
  }

  public int getCreditos() {
    return creditos.get();
  }

  public void setCreditos(int creditos) {
    this.creditos.set(creditos);
  }

  public int getHorasLectivas() {
    return horasLectivas.get();
  }

  public void setHorasLectivas(int horasLectivas) {
    this.horasLectivas.set(horasLectivas);
  }
  
  public String getBloque() {
    return bloque.get();
  }

  public void setBloque(String bloque) {
    this.bloque.set(bloque);
  }
  
  public String getEscuela() {
    return escuela.get();
  }

  public void setEscuela(String escuela) {
    this.escuela.set(escuela);
  }
  
  @Override
  public String toString(){
    String msg;
    msg = "Codigo: " + codigo.getValue() + ", Nombre: " + nombre.getValue() + 
       ", Escuela: " + escuela.getValue() + ", Bloque: " + bloque.getValue() +
       ", Creditos: " + creditos.getValue() + ", Horas lectivas: " + horasLectivas.getValue();
    return msg;
  }
  
    public static void main(String[] args) {
      Curso curso = new Curso("a","r",0,0,"t");
      curso.toString();
    }
}
