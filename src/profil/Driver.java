package profil;

import java.util.Map;

public class Driver extends User {
    private static int urutan = 0;

    private String kendaraan;
    private String plat;

    public Driver(String nama, String email, String password, String phone,
                  String kendaraan, String plat) {
        super(formatKode("DR", ++urutan), nama, email, password, phone);
        this.kendaraan = kendaraan;
        this.plat = plat;
    }

    @Override
    public String getDashboardUrl() {
        return "/driver/dashboard";
    }

    @Override
    public Map<String, String> getInfoProfil() {
        Map<String, String> info = infoDasar();
        info.put("Kendaraan", kendaraan);
        info.put("Plat", plat);
        return info;
    }

    public String getKendaraan() { return kendaraan; }
    public String getPlat() { return plat; }
}