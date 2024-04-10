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
        display.setText(String.valueOf(resultado));

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
        operacionActual.setText("Operación");
        primerNumero=0;
        segundoNumero=0;
        resultado=0;
    }
    private void anyadirListenerAlCheckbox() {
        checkBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    checkBox.setText("Ocultando deshabiltar opciones");
                    radioGroup.setVisibility(View.GONE);
                } else {
                    checkBox.setText("Mostrando deshabilitar opciones");
                    radioGroup.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}