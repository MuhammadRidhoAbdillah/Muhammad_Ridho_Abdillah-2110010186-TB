
package entity;

public class Tiket {
    private int idTiket;
    private int idPengunjung2;
    private int idWahana2;
    private String tanggal;
    private String nama;
    private String umurPengunjung;
    private String nmWahana;
    private String hargaMasuk;

    public Tiket() {
    }

    public Tiket(int idTiket, int idPengunjung2, int idWahana2, String tanggal, String nama, String umurPengunjung, String nmWahana, String hargaMasuk) {
        this.idTiket = idTiket;
        this.idPengunjung2 = idPengunjung2;
        this.idWahana2 = idWahana2;
        this.tanggal = tanggal;
        this.nama = nama;
        this.umurPengunjung = umurPengunjung;
        this.nmWahana = nmWahana;
        this.hargaMasuk = hargaMasuk;
    }

    public int getIdTiket() {
        return idTiket;
    }

    public void setIdTiket(int idTiket) {
        this.idTiket = idTiket;
    }

    public int getIdPengunjung2() {
        return idPengunjung2;
    }

    public void setIdPengunjung2(int idPengunjung2) {
        this.idPengunjung2 = idPengunjung2;
    }

    public int getIdWahana2() {
        return idWahana2;
    }

    public void setIdWahana2(int idWahana2) {
        this.idWahana2 = idWahana2;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUmurPengunjung() {
        return umurPengunjung;
    }

    public void setUmurPengunjung(String umurPengunjung) {
        this.umurPengunjung = umurPengunjung;
    }

    public String getNmWahana() {
        return nmWahana;
    }

    public void setNmWahana(String nmWahana) {
        this.nmWahana = nmWahana;
    }

    public String getHargaMasuk() {
        return hargaMasuk;
    }

    public void setHargaMasuk(String hargaMasuk) {
        this.hargaMasuk = hargaMasuk;
    }
    
    
}
