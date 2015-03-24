package com.example.ruffinihelper;

import android.os.Bundle;
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

public class Polinomio5 extends Activity {

	private EditText g5, g4, g3, g2, g1, ti;
	private String[] info;

	private Object[] cadena;
	private ProgressDialog pd;
	private Context context;
	private TextView px5,px4,px3,px2, px;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_polinomio5);

		g5 = (EditText) findViewById(R.id.P5_A);
		g4 = (EditText) findViewById(R.id.P5_B);
		g3 = (EditText) findViewById(R.id.P5_C);
		g2 = (EditText) findViewById(R.id.P5_D);
		g1 = (EditText) findViewById(R.id.P5_E);
		ti = (EditText) findViewById(R.id.P5_F);

		px5 = (TextView) findViewById(R.id.P5_X5);
		px4 = (TextView) findViewById(R.id.P5_X4);
		px3 = (TextView) findViewById(R.id.P5_X3);
		px2 = (TextView) findViewById(R.id.P5_X2);
		px = (TextView) findViewById(R.id.P5_X);
		
		px5.setText(Html.fromHtml(" x<small><sup>5</sup></small>"+"   "));
		px4.setText(Html.fromHtml(" x<small><sup>4</sup></small>"+"   "));
		px3.setText(Html.fromHtml(" x<small><sup>3</sup></small>"+"   "));
		px2.setText(Html.fromHtml(" x<small><sup>2</sup></small>"+"   "));
		px.setText(Html.fromHtml(" x "));

		
		cadena = new String[6];
		context = this;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.polinomio5, menu);
		return true;
	}

	public void buttonp5(View v) {
		if (g5.getText().toString().matches("")) {
			cadena[0] = String.valueOf(0);
		} else {
			cadena[0] = g5.getText().toString();
		}
		if (g4.getText().toString().matches("")) {
			cadena[1] = String.valueOf(0);
		} else {
			cadena[1] = g4.getText().toString();
		}
		if (g3.getText().toString().matches("")) {
			cadena[2] = String.valueOf(0);
		} else {
			cadena[2] = g3.getText().toString();
		}
		if (g2.getText().toString().matches("")) {
			cadena[3] = String.valueOf(0);
		} else {
			cadena[3] = g2.getText().toString();
		}
		if (g1.getText().toString().matches("")) {
			cadena[4] = String.valueOf(0);
		} else {
			cadena[4] = g1.getText().toString();
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

				cadena[5] = ti.getText().toString();
				Intent act = new Intent(this, Raices.class);
				act.putExtra("Poli", cadena);
				startActivity(act);
			}
		}
	}

}
