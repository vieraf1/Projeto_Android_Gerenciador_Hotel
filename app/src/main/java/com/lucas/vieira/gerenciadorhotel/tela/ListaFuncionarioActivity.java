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

import com.lucas.vieira.gerenciadorhotel.dao.FuncionarioDao;
import com.lucas.vieira.gerenciadorhotel.dao.HotelDao;
import com.lucas.vieira.gerenciadorhotel.modelo.Funcionario;
import com.lucas.vieira.gerenciadorhotel.modelo.Hotel;

import java.io.Serializable;
import java.util.List;

public class ListaFuncionarioActivity extends AppCompatActivity {
    private ListView listaFuncionarios;
    private List<Funcionario> funcs;
    private List<Hotel> hots;
    private Funcionario funcSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_funcionario);
        listaFuncionarios = (ListView) findViewById(R.id.lista_funcionario);

        Button botaoAdiciona = (Button) findViewById(R.id.lista_funcionario_floating_button);

        botaoAdiciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //toast
                Toast.makeText(ListaFuncionarioActivity.this, "Floating button clicado", Toast.LENGTH_LONG).show();

                //chamar entao o formularioQuartoActivity
                Intent intent = new Intent(ListaFuncionarioActivity.this, FormularioFuncionarioActivity.class);
                intent.putExtra("listHotel", (Serializable) hots);
                startActivity(intent);

            }
        });

        //Editando o quarto selecionado
        //Novo setOnItemClickListener
        listaFuncionarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent edicao = new Intent(ListaFuncionarioActivity.this, FormularioFuncionarioActivity.class);
                edicao.putExtra("funcionario", (Serializable) listaFuncionarios.getItemAtPosition(position));
                edicao.putExtra("listHotel", (Serializable) hots);
                startActivity(edicao);
            }
        });

        registerForContextMenu(listaFuncionarios);

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
        FuncionarioDao dao = new FuncionarioDao(this);
        List<Funcionario> funcs = dao.getLista();
        dao.close();
        ArrayAdapter<Funcionario> adapter = new ArrayAdapter<Funcionario>(this, android.R.layout.simple_list_item_1, funcs);
        this.listaFuncionarios.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, view, menuInfo);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        funcSelecionado = (Funcionario) listaFuncionarios.getAdapter().getItem(info.position);

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            //Metodo para deletar
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                FuncionarioDao dao = new FuncionarioDao(ListaFuncionarioActivity.this);
                dao.deletar(funcSelecionado);
                dao.close();
                carregaLista();
                return false;
            }
        });

    }
}
