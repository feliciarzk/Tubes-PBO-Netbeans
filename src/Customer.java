import java.util.Map;

public class Customer extends User {
    private static int urutan = 0;

    private Alamat alamatProfil;

    public Customer(String nama, String email, String password, String phone, Alamat alamatProfil) {
        super(formatKode("PL", ++urutan), nama, email, password, phone);
        this.alamatProfil = alamatProfil;
    }

    @Override
    public String getDashboardUrl() {
        return "/customer/dashboard";
    }

    @Override
    public Map<String, String> getInfoProfil() {
        Map<String, String> info = infoDasar();
        info.put("Alamat utama", alamatProfil == null ? "-" : alamatProfil.getAlamatLengkap());
        return info;
    }

    public void ubahAlamat(Alamat a) {
        if (a == null || !a.validasi()) {
            throw new IllegalArgumentException("Alamat tidak valid.");
        }
        this.alamatProfil = a;
    }

    public Alamat getAlamatProfil() { return alamatProfil; }
}