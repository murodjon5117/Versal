package com.example.quruqmeva.Modellar;

import java.io.Serializable;

public class Qarizdorlik implements Serializable {
    String nomi;
    String sana;
    int summa;
    String nomer;
    String izoh;

    public Qarizdorlik(String nomi, String sana, int summa, String nomer, String izoh) {
        this.nomi = nomi;
        this.sana = sana;
        this.summa = summa;
        this.nomer = nomer;
        this.izoh = izoh;
    }

    public String getNomi() {
        return nomi;
    }

    public void setNomi(String nomi) {
        this.nomi = nomi;
    }

    public String getSana() {
        return sana;
    }

    public void setSana(String sana) {
        this.sana = sana;
    }

    public int getSumma() {
        return summa;
    }

    public void setSumma(int summa) {
        this.summa = summa;
    }

    public String getNomer() {
        return nomer;
    }

    public void setNomer(String nomer) {
        this.nomer = nomer;
    }

    public String getIzoh() {
        return izoh;
    }

    public void setIzoh(String izoh) {
        this.izoh = izoh;
    }
}
