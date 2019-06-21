package com.lucas.vieira.gerenciadorhotel.tela;

import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import com.lucas.vieira.gerenciadorhotel.modelo.Hotel;
import com.lucas.vieira.gerenciadorhotel.modelo.Quarto;

class FormularioQuartoHelper extends AppCompatActivity  {
    private Spinner spnHotel;
    private EditText numero;
    private EditText valor;
    private EditText telefone;
    private Quarto quarto;
    private ArrayAdapter<Hotel> aaHotel;

    private Hotel hotel;
    private Hotel hotelSelecionado;

    public FormularioQuartoHelper(FormularioQuartoActivity activity, ArrayAdapter<Hotel> hotelArrayAdpter)
    {
        this.aaHotel = hotelArrayAdpter;
        this.spnHotel = (Spinner) activity.findViewById(R.id.listarHotel);
        this.numero = (EditText) activity.findViewById(R.id.numeroQuarto);
        this.valor = (EditText) activity.findViewById(R.id.valorDiaria);
        this.telefone = (EditText) activity.findViewById(R.id.telefoneQuarto);
        this.quarto = new Quarto();
    }

    private void setSpnHotel(Long h) {
        for (int i = 0; i < spnHotel.getCount(); i++) {

              Hotel vHotel = (Hotel) spnHotel.getItemAtPosition(i);

            if (h.equals(vHotel.getId())) {
                spnHotel.setSelection(i);
                break;
            }
            else {
                spnHotel.setSelection(0);
            }
        }
    }

    public Quarto pegaQuartoDoFormulario()
    {
        quarto.setNumeroQuarto(Integer.parseInt(numero.getText().toString()));
        quarto.setValorDiaria(Float.parseFloat(valor.getText().toString()));
        quarto.setTelefoneQuarto(telefone.getText().toString());
        this.hotel = (Hotel) spnHotel.getSelectedItem();
        quarto.setHotel(this.hotel);
        quarto.setIdHotel(this.hotel.getId());
        return quarto;
    }

    public void colocaNoFormulario (Quarto quarto)
    {
        this.spnHotel.setAdapter(this.aaHotel);
        setSpnHotel(quarto.getIdHotel());
        numero.setText(Integer.toString(quarto.getNumeroQuarto()));
        valor.setText(Float.toString(quarto.getValorDiaria()));
        telefone.setText(quarto.getTelefoneQuarto());
        this.quarto = quarto;
    }

    public void NovoSpinner() {
        this.spnHotel.setAdapter(this.aaHotel);
        this.spnHotel.setSelection(0);
    }

}
