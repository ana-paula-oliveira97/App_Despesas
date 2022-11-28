package com.example.appdespesas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void inserir( View v) {
        EditText desc, data, valor;
        desc = findViewById(R.id.idDescricao);
        data = findViewById(R.id.idData);
        valor= findViewById(R.id.idvalor);
        String descricaoS = desc.getText().toString();
        String dataS      = data.getText().toString();
        String valorS     = valor.getText().toString();
        double val = Double.parseDouble(valorS);
        BancoDeDados bd = new BancoDeDados(this);
        if (bd.insereDespesa(descricaoS, dataS, val)>0) {
            Toast.makeText(this, "Dados Inseridos", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Erro na Inserção", Toast.LENGTH_LONG).show();
        }
    }

    public void consutar (View V){
    BancoDeDados bd = new BancoDeDados(this);
        ArrayList<String> resultado = bd.consulta();
        String mensagem ="";
        for (int i=0; i< resultado.size(); i++){
            mensagem += resultado.get(i);
        }
        EditText relatorio = findViewById(R.id.idRelatorio);
        relatorio.setText(mensagem);
    }
}