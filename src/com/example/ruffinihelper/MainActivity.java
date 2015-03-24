package com.example.ruffinihelper;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText grado;
	final static String ACT_INFO2 = "com.example.ruffinihelper.Polinimio2";
	final static String ACT_INFO3 = "com.example.ruffinihelper.Polinimio3";
	final static String ACT_INFO4 = "com.example.ruffinihelper.Polinimio4";
	final static String ACT_INFO5 = "com.example.ruffinihelper.Polinimio5";
	final static String ACT_INFO6 = "com.example.ruffinihelper.Polinimio6";
	//final static String ACT_INFO7 = "com.example.ruffinihelper.Polinimio6";
	//final static String ACT_INFO8 = "com.example.ruffinihelper.Polinimio8";
	//final static String ACT_INFO9 = "com.example.ruffinihelper.Polinimio9";
	
	private static TextView ejemplo;
	
	private Object[] cadena;
	private ProgressDialog pd;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		grado = (EditText) findViewById(R.id.editText1);
		cadena = new String[1];
		context = this;
		ejemplo = (TextView) findViewById(R.id.ejemplo);
		ejemplo.setText(Html.fromHtml("Por ejemplo "+"4x<small><sup>6</sup></small>"+"-2x<small><sup>2</sup></small>"+"+1 es de grado 6."));
	}

	protected void onDestroy() {
		if (pd != null) {
			pd.dismiss();
		}
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressLint("NewApi")
	public void polinomio(View v) {
		if (Integer.parseInt(grado.getText().toString()) >= 10) {
			Toast toast = Toast.makeText(this,
					"Introduce un grado menor o igual que 6,(+6 Premium)", Toast.LENGTH_SHORT);
			toast.show();
		} else if (Integer.parseInt(grado.getText().toString()) <= 1) {
			Toast toast = Toast.makeText(this,
					"Introduce un grado mayor que 1.", Toast.LENGTH_SHORT);
			toast.show();
		} else {
			cadena[0] = grado.getText().toString();
			
			int aux = Integer.parseInt(grado.getText().toString());
			if (aux == 2) {
				Intent act = new Intent(this, Polinomio2.class);
				act.putExtra(ACT_INFO2, cadena);
				startActivity(act);

			} else if (aux == 3) {
				Intent act = new Intent(this, Polinomio3.class);
				act.putExtra(ACT_INFO3, cadena);
				startActivity(act);

			} else if (aux == 4) {
				Intent act = new Intent(this, Polinomio4.class);
				act.putExtra(ACT_INFO4, cadena);
				startActivity(act);

			} else if (aux == 5) {
				Intent act = new Intent(this, Polinomio5.class);
				act.putExtra(ACT_INFO5, cadena);
				startActivity(act);

			} else if (aux == 6) {
				Intent act = new Intent(this, Polinomio6.class);
				act.putExtra(ACT_INFO6, cadena);
				startActivity(act);

			}
			//Posible version de pago si va bien.
			/*
			else if (aux == 7) {
				Intent act = new Intent(this, Polinomio7.class);
				act.putExtra(ACT_INFO7, cadena);
				startActivity(act);

			} else if (aux == 8) {
				Intent act = new Intent(this, Polinomio8.class);
				act.putExtra(ACT_INFO8, cadena);
				startActivity(act);

			} else if (aux == 9) {
				Intent act = new Intent(this, Polinomio9.class);
				act.putExtra(ACT_INFO9, cadena);
				startActivity(act);
			}
			*/
		}
	}
}
