package com.example.ruffinihelper;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.widget.TextView;

public class Raices extends Activity {

	private String[] info;
	private static TextView polinomioVista;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_raices);

		polinomioVista = (TextView) findViewById(R.id.textView1);
		// Intent men = getIntent();
		// info = men.getStringArrayExtra("ACT_INFO");
		Bundle b = this.getIntent().getExtras();
		info = b.getStringArray("Poli");
		List<Integer> lista = new ArrayList<Integer>();
		for (int i = 0; i < info.length; i++) {
			lista.add(Integer.parseInt(info[i]));
		}

		factorizar(lista);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.raices, menu);
		return true;
	}

	public static void factorizar(List<Integer> polinomio) {

		System.out.println("Polinomio" + polinomio);
		System.out.println("Divisores: "
				+ divisores(polinomio.get(polinomio.size() - 1)));
		System.out.println("polinomio +  raices debajo");
		if (polinomio.size() == 3) {
			if (cuadrado(polinomio).isEmpty()) {
				polinomioVista
						.setText("No hay raices reales para este polinomio");
			} else {
				StringBuilder str = new StringBuilder();
				List<Float> l = cuadrado(polinomio);
				for (int i = 0; i < l.size(); i++) {
					if (l.get(i) > 0) {
						str.append("(x - " + l.get(i) + ")");
					} else {
						str.append("(x + " + Math.abs(l.get(i)) + ")");
					}
				}
				polinomioVista.setText("Las raices son:" + str.toString());

			}
		} else {
			List<List<Integer>> factorizado = ruffiniTotal(polinomio,
					divisores(polinomio.get(polinomio.size() - 1)));
			
			// raices
			if (factorizado.get(1).isEmpty()) {
				polinomioVista
						.setText("No hay raices reales para este polinomio");
			} else {
				StringBuilder str = new StringBuilder();
				String stPol = ("(");
				for (int i = 0; i < factorizado.get(1).size(); i++) {
					if (factorizado.get(1).get(i) > 0) {
						str.append("(x - " + factorizado.get(1).get(i) + ")");
					} else {
						str.append("(x + "
								+ Math.abs(factorizado.get(1).get(i)) + ")");
					}

				}
				for (int x = 0; x < factorizado.get(0).size(); x++) {
					if (factorizado.get(0).size() - x == 7) {
						if (factorizado.get(0).get(x) > 0) {
							stPol += ("+" + factorizado.get(0).get(x) + "x<small><sup>6</sup></small>");
						} else {
							stPol += ("-" + factorizado.get(0).get(x) + "x<small><sup>6</sup></small>");
						}
					}
					if (factorizado.get(0).size() - x == 6) {
						if (factorizado.get(0).get(x) > 0) {
							stPol += ("+" + factorizado.get(0).get(x) + "x<small><sup>5</sup></small>");
						} else {
							stPol += ("-" + factorizado.get(0).get(x) + "x<small><sup>5</sup></small>");
						}

					}
					if (factorizado.get(0).size() - x == 5) {
						if (factorizado.get(0).get(x) > 0) {
							stPol += ("+" + factorizado.get(0).get(x) + "x<small><sup>4</sup></small>");
						} else {
							stPol += ("-" + factorizado.get(0).get(x) + "x<small><sup>4</sup></small>");
						}

					}
					if (factorizado.get(0).size() - x == 4) {
						if (factorizado.get(0).get(x) > 0) {
							stPol += ("+" + factorizado.get(0).get(x) + "x<small><sup>3</sup></small>");
						} else {
							stPol += ("-" + factorizado.get(0).get(x) + "x<small><sup>3</sup></small>");
						}
					}
					if (factorizado.get(0).size() - x == 3) {
						if (factorizado.get(0).get(x) > 0) {
							stPol += ("+" + factorizado.get(0).get(x) + "x<small><sup>2</sup></small>");
						} else {
							stPol += ("-" + factorizado.get(0).get(x) + "x<small><sup>2</sup></small>");
						}

					}
					if (factorizado.get(0).size() - x == 2) {
						if (factorizado.get(0).get(x) > 0) {
							stPol += ("+" + factorizado.get(0).get(x) + "x");
						} else {
							stPol += ("-" + factorizado.get(0).get(x) + "x");
						}

					}
					if (factorizado.get(0).size() - x == 1) {
						if (factorizado.get(0).get(x) > 0) {
							stPol += ("+" + factorizado.get(0).get(x) + ")");
						} else {
							stPol += ("-" + factorizado.get(0).get(x) + ")");
						}

					}
				}

				polinomioVista.setText(Html.fromHtml("El resultado es: "
						+ stPol + str.toString()));

			}

			// polinomioVista.setText(factorizado.toString());

			List<Integer> raicesTotales = factorizado.get(1);
			List<Integer> faltaCuadrado = factorizado.get(0);
			List<Float> cuadrado1 = new ArrayList<Float>();
			if (factorizado.get(0).size() == 3) {

				cuadrado1 = cuadrado(faltaCuadrado);
				if (!cuadrado1.isEmpty()) {
					StringBuilder str = new StringBuilder();
					for (int i = 0; i < raicesTotales.size(); i++) {
						if (raicesTotales.get(i) > 0) {
							str.append("(x - " + raicesTotales.get(i) + ")");
						} else {
							str.append("(x + " + Math.abs(raicesTotales.get(i))
									+ ")");
						}
					}
					String c1, c2;
					if (cuadrado1.get(0) > 0) {
						c1 = "(x - " + cuadrado1.get(0) + ") ";
					} else {
						c1 = "(x + " + Math.abs(cuadrado1.get(0)) + ") ";
					}
					if (cuadrado1.get(0) > 0) {
						c2 = "(x - " + cuadrado1.get(1) + ") ";
					} else {
						c2 = "(x + " + Math.abs(cuadrado1.get(1)) + ") ";
					}
					polinomioVista.setText("Las raices son:" + c1 + c2
							+ str.toString());
				} else {
					StringBuilder str = new StringBuilder();
					String stPol = ("(");
					for (int i = 0; i < raicesTotales.size(); i++) {
						if (raicesTotales.get(i) > 0) {
							str.append("(x - " + raicesTotales.get(i) + ")");
						} else {
							str.append("(x + " + Math.abs(raicesTotales.get(i))
									+ ")");
						}

					}
					for (int x = 0; x < faltaCuadrado.size(); x++) {
						if (faltaCuadrado.size() - x == 7) {
							if (faltaCuadrado.get(x) > 0) {
								stPol += ("+" + faltaCuadrado.get(x) + "x<small><sup>6</sup></small>");
							} else {
								stPol += ("-" + faltaCuadrado.get(x) + "x<small><sup>6</sup></small>");
							}
						}
						if (faltaCuadrado.size() - x == 6) {
							if (faltaCuadrado.get(x) > 0) {
								stPol += ("+" + faltaCuadrado.get(x) + "x<small><sup>5</sup></small>");
							} else {
								stPol += ("-" + faltaCuadrado.get(x) + "x<small><sup>5</sup></small>");
							}

						}
						if (faltaCuadrado.size() - x == 5) {
							if (faltaCuadrado.get(x) > 0) {
								stPol += ("+" + faltaCuadrado.get(x) + "x<small><sup>4</sup></small>");
							} else {
								stPol += ("-" + faltaCuadrado.get(x) + "x<small><sup>4</sup></small>");
							}

						}
						if (faltaCuadrado.size() - x == 4) {
							if (faltaCuadrado.get(x) > 0) {
								stPol += ("+" + faltaCuadrado.get(x) + "x<small><sup>3</sup></small>");
							} else {
								stPol += ("-" + faltaCuadrado.get(x) + "x<small><sup>3</sup></small>");
							}
						}
						if (faltaCuadrado.size() - x == 3) {
							if (faltaCuadrado.get(x) > 0) {
								stPol += ("+" + faltaCuadrado.get(x) + "x<small><sup>2</sup></small>");
							} else {
								stPol += ("-" + faltaCuadrado.get(x) + "x<small><sup>2</sup></small>");
							}

						}
						if (faltaCuadrado.size() - x == 2) {
							if (faltaCuadrado.get(x) > 0) {
								stPol += ("+" + faltaCuadrado.get(x) + "x");
							} else {
								stPol += ("-" + faltaCuadrado.get(x) + "x");
							}

						}
						if (faltaCuadrado.size() - x == 1) {
							if (faltaCuadrado.get(x) > 0) {
								stPol += ("+" + faltaCuadrado.get(x) + ")");
							} else {
								stPol += ("-" + faltaCuadrado.get(x) + ")");
							}

						}
					}

					polinomioVista.setText(Html.fromHtml("El resultado es: "
							+ stPol + str.toString()));

				}
			}
		}

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
