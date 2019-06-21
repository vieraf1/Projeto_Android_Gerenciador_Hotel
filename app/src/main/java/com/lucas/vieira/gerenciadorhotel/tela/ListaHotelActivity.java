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
import com.lucas.vieira.gerenciadorhotel.modelo.Hotel;

import java.io.Serializable;
import java.util.List;

public class ListaHotelActivity extends AppCompatActivity {
    private ListView listaHotels;
    private List<Hotel> hotels;
    private Hotel hotelSelecionado;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_hotel);
        listaHotels = (ListView) findViewById(R.id.lista_hotel);

        Button botaoAdiciona = (Button) findViewById(R.id.lista_hotel_floating_button);

        botaoAdiciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //toast
                Toast.makeText(ListaHotelActivity.this, "Floating button clicado", Toast.LENGTH_LONG).show();

                //chamar entao o formularioHotelActivity
                Intent intent = new Intent(ListaHotelActivity.this, FormularioHotelActivity.class);
                startActivity(intent);

            }
        });

        //Editando o hotel selecionado
        //Novo setOnItemClickListener
        listaHotels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent edicao = new Intent(ListaHotelActivity.this, FormularioHotelActivity.class);
                edicao.putExtra("hotel", (Serializable) listaHotels.getItemAtPosition(position));
                startActivity(edicao);
            }
        });

        registerForContextMenu(listaHotels);

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.carregaLista();
    }

    private void carregaLista() {
        HotelDao dao = new HotelDao(this);
        List<Hotel> hotels = dao.getLista();
        dao.close();
        ArrayAdapter<Hotel> adapter = new ArrayAdapter<Hotel>(this, android.R.layout.simple_list_item_1, hotels);
        this.listaHotels.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, view, menuInfo);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        hotelSelecionado = (Hotel) listaHotels.getAdapter().getItem(info.position);

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            //Metodo para deletar
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                HotelDao dao = new HotelDao(ListaHotelActivity.this);
                dao.deletar(hotelSelecionado);
                dao.close();
                carregaLista();
                return false;
            }
        });

    }
}
