package com.lucas.vieira.gerenciadorhotel.tela;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    Button crudHotel, crudQuarto, crudFunc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        crudHotel = (Button) findViewById(R.id.bthotel);
        crudHotel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(MenuActivity.this, ListaHotelActivity.class);
                startActivity(it);
            }
        });

        crudQuarto = (Button) findViewById(R.id.btquarto);
        crudQuarto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(MenuActivity.this, ListaQuartoActivity.class);
                startActivity(it);
            }
        });

        crudFunc = (Button) findViewById(R.id.btfunc);
        crudFunc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(MenuActivity.this, ListaFuncionarioActivity.class);
                startActivity(it);
            }
        });

    }
}
