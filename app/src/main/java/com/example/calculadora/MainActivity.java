package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import java.util.EventListener;

public class MainActivity extends AppCompatActivity {
    private CheckBox checkBox;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox=findViewById(R.id.checkBox);
        radioGroup=findViewById(R.id.radioGroup);
    }

    protected void mostrarOpciones(){
        radioGroup.setVisibility(View.GONE);
    }
}