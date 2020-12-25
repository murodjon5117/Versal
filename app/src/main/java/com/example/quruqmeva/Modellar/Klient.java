package com.example.quruqmeva.Modellar;

import java.io.Serializable;

public class Klient implements Serializable {
    int id;
    String nomi;
    String tel;
    int tur_id;
    String tur_nomi;

    public Klient(int id, String nomi, String tel, int tur_id, String tur_nomi) {
        this.id = id;
        this.nomi = nomi;
        this.tel = tel;
        this.tur_id = tur_id;
        this.tur_nomi = tur_nomi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomi() {
        return nomi;
    }

    public void setNomi(String nomi) {
        this.nomi = nomi;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getTur_id() {
        return tur_id;
    }

    public void setTur_id(int tur_id) {
        this.tur_id = tur_id;
    }

    public String getTur_nomi() {
        return tur_nomi;
    }

    public void setTur_nomi(String tur_nomi) {
        this.tur_nomi = tur_nomi;
    }
}
