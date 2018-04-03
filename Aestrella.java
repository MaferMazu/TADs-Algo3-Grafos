import java.util.LinkedList;
import java.util.List;

public class Aestrella{

	double infinito;

	public Aestrella(){
		infinito = Double.POSITIVE_INFINITY;
	}

	public int caminoMasCorto(GrafoNoDirigido grafo, Nodo nodoS, Nodo nodoF){
		LinkedList<Vertice> listaCerrados;
		listaCerrados = new LinkedList<Vertice>();
		ColaPrioridad colaAbiertos;
		colaAbiertos = new ColaPrioridad();

		List<Vertice> listaVertices;
		listaVertices = grafo.vertices();

		for (Vertice iterador : listaVertices){
			iterador.setPadre(null);
			iterador.setPeso(infinito);
			iterador.setG(infinito);
		}

		nodoS.getVertice().setG(0);
		nodoS.getVertice().setPeso( h(nodoS.getVertice(),nodoF.getVertice()) );

		Elemento elemento;
		elemento = new Elemento(nodoS,nodoS.getVertice().getPeso());

		colaAbiertos.agregarElemento(elemento);

		while (colaAbiertos.getTamano()>0){
			Elemento actual = colaAbiertos.extraerMinimo();
			if (actual.getNodo().getVertice().getId().equals(nodoF.getVertice().getId())){
				 reconstruirCamino(actual.getNodo().getVertice());
				 return 0;
			}

			listaCerrados.add(actual.getNodo().getVertice());
			List<Vertice> adyacentes = grafo.adyacentes(actual.getNodo().getVertice().getId());
			for (Vertice iterador2 : adyacentes){
				if (listaCerrados.contains(iterador2)){

					continue;
				}
				if (!colaAbiertos.estaEnLaCola(iterador2)){

					Nodo nodo;
					Elemento e1;
					nodo = new Nodo(iterador2);
					e1 = new Elemento(nodo, iterador2.getPeso());
					colaAbiertos.agregarElemento(e1);
				}


				double gTentativo = actual.getNodo().getVertice().getG() + h(iterador2,actual.getNodo().getVertice());

				if (gTentativo >= iterador2.getG()){
						continue;
				}

				iterador2.setPadre(actual.getNodo().getVertice());

				iterador2.setG(gTentativo);
				iterador2.setPeso(iterador2.getG()+h(iterador2,nodoF.getVertice()));
			}

		}

		return 0;

	}

	public double h(Vertice c1, Vertice c2){
		double posX1;
		double posX2;
		double posY1;
		double posY2;

		double distanciaEuclediana;
		posX1 = c1.getPosX();
		posX2 = c2.getPosX();

		posY1 = c1.getPosY();
		posY2 = c2.getPosY();

		distanciaEuclediana = Math.sqrt( Math.pow(posX1-posX2,2) + Math.pow(posY1 - posY2,2) );
		return distanciaEuclediana;
	}

	public LinkedList<Vertice> reconstruirCamino(Vertice s){
		LinkedList<Vertice> listaDePadres;
		listaDePadres = new LinkedList<Vertice>();
		while (s.getPadre()!=null){
			//System.out.println("Soy su papa!: " + s.getPadre().getId());
			listaDePadres.add(s.getPadre());
			s = s.getPadre();
			reconstruirCamino(s);
		}

		return listaDePadres;


	}




}
