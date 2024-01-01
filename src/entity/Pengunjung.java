
package entity;

public class Pengunjung {
    private int idPengunjung;
    private String namaPengunjung;
    private String jenkel;
    private String umur;
    private String telp;
    private String alamat;

    public Pengunjung() {
    }

    public Pengunjung(int idPengunjung, String namaPengunjung, String jenkel, String umur, String telp, String alamat) {
        this.idPengunjung = idPengunjung;
        this.namaPengunjung = namaPengunjung;
        this.jenkel = jenkel;
        this.umur = umur;
        this.telp = telp;
        this.alamat = alamat;
    }

    public int getIdPengunjung() {
        return idPengunjung;
    }

    public void setIdPengunjung(int idPengunjung) {
        this.idPengunjung = idPengunjung;
    }

    public String getNamaPengunjung() {
        return namaPengunjung;
    }

    public void setNamaPengunjung(String namaPengunjung) {
        this.namaPengunjung = namaPengunjung;
    }

    public String getJenkel() {
        return jenkel;
    }

    public void setJenkel(String jenkel) {
        this.jenkel = jenkel;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    
}
