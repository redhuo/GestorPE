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
  
  private final StringProperty codigo;
  private final StringProperty nombre;
  private final StringProperty escuela;
  private final StringProperty bloque = new SimpleStringProperty("");;
  private final IntegerProperty creditos;
  private final IntegerProperty horasLectivas;
  
  public Curso(String pCodigo,String pNombre, int pCreditos, int pHorasLectivas, String pEscuela){
    this.codigo = new SimpleStringProperty(pCodigo);
    this.nombre =  new SimpleStringProperty(pNombre);
    this.creditos = new SimpleIntegerProperty(pCreditos);
    this.horasLectivas = new SimpleIntegerProperty(pHorasLectivas);
    this.escuela =  new SimpleStringProperty(pEscuela);
  }
  
  public Curso(String pCodigo,String pNombre, int pCreditos, int pHorasLectivas, String pBloque, String pEscuela){
    this.codigo = new SimpleStringProperty(pCodigo);
    this.nombre = new SimpleStringProperty(pNombre);
    this.creditos = new SimpleIntegerProperty(pCreditos);
    this.horasLectivas = new SimpleIntegerProperty(pHorasLectivas);
    this.escuela = new SimpleStringProperty(pEscuela);
    this.bloque.set(pBloque);
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
}
