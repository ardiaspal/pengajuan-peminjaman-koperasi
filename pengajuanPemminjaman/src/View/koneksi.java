/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author coeng
 */
public class koneksi {

    private static Connection connection = null;
    private String url = "jdbc:mysql://localhost:3306/pbo2";
    private String user = "root";
    private final String password = "";

    public Connection con() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("koneksi berhasil");
        } catch (Exception e) {
            System.out.println("Error");
        }
        return connection;
    }
}
