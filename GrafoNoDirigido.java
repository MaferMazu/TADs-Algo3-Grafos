/*************************************************************************                           
 * LABORATORIO ALGORITMOS Y ESTRUCTURAS 3
 *
 * Clase concreta GrafoNoDirigido
 * Compilacion:  javac GrafoNoDirigido.java
 *
 *  @author Maria Fernanda Magallanes: 13-10787
 *  @author Victoria Torres: 12-11468
 *************************************************************************
 **/

import java.util.LinkedList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.NoSuchElementException;


public class GrafoNoDirigido implements Grafo
{
    private int nVertices;                              //Atributo numero de vertices en el grafo
    private int nLados;                                 //Atributo numero de lados en el grafo
    private LinkedList<Vertice> listaVertices;          //Atributo lista de vertices del grafo
    private LinkedList<Arista> listaLados;              //Atributo lista de lados del grafo


    public GrafoNoDirigido() {
        nVertices = 0;                                  //Inicializa numero de vertices en el grafo
        nLados = 0;                                     //Inicializa numero de lados en el grafo
        listaVertices = new LinkedList();               //Inicializa lista de vertices del grafo
        listaLados = new LinkedList();                  //Inicializa lista de lados del grafo
    }

    /**
    *Este metodo carga un grafo no dirigido a partir de un archivo de tipo .txt 
    *que toma el numero de vertices, el numero de lados, el id y peso del 
    *vertice, y el id y peso de la arista con los respectivos id de los vertices
    *que lo conforman
    *
    *@param dirArchivo Nombre del archivo .txt
    *@throws IllegalArgumentException El nombre del archivo no es
    *el correcto
    *@return false, El grafo no se cargo satifactoriamente
    *@return true, Grafo cargado satifactoriamente
    */
    public boolean cargarGrafo(String dirArchivo) {
        try{
        In in = new In(dirArchivo);
        Integer numVertices = in.readInt();
        Integer numLados = in.readInt();

        if(numVertices > 0 && numLados > 0){

            String idVertice, idArista, idV1, idV2, a;
            Double pesoVertice, pesoArista, b;

            for(int i = 0; i < numVertices; i++) {
                idVertice = in.readString();
                pesoVertice = in.readDouble();

                Vertice v = new Vertice(idVertice, pesoVertice);
                agregarVertice(v);
            }    

            for(int j = 0; j < numLados; j++){
                idArista = in.readString();
                idV1 = in.readString();
                idV2 = in.readString();
                pesoArista = in.readDouble();

                agregarArista(idArista, pesoArista, idV1, idV2);
            }
            return true;
        }
        else{
            return false;
        }
    }catch(Exception e){
            System.out.println("No se pudo cargar el archivo");
            return false;
     }

    }
    
    /**
    *Metodo que retorna el numero de vertices
    *@return numero de vertices
    */
    public int numeroDeVertices() {
        return nVertices;
    }

    /**
    *Metodo que retorna el numero de lados
    *@return numero de lados
    */
    public int numeroDeLados() {
        return nLados;
    }
   
   /**
    *Metodo que agrega un vertice al grafo
    *@param v, objeto de tipo vertice
    *@return <tt>true</tt>, si se agrega el vertice
    *@return <tt>false</tt>, si no se agrega el vertice
    */
    public boolean agregarVertice(Vertice v) {
        if (estaVertice(v.getId())){
            return false;
        }
        else{
            nVertices++;
            listaVertices.add(v);
            return true;
        }
    }

    /**
    *Metodo que agrega un vertice al grafo
    *@param id, identificador del vertice
    *@param peso, peso del vertice
    *@return <tt>true</tt>, si se agrega el vertice
    *@return <tt>false</tt>, si no se agrega el vertice
    */
    public boolean agregarVertice(String id, double peso) {
        Vertice v = new Vertice(id,peso);
        return agregarVertice(v);
    }
    
    /**
    *Metodo que dado el identificador del vertice se busca en el grafo,
    *el objeto vertice asociado.
    *@param id, identificador del vertice
    *@throws NoSuchElementException si el vertice no se encuentra en el grafo
    *@return vertice pedido con el identificador id
    */
    public Vertice obtenerVertice(String id) throws NoSuchElementException {
        try{
            for(Vertice v: listaVertices){
                if(v.getId().equals(id)){
                    return v;
                }
            }
        }
        catch (NoSuchElementException nsee){
            System.out.println("El grafo no contiene ningun vertice con ese identificador.");
        }
        return null;
    }

    /**
    *Metodo que dice si el vertice se encuentra o no en el grafo
    *@param id, identificador del vertice
    *@return <tt>true</tt>, si el vertice se encuentra en el grafo
    *@return <tt>false</tt>, si el vertice no se encuentra en el grafo
    */
    public boolean estaVertice(String id) {
        for(Vertice v: listaVertices){
            if(v.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    /**
    *Metodo que dice si el lado solicitado se encuentra o no el grafo
    *@param u, identificador del vertice inicial
    *@param v, identificador del vertice final
    *@return <tt>true</tt>, si el lado se encuentra en el grafo
    *@return <tt>false</tt>, si el lado no se encuentra en el grafo
    */
    public boolean estaLado(String u, String v){
        if(obtenerArista(u,v) == null || obtenerArista(v,u)==null){
            return false;
        }
        else{
            return true;
        }
    }

    /**
    *Metodo que dado un idenficador del vertice, se elimina este vertice del grafo
    *@param id, identificador dee vertice
    *@return <tt>true</tt> si se elimino el vertice
    *@return <tt>false</tt> si no se elimino el vertice
    */
    public boolean eliminarVertice(String id) {
        boolean v = false;

        if(!(estaVertice(id))){
            return false;
        }
        else{
            Vertice vertice = obtenerVertice(id);
            nVertices = nVertices - 1;
            for(Lado lado : vertice.getIncidentes()){
                Arista arista = obtenerArista(lado.getId());
                v = eliminarArista(arista.getId());
            }

            return listaVertices.remove(vertice);
        }
    }

    /**
    *Metodo que da la lista de vertices que se encuentran en el grafo
    *@return lista con los vertices del grafo
    */
    public List<Vertice> vertices() {
        return listaVertices;
    }

    /**
    *Metodo que da la lista de lados que se encuentran en el grafo
    *@return lista con lados del grafo
    */ 
    public List<Lado> lados() {
        LinkedList<Lado> listaDeLados = new LinkedList();
        for(Arista arista: listaLados){
            listaDeLados.add(arista);
        }
        return listaDeLados;
    }

    /**
    *Metodo que dado un identificador id del vertice, calcula y retorna el grado del mismo. 
    *@param id, identificador del vertice
    *@throws NullPointerException, no se encuentra el vertice
    *@return el grado del vertice
    */
    public int grado(String id) {
        try{
            return incidentes(id).size();
        }
        catch (NullPointerException npe){
        }
        return 0;

    }

    /**
    *Metodo que devuelve una lista con todos los vertices adyacentes
    *al vertice con identificador id
    *@param id, identificador del vertice
    *@throws NullPointerException, no se encuentra el vertice
    *@return la lista de adyacentes al vertice con identificador id
    */
    public List<Vertice> adyacentes(String id) {
        try{
            Vertice vertice = obtenerVertice(id);
            return vertice.getAdyacentes();
        }
        catch (NullPointerException npe){
        }
        return null;
    }
 
    /**
    *Metodo que devuelve una lista con todos los lados incidentes al
    *vertice con identificador id
    *@param id, identificador del lado
    *@throws NullPointerException, no se encuentra el vertice
    *@return la lista de lados incidentes al vertice con identidifacor id
    */
    public List<Lado> incidentes(String id) {
        try{
            Vertice vertice = obtenerVertice(id);
            return vertice.getIncidentes();
        }
        catch (NullPointerException npe){
        }
        return null;

    }

    /**
    *Metodo que clona/copia un grafo 
    *@return el grafo clonado
    */
    public Object clone() {
        GrafoNoDirigido grafo = new GrafoNoDirigido();
        grafo.nVertices = this.nVertices;
        grafo.nLados = this.nLados;
        grafo.listaVertices = this.listaVertices;
        grafo.listaLados = this.listaLados;
        return grafo;
    }

    /**
    *Metodo que transforma el grafo construido en String
    *@throws IllegalArgumentException, no se puede escribir el archivo
    *@return el grafo contruido
    */
    public String toString() {
        String ret;
        try{
            BufferedWriter archivo = new BufferedWriter(new FileWriter("grafoNoDirigido.txt"));
            PrintWriter salida = new PrintWriter(archivo);
            salida.print(Integer.toString(this.nVertices) + "\n");
            ret = Integer.toString(this.nVertices) + "\n";
            salida.print(Integer.toString(this.nLados) + "\n");
            ret= ret + Integer.toString(this.nLados) + "\n";
            for(Vertice i: listaVertices){
                salida.print(i.getId() + " " + Double.toString(i.getPeso()) +"\n");
                ret= ret + i.getId() + " " + Double.toString(i.getPeso()) +"\n";
            }
            for (Lado j: listaLados){
                Arista k = obtenerArista(j.getId());
                salida.print(k.getId()+ " " + k.getExtremo1().getId() + " " + k.getExtremo2().getId() + " " + Double.toString(k.getPeso())+"\n");
                ret= ret + k.getId()+ " " + k.getExtremo1().getId() + " " + k.getExtremo2().getId() + " " + Double.toString(k.getPeso()) +"\n";
            }
            salida.close();
            return ret;
        }
        catch (IOException e) {
            throw new IllegalArgumentException("No se pudo escribir correctamente el archivo");
        }
    }

    /**
    *Metodo que permite agregar un arista al grafo
    *@param a, objeto de tipo arista
    *@return <tt>true</tt>, si se agrego el arista
    *@return <tt>false</tt>, si no se agrego el arista
    */
    public boolean agregarArista(Arista a) {
        if(estaLado(a.getExtremo1().getId(), a.getExtremo2().getId())){
            return false;
        }
        else{
            nLados++;
            listaLados.add(a);
            a.getExtremo1().agregarAdyacentes(a.getExtremo2());
            a.getExtremo2().agregarAdyacentes(a.getExtremo1());
            a.getExtremo1().agregarIncidentes(a);
            a.getExtremo2().agregarIncidentes(a);
            return true;
        }
    }

    /**
    *Metodo que permite agregar una arista al grafo
    *@param id, identificador del lado
    *@param peso, peso del lado
    *@param eInicial, identificador del vertice inicial
    *@param eFinal, identificador del vertice final
    *@throws NullPointerException, no se encuentra el arista
    *@return <tt>true</tt>, si se agrego el arista
    *@return <tt>false</tt>, si no se agrego el arista
    */
    public boolean agregarArista(String id, double peso, String u, String v) {
        try{
            Vertice v1 = obtenerVertice(u);
            Vertice v2 = obtenerVertice(v);
            Arista a = new Arista(id,peso,v1,v2);
            return agregarArista(a);
        }
        catch (NullPointerException npe){
        }
        return false;

    }

    /**
    *Metodo que dado un idenficador del arista, elimina este arista del grafo
    *@param id, identificador del arista
    *@return <tt>true</tt>, si se elimino el arista
    *@return <tt>false</tt>, si no se elimino el arista
    */
    public boolean eliminarArista(String id) {
        try{
            Arista a = obtenerArista(id);
            boolean v = false;
            if(!(estaLado(a.getExtremo1().getId(),a.getExtremo2().getId()))){
                return false;
            }
            else{
                v = a.getExtremo1().eliminarAdyacentes(a.getExtremo2());
                v = a.getExtremo2().eliminarAdyacentes(a.getExtremo1());
                v = a.getExtremo1().eliminarIncidentes(a);
                v = a.getExtremo2().eliminarIncidentes(a);
                nLados = nLados - 1;
                return listaLados.remove(a);
            }   
        }
        catch (NullPointerException npe){
        }
        return false;
    }

    /**
    *Metodo que dado el identificador del arista, busca en el grafo el objeto
    *arista asociado.
    *@param id, identificador del arista
    *@throws NoSuchElementException, el vertice no se encuentra en el grafo
    *@return arista pedido con el identificador id
    */
    public Arista obtenerArista(String id){
        try{
            for(Arista a: listaLados){
                if(a.getId().equals(id)){
                    return a;
                }
            }
        }
        catch (NoSuchElementException nsee){
            System.out.println("El grafo no contiene ningun lado con ese identificador.");
        }
        return null;
    }

    /**
    *Metodo que dado los identificadores de los vertices inicial y final, busca en
    *el grafo el objeto arista asociado.
    *@param u, identificador del vertice inicial
    *@param v, identificador del vertice final
    *@return arista pedido con el identificador id
    */
    public Arista obtenerArista(String u, String v) {
        for(Arista a : listaLados){
            if(a.getExtremo1().getId().equals(u)){
                if(a.getExtremo2().getId().equals(v)){
                    return a;
                }
            }
            if(a.getExtremo2().getId().equals(u)){
                if(a.getExtremo1().getId().equals(v)){
                    return a;
                }
            }
        }
        return null;
    }
}