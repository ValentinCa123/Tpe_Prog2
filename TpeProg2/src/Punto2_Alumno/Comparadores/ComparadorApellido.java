package Punto2_Alumno.Comparadores;

import java.util.Comparator;

import Punto2_Alumno.Alumno;

public class ComparadorApellido<T> implements Comparator<T>{

	@Override
	public int compare(T o1, T o2) {
		return ((Alumno) o1).getApellido().compareTo(((Alumno) o2).getApellido());
	}

}
