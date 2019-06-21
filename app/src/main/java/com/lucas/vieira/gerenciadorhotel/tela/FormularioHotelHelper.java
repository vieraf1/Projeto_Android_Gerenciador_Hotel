package com.lucas.vieira.gerenciadorhotel.tela;

import android.widget.EditText;

import com.lucas.vieira.gerenciadorhotel.modelo.Hotel;

public class FormularioHotelHelper {
    private EditText nome;
    private EditText cnpj;
    private EditText telefone;
    private EditText endereco;
    private Hotel hotel;

    public FormularioHotelHelper(FormularioHotelActivity activity)
    {
        this.nome = (EditText) activity.findViewById(R.id.nomeHotel);
        this.cnpj = (EditText) activity.findViewById(R.id.cnpjHotel);
        this.telefone = (EditText) activity.findViewById(R.id.telefoneHotel);
        this.endereco = (EditText) activity.findViewById(R.id.enderecoHotel);
        this.hotel = new Hotel();
    }

    public Hotel pegaHotelDoFormulario()
    {
        hotel.setNome(nome.getText().toString());
        hotel.setCnpj(cnpj.getText().toString());
        hotel.setEndereco(endereco.getText().toString());
        hotel.setTelefone(telefone.getText().toString());
        return hotel;
    }

    public void colocaNoFormulario (Hotel hotel)
    {
        nome.setText(hotel.getNome());
        cnpj.setText(hotel.getCnpj());
        telefone.setText(hotel.getTelefone());
        endereco.setText(hotel.getEndereco());
        this.hotel = hotel;
    }
}
