/**
 * Clase Escuela ( o area academica )
 * @author WonMi Lim y Jimmy Tsang
 */

package modelo;

public class Escuela {

  private String codigo;
  private String nombre;
  
  /**
   * Constructor Escuela default vacio
   */
  public Escuela(){ }
  
  /**
   * Construtor Escuela cons atributos especificados
   * @param pCodigo
   * @param pNombre 
   */
  public Escuela(String pCodigo, String pNombre){
    this.codigo = pCodigo;
    this.nombre = pNombre;
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
  
  @Override
  public String toString(){
    String msg;
    msg = "Codigo: " + codigo + ", Nombre: " + nombre;
    return msg;
  }
}
