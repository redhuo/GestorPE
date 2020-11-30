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
  
  private final IntegerProperty numero;
  private final StringProperty fechaVigencia;
  private final StringProperty escuela;
  
  public PlanDeEstudio(int pNumero, String pFecha, String pEscuela){
    this.numero = new SimpleIntegerProperty(pNumero);
    this.fechaVigencia = new SimpleStringProperty(pFecha);
    this.escuela = new SimpleStringProperty(pEscuela);
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
  
}
