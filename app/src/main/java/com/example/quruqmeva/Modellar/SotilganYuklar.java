package com.example.quruqmeva.Modellar;

import java.io.Serializable;

public class SotilganYuklar implements Serializable {
    int id;

    int klient_id;
    String klient_nomi;
    String klient_tel;
    String klient_turi;

    String mahsulot;
    String mahsulot_turi;

    String sana;
    String miqdor;
    String narx;
    String summa;

    public SotilganYuklar(int id,  String klient_nomi, String klient_tel, String mahsulot, String mahsulot_turi, String sana, String miqdor, String narx, String summa) {
        this.id = id;
        this.klient_nomi = klient_nomi;
        this.klient_tel = klient_tel;
        this.mahsulot = mahsulot;
        this.mahsulot_turi = mahsulot_turi;
        this.sana = sana;
        this.miqdor = miqdor;
        this.narx = narx;
        this.summa = summa;
    }

    public SotilganYuklar() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKlient_id() {
        return klient_id;
    }

    public void setKlient_id(int klient_id) {
        this.klient_id = klient_id;
    }

    public String getKlient_nomi() {
        return klient_nomi;
    }

    public void setKlient_nomi(String klient_nomi) {
        this.klient_nomi = klient_nomi;
    }

    public String getKlient_tel() {
        return klient_tel;
    }

    public void setKlient_tel(String klient_tel) {
        this.klient_tel = klient_tel;
    }

    public String getKlient_turi() {
        return klient_turi;
    }

    public void setKlient_turi(String klient_turi) {
        this.klient_turi = klient_turi;
    }

    public String getMahsulot() {
        return mahsulot;
    }

    public void setMahsulot(String mahsulot) {
        this.mahsulot = mahsulot;
    }

    public String getMahsulot_turi() {
        return mahsulot_turi;
    }

    public void setMahsulot_turi(String mahsulot_turi) {
        this.mahsulot_turi = mahsulot_turi;
    }

    public String getSana() {
        return sana;
    }

    public void setSana(String sana) {
        this.sana = sana;
    }

    public String getMiqdor() {
        return miqdor;
    }

    public void setMiqdor(String miqdor) {
        this.miqdor = miqdor;
    }

    public String getNarx() {
        return narx;
    }

    public void setNarx(String narx) {
        this.narx = narx;
    }

    public String getSumma() {
        return summa;
    }

    public void setSumma(String summa) {
        this.summa = summa;
    }
}
