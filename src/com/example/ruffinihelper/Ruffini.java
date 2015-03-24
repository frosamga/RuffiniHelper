package com.example.ruffinihelper;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Ruffini {

	/**
	 * @param args
	 */
	public Ruffini() {
		List<Integer> polinomio = new ArrayList<Integer>();
		polinomio.add(1);
		polinomio.add(2);
		polinomio.add(3);
		polinomio.add(2);
		polinomio.add(1);

		factorizar(polinomio);

	}

	// Fallo aqui, TODO
	public static List<Integer> factorizar(List<Integer> polinomio) {

		System.out.println("Polinomio" + polinomio);
		System.out.println("Divisores: "
				+ divisores(polinomio.get(polinomio.size() - 1)));
		System.out.println("polinomio +  raices debajo");
		List<List<Integer>> factorizado = ruffiniTotal(polinomio,
				divisores(polinomio.get(polinomio.size() - 1)));

		List<Integer> raicesTotales = factorizado.get(1);
		List<Integer> faltaCuadrado = factorizado.get(0);
		List<Float> cuadrado1 = new ArrayList<Float>();

		if (factorizado.get(0).size() == 3) {

			cuadrado1 = cuadrado(faltaCuadrado);

			if (!cuadrado1.isEmpty()) {
				System.out.println("raices: " + cuadrado1.toString()
						+ raicesTotales);
				return null;
			} else {
				System.out.println("polinomio" + faltaCuadrado.toString()
						+ "raices: " + raicesTotales);
				return null;
			}
		}
		return null;
	}

	public static List<Integer> divisores(int terminoInd) {
		int n;
		if (terminoInd < 0) {
			n = -terminoInd;
		} else {
			n = terminoInd;
		}

		List<Integer> divisores = new ArrayList<Integer>();
		List<Integer> divisoresNegativos = new ArrayList<Integer>();
		for (int i = 1; i <= n; i++) {
			if (n % i == 0) {
				divisores.add(i);
			}
		}
		for (int x = 1; x <= n; x++) {
			if (n % x == 0) {
				divisoresNegativos.add(-x);
			}
		}
		divisores.addAll(divisoresNegativos);
		return divisores;
	}

	private static List<Float> cuadrado(List<Integer> faltaCuadrado) {
		float a, b, c;
		a = faltaCuadrado.get(0);
		b = faltaCuadrado.get(1);
		c = faltaCuadrado.get(2);

		float aux = (float) (Math.pow(b, 2) - 4 * a * c);
		List<Float> listaAux = new ArrayList<Float>();
		if (aux < 0) {
			listaAux.clear();
		} else {
			float r1, r2;
			aux = (float) Math.sqrt(aux);
			r1 = (-b + aux) / 2 * a;
			r2 = (-b - aux) / 2 * a;
			listaAux.add(r1);
			listaAux.add(r2);
		}
		return listaAux;
	}

	/*
	 * public static List<Object> ruffini(List<Object> polinomio, List<Object>
	 * divisores) {
	 * 
	 * List<Object> listaAux = new ArrayList<Object>(); List<Object> listaAux1 =
	 * new ArrayList<Object>(); boolean raiz=false; int x=0;
	 * 
	 * if (polinomio.size() <= 3) { return
	 * cuadrado(polinomio.get(0),polinomio.get(1),polinomio.get(2));
	 * 
	 * } else {
	 * 
	 * while(!raiz) { int aux = (Integer)polinomio.get(0); for (int i = 1; i <
	 * polinomio.size(); i++) { aux *= (Integer)divisores.get(x); aux +=
	 * (Integer)polinomio.get(i); listaAux.add(aux); //System.out.println(aux);
	 * } x++; if((Integer)listaAux.get(listaAux.size()-1)==0){ raiz=true;
	 * listaAux1.add(polinomio.get(0)); listaAux1.addAll(listaAux.subList(0,
	 * listaAux.size()-2));
	 * ruffini(listaAux1,divisores(listaAux1.get(listaAux1.size()-1))); }
	 * 
	 * //System.out.println("-----------------------"); } return listaAux; } }
	 */

	/*
	 * public static List<Integer> ruffini1(List<Integer> polinomio,
	 * List<Integer> divisores) {
	 * 
	 * List<Integer> listaAux = new ArrayList<Integer>(); List<Integer>
	 * listaAux1 = new ArrayList<Integer>(); boolean raiz=false; int x=0;
	 * 
	 * if (polinomio.size() <= 3) { //return
	 * cuadrado(polinomio.get(0),polinomio.get(1),polinomio.get(2)); } else {
	 * 
	 * while(!raiz) { int aux = polinomio.get(0); for (int i = 1; i <
	 * polinomio.size(); i++) { aux *= divisores.get(x); aux +=
	 * polinomio.get(i); listaAux.add(aux); System.out.println(aux); } x++;
	 * if(listaAux.get(listaAux.size()-1)==0){ raiz=true;
	 * listaAux1.add(polinomio.get(0)); listaAux1.addAll(listaAux.subList(0,
	 * listaAux.size()-2));
	 * ruffini1(listaAux1,divisores(listaAux1.get(listaAux1.size()-1))); }
	 * 
	 * System.out.println("-----------------------"); } } return listaAux; }
	 */

	public static List<Integer> ruffini1(List<Integer> polinomio,
			List<Integer> divisores) {

		List<Integer> listaAux = new ArrayList<Integer>();
		List<Integer> listaAux1 = new ArrayList<Integer>();
		listaAux1.addAll(polinomio);
		boolean raiz = false;
		int x = 0;

		while (!raiz && x < divisores.size()) {
			int aux = polinomio.get(0);
			for (int i = 1; i < polinomio.size(); i++) {
				aux *= divisores.get(x);
				aux += polinomio.get(i);
				listaAux.add(aux);

			}
			if (listaAux.get(listaAux.size() - 1) != 0) {
				listaAux.clear();
			} else {
				raiz = true;
				listaAux1.clear();
				listaAux1.add(polinomio.get(0));
				// Coge el nuevo polinomio
				listaAux1.addAll(listaAux.subList(0, listaAux.size() - 1));
				// añade de ultimo a la lista el divisor
				listaAux1.add(divisores.get(x));
				// ruffini1(listaAux1,
				// divisores(listaAux1.get(listaAux1.size() - 1)));
			}
			x++;
		}
		return listaAux1;
	}

	public static List<List<Integer>> ruffiniTotal(List<Integer> polinomio,
			List<Integer> divisores) {
		// suponemos que al menos entra con grado 3, eso se hace desde la gui de
		// android.
		List<List<Integer>> listaFinal = new ArrayList<List<Integer>>();
		List<Integer> aux1 = ruffini1(polinomio,
				divisores(polinomio.get(polinomio.size() - 1)));
		List<Integer> aux2 = new ArrayList<Integer>();
		System.out.println(aux1);
		boolean noRaiz = false;

		List<Integer> raices = new ArrayList<Integer>();
		if (!polinomio.equals(aux1)) {
			while (aux1.size() > 3 && !noRaiz) {
				if (aux1.size() == 4) {
					// si devuelve una raiz y queda un polinomio cuadratico,
					// entonces entra en cuadrado, TODO
					raices.add(aux1.get(3));
					aux1.remove(aux1.size() - 1);
				} else {
					raices.add(aux1.get(aux1.size() - 1));
					aux1.remove(aux1.size() - 1);
					aux2 = aux1;
					aux1 = ruffini1(aux1, divisores(aux1.get(aux1.size() - 1)));
					if (aux2.equals(aux1)) {
						noRaiz = true;
					}
				}
			}
		}
		listaFinal.add(aux1);
		listaFinal.add(raices);

		return listaFinal;
	}
}
