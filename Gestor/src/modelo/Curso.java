/**
 * Clase Curso
 * @author WonMi Lim y Jimmy Tsang
 */

package modelo;

public class Curso {
  
  private String codigo;
  private String nombre;
  private String escuela;
  private String bloque;
  private int creditos;
  private int horasLectivas;
  
  public Curso(String pCodigo,String pNombre, int pCreditos, int pHorasLectivas, String pEscuela){
    this.codigo = pCodigo;
    this.nombre = pNombre;
    this.creditos = pCreditos;
    this.horasLectivas = pHorasLectivas;
    this.escuela = pEscuela;
  }
  
  public Curso(String pCodigo,String pNombre, int pCreditos, int pHorasLectivas, String pBloque, String pEscuela){
    this.codigo = pCodigo;
    this.nombre = pNombre;
    this.creditos = pCreditos;
    this.horasLectivas = pHorasLectivas;
    this.escuela = pEscuela;
    this.bloque = pBloque;
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getCreditos() {
    return creditos;
  }

  public void setCreditos(int creditos) {
    this.creditos = creditos;
  }

  public int getHorasLectivas() {
    return horasLectivas;
  }

  public void setHorasLectivas(int horasLectivas) {
    this.horasLectivas = horasLectivas;
  }
  
  public String getBloque() {
    return bloque;
  }

  public void setBloque(String bloque) {
    this.bloque = bloque;
  }
  
  public String getEscuela() {
    return escuela;
  }

  public void setEscuela(String escuela) {
    this.escuela = escuela;
  }
}
