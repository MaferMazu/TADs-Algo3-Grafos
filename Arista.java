/*************************************************************************                           
 * LABORATORIO ALGORITMOS Y ESTRUCTURAS 3
 *
 * Clase concreta Arista
 * Compilacion:  javac Arista.java
 *
 *  @author Maria Fernanda Magallanes: 13-10787
 *  @author Victoria Torres: 12-11468
 *************************************************************************
 **/

public class Arista extends Lado
{
  private Vertice u;
  private Vertice v;
  
  /**
   * Crea una nueva arista con un identificador <tt>id</tt>, un peso <tt>peso</tt>
   * un vertice en el extremo inicial <tt>u</tt> y un vertice en el extremo final <tt>v</tt>
   * @param  id, id de la arista
   * @param  peso, peso de la arista
   * @param  u, vertice inicial de la arista
   * @param  v, vertice final de la arista
  */
  public Arista(String id, double peso, Vertice u, Vertice v) {
  super(id,peso);
  this.u = u;
  this.v = v; 
  }

  /**
  *  Retorna el vertice <tt>u</tt> que es el extremo inicial de la arista 
  * @return u, vertice inicial de la arista
  */
  public Vertice getExtremo1() {
    return u;
  }

  /**
  *  Retorna el vertice <tt>v</tt> que es el extremo final de la arista 
  * @return v, vertice final de la arista
  */
  public Vertice getExtremo2() {
    return v;
  }

  /** 
   * Retorna la representacion String de la arista 
   * @return String que representa la arista con el formato:
   * Arista id: "+arId+". Arista peso: "+arPeso+" ( "+vInicial+" ->  "+vFinal+" )
  */
  public String toString() {
    String arId = getId();
    String arPeso = new Double(getPeso()).toString();
    String vInicial = u.toString();
    String vFinal = v.toString();

    //String str = "Arista id: "+arId+". Arista peso: "+arPeso+" ( "+vInicial+" ->  "+vFinal+" )";
    String str = vInicial+"------>"+vFinal;

    return str;
  }
}