package com.lucas.vieira.gerenciadorhotel.tela;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.lucas.vieira.gerenciadorhotel.dao.QuartoDao;
import com.lucas.vieira.gerenciadorhotel.modelo.Hotel;
import com.lucas.vieira.gerenciadorhotel.modelo.Quarto;

import java.util.List;

public class FormularioQuartoActivity extends AppCompatActivity {

    private FormularioQuartoHelper helper;
    //Editando o quarto selecionado
    public  static  final String Quarto_SELECIONADO = "quartoselecionado";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quarto_formulario);
        Intent intent = this.getIntent();

        List<Hotel> hotelList = (List<Hotel>) intent.getSerializableExtra("listHotel");
        ArrayAdapter<Hotel> aaHotel = new ArrayAdapter<Hotel>(this, android.R.layout.simple_spinner_item, hotelList);

        this.helper = new FormularioQuartoHelper(this, aaHotel);


        Quarto quarto = (Quarto) intent.getSerializableExtra("quarto");
        if (quarto != null)
        {
            helper.colocaNoFormulario(quarto);
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
                Quarto quarto = helper.pegaQuartoDoFormulario();
                QuartoDao dao = new QuartoDao(FormularioQuartoActivity.this);
                if(quarto.getId() != null)
                {
                    dao.alterar(quarto);
                }
                else
                {
                    dao.insere(quarto);
                }
                dao.close();

                finish();

                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}