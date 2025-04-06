// kelas Main
import java.util.ArrayList;
import java.util.Scanner;
public class TransaksiSwalayanTiny {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); 
// membuat daftar pelanggan awal
    ArrayList<Pelanggan> daftarPelanggan = new ArrayList<>();
    daftarPelanggan.add(new Pelanggan("3812345678", "Andi", 1500000, "1111")); // Silver
    daftarPelanggan.add(new Pelanggan("5612345678", "Budi", 5000000, "1234")); // Gold
    daftarPelanggan.add(new Pelanggan("7412345678", "Cindy", 10000000, "4321")); // Platinum
// loop program utama 
    while (true) {
// menampilkan menu
    System.out.println("\nMenu:");
    System.out.println("1. Top-Up");
    System.out.println("2. Beli");
    System.out.println("3. Keluar");
    System.out.print("Pilih menu: ");
    int pilihan = input.nextInt();
// keluar dari program
    if (pilihan == 3) {
        System.out.println("Terima kasih!");
        break;
}
// input nomor pelanggan dan PIN
    System.out.print("Masukkan nomor pelanggan: ");
    String nomor = input.next();
    System.out.print("Masukkan PIN: ");
    String pin = input.next();
// mencari pelanggan berdasarkan nomor
    Pelanggan pelangganAktif = null;
        for (Pelanggan p : daftarPelanggan) {
            if (p.getNomorPelanggan().equals(nomor)) {
                pelangganAktif = p;
                break;
    }
}
// Validasi nomor pelanggan
    if (pelangganAktif == null) {
        System.out.println("Nomor pelanggan tidak ditemukan.");
        continue;
}
// Autentikasi pelanggan
    if (!pelangganAktif.autentikasi(nomor, pin)) continue;
// Tampilkan info pelanggan setelah login berhasil
    System.out.println("\nSelamat datang, " + pelangganAktif.getNama() + 
        " (Tipe: " + pelangganAktif.getJenisPelanggan() + ")");
// Proses menu yang dipilih
    if (pilihan == 1) {
        System.out.print("Masukkan jumlah top-up: Rp");
        int jumlah = input.nextInt();
        pelangganAktif.topUp(jumlah);
            } else if (pilihan == 2) {
                System.out.print("Masukkan jumlah pembelian: Rp");
                int jumlah = input.nextInt();
                pelangganAktif.beli(jumlah);
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
        input.close();  // Tutup scanner
    }
}