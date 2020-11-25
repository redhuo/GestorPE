package modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arco Iris
 */
public class PlanDeEstudio {
  
  private int numero;
  private String fecha;
  private String bloque;
  private Escuela escuela;
  private Curso curso;
  
  public PlanDeEstudio(int pNumero, String pFecha){
    this.numero = pNumero;
    this.fecha = pFecha;
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

  public String getBloque() {
    return bloque;
  }

  public void setBloque(String bloque) {
    this.bloque = bloque;
  }

  public Escuela getEscuela() {
    return escuela;
  }

  public void setEscuela(Escuela escuela) {
    this.escuela = escuela;
  }

  public Curso getCurso() {
    return curso;
  }

  public void setCurso(Curso curso) {
    this.curso = curso;
  }
  
}
