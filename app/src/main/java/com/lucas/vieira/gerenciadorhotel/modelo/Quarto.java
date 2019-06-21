package com.lucas.vieira.gerenciadorhotel.modelo;

import java.io.Serializable;

public class Quarto implements Serializable {

    private Long idHotel;
    private int numeroQuarto;
    private float valorDiaria;
    private String telefoneQuarto;
    private Hotel hotel;
    private Long id;

    public Long getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Long idHotel) {
        this.idHotel = idHotel;
    }

    public int getNumeroQuarto() {
        return numeroQuarto;
    }

    public void setNumeroQuarto(int numeroQuarto) {
        this.numeroQuarto = numeroQuarto;
    }

    public float getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(float valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public String getTelefoneQuarto() {
        return telefoneQuarto;
    }

    public void setTelefoneQuarto(String telefoneQuarto) {
        this.telefoneQuarto = telefoneQuarto;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return id + " - " + " Quarto nº " + numeroQuarto;
    }

}
