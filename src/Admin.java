import java.util.Map;

public class Admin extends User {
    private static int urutan = 0;

    private String shift;

    public Admin(String nama, String email, String password, String phone, String shift) {
        super(formatKode("AD", ++urutan), nama, email, password, phone);
        this.shift = shift;
    }

    @Override
    public String getDashboardUrl() {
        return "/admin/dashboard";
    }

    @Override
    public Map<String, String> getInfoProfil() {
        Map<String, String> info = infoDasar();
        info.put("Shift", shift);
        return info;
    }

    public String getShift() { return shift; }
    public void setShift(String shift) { this.shift = shift; }
}