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

    private void avisoMaximoAlcanzado() {
        Toast.makeText(this,"Límite de caracteres alcanzado",Toast.LENGTH_SHORT).show();
    }

    public void clear(View v){
        display.setText("0");
        operacionActual.setText("Operación");
    }
    private void anyadirListenerAlCheckbox() {
        checkBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    checkBox.setText("Ocultando opciones");
                    radioGroup.setVisibility(View.GONE);
                } else {
                    checkBox.setText("Mostrando opciones");
                    radioGroup.setVisibility(View.VISIBLE);
                }
            }

        });
    }
}