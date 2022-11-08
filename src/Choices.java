import java.sql.SQLException;
import java.util.Scanner;

public class Choices {
    public void Insert() throws SQLException {
        try (Scanner userInput = new Scanner(System.in)) {
            // NIM Input
            System.out.print("Masukkan NIM: ");
            String nim = userInput.nextLine();
            // nama_lengkap INPUT
            System.out.print("Masukkan Nama Lengkap: ");
            String namaLengkap = userInput.nextLine();
            // Jenis Kelamin INPUT
            System.out.print("Masukkan Jenis Kelamin: ");
            String jenisKelamin = userInput.nextLine();
            // Jurusan INPUT
            System.out.print("Masukkan Jurusan: ");
            String jurusan = userInput.nextLine();
            // Tanggal Lahir
            System.out.print("Masukkan Tanggal Lahir(YYYY-MM-DD): ");
            String tanggalLahir = userInput.nextLine();

            System.out.println();
            System.out.println("Inserting data..."); 

            DBConnection.insertOne(Integer.parseInt(nim), namaLengkap, jenisKelamin, jenisKelamin, jurusan, tanggalLahir);

            System.out.println();

            DBConnection.getDataByNIM(Integer.parseInt(nim));
        }
    }
}
