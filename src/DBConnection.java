/*
* @author fajhri
* DB Connection Package
*/

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
    /*
     * @author fajhri
     * DB Connection Package
     */
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://localhost/akademik";
    static final String USER = "root";
    static final String PASS = "rafi213fajri";

    static Connection conn;
    static Statement stmt;
    static ResultSet result;

    public static void connectDatabase() {
        /*
         * This method for try connecting into database.
         * If connecting is failed. Catch error message.
         */
        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Connecting to database...");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeDatabase() throws SQLException {
        /*
         * Close the database
         */
        stmt.close();
        conn.close();
    }

    public static void insertOne(Integer nim, String namaLengkap, String jenisKelamin, String alamat, String jurusan, String tanggalLahir) throws SQLException {
        /*
         * Insert data
         */
        String query = String.format("INSERT INTO mahasiswa (`nim`, `nama_lengkap`, `jenis_kelamin`, `alamat`, `jurusan`, `tanggal_lahir`) VALUE (%s, '%s', '%s', '%s', '%s', '%s')", 
            nim, namaLengkap, jenisKelamin, alamat, jurusan, tanggalLahir);

        connectDatabase();

        stmt = conn.createStatement();

        result = stmt.executeQuery(query);

        System.out.println("Successfully to insert data into database.");

        closeDatabase();
    }

    public static void getDataByNIM(Integer nim) throws SQLException {
        /*
         * Get data by NIM 
         */
        connectDatabase();

        stmt = conn.createStatement();

        String query = "SELECT * FROM mahasiswa WHERE nim=" + nim;

        result = stmt.executeQuery(query);

        while (result.next()) {
            System.out.println("NIM\t\t:\t" + result.getString("nim"));
            System.out.println("Nama Lengkap\t:\t" + result.getString("nama_lengkap"));
            System.out.println("Jenis Kelamin\t:\t" + result.getString("jenis_kelamin"));
            System.out.println("Jurusan\t\t:\t" + result.getString("jurusan"));
            System.out.println("Tanggal Lahir\t:\t" + result.getString("tanggal_lahir"));
        }
    }

    public static void getAllData() throws SQLException {
        /*
         * Get all of data
         */
        connectDatabase();

        String query = "SELECT * from mahasiswa";

        stmt = conn.createStatement();

        result = stmt.executeQuery(query);

        while(result.next()) {
            System.out.println("NIM\t\t:\t" + result.getString("nim"));
            System.out.println("Name Lengkap\t:\t" + result.getString("nama_lengkap"));
            System.out.println("Jenis Kelamin\t:\t" + result.getString("jenis_kelamin"));
            System.out.println("Jerusan\t\t:\t" + result.getString("jurusan"));
            System.out.println("Tanggal Lahir\t:\t" + result.getDate("tanggal_lahir"));
            System.out.println();
            System.out.println("*********************************************************");
            System.out.println();
        }

        stmt.close();
        conn.close();
    }
}
