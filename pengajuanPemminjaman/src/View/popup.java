/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.JOptionPane;

/**
 *
 * @author coeng
 */
public class popup {

    public static void sukses(String text) {
        JOptionPane.showMessageDialog(null, text, "Sukses", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void error(String text) {
        JOptionPane.showMessageDialog(null, text, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void gagal(String text) {
        JOptionPane.showMessageDialog(null, text, "Gagal", JOptionPane.ERROR_MESSAGE);
    }

    public static boolean konfirmasi() {
        return JOptionPane.showConfirmDialog(null, "Anda yakin?", "Konfirmasi", 0) == 0;
    }
}
