package com.example.quruqmeva.Modellar;

public class mahsulotOmbor {
    String nomi;
    String turi;
    int narxi;
    double miqdor;

    public mahsulotOmbor(String nomi, String turi, int narxi, double miqdor) {
        this.nomi = nomi;
        this.turi = turi;
        this.narxi = narxi;
        this.miqdor = miqdor;
    }

    public String getNomi() {
        return nomi;
    }

    public void setNomi(String nomi) {
        this.nomi = nomi;
    }

    public String getTuri() {
        return turi;
    }

    public void setTuri(String turi) {
        this.turi = turi;
    }

    public int getNarxi() {
        return narxi;
    }

    public void setNarxi(int narxi) {
        this.narxi = narxi;
    }

    public double getMiqdor() {
        return miqdor;
    }

    public void setMiqdor(double miqdor) {
        this.miqdor = miqdor;
    }
}
