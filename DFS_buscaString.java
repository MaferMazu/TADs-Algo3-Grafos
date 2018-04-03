/*************************************************************************                           
 * QUIZ 1
 *
 * Clase: DFS_buscaString
 * Compilacion:  javac DFS_buscaString.java
 *
 * Prueba todas las funciones.
 *
 *  @author Maria Fernanda Magallanes: 13-10787
 *
 * Implementación de www.cs.toronto.edu/~heap/270F02/node36.html
 *************************************************************************
 **/
import java.util.Stack;

public class DFS_buscaString{
	private boolean[][] visited;

	public DFS_buscaString(){
		visited = new boolean[1][1];
	}

	public int dfs_Buscar(Grafo g, String string, int nFilas, int nColumnas){
		String letra = Character.toString(string.charAt(0));
		boolean b;
		int i=1;
		int respuesta = 0;
		for(Vertice v: g.vertices()){
			if(v.getInfo().equals(letra)){
				String conseguido = "";
				Stack<Vertice> stack = new Stack();
				visited = new boolean[nFilas][nColumnas]; 
				for(Vertice u: g.vertices()){
					b = editarVisitado(u,false);
				}
				stack.push(v);
				while(!(stack.empty())){
					Vertice m = stack.peek();
					stack.pop();
					if(!(fueVisitado(m))){
						b=editarVisitado(m,true);
						conseguido = conseguido + m.getInfo();
						String letra1 = Character.toString(string.charAt(i));
						for(Vertice w: m.getAdyacentes()){
							if(w.getInfo().equals(letra1)){
								stack.push(w);
							}	
						}
						i++;
					}	
				}
				if(respuesta<conseguido.length()){
					respuesta = conseguido.length();
				}
			}
		}
		return respuesta;
	}
	
	public boolean fueVisitado(Vertice v){
		String id = v.getId().substring(1,v.getId().length()-1);
		String[] ij = id.split(",");
		return visited[Integer.parseInt(ij[0])][Integer.parseInt(ij[1])];
	}

	public boolean editarVisitado(Vertice v,boolean b){
		String id = v.getId().substring(1,v.getId().length()-1);
		String[] ij = id.split(",");
		visited[Integer.parseInt(ij[0])][Integer.parseInt(ij[1])] = b;
		return visited[Integer.parseInt(ij[0])][Integer.parseInt(ij[1])] == b;
	}

	public boolean imprimirMatriz(boolean[][] matriz){
		for (int x=0; x < matriz.length; x++) {
	    	System.out.print("|");
	    	for (int y=0; y < matriz[x].length; y++) {
	    		System.out.print (matriz[x][y]);
	    		if (y!=matriz[x].length-1) System.out.print("\t");
	    		}
	    	System.out.println("|");
    	}return true;
	}
}
