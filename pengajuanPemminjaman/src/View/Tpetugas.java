/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.sql.Date;
import javax.swing.JComboBox;
import javax.swing.JTable;
import View.anggota;
import static View.users.id_admin;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author coeng
 */
public class Tpetugas extends users {

    @Override
    protected void tambah_anggota(String Nama, String Alamat, String Aktifitas_Sepeda, String Angsuran, String Status_anggota, String identitas, String noHp, Date Tanggal) {
        String query = "INSERT INTO anggota (AdminId, Nama, Alamat, Aktifitas_Sepeda,Angsuran,Status_anggota,identitas,noHp,Tanggal) VALUES ('" + id_admin + "','"
                + Nama + "','" + Alamat + "','" + Aktifitas_Sepeda + "','"
                + Angsuran + "','" + Status_anggota + "','" + identitas + "','" + noHp + "','" + Tanggal + "')";
        status_proses = querySql(query);
        if (status_proses) {
            popup.sukses("pendaftaran anggota berhasil");
        } else {
            popup.gagal("pendaftaran anggota gagal !!");
        }
    }

    @Override
    protected void tambah_anggota(String Nama, String Alamat, Date Tanggal, String identitas, String noHp, String Status_Canggota) {
        String query = "INSERT INTO calon_anggota (AdminId, Nama, Alamat, Tanggal,identitas,noHp,Status_Canggota) VALUES ('" + id_admin + "','"
                + Nama + "','" + Alamat + "','" + Tanggal + "','"
                + identitas + "','" + noHp + "','" + Status_Canggota + "')";
        status_proses = querySql(query);
        if (status_proses) {
            popup.sukses("pendaftaran anggota berhasil");
        } else {
            popup.gagal("pendaftaran anggota gagal !!");
        }
    }

    @Override
    protected void tambah_peminjaman(String nama, int jmlpeminjaman, Date tanggal, String jangkaWaktu, String CatatanPemnjaman) {
        try {
            koneksi DB = new koneksi();
            con = DB.con();
            System.out.println("masuk sini");
            String queryAnggota = "SELECT * from anggota where Nama= '" + nama + "' AND Status_anggota='Diterima' AND peminjaman='0'";
            stat = con.createStatement();
            rs = stat.executeQuery(queryAnggota);
            while (rs.next()) {
                System.out.println("ini" + Integer.parseInt(rs.getString("anggota_id")));
                anggotaid = Integer.parseInt(rs.getString("anggota_id"));
            }
            stat.close();
            String queryUpdateAnggota = "UPDATE anggota SET peminjaman= '1' WHERE Status_anggota= 'Diterima' AND anggota_id= '" + anggotaid + "'";
            stat = con.createStatement();
            stat.executeUpdate(queryUpdateAnggota);
            if (jangkaWaktu.equalsIgnoreCase("3 bulan")) {
                String query = "INSERT INTO peminjaman_anggota (anggota_id,AdminId,status_peminjman, jumlah_pinjaman, sisa_pengembalian, tanggal_peminjaman,jangka,catatan,tanggal_pembayaran,bayar) VALUES ('" + anggotaid + "','" + id_admin + "','diproses','"
                        + jmlpeminjaman + "','" + jmlpeminjaman + "','" + tanggal + "','" + jangkaWaktu + "','" + CatatanPemnjaman + "','" + tanggal + "','" + (jmlpeminjaman / 3)
                        + "')";
                boolean status = querySql(query);
                if (status) {
                    popup.sukses("Penamabahan Pengajuan peminjaman Anggota Berhasil");
                } else {
                    popup.gagal("Maaf anda sudah melakukan pinjaman, silahakan melakukan pelunasan peminjaman");
                }
            } else if (jangkaWaktu.equalsIgnoreCase("12 bulan")) {
                String query = "INSERT INTO peminjaman_anggota (anggota_id,AdminId,status_peminjman, jumlah_pinjaman, sisa_pengembalian, tanggal_peminjaman,jangka,catatan,tanggal_pembayaran,bayar) VALUES ('" + anggotaid + "','" + id_admin + "','diproses','"
                        + jmlpeminjaman + "','" + jmlpeminjaman + "','" + tanggal + "','" + jangkaWaktu + "','" + CatatanPemnjaman + "','" + tanggal + "','" + (jmlpeminjaman / 12)
                        + "')";
                boolean status = querySql(query);
                if (status) {
                    popup.sukses("Penamabahan Pengajuan peminjaman Anggota Berhasil");
                } else {
                    popup.gagal("Maaf anda sudah melakukan pinjaman, silahakan melakukan pelunasan peminjaman");
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            popup.error("Maaf anda sudah melakukan pinjaman, silahakan melakukan pelunasan peminjaman");
        }
    }

    @Override
    protected void tambah_peminjaman(String nama, int jmlpeminjaman, Date tanggal, String jangkaWaktu, String administrasi, String jaminan, String CatatanPemnjaman) {
        try {
            koneksi DB = new koneksi();
            con = DB.con();
            System.out.println("masuk sini");
            String queryAnggota = "SELECT * from calon_anggota where Nama= '" + nama + "' AND peminjaman='0'";
            stat = con.createStatement();
            rs = stat.executeQuery(queryAnggota);
            while (rs.next()) {
                System.out.println("ini" + Integer.parseInt(rs.getString("calonAnggota_id")));
                calonanggotaId = Integer.parseInt(rs.getString("calonAnggota_id"));
            }
            stat.close();
            String queryUpdateAnggota = "UPDATE calon_anggota SET peminjaman= '1' WHERE calonAnggota_id= '" + calonanggotaId + "'";
            stat = con.createStatement();
            stat.executeUpdate(queryUpdateAnggota);
            if (jangkaWaktu.equalsIgnoreCase("3 bulan")) {
                String query = "INSERT INTO peminjaman_canggota (Canggota_id,AdminId,status_peminjman, jumlah_pinjaman, sisa_pengembalian, tanggal_pinjaman,jangka,administrasi,jaminan,catatan,tanggal_pembayaran,bayar) VALUES ('" + calonanggotaId + "','" + id_admin + "','diproses','"
                        + jmlpeminjaman + "','" + jmlpeminjaman + "','" + tanggal + "','" + jangkaWaktu + "','" + administrasi + "','" + jaminan + "','" + CatatanPemnjaman + "','" + tanggal + "','" + (jmlpeminjaman / 3) + "')";
                boolean status = querySql(query);
                if (status) {
                    popup.sukses("Penamabahan Pengajuan peminjaman Calon Anggota Berhasil");
                } else {
                    popup.gagal("Maaf anda sudah melakukan pinjaman, silahakan melakukan pelunasan peminjaman");
                }
            } else if (jangkaWaktu.equalsIgnoreCase("12 bulan")) {
                String query = "INSERT INTO peminjaman_canggota (Canggota_id,AdminId,status_peminjman, jumlah_pinjaman, sisa_pengembalian, tanggal_pinjaman,jangka,administrasi,jaminan,catatan,tanggal_pembayaran,bayar) VALUES ('" + calonanggotaId + "','" + id_admin + "','diproses','"
                        + jmlpeminjaman + "','" + jmlpeminjaman + "','" + tanggal + "','" + jangkaWaktu + "','" + administrasi + "','" + jaminan + "','" + CatatanPemnjaman + "','" + tanggal + "','" + (jmlpeminjaman / 12) + "')";
                boolean status = querySql(query);
                if (status) {
                    popup.sukses("Penamabahan Pengajuan peminjaman Calon Anggota Berhasil");
                } else {
                    popup.gagal("Maaf anda sudah melakukan pinjaman, silahakan melakukan pelunasan peminjaman");
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            popup.error("Maaf anda sudah melakukan pinjaman, silahakan melakukan pelunasan peminjaman");
        }
    }
    protected static String catatan_angota;
    static String id_update_anggota;

    protected void edit_pengajuanpinjaman_Anggota(String Nama, int jumlah_Peminjmana, String Jangka, String Status_Peminjaman, String Tipe) {
        try {
            String sql = "SELECT PAnggota_id,Nama,Status_anggota,catatan, tanggal_peminjaman, status_peminjman, sisa_pengembalian, jumlah_pinjaman, noHp, identitas, jangka, Alamat, Aktifitas_Sepeda FROM peminjaman_anggota pa join anggota a on pa.anggota_id = a.anggota_id WHERE a.Nama ='" + Nama + "' "
                    + "AND pa.jumlah_pinjaman ='" + jumlah_Peminjmana + "' AND pa.jangka ='" + Jangka + "' AND pa.status_peminjman ='" + Status_Peminjaman + "' "
                    + "AND pa.tipe ='" + Tipe + "'";
            koneksi DB = new koneksi();
            con = DB.con();
            System.out.println("belum di eksekusi");
            stat = con.createStatement();
            rs = stat.executeQuery(sql);
            System.out.println("sudah d exsekusi");
            while (rs.next()) {
                System.out.println(rs.getString("a.Nama"));
                System.out.println(rs.getString("pa.tanggal_peminjaman"));
                System.out.println(rs.getString("pa.catatan"));
                ReturnNama = rs.getString("a.Nama");
                Returntanggal_peminjaman = rs.getString("pa.tanggal_peminjaman");
                Returnjumlah_pinjaman = rs.getString("pa.jumlah_pinjaman");
                Returnjangka = rs.getString("pa.jangka");
                catatan_angota = rs.getString("pa.catatan");
                id_update_anggota = rs.getString("pa.PAnggota_id");
                System.out.println("id edit = " + id_update_anggota);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getStackTrace());
            popup.gagal("Ada kesalahan silahan periksa pilihan anda");
        }
    }

    protected void return_edit_Anggota(JDateChooser Anggotaupdatetanggalpnjaan, JComboBox angggotaupdatejangka, JTextArea anggotaeditCatatan, JTextField anggotaupdatejml, JComboBox anggotaupdatenama) {
        try {
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(Returntanggal_peminjaman);
            Anggotaupdatetanggalpnjaan.setDate(date);
            angggotaupdatejangka.setSelectedItem(Returnjangka);
            System.out.println(catatan_angota);
            anggotaeditCatatan.append(catatan_angota);
            anggotaupdatejml.setText(Returnjumlah_pinjaman);
            anggotaupdatenama.setSelectedItem(ReturnNama);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    static String catatanCa, administrasi, id_update_canggota;

    protected void edit_pengajuanpinjaman_Canggota(String Nama, int jumlah_Peminjmana, String Jangka, String Status_Peminjaman, String Tipe) {
        try {
            String sql = "SELECT PCanggota,administrasi,catatan,Nama, tanggal_pinjaman, status_peminjman, sisa_pengembalian, jumlah_pinjaman, noHp, identitas, jangka, Alamat, jaminan FROM peminjaman_canggota pc join calon_anggota ca on pc.Canggota_id = ca.calonAnggota_id WHERE ca.Nama ='" + Nama + "' "
                    + "AND pc.jumlah_pinjaman ='" + jumlah_Peminjmana + "' AND pc.jangka ='" + Jangka + "' AND pc.status_peminjman ='" + Status_Peminjaman + "' "
                    + "AND pc.tipe ='" + Tipe + "'";
            koneksi DB = new koneksi();
            con = DB.con();
            System.out.println("belum di eksekusi");
            stat = con.createStatement();
            rs = stat.executeQuery(sql);
            System.out.println("sudah d exsekusi");
            while (rs.next()) {
                System.out.println(rs.getString("ca.Nama"));
                System.out.println(rs.getString("pc.tanggal_pinjaman"));
                Cnama = rs.getString("ca.Nama");
                Ctanggalpeminjaman = rs.getString("pc.tanggal_pinjaman");
                Cpinjaman = rs.getString("pc.jumlah_pinjaman");
                Cangsuran = rs.getString("pc.jangka");
                Cjaminan = rs.getString("pc.jaminan");
                catatanCa = rs.getString("pc.catatan");
                administrasi = rs.getString("pc.administrasi");
                id_update_canggota = rs.getString("PCanggota");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getStackTrace());
            popup.gagal("Ada kesalahan silahan periksa pilihan anda");
        }
    }

    protected void return_edit_canggota(JComboBox Canggotaeditadministrasi, JTextArea Canggotaeditcatatan,
            JTextField Canggotaeditjaminan, JComboBox Canggotaeditjangka,
            JTextField Canggotaeditjml, JComboBox Canggotaupdatenama, JDateChooser Canggotaedittanggal) {

        try {
            Canggotaeditadministrasi.setSelectedItem(administrasi);
            Canggotaeditcatatan.append(catatanCa);
            Canggotaeditjaminan.setText(Cjaminan);
            Canggotaeditjangka.setSelectedItem(Cangsuran);
            Canggotaeditjml.setText(Cpinjaman);
            Canggotaupdatenama.setSelectedItem(Cnama);
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(Ctanggalpeminjaman);
            Canggotaedittanggal.setDate(date);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    protected void update_tambah_peminjaman(String nama, int jmlpeminjaman, Date tanggal, String jangkaWaktu, String CatatanPemnjaman) {
        try {
            koneksi DB = new koneksi();
            con = DB.con();
            System.out.println("masuk sini");
            String queryAnggota = "SELECT * from anggota where Nama= '" + nama + "' AND Status_anggota='Diterima'";
            stat = con.createStatement();
            rs = stat.executeQuery(queryAnggota);
            while (rs.next()) {
                System.out.println("ini" + Integer.parseInt(rs.getString("anggota_id")));
                anggotaid = Integer.parseInt(rs.getString("anggota_id"));
            }
            stat.close();
            String query = "UPDATE peminjaman_anggota SET anggota_id = '" + anggotaid + "',AdminId ='" + id_admin + "', jumlah_pinjaman ='" + jmlpeminjaman
                    + "', tanggal_peminjaman ='" + tanggal + "',jangka='" + jangkaWaktu + "',catatan='" + CatatanPemnjaman + "' where PAnggota_id='" + id_update_anggota + "'";
            boolean status = querySql(query);
            if (status) {
                popup.sukses("Edit Pengajuan peminjaman Anggota Berhasil");
            } else {
                popup.gagal("Maaf Edit Pengajuan peminjaman Anggota gagal, silahkan periksa pilihan anda");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e);
            popup.error("Maaf Edit Pengajuan peminjaman Anggota gagal");
        }
    }

    protected void update_tambah_peminjaman(String nama, int jmlpeminjaman, Date tanggal, String jangkaWaktu, String administrasi, String jaminan, String CatatanPemnjaman) {
        try {
            koneksi DB = new koneksi();
            con = DB.con();
            System.out.println("masuk sini");
            String queryAnggota = "SELECT * from calon_anggota where Nama= '" + nama + "'";
            stat = con.createStatement();
            rs = stat.executeQuery(queryAnggota);
            while (rs.next()) {
                System.out.println("ini" + Integer.parseInt(rs.getString("calonAnggota_id")));
                calonanggotaId = Integer.parseInt(rs.getString("calonAnggota_id"));
            }
            String query = "UPDATE peminjaman_canggota SET Canggota_id ='" + calonanggotaId + "',AdminId ='" + id_admin
                    + "', jumlah_pinjaman='" + jmlpeminjaman + "', tanggal_pinjaman='" + tanggal
                    + "',jangka='" + jangkaWaktu + "',administrasi='" + administrasi + "',jaminan='" + jaminan + "',catatan='" + CatatanPemnjaman + "' where PCanggota='" + id_update_canggota + "'";
            boolean status = querySql(query);
            if (status) {
                popup.sukses("Edit Pengajuan peminjaman Calon Anggota Berhasil");
            } else {
                popup.gagal("Maaf Edit Pengajuan peminjaman Calon Anggota gagal, silahkan periksa pilihan anda");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            popup.error("Maaf Edit Pengajuan peminjaman Calon Anggota gagal");
        }
    }

    protected void hapus_pengajuan_peminjaman(String Nama, int jumlah_Peminjmana, String Jangka, String Status_Peminjaman, String Tipe) {
        try {
            if (Tipe.equalsIgnoreCase("anggota")) {
                String query = "DELETE FROM peminjaman_anggota WHERE jumlah_pinjaman ='" + jumlah_Peminjmana + "' AND jangka ='" + Jangka + "' AND status_peminjman ='" + Status_Peminjaman + "' "
                        + "AND tipe ='" + Tipe + "'";
                boolean status = querySql(query);
                if (status) {
                    popup.sukses("Menghapus pengajuan peminjaman Anggota Berhasil");
                } else {
                    popup.gagal("Maaf menghapus Pengajuan peminjaman Anggota gagal, silahkan periksa pilihan anda");
                }
            } else if (Tipe.equalsIgnoreCase("calon anggota")) {
                String query = "DELETE FROM peminjaman_canggota WHERE jumlah_pinjaman ='" + jumlah_Peminjmana + "' AND jangka ='" + Jangka + "' AND status_peminjman ='" + Status_Peminjaman + "' "
                        + "AND tipe ='" + Tipe + "'";
                boolean status = querySql(query);
                if (status) {
                    popup.sukses("Menghapus pengajuan peminjaman Calon Anggota Berhasil");
                } else {
                    popup.gagal("Maaf menghapus Pengajuan peminjaman Calaon Anggota gagal, silahkan periksa pilihan anda");
                }
            } else {
                popup.gagal("silahkan chek pilihan anda");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            popup.error("maaf ada kesalahan silahkan periksa lagi pilihan anda!!");
        }
    }

    protected void return_edit_anggota(JRadioButton a1, JRadioButton a2, JRadioButton a3, JTextField aaktifitas, JTextField aalamat, JTextField aidentitas, JTextField anama, JTextField anohp) {
        aaktifitas.setText(aktifitas_sepeda);
        aalamat.setText(alamat);
        aidentitas.setText(identitas);
        anama.setText(nama);
        anohp.setText(nohp);

        if (abutton.equalsIgnoreCase("3 Bulan")) {
            a1.setSelected(true);
        } else if (abutton.equalsIgnoreCase("5 Bulan")) {
            a2.setSelected(true);
        } else if (abutton.equalsIgnoreCase("1 taun")) {
            a3.setSelected(true);
        } else {
            popup.gagal("anda tidak memiliki angsurah, silahkan pilih lagi");
        }
    }
    static String abutton, id_edit_anggota;

    protected void edit_anggota(String Nama, String Status_Anggota, String Alamat, String noHp) {
        detail detail = new detail();
        try {
            String sql = "SELECT * FROM anggota WHERE Nama ='" + Nama + "' AND Status_anggota ='" + Status_Anggota + "' AND Alamat ='" + Alamat + "' AND noHp ='" + noHp + "'";
            koneksi DB = new koneksi();
            con = DB.con();
            System.out.println("belum di eksekusi");
            stat = con.createStatement();
            rs = stat.executeQuery(sql);
            System.out.println("sudah d exsekusi");
            while (rs.next()) {
                System.out.println(rs.getString("Nama"));
                System.out.println(rs.getString("noHp"));
                nama = rs.getString("Nama");
                alamat = rs.getString("Alamat");
                aktifitas_sepeda = rs.getString("Aktifitas_Sepeda");
                abutton = rs.getString("Angsuran");
                identitas = rs.getString("identitas");
                nohp = rs.getString("noHp");
                id_edit_anggota = rs.getString("anggota_id");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getStackTrace());
            popup.gagal("Ada kesalahan silahan periksa pilihan anda");
        }
    }

    protected void update_anggota(String Nama, String Alamat, String Aktifitas_Sepeda, String Angsuran, String identitas, String noHp, Date Tanggal) {
        String query = "UPDATE anggota SET AdminId ='" + id_admin + "', Nama ='" + Nama + "', Alamat='" + Alamat + "', Aktifitas_Sepeda='" + Aktifitas_Sepeda + "',Angsuran='"
                + Angsuran + "',identitas='" + identitas + "',noHp='" + noHp + "',Tanggal='" + Tanggal + "' where anggota_id='" + id_edit_anggota + "'";
        status_proses = querySql(query);
        if (status_proses) {
            popup.sukses("Update anggota berhasil");
        } else {
            popup.gagal("Update anggota gagal !!");
        }
    }
    static String id_anggota_nonaktif, id_Pengajuan_nonaktif;

    protected void non_aktif_anggota(String Nama, String Status_Anggota, String Alamat, String noHp) {
        try {
            String sql = "SELECT * FROM peminjaman_anggota pa join anggota a on pa.anggota_id = a.anggota_id WHERE a.Nama ='" + Nama
                    + "' AND a.Status_anggota ='"
                    + Status_Anggota + "' AND a.Alamat ='" + Alamat + "' AND a.noHp ='" + noHp + "'";
            koneksi DB = new koneksi();
            con = DB.con();
            System.out.println("belum di eksekusi");
            stat = con.createStatement();
            rs = stat.executeQuery(sql);

            while (rs.next()) {
                id_anggota_nonaktif = rs.getString("a.anggota_id");
                System.out.println(id_anggota_nonaktif);
                id_Pengajuan_nonaktif = rs.getString("pa.PAnggota_id");
                System.out.println(id_Pengajuan_nonaktif);
            }

            String query2 = "DELETE FROM peminjaman_anggota WHERE PAnggota_id='" + id_Pengajuan_nonaktif + "'";
            querySql(query2);

            String query = "UPDATE anggota SET Status_anggota ='non aktif', peminjaman ='0' WHERE anggota_id='" + id_anggota_nonaktif + "'";
            status_proses = querySql(query);

            if (status_proses) {
                popup.sukses("Update Status anggota berhasil");
            } else {
                popup.gagal("Update Status anggota gagal !!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void aktifkan_anggota(String Nama, String Status_Anggota, String Alamat) {
        try {
            String sql = "SELECT * FROM anggota WHERE Nama ='" + Nama
                    + "' AND Status_anggota ='"
                    + Status_Anggota + "' AND Alamat ='" + Alamat + "'";
            koneksi DB = new koneksi();
            con = DB.con();
            System.out.println("belum di eksekusi");
            stat = con.createStatement();
            rs = stat.executeQuery(sql);

            while (rs.next()) {
                id_anggota_nonaktif = rs.getString("anggota_id");
                System.out.println(id_anggota_nonaktif);
            }

            String query = "UPDATE anggota SET Status_anggota ='Diterima' WHERE anggota_id='" + id_anggota_nonaktif + "'";
            status_proses = querySql(query);

            if (status_proses) {
                popup.sukses("Update Status anggota berhasil");
            } else {
                popup.gagal("Update Status anggota gagal !!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    static int id_edit_user;

    protected void edit_cAnggota(String Nama, int jumlah_Peminjmana, String jaminan, String Status_Peminjaman) {
        try {
            String sql = "SELECT calonAnggota_id,Nama, tanggal_pinjaman, status_peminjman, sisa_pengembalian, jumlah_pinjaman, noHp, identitas, jangka, Alamat, jaminan FROM peminjaman_canggota pc join calon_anggota ca on pc.Canggota_id = ca.calonAnggota_id WHERE ca.Nama ='" + Nama + "' "
                    + "AND pc.jumlah_pinjaman ='" + jumlah_Peminjmana + "' AND pc.jaminan ='" + jaminan + "' AND pc.status_peminjman ='" + Status_Peminjaman + "'";
            koneksi DB = new koneksi();
            con = DB.con();
            System.out.println("belum di eksekusi");
            stat = con.createStatement();
            rs = stat.executeQuery(sql);
            System.out.println("sudah d exsekusi");
            boolean hasil = rs.next();
            if (hasil) {
                System.out.println(rs.getString("ca.Nama"));
                System.out.println(rs.getString("pc.tanggal_pinjaman"));
                Cnama = rs.getString("ca.Nama");
                Cnohp = rs.getString("ca.noHp");
                Cidentitas = rs.getString("ca.identitas");;
                Calamat = rs.getString("ca.Alamat");
                id_edit_user = Integer.parseInt(rs.getString("ca.calonAnggota_id"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            popup.error("maaf ada kesalahan silahkan periksa lagi");
        }
    }

    protected void retun_edit_canggota(JTextField CTNama, JTextField nohp, JTextField identitas,
            JTextField alamat) {
        try {
            nohp.setText(Cnohp);
            identitas.setText(Cidentitas);
            alamat.setText(Calamat);
            CTNama.setText(Cnama);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void update_data_canggota(String nama, String alamat, String nohp, String identitas) {
        try {
            String query = "UPDATE calon_anggota SET Nama ='" + nama + "', Alamat='" + alamat + "', noHp='" + nohp + "',identitas='"
                    + identitas + "' where calonAnggota_id='" + id_edit_user + "'";
            status_proses = querySql(query);
            if (status_proses) {
                popup.sukses("Update Calon anggota berhasil");
            } else {
                popup.gagal("Update Calon anggota gagal !!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    protected void hapus_canggota(String Nama, int jumlah_Peminjmana, String jaminan, String Status_Peminjaman) {
        try {
            String sql = "SELECT calonAnggota_id,Nama, tanggal_pinjaman, status_peminjman, sisa_pengembalian, jumlah_pinjaman, noHp, identitas, jangka, Alamat, jaminan FROM peminjaman_canggota pc join calon_anggota ca on pc.Canggota_id = ca.calonAnggota_id WHERE ca.Nama ='" + Nama + "' "
                    + "AND pc.jumlah_pinjaman ='" + jumlah_Peminjmana + "' AND pc.jaminan ='" + jaminan + "' AND pc.status_peminjman ='" + Status_Peminjaman + "'";
            koneksi DB = new koneksi();
            con = DB.con();
            System.out.println("belum di eksekusi");
            stat = con.createStatement();
            rs = stat.executeQuery(sql);
            System.out.println("sudah d exsekusi");
            boolean hasil = rs.next();
            if (hasil) {
                id_edit_user = Integer.parseInt(rs.getString("ca.calonAnggota_id"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String query = "DELETE FROM calon_anggota where calonAnggota_id= '" + id_edit_user + "'";
        boolean status = querySql(query);
        if (status) {
            popup.sukses("Menghapus Calon Anggota Berhasil");
        } else {
            popup.gagal("Maaf menghapus Calaon Anggota gagal, silahkan periksa pilihan anda");
        }
    }

    static int id_anggota_delete, id_peminjaman_anggota_delete;

    @Override
    protected void Hapus_peminjman(String Nama, int jumlah_Peminjmana, String Jangka, String Status_Peminjaman, String Tipe) {
        try {
            if (Tipe.equalsIgnoreCase("anggota")) {
                String sql = "SELECT anggota_id,PAnggota_id FROM peminjaman_anggota pa join anggota a on pa.anggota_id = a.anggota_id WHERE a.Nama ='" + Nama + "' "
                        + "AND pa.jumlah_pinjaman ='" + jumlah_Peminjmana + "' AND pa.jangka ='" + Jangka + "' AND pa.status_peminjman ='" + Status_Peminjaman + "' "
                        + "AND pa.tipe ='" + Tipe + "'";
                koneksi DB = new koneksi();
                con = DB.con();
                System.out.println("belum di eksekusi");
                stat = con.createStatement();
                rs = stat.executeQuery(sql);
                System.out.println("sudah d exsekusi");
                while (rs.next()) {
                    System.out.println(rs.getString("a.Nama"));
                    System.out.println(rs.getString("pa.tanggal_peminjaman"));
                    id_anggota_delete = Integer.parseInt(rs.getString("a.anggota_id"));
                    id_peminjaman_anggota_delete = Integer.parseInt(rs.getString("pa.PAnggota_id"));
                }

                String query2 = "DELETE FROM peminjaman_anggota WHERE PAnggota_id='" + id_peminjaman_anggota_delete + "'";
                querySql(query2);

                String query = "UPDATE anggota SET peminjaman ='0' WHERE anggota_id='" + id_anggota_delete + "'";
                status_proses = querySql(query);

                if (status_proses) {
                    popup.sukses("Peminjaman berhasil di hapus");
                } else {
                    popup.gagal("Adakesalahan silahkan periksa pilihan anda !!");
                }

            } else if (Tipe.equalsIgnoreCase("calon anggota")) {
                String sql = "SELECT calonAnggota_id,PCanggota FROM peminjaman_canggota pc join calon_anggota ca on pc.Canggota_id = ca.calonAnggota_id WHERE ca.Nama ='" + Nama + "' "
                        + "AND pc.jumlah_pinjaman ='" + jumlah_Peminjmana + "' AND pc.jangka ='" + Jangka + "' AND pc.status_peminjman ='" + Status_Peminjaman + "' "
                        + "AND pc.tipe ='" + Tipe + "'";
                koneksi DB = new koneksi();
                con = DB.con();
                System.out.println("belum di eksekusi");
                stat = con.createStatement();
                rs = stat.executeQuery(sql);
                System.out.println("sudah d exsekusi");
                while (rs.next()) {
                    System.out.println(rs.getString("ca.Nama"));
                    System.out.println(rs.getString("pc.tanggal_pinjaman"));

                    id_anggota_delete = Integer.parseInt(rs.getString("calonAnggota_id"));
                    id_peminjaman_anggota_delete = Integer.parseInt(rs.getString("PCanggota"));
                }

                String query2 = "DELETE FROM peminjaman_canggota WHERE PCanggota='" + id_peminjaman_anggota_delete + "'";
                querySql(query2);

                String query = "UPDATE calon_anggota SET peminjaman ='0' WHERE calonAnggota_id='" + id_anggota_delete + "'";
                status_proses = querySql(query);

                if (status_proses) {
                    popup.sukses("Peminjaman berhasil di hapus");
                } else {
                    popup.gagal("Adakesalahan silahkan periksa pilihan anda !!");
                }
            } else {
                popup.gagal("Ada kesalahan silahan periksa pilihan anda");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getStackTrace());
            popup.gagal("Ada kesalahan silahan periksa pilihan anda");
        }
    }

//    protected String Chekpembayaran(Date tanggal_pembayaran) {
//        convent string to date
//        convert date to string (selisih bulan)
//        untuk menentukan dapat denda aapa tidak 
//                
//                upcate klik bayar, 
//                -jml pembayaran berkurang
//                - tanggal peminjman
//                - menampilkan jml dan sisa pengembalian di table pembayaran
//                -jika jml pinjman 0 makan mendelet peminjaman/menambah status lunas dan tidak
//                -menampilkan kembalian dan pop up jika uang kuranng
//                        
//               
//    }
    static String selisih;

    protected String Chekpembayaran(Date tanggal_pembayaran, String Nama, int jumlah_Peminjmana, String Jangka, String Status_Peminjaman, String Tipe) {
        try {
            tanggal_bayar = tanggal_pembayaran;
            if (Tipe.equalsIgnoreCase("anggota")) {
                String sql = "SELECT Datediff('" + tanggal_pembayaran + "',pa.tanggal_pembayaran) FROM peminjaman_anggota pa join anggota a on pa.anggota_id = a.anggota_id WHERE a.Nama ='" + Nama + "' "
                        + "AND pa.jumlah_pinjaman ='" + jumlah_Peminjmana + "' AND pa.jangka ='" + Jangka + "' AND pa.status_peminjman ='" + Status_Peminjaman + "' "
                        + "AND pa.tipe ='" + Tipe + "'";
                koneksi DB = new koneksi();
                con = DB.con();
                System.out.println("belum di eksekusi");
                stat = con.createStatement();
                rs = stat.executeQuery(sql);
                System.out.println("sudah d exsekusi");
                while (rs.next()) {
                    System.out.println("masuk sini");
//                System.out.println(rs.getString("pa.tanggal_peminjaman"));
//                String tanggal = rs.getString("pa.tanggal_pembayaran)");
                    selisih = rs.getString("Datediff('" + tanggal_pembayaran + "',pa.tanggal_pembayaran)");
                    System.out.println(selisih);
                }
            } else if (Tipe.equalsIgnoreCase("calon anggota")) {
                String sql = "SELECT Datediff('" + tanggal_pembayaran + "',pc.tanggal_pembayaran) FROM peminjaman_canggota pc join calon_anggota ca on pc.Canggota_id = ca.calonAnggota_id WHERE ca.Nama ='" + Nama + "' "
                        + "AND pc.jumlah_pinjaman ='" + jumlah_Peminjmana + "' AND pc.jangka ='" + Jangka + "' AND pc.status_peminjman ='" + Status_Peminjaman + "' "
                        + "AND pc.tipe ='" + Tipe + "'";
                koneksi DB = new koneksi();
                con = DB.con();
                System.out.println("belum di eksekusi");
                stat = con.createStatement();
                rs = stat.executeQuery(sql);
                System.out.println("sudah d exsekusi");
                while (rs.next()) {
                    System.out.println("masuk sini");
//                System.out.println(rs.getString("pa.tanggal_peminjaman"));
//                String tanggal = rs.getString("pa.tanggal_pembayaran)");
                    selisih = rs.getString("Datediff('" + tanggal_pembayaran + "',pc.tanggal_pembayaran)");
                    System.out.println(selisih);
                }
            }
            return selisih;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    static int sisa_pengembalian, bayar_bulan;
    static String sisaString;

    protected void pembayaran_angsuran(int selisih, String Nama, int jumlah_Peminjmana,
            String Jangka, String Status_Peminjaman, String Tipe, JLabel Bayardenda, JLabel Bayarjml_pinjam,
            JLabel Bayarjml_pinjam2, JLabel Bayartotal, JLabel bayartelat) {
        try {
            tipe_pembayar = Tipe;
            nama_pembayar = Nama;
            jangka_pembayar = Jangka;
            status_pembayar = Status_Peminjaman;
            jlm_harus_dibayar = jumlah_Peminjmana;
            if (Tipe.equalsIgnoreCase("anggota")) {
                if (Jangka.equalsIgnoreCase("3 bulan")) {
                    if (selisih <= 30) {
                        String sql = "SELECT * FROM peminjaman_anggota pa join anggota a on pa.anggota_id = a.anggota_id WHERE a.Nama ='" + Nama + "' "
                                + "AND pa.jumlah_pinjaman ='" + jumlah_Peminjmana + "' AND pa.jangka ='" + Jangka + "' AND pa.status_peminjman ='" + Status_Peminjaman + "' "
                                + "AND pa.tipe ='" + Tipe + "'";
                        koneksi DB = new koneksi();
                        con = DB.con();
                        System.out.println("belum di eksekusi");
                        stat = con.createStatement();
                        rs = stat.executeQuery(sql);
                        System.out.println("sudah d exsekusi");
                        while (rs.next()) {
                            System.out.println(rs.getString("sisa_pengembalian"));
                            sisaString = rs.getString("sisa_pengembalian");
                            sisa_pengembalian = Integer.parseInt(rs.getString("sisa_pengembalian"));
                            bayar_bulan = Integer.parseInt(rs.getString("bayar"));
                        }
//                        int bayar = sisa_pengembalian / 3;
                        Bayarjml_pinjam.setText(sisaString);
                        Bayarjml_pinjam2.setText( String.valueOf(bayar_bulan));
                        Bayardenda.setText("0");
                        Bayartotal.setText( String.valueOf(bayar_bulan));

                    } else if (selisih > 30) {
                        String sql = "SELECT * FROM peminjaman_anggota pa join anggota a on pa.anggota_id = a.anggota_id WHERE a.Nama ='" + Nama + "' "
                                + "AND pa.jumlah_pinjaman ='" + jumlah_Peminjmana + "' AND pa.jangka ='" + Jangka + "' AND pa.status_peminjman ='" + Status_Peminjaman + "' "
                                + "AND pa.tipe ='" + Tipe + "'";
                        koneksi DB = new koneksi();
                        con = DB.con();
                        System.out.println("belum di eksekusi");
                        stat = con.createStatement();
                        rs = stat.executeQuery(sql);
                        System.out.println("sudah d exsekusi");
                        while (rs.next()) {
                            System.out.println(rs.getString("sisa_pengembalian"));
                            sisaString = rs.getString("sisa_pengembalian");
                            sisa_pengembalian = Integer.parseInt(rs.getString("sisa_pengembalian"));
                            bayar_bulan = Integer.parseInt(rs.getString("bayar"));
                        }
//                        int bayar = sisa_pengembalian / 3;
                        Bayarjml_pinjam.setText( sisaString);
                        Bayarjml_pinjam2.setText( String.valueOf(bayar_bulan));
                        hitung_orang = (int) (0.005 * (selisih - 30) * bayar_bulan);
                        Bayardenda.setText( String.valueOf(hitung_orang));
                        int total = hitung_orang + bayar_bulan;
                        Bayartotal.setText( String.valueOf(total));
                        bayartelat.setText((selisih - 30) + " HARI");
                    }
                } else if (Jangka.equalsIgnoreCase("12 bulan")) {
                    if (selisih <= 30) {
                        String sql = "SELECT * FROM peminjaman_anggota pa join anggota a on pa.anggota_id = a.anggota_id WHERE a.Nama ='" + Nama + "' "
                                + "AND pa.jumlah_pinjaman ='" + jumlah_Peminjmana + "' AND pa.jangka ='" + Jangka + "' AND pa.status_peminjman ='" + Status_Peminjaman + "' "
                                + "AND pa.tipe ='" + Tipe + "'";
                        koneksi DB = new koneksi();
                        con = DB.con();
                        System.out.println("belum di eksekusi");
                        stat = con.createStatement();
                        rs = stat.executeQuery(sql);
                        System.out.println("sudah d exsekusi");
                        while (rs.next()) {
                            System.out.println(rs.getString("sisa_pengembalian"));
                            sisaString = rs.getString("sisa_pengembalian");
                            sisa_pengembalian = Integer.parseInt(rs.getString("sisa_pengembalian"));
                            bayar_bulan = Integer.parseInt(rs.getString("bayar"));
                        }
//                        int bayar = sisa_pengembalian / 3;
                        Bayarjml_pinjam.setText( sisaString);
                        Bayarjml_pinjam2.setText( String.valueOf(bayar_bulan));
                        Bayardenda.setText("0");
                        Bayartotal.setText( String.valueOf(bayar_bulan));

                    } else if (selisih > 30) {
                        String sql = "SELECT * FROM peminjaman_anggota pa join anggota a on pa.anggota_id = a.anggota_id WHERE a.Nama ='" + Nama + "' "
                                + "AND pa.jumlah_pinjaman ='" + jumlah_Peminjmana + "' AND pa.jangka ='" + Jangka + "' AND pa.status_peminjman ='" + Status_Peminjaman + "' "
                                + "AND pa.tipe ='" + Tipe + "'";
                        koneksi DB = new koneksi();
                        con = DB.con();
                        System.out.println("belum di eksekusi");
                        stat = con.createStatement();
                        rs = stat.executeQuery(sql);
                        System.out.println("sudah d exsekusi");
                        while (rs.next()) {
                            System.out.println(rs.getString("sisa_pengembalian"));
                            sisaString = rs.getString("sisa_pengembalian");
                            sisa_pengembalian = Integer.parseInt(rs.getString("sisa_pengembalian"));
                            bayar_bulan = Integer.parseInt(rs.getString("bayar"));
                        }
//                        int bayar = sisa_pengembalian / 3;
                        Bayarjml_pinjam.setText( sisaString);
                        Bayarjml_pinjam2.setText( String.valueOf(bayar_bulan));
                        hitung_orang = (int) (0.005 * (selisih - 30) * bayar_bulan);
                        Bayardenda.setText( String.valueOf(hitung_orang));
                        int total = hitung_orang + bayar_bulan;
                        Bayartotal.setText( String.valueOf(total));
                        bayartelat.setText((selisih - 30) + " HARI");
                    }
                } else {
                    popup.gagal("ada kesalahan silahkan periksa pilihan anda");
                }
            } else if (Tipe.equalsIgnoreCase("calon anggota")) {
                if (Jangka.equalsIgnoreCase("3 bulan")) {
                    if (selisih <= 30) {
                        String sql = "SELECT * FROM peminjaman_canggota pc join calon_anggota ca on pc.Canggota_id = ca.calonAnggota_id WHERE ca.Nama ='" + Nama + "' "
                                + "AND pc.jumlah_pinjaman ='" + jumlah_Peminjmana + "' AND pc.jangka ='" + Jangka + "' AND pc.status_peminjman ='" + Status_Peminjaman + "' "
                                + "AND pc.tipe ='" + Tipe + "'";
                        koneksi DB = new koneksi();
                        con = DB.con();
                        System.out.println("belum di eksekusi");
                        stat = con.createStatement();
                        rs = stat.executeQuery(sql);
                        System.out.println("sudah d exsekusi");
                        while (rs.next()) {
                            System.out.println(rs.getString("sisa_pengembalian"));
                            sisaString = rs.getString("sisa_pengembalian");
                            sisa_pengembalian = Integer.parseInt(rs.getString("sisa_pengembalian"));
                            bayar_bulan = Integer.parseInt(rs.getString("bayar"));
                        }
//                        int bayar = sisa_pengembalian / 3;
                        Bayarjml_pinjam.setText( sisaString);
                        Bayarjml_pinjam2.setText( String.valueOf(bayar_bulan));
                        Bayardenda.setText("0");
                        Bayartotal.setText( String.valueOf(bayar_bulan));

                    } else if (selisih > 30) {
                        String sql = "SELECT *  FROM peminjaman_canggota pc join calon_anggota ca on pc.Canggota_id = ca.calonAnggota_id WHERE ca.Nama ='" + Nama + "' "
                                + "AND pc.jumlah_pinjaman ='" + jumlah_Peminjmana + "' AND pc.jangka ='" + Jangka + "' AND pc.status_peminjman ='" + Status_Peminjaman + "' "
                                + "AND pc.tipe ='" + Tipe + "'";
                        koneksi DB = new koneksi();
                        con = DB.con();
                        System.out.println("belum di eksekusi");
                        stat = con.createStatement();
                        rs = stat.executeQuery(sql);
                        System.out.println("sudah d exsekusi");
                        while (rs.next()) {
                            System.out.println(rs.getString("sisa_pengembalian"));
                            sisaString = rs.getString("sisa_pengembalian");
                            sisa_pengembalian = Integer.parseInt(rs.getString("sisa_pengembalian"));
                            bayar_bulan = Integer.parseInt(rs.getString("bayar"));
                        }
//                        int bayar = sisa_pengembalian / 3;
                        Bayarjml_pinjam.setText( sisaString);
                        Bayarjml_pinjam2.setText( String.valueOf(bayar_bulan));
                        hitung_orang = (int) (0.005 * (selisih - 30) * bayar_bulan);
                        Bayardenda.setText( String.valueOf(hitung_orang));
                        int total = hitung_orang + bayar_bulan;
                        Bayartotal.setText( String.valueOf(total));
                        bayartelat.setText((selisih - 30) + " HARI");
                    }
                } else if (Jangka.equalsIgnoreCase("12 bulan")) {
                    if (selisih <= 30) {
                        String sql = "SELECT * FROM peminjaman_canggota pc join calon_anggota ca on pc.Canggota_id = ca.calonAnggota_id WHERE ca.Nama ='" + Nama + "' "
                                + "AND pc.jumlah_pinjaman ='" + jumlah_Peminjmana + "' AND pc.jangka ='" + Jangka + "' AND pc.status_peminjman ='" + Status_Peminjaman + "' "
                                + "AND pc.tipe ='" + Tipe + "'";
                        koneksi DB = new koneksi();
                        con = DB.con();
                        System.out.println("belum di eksekusi");
                        stat = con.createStatement();
                        rs = stat.executeQuery(sql);
                        System.out.println("sudah d exsekusi");
                        while (rs.next()) {
                            System.out.println(rs.getString("sisa_pengembalian"));
                            sisaString = rs.getString("sisa_pengembalian");
                            sisa_pengembalian = Integer.parseInt(rs.getString("sisa_pengembalian"));
                            bayar_bulan = Integer.parseInt(rs.getString("bayar"));
                        }
//                        int bayar = sisa_pengembalian / 3;
                        Bayarjml_pinjam.setText( sisaString);
                        Bayarjml_pinjam2.setText( String.valueOf(bayar_bulan));
                        Bayardenda.setText("0");
                        Bayartotal.setText( String.valueOf(bayar_bulan));

                    } else if (selisih > 30) {
                        String sql = "SELECT *  FROM peminjaman_canggota pc join calon_anggota ca on pc.Canggota_id = ca.calonAnggota_id WHERE ca.Nama ='" + Nama + "' "
                                + "AND pc.jumlah_pinjaman ='" + jumlah_Peminjmana + "' AND pc.jangka ='" + Jangka + "' AND pc.status_peminjman ='" + Status_Peminjaman + "' "
                                + "AND pc.tipe ='" + Tipe + "'";
                        koneksi DB = new koneksi();
                        con = DB.con();
                        System.out.println("belum di eksekusi");
                        stat = con.createStatement();
                        rs = stat.executeQuery(sql);
                        System.out.println("sudah d exsekusi");
                        while (rs.next()) {
                            System.out.println(rs.getString("sisa_pengembalian"));
                            sisaString = rs.getString("sisa_pengembalian");
                            sisa_pengembalian = Integer.parseInt(rs.getString("sisa_pengembalian"));
                            bayar_bulan = Integer.parseInt(rs.getString("bayar"));
                        }
//                        int bayar = sisa_pengembalian / 3;
                        Bayarjml_pinjam.setText( sisaString);
                        Bayarjml_pinjam2.setText( String.valueOf(bayar_bulan));
                        hitung_orang = (int) (0.005 * (selisih - 30) * bayar_bulan);
                        Bayardenda.setText( String.valueOf(hitung_orang));
                        int total = hitung_orang + bayar_bulan;
                        Bayartotal.setText( String.valueOf(total));
                        bayartelat.setText((selisih - 30) + " HARI");
                    }
                } else {
                    popup.gagal("ada kesalahan silahkan periksa pilihan anda");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static int sisa;
    static String tipe_pembayar, nama_pembayar, jangka_pembayar, status_pembayar;
    static int jlm_harus_dibayar, id_pembayar, sisa_pengembalian_uang, 
            id_orang, hitung_orang = 0, denda_sebelum, total_denda, kembalian, sisa_bayar;
    static Date tanggal_bayar;

    protected void bayar_uang(int denda, int total, int pembayaran) {
        try {
            if (pembayaran < total) {
                popup.gagal("maaf uang yang anda bayarkan kurang, jumlah yang harus di bayarkan adalah Rp." + total);
            } else if (pembayaran > total) {
                kembalian = pembayaran - total;
                if (tipe_pembayar.equalsIgnoreCase("anggota")) {
                    sisa = pembayaran - total;

                    String sql = "SELECT * FROM peminjaman_anggota pa join anggota a on pa.anggota_id = a.anggota_id WHERE a.Nama ='" + nama_pembayar + "' "
                            + "AND pa.jumlah_pinjaman ='" + jlm_harus_dibayar + "' AND pa.jangka ='" + jangka_pembayar + "' AND pa.status_peminjman ='" + status_pembayar + "' "
                            + "AND pa.tipe ='" + tipe_pembayar + "'";
                    koneksi DB = new koneksi();
                    con = DB.con();
                    System.out.println("belum di eksekusi");
                    stat = con.createStatement();
                    rs = stat.executeQuery(sql);

                    while (rs.next()) {
                        id_pembayar = Integer.parseInt(rs.getString("PAnggota_id"));
                        sisa_pengembalian_uang = Integer.parseInt(rs.getString("sisa_pengembalian"));
                        id_orang = Integer.parseInt(rs.getString("anggota_id"));
                        denda_sebelum = Integer.parseInt(rs.getString("denda"));
                    }
//                    int bayar_uatang = jlm_harus_dibayar - angsuran;
                    sisa_bayar = sisa_pengembalian_uang - bayar_bulan;

                    if (sisa_bayar == 0) {
                        total_denda = denda_sebelum + hitung_orang;
                        if (hitung_orang > 0) {

                            String query2 = "UPDATE anggota SET peminjaman ='0' WHERE anggota_id='" + id_orang + "'";
                            querySql(query2);

                            String query = "UPDATE peminjaman_anggota SET sisa_pengembalian ='" + sisa_bayar + "', tanggal_pembayaran= '" + tanggal_bayar
                                    + "', denda ='" + total_denda + "', status_uang='lunas'  WHERE PAnggota_id='" + id_pembayar + "'";
                            status_proses = querySql(query);
                        } else {
                            String query2 = "UPDATE anggota SET peminjaman ='0' WHERE anggota_id='" + id_orang + "'";
                            querySql(query2);
                            String query = "UPDATE peminjaman_anggota SET sisa_pengembalian ='" + sisa_bayar + "', tanggal_pembayaran= '" + tanggal_bayar
                                    + "', status_uang='lunas'  WHERE PAnggota_id='" + id_pembayar + "'";
                            status_proses = querySql(query);
                        }
                    } else {
                        total_denda = denda_sebelum + hitung_orang;
                        if (hitung_orang > 0) {
                            String query = "UPDATE peminjaman_anggota SET sisa_pengembalian ='" + sisa_bayar + "', tanggal_pembayaran= '" + tanggal_bayar
                                    + "', denda ='" + total_denda + "'  WHERE PAnggota_id='" + id_pembayar + "'";
                            status_proses = querySql(query);
                        } else {
                            String query = "UPDATE peminjaman_anggota SET sisa_pengembalian ='" + sisa_bayar + "', tanggal_pembayaran= '" + tanggal_bayar
                                    + "'  WHERE PAnggota_id='" + id_pembayar + "'";
                            status_proses = querySql(query);
                        }
                    }

                    if (status_proses) {
                        popup.sukses("Pembayaran berhasil");
                    } else {
                        popup.gagal("Ada kesalahan silahkan periksa inputan anda !!");
                    }
                } else if (tipe_pembayar.equalsIgnoreCase("calon anggota")) {
                    sisa = pembayaran - total;
                    String sql = "SELECT *  FROM peminjaman_canggota pc join calon_anggota ca on pc.Canggota_id = ca.calonAnggota_id WHERE ca.Nama ='" + nama_pembayar + "' "
                            + "AND pc.jumlah_pinjaman ='" + jlm_harus_dibayar + "' AND pc.jangka ='" + jangka_pembayar + "' AND pc.status_peminjman ='" + status_pembayar + "' "
                            + "AND pc.tipe ='" + tipe_pembayar + "'";
                    koneksi DB = new koneksi();
                    con = DB.con();
                    System.out.println("belum di eksekusi");
                    stat = con.createStatement();
                    rs = stat.executeQuery(sql);

                    while (rs.next()) {
                        id_pembayar = Integer.parseInt(rs.getString("PCanggota"));
                        sisa_pengembalian_uang = Integer.parseInt(rs.getString("sisa_pengembalian"));
                        id_orang = Integer.parseInt(rs.getString("calonAnggota_id"));
                        denda_sebelum = Integer.parseInt(rs.getString("denda"));
                    }
//                    int bayar_uatang = jlm_harus_dibayar - angsuran;
                    sisa_bayar = sisa_pengembalian_uang - bayar_bulan;

                    if (sisa_bayar == 0) {
                        total_denda = denda_sebelum + hitung_orang;
                        if (hitung_orang > 0) {

                            String query2 = "UPDATE calon_anggota SET peminjaman ='0' WHERE calonAnggota_id='" + id_orang + "'";
                            querySql(query2);

                            String query = "UPDATE peminjaman_canggota SET sisa_pengembalian ='" + sisa_bayar + "', tanggal_pembayaran= '" + tanggal_bayar
                                    + "', denda ='" + total_denda + "', status_uang='lunas'  WHERE PCanggota='" + id_pembayar + "'";
                            status_proses = querySql(query);
                        } else {
                            String query2 = "UPDATE calon_anggota SET peminjaman ='0' WHERE calonAnggota_id='" + id_orang + "'";
                            querySql(query2);
                            String query = "UPDATE peminjaman_canggota SET sisa_pengembalian ='" + sisa_bayar + "', tanggal_pembayaran= '" + tanggal_bayar
                                    + "', status_uang='lunas'  WHERE PCanggota='" + id_pembayar + "'";
                            status_proses = querySql(query);
                        }
                    } else {
                        total_denda = denda_sebelum + hitung_orang;
                        if (hitung_orang > 0) {
                            String query = "UPDATE peminjaman_canggota SET sisa_pengembalian ='" + sisa_bayar + "', tanggal_pembayaran= '" + tanggal_bayar
                                    + "', denda ='" + total_denda + "'  WHERE PCanggota='" + id_pembayar + "'";
                            status_proses = querySql(query);
                        } else {
                            String query = "UPDATE peminjaman_canggota SET sisa_pengembalian ='" + sisa_bayar + "', tanggal_pembayaran= '" + tanggal_bayar
                                    + "'  WHERE PCanggota='" + id_pembayar + "'";
                            status_proses = querySql(query);
                        }
                    }

                    if (status_proses) {
                        popup.sukses("Pembayaran berhasil");
                    } else {
                        popup.gagal("Ada kesalahan silahkan periksa inputan anda !!");
                    }
                }
            } else if (pembayaran == total) {
                kembalian = 0;
                if (tipe_pembayar.equalsIgnoreCase("anggota")) {
                    sisa = pembayaran - total;

                    String sql = "SELECT * FROM peminjaman_anggota pa join anggota a on pa.anggota_id = a.anggota_id WHERE a.Nama ='" + nama_pembayar + "' "
                            + "AND pa.jumlah_pinjaman ='" + jlm_harus_dibayar + "' AND pa.jangka ='" + jangka_pembayar + "' AND pa.status_peminjman ='" + status_pembayar + "' "
                            + "AND pa.tipe ='" + tipe_pembayar + "'";
                    koneksi DB = new koneksi();
                    con = DB.con();
                    System.out.println("belum di eksekusi");
                    stat = con.createStatement();
                    rs = stat.executeQuery(sql);

                    while (rs.next()) {
                        id_pembayar = Integer.parseInt(rs.getString("PAnggota_id"));
                        sisa_pengembalian_uang = Integer.parseInt(rs.getString("sisa_pengembalian"));
                        id_orang = Integer.parseInt(rs.getString("anggota_id"));
                        denda_sebelum = Integer.parseInt(rs.getString("denda"));
                    }
//                    int bayar_uatang = jlm_harus_dibayar - angsuran;
                    sisa_bayar = sisa_pengembalian_uang - bayar_bulan;

                    if (sisa_bayar == 0) {
                        total_denda = denda_sebelum + hitung_orang;
                        if (hitung_orang > 0) {

                            String query2 = "UPDATE anggota SET peminjaman ='0' WHERE anggota_id='" + id_orang + "'";
                            querySql(query2);

                            String query = "UPDATE peminjaman_anggota SET sisa_pengembalian ='" + sisa_bayar + "', tanggal_pembayaran= '" + tanggal_bayar
                                    + "', denda ='" + total_denda + "', status_uang='lunas'  WHERE PAnggota_id='" + id_pembayar + "'";
                            status_proses = querySql(query);
                        } else {
                            String query2 = "UPDATE anggota SET peminjaman ='0' WHERE anggota_id='" + id_orang + "'";
                            querySql(query2);
                            String query = "UPDATE peminjaman_anggota SET sisa_pengembalian ='" + sisa_bayar + "', tanggal_pembayaran= '" + tanggal_bayar
                                    + "', status_uang='lunas'  WHERE PAnggota_id='" + id_pembayar + "'";
                            status_proses = querySql(query);
                        }
                    } else {
                        total_denda = denda_sebelum + hitung_orang;
                        if (hitung_orang > 0) {
                            String query = "UPDATE peminjaman_anggota SET sisa_pengembalian ='" + sisa_bayar + "', tanggal_pembayaran= '" + tanggal_bayar
                                    + "', denda ='" + total_denda + "'  WHERE PAnggota_id='" + id_pembayar + "'";
                            status_proses = querySql(query);
                        } else {
                            String query = "UPDATE peminjaman_anggota SET sisa_pengembalian ='" + sisa_bayar + "', tanggal_pembayaran= '" + tanggal_bayar
                                    + "'  WHERE PAnggota_id='" + id_pembayar + "'";
                            status_proses = querySql(query);
                        }
                    }

                    if (status_proses) {
                        popup.sukses("Pembayaran berhasil");
                    } else {
                        popup.gagal("Ada kesalahan silahkan periksa inputan anda !!");
                    }
                } else if (tipe_pembayar.equalsIgnoreCase("calon anggota")) {
                    sisa = pembayaran - total;
                    String sql = "SELECT *  FROM peminjaman_canggota pc join calon_anggota ca on pc.Canggota_id = ca.calonAnggota_id WHERE ca.Nama ='" + nama_pembayar + "' "
                            + "AND pc.jumlah_pinjaman ='" + jlm_harus_dibayar + "' AND pc.jangka ='" + jangka_pembayar + "' AND pc.status_peminjman ='" + status_pembayar + "' "
                            + "AND pc.tipe ='" + tipe_pembayar + "'";
                    koneksi DB = new koneksi();
                    con = DB.con();
                    System.out.println("belum di eksekusi");
                    stat = con.createStatement();
                    rs = stat.executeQuery(sql);

                    while (rs.next()) {
                        id_pembayar = Integer.parseInt(rs.getString("PCanggota"));
                        sisa_pengembalian_uang = Integer.parseInt(rs.getString("sisa_pengembalian"));
                        id_orang = Integer.parseInt(rs.getString("calonAnggota_id"));
                        denda_sebelum = Integer.parseInt(rs.getString("denda"));
                    }
//                    int bayar_uatang = jlm_harus_dibayar - angsuran;
                    sisa_bayar = sisa_pengembalian_uang - bayar_bulan;

                    if (sisa_bayar == 0) {
                        total_denda = denda_sebelum + hitung_orang;
                        if (hitung_orang > 0) {

                            String query2 = "UPDATE calon_anggota SET peminjaman ='0' WHERE calonAnggota_id='" + id_orang + "'";
                            querySql(query2);

                            String query = "UPDATE peminjaman_canggota SET sisa_pengembalian ='" + sisa_bayar + "', tanggal_pembayaran= '" + tanggal_bayar
                                    + "', denda ='" + total_denda + "', status_uang='lunas'  WHERE PCanggota='" + id_pembayar + "'";
                            status_proses = querySql(query);
                        } else {
                            String query2 = "UPDATE calon_anggota SET peminjaman ='0' WHERE calonAnggota_id='" + id_orang + "'";
                            querySql(query2);
                            String query = "UPDATE peminjaman_canggota SET sisa_pengembalian ='" + sisa_bayar + "', tanggal_pembayaran= '" + tanggal_bayar
                                    + "', status_uang='lunas'  WHERE PCanggota='" + id_pembayar + "'";
                            status_proses = querySql(query);
                        }
                    } else {
                        total_denda = denda_sebelum + hitung_orang;
                        if (hitung_orang > 0) {
                            String query = "UPDATE peminjaman_canggota SET sisa_pengembalian ='" + sisa_bayar + "', tanggal_pembayaran= '" + tanggal_bayar
                                    + "', denda ='" + total_denda + "'  WHERE PCanggota='" + id_pembayar + "'";
                            status_proses = querySql(query);
                        } else {
                            String query = "UPDATE peminjaman_canggota SET sisa_pengembalian ='" + sisa_bayar + "', tanggal_pembayaran= '" + tanggal_bayar
                                    + "'  WHERE PCanggota='" + id_pembayar + "'";
                            status_proses = querySql(query);
                        }
                    }

                    if (status_proses) {
                        popup.sukses("Pembayaran berhasil");
                    } else {
                        popup.gagal("Ada kesalahan silahkan periksa inputan anda !!");
                    }
                }
            } else {
                popup.error("ada kesalahan silahkan periksa inputan anda");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            popup.error("ada kesalahan silahkan periksa inputan anda");
        }
    }
    
    protected void return_kembalian(JLabel kembalian,JLabel sisa_bayar,JLabel tanggal_bayar,JLabel namane){
        System.out.println(this.kembalian);
        kembalian.setText(String.valueOf(this.kembalian));
        sisa_bayar.setText(String.valueOf(this.sisa_bayar));
        tanggal_bayar.setText(this.tanggal_bayar.toString());
        namane.setText(this.nama_pembayar);
    }

}
