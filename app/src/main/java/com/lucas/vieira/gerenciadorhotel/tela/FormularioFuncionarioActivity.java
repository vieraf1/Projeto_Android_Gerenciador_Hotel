package com.lucas.vieira.gerenciadorhotel.tela;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.lucas.vieira.gerenciadorhotel.dao.FuncionarioDao;
import com.lucas.vieira.gerenciadorhotel.modelo.Funcionario;
import com.lucas.vieira.gerenciadorhotel.modelo.Hotel;

import java.util.List;

public class FormularioFuncionarioActivity extends AppCompatActivity {
    private FormularioFuncionarioHelper helper;
    //Editando o quarto selecionado
    public  static  final String FUNCIONARIO_SELECIONADO = "funcionarioselecionado";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funcionario_formulario);
        Intent intent = this.getIntent();

        List<Hotel> hotelList = (List<Hotel>) intent.getSerializableExtra("listHotel");
        ArrayAdapter<Hotel> aaHotel = new ArrayAdapter<Hotel>(this, android.R.layout.simple_spinner_item, hotelList);

        this.helper = new FormularioFuncionarioHelper(this, aaHotel);


        Funcionario func = (Funcionario) intent.getSerializableExtra("funcionario");
        if (func != null)
        {
            helper.colocaNoFormulario(func);
        }
        else {
            helper.NovoSpinner();
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_formulario_ok:
                Funcionario func = helper.pegaFuncionarioDoFormulario();
                FuncionarioDao dao = new FuncionarioDao(FormularioFuncionarioActivity.this);
                if(func.getId() != null)
                {
                    dao.alterar(func);
                }
                else
                {
                    dao.insere(func);
                }
                dao.close();

                finish();

                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
