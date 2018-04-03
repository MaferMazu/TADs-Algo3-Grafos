/*************************************************************************                           
 * LABORATORIO ALGORITMOS Y ESTRUCTURAS 3
 *
 * Clase que lee una matriz y la convierte en GrafoNoDirigido
 * Compilacion:  javac Lectura.java
 *
 *  @author Maria Fernanda Magallanes: 13-10787
 *  @author Victoria Torres: 12-11468
 *************************************************************************
 **/
import java.util.LinkedList;
import java.util.List;

public class LecturaMatrices{
    private int nCasos;
    private LinkedList<int[]> size;                           //Numero de casos a leer
    public Lectura(){
        nCasos = 0;
        size = new LinkedList();
    }
    public LinkedList<int[]> getSize(){
        return size;
    }
    public LinkedList<GrafoNoDirigido> leer(String dirArchivo) {
        LinkedList<GrafoNoDirigido> lista = new LinkedList();
        try{
            In in = new In(dirArchivo);
            nCasos = in.readInt();
            char letra;
            String id;
            String idArista;
            boolean b;
            while(nCasos>0){
                nCasos=nCasos-1;
                Integer nFila = in.readInt();
                Integer nColumnas = in.readInt();
                int[] par = new int[2];
                par[0] = nFila;
                par[1] = nColumnas;
                size.add(par);
                GrafoNoDirigido grafo = new GrafoNoDirigido();
                for(int i = 0; i < nFila; i++){
                    letra = in.readChar();
                    for(int j = 0; j < nColumnas; j++){
                        letra = in.readChar();
                        id = "(" + String.valueOf(i) + "," + String.valueOf(j) + ")";
                        b = grafo.agregarVertice(id,0.0,Character.toString(letra));
                    }
                }

                for(int i = 0; i < nFila; i++){
                    for(int j = 0; j < nColumnas; j++){
                        id = "(" + String.valueOf(i) + "," + String.valueOf(j) + ")";
                        if(i!=0){
                            String idArriba = "(" + String.valueOf(i-1) + "," + String.valueOf(j) + ")";
                            idArista = idArriba + "-" + id;
                            b = grafo.agregarArista(idArista,0.0,id,idArriba);

                            if(j!=(nColumnas-1)){
                                String idArribaDerecha = "(" + String.valueOf(i-1) + "," + String.valueOf(j+1) + ")";
                                idArista = idArribaDerecha + "-" + id;
                                b = grafo.agregarArista(idArista,0.0,id,idArribaDerecha);
                            }
                            if(j!=0){
                                String idArribaIzquierda = "(" + String.valueOf(i-1) + "," + String.valueOf(j-1) + ")";
                                idArista = idArribaIzquierda + "-" + id;
                                b = grafo.agregarArista(idArista,0.0,id,idArribaIzquierda);
                            }
                        }
                        if(i!=(nFila-1)){
                            String idAbajo = "(" + String.valueOf(i+1) + "," + String.valueOf(j) + ")";
                            idArista = idAbajo + "-" + id;
                            b = grafo.agregarArista(idArista,0.0,id,idAbajo);

                            if(j!=(nColumnas-1)){
                                String idAbajoDerecha = "(" + String.valueOf(i+1) + "," + String.valueOf(j+1) + ")";
                                idArista = idAbajoDerecha + "-" + id;
                                b = grafo.agregarArista(idArista,0.0,id,idAbajoDerecha);
                            }
                            if(j!=0){
                                String idAbajoIzquierda = "(" + String.valueOf(i+1) + "," + String.valueOf(j-1) + ")";
                                idArista = idAbajoIzquierda + "-" + id;
                                b = grafo.agregarArista(idArista,0.0,id,idAbajoIzquierda);
                            }
                        }
                        if(j!=0){
                            String idAnterior = "(" + String.valueOf(i) + "," + String.valueOf(j-1) + ")";
                            idArista = idAnterior + "-" + id;
                            b = grafo.agregarArista(idArista,0.0,id,idAnterior);
                        }
                        if(j!=(nColumnas-1)){
                            String idSiguiente = "(" + String.valueOf(i) + "," + String.valueOf(j+1) + ")";
                            idArista = idSiguiente + "-" + id;
                            b = grafo.agregarArista(idArista,0.0,id,idSiguiente);
                        }
                    }
                }
                lista.add(grafo);
            }
            return lista;
        }
        catch(Exception e){
            System.out.println("No se pudo cargar el archivo, y se ha retornado una lista vacía");
            return lista;
        }
    }
}
