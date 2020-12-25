package com.example.quruqmeva.Modellar;

public class Savdo {
    int yukchi_id;
    int mahsulot_id;
    int narx;
    double miqdor;
    int tolov;
    int check;
    String yukchi_nomi;
    String mahsulot_nomi;

    public Savdo(int yukchi_id, int mahsulot_id, int narx, double miqdor, int tolov,int check, String yukchi_nomi, String mahsulot_nomi) {
        this.yukchi_id = yukchi_id;
        this.mahsulot_id = mahsulot_id;
        this.narx = narx;
        this.miqdor = miqdor;
        this.tolov = tolov;
        this.check=check;
        this.yukchi_nomi = yukchi_nomi;
        this.mahsulot_nomi = mahsulot_nomi;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public String getYukchi_nomi() {
        return yukchi_nomi;
    }

    public void setYukchi_nomi(String yukchi_nomi) {
        this.yukchi_nomi = yukchi_nomi;
    }

    public String getMahsulot_nomi() {
        return mahsulot_nomi;
    }

    public void setMahsulot_nomi(String mahsulot_nomi) {
        this.mahsulot_nomi = mahsulot_nomi;
    }

    public int getYukchi_id() {
        return yukchi_id;
    }

    public void setYukchi_id(int yukchi_id) {
        this.yukchi_id = yukchi_id;
    }

    public int getMahsulot_id() {
        return mahsulot_id;
    }

    public void setMahsulot_id(int mahsulot_id) {
        this.mahsulot_id = mahsulot_id;
    }

    public int getNarx() {
        return narx;
    }

    public void setNarx(int narx) {
        this.narx = narx;
    }

    public double getMiqdor() {
        return miqdor;
    }

    public void setMiqdor(double miqdor) {
        this.miqdor = miqdor;
    }

    public int getTolov() {
        return tolov;
    }

    public void setTolov(int tolov) {
        this.tolov = tolov;
    }
}
