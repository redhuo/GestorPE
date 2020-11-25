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
public class Escuela {

  private String codigo;
  private String nombre;
  
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
  
}
