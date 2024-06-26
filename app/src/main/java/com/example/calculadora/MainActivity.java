package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.EventListener;

public class MainActivity extends AppCompatActivity {
    private CheckBox checkBox;
    private RadioGroup radioGroup;
    private Button suma;
    private Button resta;
    private Button mult;
    private Button divi;
    private double primerNumero=0;
    private double segundoNumero=0;
    private double resultado=0;
    private TextView display;
    private TextView operacionActual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display=findViewById(R.id.display);
        operacionActual=findViewById(R.id.operacion);

        suma=findViewById(R.id.boton_suma);
        resta=findViewById(R.id.boton_restar);
        mult=findViewById(R.id.boton_multiplicar);
        divi=findViewById(R.id.boton_dividir);

        checkBox=findViewById(R.id.checkBox);
        radioGroup=findViewById(R.id.radioGroup);
        anyadirListenerAlCheckbox();
    }

    public void anyadirComa(View v){
        String texto = (String) display.getText();
        //Nos aseguramos de que no hayan 2 comas
        if(texto.contains("."))
            display.setText(texto);
        else
            display.setText(texto+".");

        if (texto.length()==16) avisoMaximoAlcanzado();

    }
    public void botonPulsado(View v){
        String texto = (String) display.getText();
        Button b = (Button) v;

        if (texto.equals("0"))
            display.setText(b.getText());
        else
            display.setText(texto+b.getText());

        if (texto.length()==16) avisoMaximoAlcanzado();
    }

    public void botonOperacionPulsado(View view){
        Button b = (Button) view;
        String operacion = (String) b.getText();

        operacionActual.setText(operacion);
        primerNumero=Double.parseDouble((String) display.getText());

        display.setText("0");
    }

    private void avisoMaximoAlcanzado() {
        Toast.makeText(this,"Límite de caracteres alcanzado",Toast.LENGTH_SHORT).show();
    }

    public void mostrarResultado(View v){
        segundoNumero=Double.parseDouble((String) display.getText());
        if (segundoNumero!=0)
            realizarOperacion();
        if (resultado==0) display.setText("0");
        else display.setText(String.valueOf(resultado));
        operacionActual.setText("");
    }
    private void realizarOperacion(){
        switch (operacionActual.getText().toString()) {
            case "+":
                resultado = primerNumero + segundoNumero;
                break;
            case "-":
                resultado = primerNumero - segundoNumero;
                break;
            case "÷":
                resultado = primerNumero / segundoNumero;
                break;
            case "x":
                resultado = primerNumero * segundoNumero;
                break;
        }
    }
    public void clear(View v){
        display.setText("0");
        operacionActual.setText("");
        primerNumero=0;
        segundoNumero=0;
        resultado=0;
    }

    public void eliminarOpciones(View v){
        reestablecerBotones();
        Button b = (Button) v;
        String operacion = b.getText().toString();
        switch (operacion){
            case "SUMA":
                suma.setVisibility(View.INVISIBLE);
                break;
            case "RESTA":
                resta.setVisibility(View.INVISIBLE);
                break;
            case "MULTIPLICAR":
                mult.setVisibility(View.INVISIBLE);
                break;
            case "DIVIDIR":
                divi.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private void reestablecerBotones() {
        suma.setVisibility(View.VISIBLE);
        resta.setVisibility(View.VISIBLE);
        divi.setVisibility(View.VISIBLE);
        mult.setVisibility(View.VISIBLE);
    }

    private void anyadirListenerAlCheckbox() {
        checkBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    checkBox.setText("Ocultando deshabiltar opciones");
                    reestablecerBotones();
                    radioGroup.clearCheck();
                    radioGroup.setVisibility(View.GONE);
                } else {
                    checkBox.setText("Mostrando deshabilitar opciones");
                    radioGroup.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}