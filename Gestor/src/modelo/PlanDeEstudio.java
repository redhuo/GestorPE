/**
 * Clase Plan de Estudio
 * @author WonMi Lim y Jimmy Tsang
 */

package modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PlanDeEstudio {
  
  private IntegerProperty numero = new SimpleIntegerProperty(0);
  private StringProperty fechaVigencia = new SimpleStringProperty("");
  private StringProperty escuela = new SimpleStringProperty("");
  
  /**
   * Constructor default vacio
   */
  public PlanDeEstudio(){ }
  
  /**
   * Contructor PlanDeEstudio con atributos especificados
   * @param pNumero
   * @param pFecha
   * @param pEscuela 
   */
  public PlanDeEstudio(int pNumero, String pFecha, String pEscuela){
    setNumero(pNumero);
    setFechaVigencia(pFecha);
    setEscuela(pEscuela);
  }

  public int getNumero() {
    return numero.get();
  }

  public void setNumero(int numero) {
    this.numero.set(numero);
  }

  public String getFechaVigencia() {
    return fechaVigencia.get();
  }

  public void setFechaVigencia(String fecha) {
    this.fechaVigencia.set(fecha);
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
    msg = "Numero: " + numero.getValue() + ", Fecha vigencia: " + fechaVigencia.getValue() + 
       ", Escuela: " + escuela.getValue();
    return msg;
  }
}
