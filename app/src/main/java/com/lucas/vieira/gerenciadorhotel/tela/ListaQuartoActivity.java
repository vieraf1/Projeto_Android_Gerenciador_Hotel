package com.lucas.vieira.gerenciadorhotel.tela;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.lucas.vieira.gerenciadorhotel.dao.HotelDao;
import com.lucas.vieira.gerenciadorhotel.dao.QuartoDao;
import com.lucas.vieira.gerenciadorhotel.modelo.Hotel;
import com.lucas.vieira.gerenciadorhotel.modelo.Quarto;

import java.io.Serializable;
import java.util.List;

public class ListaQuartoActivity extends AppCompatActivity {

    private ListView listaQuartos;
    private List<Quarto> quarts;
    private List<Hotel> hots;
    private Quarto quartoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_quarto);
        listaQuartos = (ListView) findViewById(R.id.lista_quarto);

        Button botaoAdiciona = (Button) findViewById(R.id.lista_quarto_floating_button);

        botaoAdiciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //toast
                Toast.makeText(ListaQuartoActivity.this, "Floating button clicado", Toast.LENGTH_LONG).show();

                //chamar entao o formularioQuartoActivity
                Intent intent = new Intent(ListaQuartoActivity.this, FormularioQuartoActivity.class);
                intent.putExtra("listHotel", (Serializable) hots);
                startActivity(intent);

            }
        });

        //Editando o quarto selecionado
        //Novo setOnItemClickListener
        listaQuartos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent edicao = new Intent(ListaQuartoActivity.this, FormularioQuartoActivity.class);
                edicao.putExtra("quarto", (Serializable) listaQuartos.getItemAtPosition(position));
                edicao.putExtra("listHotel", (Serializable) hots);
                startActivity(edicao);
            }
        });

        registerForContextMenu(listaQuartos);

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.carregaLista();
    }

    private void carregaLista() {
        HotelDao daoHot = new HotelDao(this);
        List<Hotel> hotelList = daoHot.getLista();
        daoHot.close();
        hots = hotelList;
        QuartoDao dao = new QuartoDao(this);
        List<Quarto> quarts = dao.getLista();
        dao.close();
        ArrayAdapter<Quarto> adapter = new ArrayAdapter<Quarto>(this, android.R.layout.simple_list_item_1, quarts);
        this.listaQuartos.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, view, menuInfo);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        quartoSelecionado = (Quarto) listaQuartos.getAdapter().getItem(info.position);

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            //Metodo para deletar
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                QuartoDao dao = new QuartoDao(ListaQuartoActivity.this);
                dao.deletar(quartoSelecionado);
                dao.close();
                carregaLista();
                return false;
            }
        });

    }
}
