/**
 * Clase Plan de Estudio
 * @author WonMi Lim y Jimmy Tsang
 */

package modelo;

public class PlanDeEstudio {
  
  private int numero;
  private String fechaVigencia;
  private String escuela;
  
  public PlanDeEstudio(int pNumero, String pFecha, String pEscuela){
    this.numero = pNumero;
    this.fechaVigencia = pFecha;
    this.escuela = pEscuela;
  }

  public int getNumero() {
    return numero;
  }

  public void setNumero(int numero) {
    this.numero = numero;
  }

  public String getFecha() {
    return fechaVigencia;
  }

  public void setFecha(String fecha) {
    this.fechaVigencia = fecha;
  }

  public String getEscuela() {
    return escuela;
  }

  public void setEscuela(String escuela) {
    this.escuela = escuela;
  }
  
}
