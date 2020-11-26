/**
 * Clase Plan de Estudio
 * @author WonMi Lim y Jimmy Tsang
 */

package modelo;

public class PlanDeEstudio {
  
  private int numero;
  private String fecha;
  private String escuela;
  
  public PlanDeEstudio(int pNumero, String pFecha, String pEscuela){
    this.numero = pNumero;
    this.fecha = pFecha;
    this.escuela = pEscuela;
  }

  public int getNumero() {
    return numero;
  }

  public void setNumero(int numero) {
    this.numero = numero;
  }

  public String getFecha() {
    return fecha;
  }

  public void setFecha(String fecha) {
    this.fecha = fecha;
  }

  public String getEscuela() {
    return escuela;
  }

  public void setEscuela(String escuela) {
    this.escuela = escuela;
  }
  
}
