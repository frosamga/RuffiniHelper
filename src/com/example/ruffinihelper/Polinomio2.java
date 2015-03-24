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
import android.widget.TextView;
import android.widget.Toast;

public class Polinomio2 extends Activity {

	private EditText g2, g1, ti;
	private TextView px2, px;

	private String[] info;

	private Object[] cadena;
	private ProgressDialog pd;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_polinomio);

		g2 = (EditText) findViewById(R.id.P2_A);
		g1 = (EditText) findViewById(R.id.P2_B);
		ti = (EditText) findViewById(R.id.P2_C);
		
		px2 = (TextView) findViewById(R.id.P2_X2);
		px = (TextView) findViewById(R.id.P2_X);
		
		px2.setText(Html.fromHtml("x<small><sup>2</sup></small>"));
		px.setText(Html.fromHtml("x"));
		
		
		cadena = new String[3];
		context = this;
	}

	protected void onDestroy() {
		if (pd != null) {
			pd.dismiss();
		}
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.polinomio, menu);
		return true;
	}

	@SuppressLint("NewApi")
	public void buttonp2(View v) {

		if (g2.getText().toString().matches("")) {
			cadena[0] = String.valueOf(0);
		} else {
			cadena[0] = g2.getText().toString();
		}
		if (g1.getText().toString().matches("")) {
			cadena[1] = String.valueOf(0);
		} else {
			cadena[1] = g1.getText().toString();
		}
		if (ti.getText().toString().matches("")) {
			Toast toast = Toast.makeText(this,
					"El termino independiente debe ser diferente de 0",
					Toast.LENGTH_SHORT);
			toast.show();
		} else {
			if (Integer.parseInt(ti.getText().toString()) == 0) {
				Toast toast = Toast
						.makeText(
								this,
								"El termino independiente debe ser diferente de 0(Saca factor común)",
								Toast.LENGTH_SHORT);
				toast.show();
			} else {

				cadena[2] = ti.getText().toString();
				Intent act = new Intent(this, Raices.class);
				act.putExtra("Poli", cadena);
				startActivity(act);
			}
		}
	}
}
