package profil;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class User {
    private static int jumlahUser = 0;

    private final int id;
    private final String kode;
    private String nama;
    private String email;
    private String password;      // disimpan dalam bentuk hash
    protected String phone;
    private boolean sedangLogin;

    protected User(String kode, String nama, String email, String password, String phone) {
        if (nama == null || nama.isBlank()) {
            throw new IllegalArgumentException("Nama wajib diisi.");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email tidak valid.");
        }
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Password minimal 6 karakter.");
        }
        this.id = ++jumlahUser;
        this.kode = kode;
        this.nama = nama;
        this.email = email;
        this.password = hash(password);
        this.phone = phone;
    }

    public boolean login(String email, String password) {
        if (this.email.equalsIgnoreCase(email) && this.password.equals(hash(password))) {
            sedangLogin = true;
        }
        return sedangLogin;
    }

    public void logout() {
        sedangLogin = false;
    }

    public void updateProfil(String nama, String phone) {
        if (nama == null || nama.isBlank()) {
            throw new IllegalArgumentException("Nama wajib diisi.");
        }
        this.nama = nama;
        this.phone = phone;
    }

    public boolean gantiPassword(String lama, String baru) {
        if (!password.equals(hash(lama))) {
            return false;                       // password lama salah
        }
        if (baru == null || baru.length() < 6) {
            return false;                       // password baru terlalu pendek
        }
        this.password = hash(baru);
        return true;
    }

    public abstract String getDashboardUrl();
    public abstract Map<String, String> getInfoProfil();

    protected Map<String, String> infoDasar() {
        Map<String, String> info = new LinkedHashMap<>();
        info.put("Kode", kode);
        info.put("Nama", nama);
        info.put("Email", email);
        info.put("No. HP", phone);
        return info;
    }

    protected static String formatKode(String prefix, int urutan) {
        return String.format("%s-%03d", prefix, urutan);
    }

    private static String hash(String teks) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(teks.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    public int getId() { return id; }
    public String getKode() { return kode; }
    public String getNama() { return nama; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public boolean isSedangLogin() { return sedangLogin; }
}