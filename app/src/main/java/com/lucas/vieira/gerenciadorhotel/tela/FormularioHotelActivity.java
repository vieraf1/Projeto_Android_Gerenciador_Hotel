package com.lucas.vieira.gerenciadorhotel.tela;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.lucas.vieira.gerenciadorhotel.dao.HotelDao;
import com.lucas.vieira.gerenciadorhotel.modelo.Hotel;

public class FormularioHotelActivity extends AppCompatActivity {

    private FormularioHotelHelper helper;
    //Editando o aluno selecionado
    public  static  final String Hotel_SELECIONADO = "hotelselecionado";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_formulario);

        this.helper = new FormularioHotelHelper(this);

        Intent intent = this.getIntent();
        Hotel hotel = (Hotel) intent.getSerializableExtra("hotel");
        if (hotel != null)
        {
            helper.colocaNoFormulario(hotel);
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
                Hotel hotel = helper.pegaHotelDoFormulario();
                HotelDao dao = new HotelDao(FormularioHotelActivity.this);
                if(hotel.getId() != null)
                {
                    dao.alterar(hotel);
                }
                else
                {
                    dao.insere(hotel);
                }
                dao.close();

                finish();

                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
