/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arco Iris
 */
public class Curso {
  
  private String codigo;
  private String nombre;
  private int creditos;
  private int horasLectivas;
  private Escuela escuela;
  private Curso requisitos;
  private Curso correquisitos;
  
  public Curso(String pCodigo,String pNombre, int pCreditos, int pHorasLectivas,Escuela pEscuela){
    this.codigo = pCodigo;
    this.nombre = pNombre;
    this.creditos = pCreditos;
    this.horasLectivas = pHorasLectivas;
    this.escuela = pEscuela; 
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

  public Escuela getEscuela() {
    return escuela;
  }

  public void setEscuela(Escuela escuela) {
    this.escuela = escuela;
  }

  public Curso getRequisitos() {
    return requisitos;
  }

  public void setRequisitos(Curso requisitos) {
    this.requisitos = requisitos;
  }

  public Curso getCorrequisitos() {
    return correquisitos;
  }

  public void setCorrequisitos(Curso correquisitos) {
    this.correquisitos = correquisitos;
  }
  
}
