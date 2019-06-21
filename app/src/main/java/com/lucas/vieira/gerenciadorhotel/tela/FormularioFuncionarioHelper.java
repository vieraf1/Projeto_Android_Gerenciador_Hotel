package com.lucas.vieira.gerenciadorhotel.tela;

import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.lucas.vieira.gerenciadorhotel.modelo.Funcionario;
import com.lucas.vieira.gerenciadorhotel.modelo.Hotel;

public class FormularioFuncionarioHelper extends AppCompatActivity {
    private Spinner spnHotel;
    private EditText nome;
    private EditText idade;
    private EditText rg;
    private Funcionario func;
    private ArrayAdapter<Hotel> aaHotel;

    private Hotel hotel;
    private Hotel hotelSelecionado;

    public FormularioFuncionarioHelper(FormularioFuncionarioActivity activity, ArrayAdapter<Hotel> hotelArrayAdpter)
    {
        this.aaHotel = hotelArrayAdpter;
        this.spnHotel = (Spinner) activity.findViewById(R.id.listarHotel);
        this.nome = (EditText) activity.findViewById(R.id.nomeFuncionario);
        this.idade = (EditText) activity.findViewById(R.id.idadeFuncionario);
        this.rg = (EditText) activity.findViewById(R.id.rgFuncionario);
        this.func = new Funcionario();
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

    public Funcionario pegaFuncionarioDoFormulario()
    {
        func.setNome(nome.getText().toString());
        func.setIdade(Integer.parseInt(idade.getText().toString()));
        func.setRg(rg.getText().toString());
        this.hotel = (Hotel) spnHotel.getSelectedItem();
        func.setHotel(this.hotel);
        func.setIdHotel(this.hotel.getId());
        return func;
    }

    public void colocaNoFormulario (Funcionario func)
    {
        this.spnHotel.setAdapter(this.aaHotel);
        setSpnHotel(func.getIdHotel());
        nome.setText(func.getNome());
        idade.setText(Integer.toString(func.getIdade()));
        rg.setText(func.getRg());
        this.func = func;
    }

    public void NovoSpinner() {
        this.spnHotel.setAdapter(this.aaHotel);
        this.spnHotel.setSelection(0);
    }
}
