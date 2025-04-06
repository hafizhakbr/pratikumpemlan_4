// Kelas Pelanggan
class Pelanggan {
    // deklarasi atribut kelas Pelanggan
    private String nomorPelanggan;  // menyimpan nomor pelanggan (10 digit)
    private String nama;           // menyimpan nama pelanggan
    private int saldo;             // menyimpan jumlah saldo pelanggan
    private String pin;            // menyimpan PIN untuk autentikasi
    private boolean diblokir;      // status apakah akun diblokir atau tidak
    private int percobaanGagal;    // menghitung jumlah percobaan login gagal
// constructor untuk inisialisasi objek Pelanggan
public Pelanggan(String nomorPelanggan, String nama, int saldo, String pin) {
        this.nomorPelanggan = nomorPelanggan;
        this.nama = nama;
        this.saldo = saldo;
        this.pin = pin;
        this.diblokir = false;     // default status tidak diblokir
        this.percobaanGagal = 0;   // default percobaan gagal 0
    }
// method untuk autentikasi pelanggan
public boolean autentikasi(String nomor, String pin) {
        if (diblokir) {
            System.out.println("Akun telah diblokir.");
            return false;
        }
// cek kecocokan nomor pelanggan dan PIN
if (this.nomorPelanggan.equals(nomor) && this.pin.equals(pin)) {
            percobaanGagal = 0;    // reset percobaan gagal jika berhasil
            return true;
    } else {
        percobaanGagal++;
        System.out.println("Autentikasi gagal. Percobaan ke-" + percobaanGagal);
// memblokir akun setelah 3x gagal
    if (percobaanGagal >= 3) {
        diblokir = true;
            System.out.println("Akun telah diblokir karena 3x kesalahan autentikasi.");
        }
        return false;
    }
}
// method untuk top-up saldo
public void topUp(int jumlah) {
        if (jumlah > 0) {
            saldo += jumlah;
            System.out.println("Top-up berhasil. Saldo sekarang: Rp" + saldo);
        } else {
            System.out.println("Jumlah top-up harus lebih dari 0.");
        }
    }
// method untuk transaksi pembelian
public void beli(int jumlah) {
        if (jumlah <= 0) {
            System.out.println("Jumlah pembelian harus lebih dari 0.");
            return;
        }
        int cashback = hitungCashback(jumlah);  // hitung cashback
        int totalDibayar = jumlah - cashback;   // hitung total yang dibayar
// cek kecukupan saldo (minimal saldo Rp10.000 setelah transaksi)
if (saldo - totalDibayar >= 10000) {
            saldo -= totalDibayar;
            saldo += cashback; // cashback masuk ke saldo
            System.out.println("Pembelian berhasil. Total dibayar: Rp" + totalDibayar + ", Cashback: Rp" + cashback);
            System.out.println("Saldo sekarang: Rp" + saldo);
        } else {
            System.out.println("Saldo tidak mencukupi. Transaksi gagal.");
        }
    }
// method untuk menghitung cashback berdasarkan jenis pelanggan
private int hitungCashback(int jumlah) {
    String jenis = nomorPelanggan.substring(0, 2);  // ambil 2 digit pertama
    if (jenis.equals("38")) { // silver
        return jumlah > 1000000 ? jumlah * 5 / 100 : 0;
    } else if (jenis.equals("56")) { // Ggld
        return jumlah > 1000000 ? jumlah * 7 / 100 : jumlah * 2 / 100;
    } else if (jenis.equals("74")) { // platinum
        return jumlah > 1000000 ? jumlah * 10 / 100 : jumlah * 5 / 100;
    }
    return 0;
}
// getter untuk nomor pelanggan
public String getNomorPelanggan() {
        return nomorPelanggan;
    }
// getter untuk nama pelanggan
public String getNama() {
    return nama;
}
// getter untuk jenis pelanggan (Silver/Gold/Platinum)
public String getJenisPelanggan() {
    String jenis = nomorPelanggan.substring(0, 2);
    switch (jenis) {
        case "38": return "Silver";
        case "56": return "Gold";
        case "74": return "Platinum";
        default: return "Reguler";
        }
    }
}