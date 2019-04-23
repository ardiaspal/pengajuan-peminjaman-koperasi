/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author coeng
 */
public class Tketua extends users {

    @Override
    public void approveanggota(JTable aproveanggota) {

        Object[] rows = {"Nama ", "Status Anggota"};
        tabelAnggota = new DefaultTableModel(null, rows);
        aproveanggota.setModel(tabelAnggota);
        koneksi DB = new koneksi();
        con = DB.con();

        try {
            String sql = "SELECT * FROM anggota Where Status_anggota= 'Diproses'";
            stat = con.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                System.out.println("anggora approve---");
                System.out.println(rs.getString("Nama"));
                String Nama = rs.getString("Nama");
                String Status_Anggota = rs.getString("Status_anggota");
                String[] tampil = {Nama, Status_Anggota};
                tabelAnggota.addRow(tampil);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("1");
        }
    }

    @Override
    public void persetujuanAnggota(String status_sekarang, String setatus_sebelum, String nama) {

        try {
            if (nama.equalsIgnoreCase("")) {
                popup.gagal("pilih dulu data di tabelnya ||");
            } else if (status_sekarang.equalsIgnoreCase("")) {
                popup.gagal("pilih dulu Statusnya ||");
            } else {
                String query = "UPDATE anggota SET Status_anggota= '" + status_sekarang + "' WHERE Status_anggota= '" + setatus_sebelum + "' AND Nama= '" + nama + "'";
                boolean status = querySql(query);
                if (status) {
                    popup.sukses("Status Diterima Berhasil di simpan");
                } else {
                    popup.gagal("Status Ditolak Berhasil di simpan");
                }
            }
        } catch (Exception e) {
            popup.error("Periksa pilihan anda");
        }
    }

    @Override
    public void aprovePengajuanpeminjaman(JTable pengajuanPeminjaman) {
        Object[] rows = {"Nama", "jumlah Peminjmana", "Status Peminjaman", "Tipe"};
        tabelAnggota = new DefaultTableModel(null, rows);
        pengajuanPeminjaman.setModel(tabelAnggota);
        koneksi DB = new koneksi();
        con = DB.con();

        try {
//            String sql = "SELECT * FROM anggota Where Status_anggota= 'Diterima'";
            String sql = "SELECT Nama,jumlah_pinjaman,status_peminjman,tipe FROM peminjaman_anggota pa join anggota a on pa.anggota_id = a.anggota_id where status_peminjman= 'diproses'"
                    + " union all SELECT Nama,jumlah_pinjaman,status_peminjman,tipe FROM peminjaman_canggota pc join calon_anggota ca on pc.Canggota_id = ca.calonAnggota_id where status_peminjman= 'diproses'";
            stat = con.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                System.out.println("anggota-----");
//                System.out.println(rs.getString("Nama"));
                String Nama = rs.getString("nama");
                String jml_peminjaman = rs.getString("jumlah_pinjaman");
                String status_peminjaman = rs.getString("status_peminjman");
                String tipe = rs.getString("tipe");
                String[] tampil = {Nama, jml_peminjaman, status_peminjaman, tipe};
                tabelAnggota.addRow(tampil);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("1");
            popup.error("tidak bisa menmapilkan ada kesalahan !!");
        }
    }

    @Override
    public void simpanstatuspengajuan(String aprove, String namapengajuan, int jumlah_pinjaman, String status_peminjaman, String tipe) {
        popup popup = new popup();
        try {
            if (tipe.equalsIgnoreCase("")) {
                popup.error("pilih salah satu di tabel");
            } else if (tipe.equalsIgnoreCase("anggota")) {
                if (aprove.equalsIgnoreCase("")) {
                    popup.equals("pilih salah satu aproval");
                } else if (aprove.equalsIgnoreCase("diterima")) {
                    String query = "UPDATE peminjaman_anggota SET status_peminjman= 'diterima' WHERE tipe= '" + tipe
                            + "' AND status_peminjman= '" + status_peminjaman + "' AND jumlah_pinjaman= '" + jumlah_pinjaman + "'";
                    boolean status = querySql(query);
                    if (status) {
                        popup.sukses("Status Diterima Berhasil di simpan");
                    }
                } else if (aprove.equalsIgnoreCase("ditolak")) {
                    String query = "UPDATE peminjaman_anggota SET status_peminjman= 'ditolak' WHERE tipe= '" + tipe
                            + "' AND status_peminjman= '" + status_peminjaman + "' AND jumlah_pinjaman= '" + jumlah_pinjaman + "'";
                    boolean status = querySql(query);
                    if (status) {
                        popup.sukses("Status ditolak Berhasil di simpan");
                    }
                }
            } else if (tipe.equalsIgnoreCase("calon anggota")) {
                if (aprove.equalsIgnoreCase("")) {
                    popup.equals("pilih salah satu aproval");
                } else if (aprove.equalsIgnoreCase("diterima")) {
                    String query = "UPDATE peminjaman_canggota SET status_peminjman= 'diterima' WHERE tipe= '" + tipe
                            + "' AND status_peminjman= '" + status_peminjaman + "' AND jumlah_pinjaman= '" + jumlah_pinjaman + "'";
                    boolean status = querySql(query);
                    if (status) {
                        popup.sukses("Status Diterima Berhasil di simpan");
                    }
                } else if (aprove.equalsIgnoreCase("ditolak")) {
                    String query = "UPDATE peminjaman_canggota SET status_peminjman= 'ditolak' WHERE tipe= '" + tipe
                            + "' AND status_peminjman= '" + status_peminjaman + "' AND jumlah_pinjaman= '" + jumlah_pinjaman + "'";
                    boolean status = querySql(query);
                    if (status) {
                        popup.sukses("Status ditolak Berhasil di simpan");
                    }
                }
            } else {
                popup.gagal("pilih dulu lur");
            }
        } catch (Exception e) {
            popup.error("ada kesalahan, sialakan periksa lagi pilian anda !!");
            System.out.println(e.getMessage());
        }
    }

}
