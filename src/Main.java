import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<User> daftar = List.of(
            new Customer("Nadia", "nadia@swash.test", "rahasia1", "081234567890",
                         new Alamat("Jl. Contoh No. 1", "Surabaya")),
            new Admin("Rina", "rina@swash.test", "admin123", "081234567801", "Pagi"),
            new Driver("Bagas Pratama", "bagas@swash.test", "driver12", "081234567800",
                       "Motor", "L 1234 AB")
        );

        for (User u : daftar) {
            // polimorfisme: satu pemanggilan, hasil beda per peran
            System.out.println("== " + u.getClass().getSimpleName() + " ==");
            System.out.println("Dashboard: " + u.getDashboardUrl());
            u.getInfoProfil().forEach((k, v) -> System.out.println(k + ": " + v));
            System.out.println();
        }

        // tes login dan ganti password
        User p = daftar.get(0);
        System.out.println("Login salah : " + p.login("nadia@swash.test", "salah"));
        System.out.println("Login benar : " + p.login("nadia@swash.test", "rahasia1"));
        System.out.println("Ganti pass  : " + p.gantiPassword("rahasia1", "baru1234"));
        p.logout();
    }
}