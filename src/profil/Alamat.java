package profil;

public class Alamat {
    private String jalan;
    private String kota;

    public Alamat(String jalan, String kota) {
        this.jalan = jalan;
        this.kota = kota;
    }

    public String getAlamatLengkap() {
        return jalan + ", " + kota;
    }

    public boolean validasi() {
        return jalan != null && !jalan.isBlank()
            && kota != null && !kota.isBlank();
    }

    public String getJalan() { return jalan; }
    public String getKota() { return kota; }
    public void setJalan(String jalan) { this.jalan = jalan; }
    public void setKota(String kota) { this.kota = kota; }
}