package model;
public class AdnHeader {


    private int length; //Número de bases que té la seqüència


    private boolean teComentari; //Indica si hi ha linica de comentari (comença amb >)


    private int ComMida; // Indica logitud del comentari, sempre inclou el caràcter >


    private static final int BITS_PER_BASE = 2; // Bits que ocuparà cada símbol: 00, 01, 10, 11


    // Constructor buit
    public AdnHeader() {
    }


    // Constructor complet
    public AdnHeader(int length, boolean teComentari, int ComMida, int bitsPerBase) {
        this.length = length;
        this.teComentari = teComentari;
        this.ComMida = ComMida;
    }


    // Getters i setters


    public int getLength() {
        return length;
    }


    public void setLength(int length) {
        this.length = length;
    }


    public boolean teComentari() {
        return teComentari;
    }


    public void setteComentari(boolean teComentari) {
        this.teComentari = teComentari;
    }


    public int getComMida() {
        return ComMida;
    }


    public void setComMida(int ComMida) {
        this.ComMida = ComMida;
    }


    public int getBitsPerBase() {
        return BITS_PER_BASE;
    }


    // Mètode toString


    public String toString() {
        return "AdnHeader{" +
                "length=" + length +
                ", teComentari=" + teComentari +
                ", ComMida=" + ComMida +
                ", bitsPerBase=" + BITS_PER_BASE +
                '}';
    }
}







