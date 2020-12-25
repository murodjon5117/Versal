package com.example.quruqmeva.Modellar;

public class Yukchi {
    int id;
    String nomi;
    String tel;

    public Yukchi(int id, String nomi, String tel) {
        this.id = id;
        this.nomi = nomi;
        this.tel = tel;
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
}
