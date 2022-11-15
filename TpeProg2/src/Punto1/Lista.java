package Punto1;

import java.util.Comparator;

public class Lista<T> implements Iterable<Object>{

	private Nodo<T> cabeza; 
	private Comparator<T> criterioOrden;
	
	
	public Lista(Comparator<T> criterioOrden) {
		this.criterioOrden = criterioOrden;
		this.cabeza = null;
	}
	
	public Lista() {
		
	}
	
	public int getSize(){
		int contador = 0;
		for(Object n: this){
			contador ++;
		}
		return contador;
		
	}
	public int getPosicionPrimerOcurrencia(Nodo<T> n){
		int pos = 0;
		boolean encontrado = false;
		while(pos< this.getSize() && encontrado == false){
				if(n.equals(this.getNodoByPos(pos))){
					encontrado = true;
				}
			pos++;
		}
		return pos;
	}
	
	public void eliminarOcurrencias(T n){
		int pos =0;
		for(Object nodo: this){
			//preguntar esto, hay que redefinir algun equals?
			if(n.equals(((Nodo<T>) nodo).getValor())){
				this.eliminar(pos);	
				pos --;
		}
			pos++;
		}
	}
	
	public void eliminar(int pos){
		if(pos == 0){
			if(this.cabeza.getSiguiente()==null) {
				this.cabeza = null;
			}
			else {
				this.cabeza = this.cabeza.getSiguiente();
			}
		}
		else {
			Nodo<T> aux = new Nodo<T>();
			aux = this.getNodoByPos(pos).getSiguiente();//4 5
			this.getNodoByPos(pos-1).setSiguiente(aux);//3 5
		}
		
	}
	
	public Object getValor(int pos) {
		return this.getNodoByPos(pos).getValor();
	}
	
	
	private Nodo<T> getNodoByPos(int pos){//va a devolver el valor no un nodo
		if(pos< this.getSize()){
			int contador = 0;
			Nodo<T> aux = new Nodo<T>();
			aux = cabeza;
			while(contador < pos){
				aux = aux.getSiguiente();
				contador ++;	
			}
			return aux;	
		}
		return null;	
	}
	
	public void insertar(T o1) {
		Nodo<T> nuevo = new Nodo<T>(o1);
		if(cabeza == null) {// caso lista vacia
			cabeza = nuevo;
		} else {
			Nodo<T> comparacion = cabeza;
			boolean encontre = false;
			int res = this.criterioOrden.compare(comparacion.getValor(), nuevo.getValor());
			if(res >= 1) { // caso nuevo deberia ser la raiz
				nuevo.setSiguiente(cabeza);
				cabeza = nuevo;
			} else {
				while(comparacion.getSiguiente() != null && !encontre) {
					res = this.criterioOrden.compare(comparacion.getSiguiente().getValor(), nuevo.getValor());
					if(res >= 1) { // caso nuevo deberia ir entre dos nodos
						Nodo<T> aux = comparacion.getSiguiente();
						comparacion.setSiguiente(nuevo);
						nuevo.setSiguiente(aux);
						encontre = true;
					} else {
						comparacion = comparacion.getSiguiente(); // seguir recorriendo
					}
				}
				if(!encontre) { // caso fin de la lista
					comparacion.setSiguiente(nuevo);
				}
			}
		}
	}
	
	public void setCriterioOrden(Comparator<T> criterioOrden) {
		this.criterioOrden = criterioOrden;
		this.sort();
	}

	public void setCabeza(Nodo<T> nodo){
		this.cabeza = nodo;
	}

	public Nodo getCabeza() {
		Nodo copiaCabeza = new Nodo();
		copiaCabeza = this.cabeza;
		return copiaCabeza;
	}
	  
	public boolean estaVacia(){
		return this.cabeza == null;
	}

	//esta bien devolver sin argumentos?
	@Override
	public Iterator iterator() {
		Iterator i = new Iterator(this.cabeza); 
		return i;
	}
	
	//hay que implementarlo ja
	public void sort() {
		for( int i= 0; i< this.getSize(); i++) {
			Nodo<T> nodoA = this.getNodoByPos(i);
			for(int j = i; j< this.getSize(); j++) {
				Nodo<T> nodoB = this.getNodoByPos(j);
				T valorA = (T) nodoA.getValor();
				T  valorB = (T) nodoB.getValor();
				if(this.criterioOrden.compare((T)valorA, (T)valorB) >= 1) {
					nodoA.setSiguiente(nodoB.getSiguiente());
					if(nodoA.equals(this.cabeza)) {
						this.setCabeza(nodoB);
					}
					nodoB.setSiguiente(nodoA);
				}
			}			
		}
	}

	public void insertarTodos(Lista<T> auxCopia) {
		for(Object n: this) {
			auxCopia.insertar((T)n);
		}		
	}
	

	
	
	
	
	
	
	
	
}