/*************************************************************************                           
 * LABORATORIO ALGORITMOS Y ESTRUCTURAS 3
 *
 * Clase concreta Digrafo
 * Compilacion:  javac Digrafo.java
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

public class Digrafo implements Grafo
{
    private int nVertices;                        //Atributo numero de vertices en el grafo
    private int nLados;                           //Atributo numero de lados en el grafo
    private LinkedList<Vertice> listaVertices;     //Atributo lista de vertices del grafo
    private LinkedList<Arco> listaLados;           //Atributo lista de lados del grafo

    public Digrafo() {
        nVertices = 0;                            //Inicializa numero de vertices en el grafo
        nLados = 0;                               //Inicializa numero de lados en el grafo
        listaVertices = new LinkedList();         //Inicializa lista de vertices del grafo
        listaLados = new LinkedList();            //Inicializa lista de lados del grafo
    }

    /**
    *Este metodo carga un digrafo a partir de un archivo de tipo .txt 
    *que toma el numero de vertices, el numero de lados, el id y peso del 
    *vertice, y el id y peso del Arco con los respectivos id de los vertices
    *que lo conforman
    *
    *@param dirArchivo Nombre del archivo .txt
    *@throws IllegalArgumentException El nombre del archivo no es el correcto
    *@return <tt>false</tt>, El grafo no se cargo
    *@return <tt>true</tt>, Grafo cargado satifactoriamente
    */

    public boolean cargarGrafo(String dirArchivo) {
        try{
        In in = new In(dirArchivo);
        Integer numVertices = in.readInt();
        Integer numLados = in.readInt();

        if(numVertices > 0 && numLados > 0){

            String idVertice, idArco, idV1, idV2, a;
            Double pesoVertice, pesoArco, b;

            for(int i = 0; i < numVertices; i++) {
                idVertice = in.readString();
                pesoVertice = in.readDouble();

                Vertice v = new Vertice(idVertice, pesoVertice);
                agregarVertice(v);
            }    

            for(int j = 0; j < numLados; j++){
                idArco = in.readString();
                idV1 = in.readString();
                idV2 = in.readString();
                pesoArco = in.readDouble();

                agregarArco(idArco, pesoArco, idV1, idV2);
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
        }
        System.out.println("El grafo no contiene ningun vertice con ese identificador.");
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
        if(obtenerArco(u,v) == null){
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
                Arco arco = obtenerArco(lado.getId());
                v = eliminarArco(arco.getId());
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
        for(Arco arco: listaLados){
            listaDeLados.add(arco);
        }
        return listaDeLados;
    }

    /**
    *Metodo que dado un identificador id del vertice, calcula y retorna el grado del mismo. 
    *@param id, identificador del vertice
    *@throws NullPointerException, no se encuentra el vertice
    *@return el grado del vertice
    */
    public int grado(String id){
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
        Digrafo digrafo = new Digrafo();
        digrafo.nVertices = this.nVertices;
        digrafo.nLados = this.nLados;
        digrafo.listaVertices = this.listaVertices;
        digrafo.listaLados = this.listaLados;
        return digrafo;

    }

    /**
    *Metodo que transforma el grafo construido en String
    *@throws IllegalArgumentException, no se puede escribir el archivo
    *@return el grafo contruido
    */
    public String toString() {
        String ret;
        try{
            BufferedWriter archivo = new BufferedWriter(new FileWriter("digrafo.txt"));
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
                Arco k = obtenerArco(j.getId());
                salida.print(k.getId()+ " " + k.getExtremoInicial().getId() + " " + k.getExtremoFinal().getId() + " " + Double.toString(k.getPeso())+"\n");
                ret= ret + k.getId()+ " " + k.getExtremoInicial().getId() + " " + k.getExtremoFinal().getId() + " " + Double.toString(k.getPeso()) +"\n";
            }
            salida.close();
            return ret;
        }
        catch (IOException e) {
            throw new IllegalArgumentException("No se pudo escribir correctamente el archivo");
        }
    }

    /**
    *Metodo que permite agregar un arco al grafo
    *@param a, objeto de tipo arco
    *@return <tt>true</tt>, si se agrego el arco
    *@return <tt>false</tt>, si no se agrego el arco
    */
    public boolean agregarArco(Arco a) {
        if(estaLado(a.getExtremoInicial().getId(), a.getExtremoFinal().getId())){
            return false;
        }
        else{
            nLados++;
            listaLados.add(a);
            a.getExtremoInicial().agregarAdyacentes(a.getExtremoFinal());
            a.getExtremoInicial().agregarIncidentes(a);
            a.getExtremoFinal().agregarIncidentes(a);
            return true;
        }

    } 

    /**
    *Metodo que permite agregar una arco al grafo
    *@param id, identificador del lado
    *@param peso, peso del lado
    *@param eInicial, identificador del vertice inicial
    *@param eFinal, identificador del vertice final
    *@throws NullPointerException, no se encuentra el arco
    *@return <tt>true</tt>, si se agrego el arco
    *@return <tt>false</tt>, si no se agrego el arco
    */
    public boolean agregarArco(String id, double peso, String eInicial, String eFinal){
        try{
            Vertice vI = obtenerVertice(eInicial);
            Vertice vF = obtenerVertice(eFinal);
            Arco arco = new Arco(id,peso,vI,vF);
            return agregarArco(arco);
        }
        catch (NullPointerException npe){
        }
        return false;

    }

    /**
    *Metodo que dado un identificador id del vertice, calcula y retorna el grado
    *interno del mismo.
    *@param id, identificador del vertice
    *@throws NullPointerException, el vertice no se encuentra en el grafo
    *@return el grado interior del vertice
    */
    public int gradoInterior(String id) {
        try{
            return predecesores(id).size();
        }
        catch (NullPointerException npe){
        }
        return 0;
    }

    /**
    *Metodo que dado un identificador id del vertice, calcula y retorna el grado
    *externo del mismo.
    *@param id, identificador del vertice
    *@throws NullPointerException el vertice no se encuentra en el grafo
    *@return el grado exterior del vertice
    */
    public int gradoExterior(String id) {
        try{
            return sucesores(id).size();
        }
        catch (NullPointerException npe){
        }
        return 0;
    }

    /**
    *Metodo que dado un identificador id del vertice, retorna su lista de sucesores.
    *@param id, identificador del vertice
    *@throws NullPointerException, el vertice no se encuentra en el grafo)
    *@return la lista de sucesores del vertice
    */
    public List<Vertice> sucesores(String id) {
        try{
            return adyacentes(id);
        }
        catch (NullPointerException npe){
        }
        return null;

    }

    /**
    *Metodo que dado un identificador id del vertice, retorna su lista de predecesores.
    *@param id, identificador del vertice
    *@throws NullPointerException, el vertice no se encuentra en el grafo
    *@return la lista de sucesores del vertice
    */
    public List<Vertice> predecesores(String id) {
        try{
            Vertice vertice = obtenerVertice(id);
            LinkedList<Vertice> listaPredecesores = new LinkedList();
            for(Lado lado: vertice.getIncidentes()){
                Arco arco = obtenerArco(lado.getId());
                if(arco.getExtremoFinal().getId().equals(id)){
                    listaPredecesores.add(arco.getExtremoInicial());
                }
            }
            return listaPredecesores;
        }
        catch (NullPointerException npe){
        }
        return null;


    }

    /**
    *Metodo que dado un idenficador del arco, elimina este arco del grafo
    *@param id, identificador del arco
    *@return <tt>true</tt>, si se elimino el arco
    *@return <tt>false</tt>, si no se elimino el arco
    */
    public boolean eliminarArco(String id) {
        try{
            Arco arco = obtenerArco(id);
            boolean v = false;
            if(!(estaLado(arco.getExtremoInicial().getId(),arco.getExtremoFinal().getId()))){
                return false;
            }
            else{
                v = arco.getExtremoInicial().eliminarAdyacentes(arco.getExtremoFinal());
                v = arco.getExtremoInicial().eliminarIncidentes(arco);
                v = arco.getExtremoFinal().eliminarIncidentes(arco);
                nLados = nLados - 1;
                return listaLados.remove(arco);
            }
        }
        catch (NullPointerException npe){
        }
        return false;


    }

    /**
    *Metodo que dado el identificador del arco, busca en el grafo el objeto
    *arco asociado.
    *@param id, identificador del arco
    *@throws NoSuchElementException, el vertice no se encuentra en el grafo
    *@return arco pedido con el identificador id
    */
    public Arco obtenerArco(String id) throws NoSuchElementException{
        try{
            for(Arco arco: listaLados){
                if(arco.getId().equals(id)){
                    return arco;
                }
            }
        }
        catch (NoSuchElementException nsee){
        }
        System.out.println("El grafo no contiene ningun lado con ese identificador.");
        return null;
    }

    /**
    *Metodo que dado los identificadores de los vertices inicial y final, busca en
    *el grafo el objeto arco asociado.
    *@param u, identificador del vertice inicial
    *@param v, identificador del vertice final
    *@return arco pedido con el identificador id
    */
    public Arco obtenerArco(String u, String v) {
        for(Arco arco : listaLados){
            if(arco.getExtremoInicial().getId().equals(u)){
                if(arco.getExtremoFinal().getId().equals(v)){
                    return arco;
                }
            }
        }
        return null;
    }

}