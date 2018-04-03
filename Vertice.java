/*************************************************************************                           
 * LABORATORIO ALGORITMOS Y ESTRUCTURAS 3
 *
 * Clase concreta Vertice
 * Compilacion:  javac Vertice.java
 *
 *  @author Maria Fernanda Magallanes: 13-10787
 *  @author Victoria Torres: 12-11468
 *************************************************************************
 **/
import java.util.LinkedList;
import java.util.List;

public class Vertice
{
  private String id;                           //Identificador del vertice
  private double peso;                         //Peso del vertice
  private LinkedList<Vertice> adyacentes;       //Lista de adyacentes del vertice
  private LinkedList<Lado> incidentes;          //Lista de incidentes del vertice

  /** 
   * Crea un nuevo vertice con un identificador <tt>id</tt> y un peso <tt>p</tt>
   * @param  id, id del vertice 
   * @param  peso, peso del vertice
  */
  public Vertice(String id, double peso) {
    this.id = id;
    this.peso = peso;
    adyacentes = new LinkedList();
    incidentes = new LinkedList();
  }

  /**
   * Retorna el peso  <tt>peso</tt> del vertice 
   * @return peso del vertice
  */
  public double getPeso() {
    return peso;

  }

  /** 
   * Retorna el identificador <tt>id</tt> del vertice
   * @return id del vertice
  */
  public String getId() {
    return id;
  }

  /** 
   * Retorna la representacion String del vertice 
   * @return String de representacion del vertice
   * con los identificadores <tt>id</tt> + <tt>peso</tt>
   * del vertice en el formato:  "("+vId+"  "+vPeso+")"
  */
  public String toString() {
    String vId = id;
    String vPeso = String.valueOf(peso);
    String str = "("+vId+" , "+vPeso+")";
    return  str; 
  }

  /** 
   * Retorna la lista de vertices adyacentes.
   * @return  List<Vertice> con la lista de adyacentes.
  */
  public List<Vertice> getAdyacentes(){
    return adyacentes;
  }

  /** 
   * Agrega de la lista de adyacentes el vertice <tt>v</tt>.
   * @param  v, del tipo Vertice
  */
  public void agregarAdyacentes(Vertice v){
    adyacentes.add(v);
  }

   /** 
   * Elimina de la lista de adyacentes el vertice <tt>v</tt>.
   * @param  v, del tipo Vertice
  */
  public boolean eliminarAdyacentes(Vertice v){
    return adyacentes.remove(v);
  }

  /** 
   * Retorna la lista de vertices incidentes.
   * @return  List<Vertice> con la lista de incidentes.
  */
  public List<Lado> getIncidentes(){
    return incidentes;
  }

  /** 
   * Agrega de la lista de incidentes el vertice.
   * @param  v, del tipo Lado
  */
  public void agregarIncidentes(Lado v){
    incidentes.add(v);
  }

   /** 
   * Elimina de la lista de incidentes el vertice <tt>v</tt>.
   * @param  v, del tipo Lado
  */
  public boolean eliminarIncidentes(Lado v){
    return incidentes.remove(v);
  }
}