/*************************************************************************                           
 * LABORATORIO ALGORITMOS Y ESTRUCTURAS 3
 *
 * Clase concreta Arco
 * Compilacion:  javac Arco.java
 *
 *  @author Maria Fernanda Magallanes: 13-10787
 *  @author Victoria Torres: 12-11468
 *************************************************************************
 **/

public class Arco extends Lado
{
  private Vertice vI;
  private Vertice vF;
  
    /**
   * Crea una nuevo arco con un identificador <tt>id</tt>, un peso <tt>peso</tt>
   * un vertice en el extremo inicial <tt>vI</tt> y un vertice en el extremo final <tt>vF</tt>
   * @param  id, id del arco
   * @param  peso, peso del arco
   * @param  vI, vertice inicial del arco
   * @param  vF, vertice final del arco
  */
  public Arco(String id, double peso, Vertice vI, Vertice vF) {
    super(id,peso);
    this.vI = vI;
    this.vF = vF;
  }

  /**
  *  Retorna el vertice <tt>vI</tt> que es el extremo inicial del arco
  * @return vI, vertice inicial del arco
  */
  public Vertice getExtremoInicial() {
    return vI;
  }

  /**
  *  Retorna el vertice <tt>vF</tt> que es el extremo final del arco 
  * @return vF, vertice final del arco
  */
  public Vertice getExtremoFinal() {
    return vF;
  }

  /** 
   * Retorna la representacion String del arco 
   * @return String que representa el arco con el formato:
   * Arco id: "+aId+". Arco peso: "+aPeso+" ( "+vInicial+" ->  "+vFinal+" )
  */
  public String toString() {
    String aId = getId();
    String aPeso = new Double(getPeso()).toString();
    String vInicial = vI.toString();
    String vFinal = vF.toString();

    //String str = "Arco id: "+aId+". Arco peso: "+aPeso+" ( "+vInicial+" ->  "+vFinal+" )";
    String str = vInicial+"------>"+vFinal;

    return str;
  }
}