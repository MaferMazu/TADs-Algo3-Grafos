/*************************************************************************                           
 * LABORATORIO ALGORITMOS Y ESTRUCTURAS 3
 *
 * Interface <code>Grafo</code>
 * Representa al objeto matematico Grafo.
 * Contiene las operaciones que se pueden realizar en cualquier tipo de grafo
 * (dirigido o no dirigido).
 *
 *  @author Maria Fernanda Magallanes: 13-10787
 *  @author Victoria Torres: 12-11468
 *************************************************************************
 **/
import java.util.LinkedList;
import java.util.List;

public interface Grafo
{
    public boolean cargarGrafo(String dirArchivo);
    
    public int numeroDeVertices();

    public int numeroDeLados();
    
    public boolean agregarVertice(Vertice v);

    public boolean agregarVertice(String id, double peso);
    
    public Vertice obtenerVertice(String id);

    public boolean estaVertice(String id);

    public boolean estaLado(String u, String v);

    public boolean eliminarVertice(String id);

    public List<Vertice> vertices();

    public List<Lado> lados();

    public int grado(String id);

    public List<Vertice> adyacentes(String id);
 
    public List<Lado> incidentes(String id);

    public Object clone();

    public String toString();
}