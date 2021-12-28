import java.util.Scanner;
import com.mysql.cj.protocol.Resultset;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class Program extends Gaji {
    static Connection conn;
    public static void main(String[] args) throws SQLException {
        
        Scanner keyboard = new Scanner (System.in);
        String pilihan;
        Boolean lanjut = true;
        String url = "jdbc:mysql://localhost:3306/gaji_pegawai";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, "root", "");
            System.out.println("Driver ditemukan");

            while (lanjut){
                System.out.println("\nDatabase Penerimaan Gaji Pegawai");
                System.out.println("===================================");
                System.out.println("1. Lihat Data Keseluruhan");
                System.out.println("2. Tambah Data");
                System.out.println("3. Ubah Data");
                System.out.println("4. Hapus Data");
                System.out.println("5. Cari Data");

                System.out.print("Masukkan Pilihan Aksi : ");
                pilihan = keyboard.next();

                switch(pilihan){
                    case "1" : lihat();
                    break;

                    case "2" : tambah();
                    break;

                    case "3" : ubah();
                    break;

                    case "4" : hapus();
                    break;

                    case "5" : cari();
                    break;

                    default : System.out.println("Pilihan tidak tersedia");
                }
                System.out.print("Apakah Anda ingin melanjutkan lagi ? (y/n) : ");
                pilihan = keyboard.next();
                lanjut = pilihan.equalsIgnoreCase("y");
            }
            System.out.println("Terimakasih telah menggunakan program ini. SAYONARA");
        }
        catch(ClassNotFoundException ex){
            System.out.println("Driver tidak ditemukan");
            System.exit(0);
        }
        catch(SQLException e){
            System.err.println("Koneksi ke database gagal");
        }

        
    }
}



        
