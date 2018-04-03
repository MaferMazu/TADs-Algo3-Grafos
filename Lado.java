/*************************************************************************                           
 * LABORATORIO ALGORITMOS Y ESTRUCTURAS 3
 *
 * Clase concreta Lado
 * Compilacion:  javac Lado.java
 *
 *  @author Maria Fernanda Magallanes: 13-10787
 *  @author Victoria Torres: 12-11468
 *************************************************************************
 **/

public abstract class Lado
{
  private String id;         //Identificador del lado
  private double peso;       //Peso del lado

  /**
   * Crea un nuevo lado con un identificador <tt>id</tt> y un peso <tt>peso</tt>
   * @param  id, id del lado
   * @param  peso, peso del lado
  */
  public Lado(String id, double peso) {
    this.id = id;
    this.peso = peso;
  }

  /**
  *  Retorna el identificador <tt>id</tt> del lado 
  * @return id del lado
  */
  public String getId() {
    return id;
  }

  /**
   * Retorna el peso  <tt>peso</tt> del lado.
   * @return peso del lado
  */
  public double getPeso() {
    return peso;
  }

  /** 
   * Retorna la representacion String del lado 
   * @return String de representacion del lado
  */
  public abstract String toString();
}