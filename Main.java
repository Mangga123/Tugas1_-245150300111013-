import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        // Membuat bioskop
        Bioskop bioskop = new Bioskop("Bioskop ABC");
        // Membuat studio dan film
        bioskop.tambahStudio(new Studio(1, 100, new Film("Miaw Miaw Miaw", "Sci-Fi", "19:00", 50000)));
        bioskop.tambahStudio(new Studio(2, 120, new Film("Azab langit", "Sci-Fi", "21:00", 55000)));
        bioskop.tambahStudio(new Studio(3, 80, new Film("Dark n Darker", "Action", "18:30", 60000)));
        // Menampilkan informasi bioskop
        bioskop.tampilkanInfoBioskop();
        boolean lanjut = true;
        while (lanjut) {
            System.out.println("\n>>> PEMESANAN TIKET <<<");
            System.out.print("Masukkan nama penonton: ");
            String namaPenonton = scanner.nextLine(); 
            Studio studioDipilih;
            do {
                System.out.print("\nPilih nomor studio (1-3): ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Harap masukkan angka!");
                    scanner.next();
                }
                int nomorStudio = scanner.nextInt();
                studioDipilih = bioskop.getStudioByNomor(nomorStudio);

                if (studioDipilih == null) {
                    System.out.println("Nomor studio tidak valid! Silakan coba lagi.");
                }
            } while (studioDipilih == null);
            int nomorKursi;
            do {
                System.out.print("Pilih nomor kursi: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Harap masukkan angka!");
                    scanner.next();
                }
                nomorKursi = scanner.nextInt();
            } while (!studioDipilih.pesanKursi(nomorKursi));
            // Generate nomor tiket secara acak
            int nomorTiket = random.nextInt(1000) + 1; 
            // Membuat tiket
            Tiket tiket = new Tiket(nomorTiket, namaPenonton, studioDipilih.getNomorStudio(), nomorKursi, studioDipilih.getFilm());
            // Menampilkan tiket dan nota
            tiket.tampilkanInfoTiket();
            tiket.tampilkanNota();
            // Menanyakan apakah ingin memesan lagi
            System.out.print("\nIngin memesan tiket lagi? (ya/tidak): ");
            scanner.nextLine(); // Menghindari bug input
            String jawaban = scanner.nextLine().trim().toLowerCase();
            if (!jawaban.equals("ya")) {
                lanjut = false;
            }
        }
        System.out.println("\nTerima kasih telah memesan tiket di Bioskop ABC! Selamat menikmati film.");
        scanner.close();
    }
}
