
package entity;

public class Wahana {
      private int idWahana;
      private String namaWahana;
      private String harga;
      private String jamMulai;
      private String jamSelesai;

    public Wahana() {
    }

    public Wahana(int idWahana, String namaWahana, String harga, String jamMulai, String jamSelesai) {
        this.idWahana = idWahana;
        this.namaWahana = namaWahana;
        this.harga = harga;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
    }

    public int getIdWahana() {
        return idWahana;
    }

    public void setIdWahana(int idWahana) {
        this.idWahana = idWahana;
    }

    public String getNamaWahana() {
        return namaWahana;
    }

    public void setNamaWahana(String namaWahana) {
        this.namaWahana = namaWahana;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(String jamMulai) {
        this.jamMulai = jamMulai;
    }

    public String getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(String jamSelesai) {
        this.jamSelesai = jamSelesai;
    }
      
      
    
}
