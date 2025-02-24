import java.util.*;
class Film {
    public String judul, genre, jamTayang;
    public int hargaTiket;
    public Film(String judul, String genre, String jamTayang, int hargaTiket) {
        this.judul = judul;
        this.genre = genre;
        this.jamTayang = jamTayang;
        this.hargaTiket = hargaTiket;
    }
    void tampilkanInfoFilm() {
        System.out.printf("Judul: %s | Genre: %s | Jam: %s | Harga: Rp%,d%n",
                judul, genre, jamTayang, hargaTiket);
                System.out.println("");
    }
}
class Studio {
    public int nomorStudio, kapasitasKursi;
    public Film film;
    public Set<Integer> kursiTerpesan = new HashSet<>();
    public Film getFilm() {
        return film;
    }
    public Studio(int nomorStudio, int kapasitasKursi, Film film) {
        if (kapasitasKursi < 1) {
            throw new IllegalArgumentException("Kapasitas kursi tidak boleh kurang dari 1");
        }
        this.nomorStudio = nomorStudio;
        this.kapasitasKursi = kapasitasKursi;
        this.film = film;
    }
    boolean pesanKursi(int nomorKursi) {
        if (nomorKursi < 1 || nomorKursi > kapasitasKursi || !kursiTerpesan.add(nomorKursi)) {
            System.out.println("Kursi tidak tersedia!");
            return false;
        }
        return true;
    }
    void tampilkanInfoStudio() {
        System.out.printf("Studio %d (Kapasitas: %d)%n", nomorStudio, kapasitasKursi);
        film.tampilkanInfoFilm();
    }
    int getNomorStudio() {
        return nomorStudio;
    }
}
class Bioskop {
    public String namaBioskop;
    public List<Studio> daftarStudio = new ArrayList<>();
    public Bioskop(String namaBioskop) {
        this.namaBioskop = namaBioskop;
    }
    void tambahStudio(Studio studio) {
        daftarStudio.add(studio);
    }
    void tampilkanInfoBioskop() {
        System.out.println("\nBioskop: " + namaBioskop);
        daftarStudio.forEach(Studio::tampilkanInfoStudio);
    }
    Studio getStudioByNomor(int nomor) {
        return daftarStudio.stream().filter(s -> s.getNomorStudio() == nomor).findFirst().orElse(null);
    }
}
class Tiket {
    public int nomorTiket, studio, nomorKursi;
    public String namaPenonton;
    public Film film;
    public Tiket(int nomorTiket, String namaPenonton, int studio, int nomorKursi, Film film) {
        this.nomorTiket = nomorTiket;
        this.namaPenonton = namaPenonton;
        this.studio = studio;
        this.nomorKursi = nomorKursi;
        this.film = film;
    }
    public void tampilkanInfoTiket() {
        System.out.printf("\nTIKET BERHASIL DIPESAN! No: %d | %s | Studio %d | Kursi %d%n",
                nomorTiket, namaPenonton, studio, nomorKursi);
        film.tampilkanInfoFilm();
    }
    public void tampilkanNota() {
        System.out.println("\n====================================");
        System.out.println("           BIOSKOP ABC");
        System.out.println("====================================");
        System.out.printf("Nama Penonton   : %s%n", namaPenonton);
        System.out.printf("Nomor Studio    : %d%n", studio);
        System.out.printf("Judul Film      : %s%n", film.judul);
        System.out.printf("Genre           : %s%n", film.genre);
        System.out.printf("Jam Tayang      : %s%n", film.jamTayang);
        System.out.printf("Nomor Kursi     : %d%n", nomorKursi);
        System.out.printf("Harga Tiket     : Rp %,d%n", film.hargaTiket);
        System.out.println("------------------------------------");
        System.out.printf("Kode Tiket      : %d%n", nomorTiket);
        System.out.println("====================================");
        System.out.println("       SELAMAT MENIKMATI FILM!");
        System.out.println("====================================");
    }
}