import java.util.InputMismatchException;
import java.util.Scanner;
import com.mysql.cj.protocol.Resultset;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class Gaji{
    String jabatan,no,nama;
    Integer gaji_pokok, hari_masuk,potongan,total_gaji;
    Scanner keyboard = new Scanner(System.in);
    static Connection conn;


    public static void lihat() throws SQLException{
        System.out.println("==DATA PENERIMAAN GAJI PEGAWAI==");
        String sql = "SELECT * FROM data_penerima_gaji";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()){
            System.out.println("No. Pegawai\t: ");
            System.out.println(result.getInt("no_pegawai"));
            System.out.println("Nama Pegawai\t: ");
            System.out.println(result.getString("nama_pegawai"));
            System.out.println("Jabatan\t: ");
            System.out.println(result.getString("jabatan"));
            System.out.println("Gaji pokok\t: ");
            System.out.println(result.getInt("gaji_pokok"));
            System.out.println("Jumlah hari masuk\t: ");
            System.out.println(result.getInt("jumlah_hari_masuk"));
            System.out.println("Potongan\t: ");
            System.out.println(result.getInt("potongan"));
            System.out.println("Total gaji\t: ");
            System.out.println(result.getInt("total_gaji"));
        }
    }

    public static void tambah() throws SQLException{
        System.out.println("==TAMBAH DATA GAJI PEGAWAI==");
        Scanner keyboard = new Scanner (System.in);
        try {
            System.out.print("No. Pegawai\t: ");
		    int no = keyboard.nextInt();
		    System.out.print("Nama Pegawai\t: ");
		    String nama = keyboard.next();
		    System.out.print("Jabatan\t: ");
		    String jabatan = keyboard.next();
            System.out.print("Gaji pokok\t: ");
            int gaji_pokok = keyboard.nextInt();
            System.out.print("Jumlah hari masuk\t: ");
            int hari_masuk = keyboard.nextInt();
            int potongan = hari_masuk * (gaji_pokok/30);
            System.out.println(potongan);
            int total_gaji = gaji_pokok - potongan;
		
		    String sql = "INSERT INTO data_penerima_gaji (no_pegawai, nama_pegawai, jabatan, gaji_pokok, jumlah_hari_masuk, potongan, total_gaji) VALUES ('"+no+"','"+nama+"','"+jabatan+"', '"+gaji_pokok+"','"+hari_masuk+"','"+potongan+"','"+total_gaji+"')";
					
        Statement statement = conn.createStatement();
        statement.execute(sql);
        System.out.println("Input data berhasil");
	
	    } catch (SQLException e) {
	        System.err.println("Terjadi kesalahan saat input data");
	    } catch (InputMismatchException e) {
	    	System.err.println("Inputlah dengan angka saja");
	   	}
	}

    public static void ubah() throws SQLException{
        System.out.println("==UBAH DATA GAJI PEGAWAI==");
        Scanner keyboard = new Scanner (System.in);
		
		try {
            lihat();
            System.out.print("Masukkan No Pegawai yang ingin di update : ");
            int no = keyboard.nextInt();
            
            String sql = "SELECT * FROM data_penerima_gaji WHERE no_pegawai = " +no;
            
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            if(result.next()){
                
                System.out.print("Nama ["+result.getString("nama_pegawai")+"]\t: ");
                String nama = keyboard.nextLine();
                
                System.out.print("Jabatan ["+result.getString("jabatan")+"]\t: ");
                String jabatan = keyboard.nextLine();

                System.out.print("Gaji pokok ["+result.getInt("gaji_pokok")+"]\t: ");
                Integer gaji_pokok = keyboard.nextInt();

                System.out.print("Jumlah hari masuk ["+result.getInt("jumlah_hari_masuk")+"]\t: ");
                Integer hari_masuk = keyboard.nextInt();

                int potongan = hari_masuk * (gaji_pokok/30);
                int total_gaji = gaji_pokok - potongan;
                   
                sql = "UPDATE mahasiswa SET nama='"+nama+"',jabatan= '"+jabatan+"',gaji_pokok= '"+gaji_pokok+"',jumlah_hari_masuk= '"+hari_masuk+"',potongan= '"+potongan+"',total_gaji= '"+total_gaji+"' WHERE no_pegawai='"+no+"'";

                if(statement.executeUpdate(sql) > 0){
                    System.out.println("Berhasil memperbaharui data pegawai (no_pegawai "+no+")");
                }
            }
            statement.close();        
        } catch (SQLException e) {
            System.err.println("Terjadi kesalahan dalam mengedit data");
            System.err.println(e.getMessage());
        }
		}

        public static void hapus() {
            System.out.println("==HAPUS DATA GAJI PEGAWAI==");
            
            Scanner keyboard = new Scanner (System.in);
            
            try{
                lihat();
                System.out.print("Ketik No. Pegawai yang ingin dihapus : ");
                Integer no = keyboard.nextInt();
                
                String sql = "DELETE FROM data_penerima_gaji WHERE no_pegawai = "+ no;
                Statement statement = conn.createStatement();
                
                if(statement.executeUpdate(sql) > 0){
                    System.out.println("Berhasil menghapus data pegawai (No. Pegawai "+no+")");
                }
           }catch(SQLException e){
                System.out.println("Terjadi kesalahan dalam menghapus data");
                }
            }
        
        public static void cari() throws SQLException {
            System.out.println("==CARI DATA GAJI PEGAWAI==");
            
            Scanner keyboard = new Scanner (System.in);
                    
            System.out.print("Masukkan Nama Pegawai : ");
            
            String keyword = keyboard.nextLine();
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM data_penerima_gaji WHERE nama_pegawai LIKE '%"+keyword+"%'";
            ResultSet result = statement.executeQuery(sql); 
                    
            while(result.next()){
                System.out.println("No. Pegawai\t: ");
                System.out.println(result.getInt("no_pegawai"));
                System.out.println("Nama Pegawai\t: ");
                System.out.println(result.getString("nama_pegawai"));
                System.out.println("Jabatan\t: ");
                System.out.println(result.getString("jabatan"));
                System.out.println("Gaji pokok\t: ");
                System.out.println(result.getInt("gaji_pokok"));
                System.out.println("Jumlah hari masuk\t: ");
                System.out.println(result.getInt("jumlah_hari_masuk"));
                System.out.println("Potongan\t: ");
                System.out.println(result.getInt("potongan"));
                System.out.println("Total gaji\t: ");
                System.out.println(result.getInt("total_gaji"));
            }
        }
}
        
    

    // @Override
    // public void noPegawai(){
    //     System.out.print("No Anda : ");
    //     this.no = keyboard.nextLine();
    // }

    // @Override
    // public void namaPegawai(){
    //     System.out.print("Nama Anda : ");
    //     this.nama = keyboard.nextLine();
    // }

    // @Override
    // public void jabatan() {
    //     System.out.print("Jabatan Anda : ");
    //     this.jabatan = keyboard.nextLine();
    //    gajiPokok();
    // }

    // @Override
    // public void gajiPokok(){
    //     switch(this.jabatan.toUpperCase()){
    //         case "CEO" : this.gajiPokok = 15000000;
    //         break;
    //         case "DIREKTUR" : this.gajiPokok = 12000000;
    //         break;
    //         case "MANAGER" : this.gajiPokok = 10000000;
    //         break;
    //         case "KARYAWAN" : this.gajiPokok = 7500000;
    //         break;
    //         default : System.out.println("Jabatan tidak ditemukan atau format penulisan salah");
    //         jabatan();
    //     }
    // }

    // @Override
    // public void jumlahHariMasuk() {
    //     System.out.print("Berapa hari Anda absen ? (per 30 hari) : ");
    //     this.hariMasuk = keyboard.nextInt();
    // }

    // @Override
    // public void potongan() {
    //     int gajiPerHari = this.gajiPokok/30;
    //     this.potongan = this.gajiPokok - (gajiPerHari * (30 - this.hariMasuk));
    // }

    // @Override
    // public void totalGaji() {
    //     this.totalGaji = this.gajiPokok - this.potongan;
    // }
    
