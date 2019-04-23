/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import View.koneksi;
import View.popup;
import java.sql.Date;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import View.detail;
import javax.swing.JTextField;

/**
 *
 * @author coeng
 */
public class users {

    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    protected DefaultTableModel tabelAnggota;
    popup popup = new popup();

    public static int id_admin;
    Date tanggal, tanggal_peminjaman, tanggal_pembayaran;
    protected String nama, password, role, alamat, aktifitas_sepeda, angsuran, nohp, status_anggota, identitas, status_calonanggota, status_peminjaman, jangka, catatan, administrasi, jaminan;
    protected int adminid, anggotaid, calonanggotaId, pembayaranid, PAnggotaid, PCanggota, jml_pembayaran, pembayaranke, jumlah_pinjaman,
            sisa_pengembalian;

    protected String user, pass;

    public String login(String username, String password) {
        this.user = username;
        this.pass = password;
        if (user.isEmpty() && pass.isEmpty()) {
            popup.gagal("username & password anda kosong");
        } else if (pass.isEmpty()) {
            popup.gagal("password anda kosong");
        } else if (user.isEmpty()) {
            popup.gagal("username anda kosong");
        } else if (user.trim().equalsIgnoreCase("")) {
            popup.gagal("username anda kosong");
        } else {
            System.out.println("masuk model login");
            System.out.println(user + " " + pass);
            koneksi DB = new koneksi();
            con = DB.con();
            sql = "SELECT * FROM admin WHERE username='" + user + "' AND password='" + pass + "'";
            try {
                stat = con.createStatement();
                rs = stat.executeQuery(sql);
                while (rs.next()) {
                    role = rs.getString("Role");
                    System.out.println(role);
                    id_admin = Integer.parseInt(rs.getString("AdminId"));
                    return role;
                }

            } catch (Exception e) {
                popup.error("ada kesalahan");
                System.out.println(e.getMessage() + "login");
            }
        }

        return null;
    }
    boolean status_proses;

    protected void tambah_anggota(String Nama, String Alamat, String Aktifitas_Sepeda,
            String Angsuran, String Status_anggota, String identitas, String noHp, Date Tanggal) {
        popup.sukses("menambahkan anggota");
    }

    protected void tambah_anggota(String Nama, String Alamat, Date Tanggal, String identitas, String noHp, String Status_Canggota) {
        popup.sukses("menambahkan Calon anggota");
    }

    protected void approveanggota(JTable aproveanggota) {
        popup.sukses("menampilkan anggota");
    }

    protected void persetujuanAnggota(String status_sekarang, String setatus_sebelum, String nama) {
        popup.sukses("Persetujuan anggota");
    }

    protected void tampilkanAnggota(JTable daftarAnggota) {
        Object[] rows = {"Nama ", "Status Anggota", "Alamat", "No Hp"};
        tabelAnggota = new DefaultTableModel(null, rows);
        daftarAnggota.setModel(tabelAnggota);
        koneksi DB = new koneksi();
        con = DB.con();

        try {
//            String sql = "SELECT * FROM anggota Where Status_anggota= 'Diterima'";
            String sql = "SELECT * FROM anggota order by Status_anggota";
            stat = con.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                System.out.println("anggota-----");
                System.out.println(rs.getString("Nama"));
                String Nama = rs.getString("Nama");
                String Status_Anggota = rs.getString("Status_anggota");
                String Alamat = rs.getString("Alamat");
                String nohp = rs.getString("noHp");
                String[] tampil = {Nama, Status_Anggota, Alamat, nohp};
                tabelAnggota.addRow(tampil);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("1");
        }
    }

    protected void dataanggotapinjaman(JComboBox NamaAnggotaPeminjmaman) {
        koneksi DB = new koneksi();
        con = DB.con();
        try {
            String query = "SELECT * FROM anggota where Status_anggota= 'Diterima' AND peminjaman = '0' order by Nama";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                NamaAnggotaPeminjmaman.addItem(rs.getString("Nama"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void dataanggotapinjaman_edit(JComboBox NamaAnggotaPeminjmaman) {
        koneksi DB = new koneksi();
        con = DB.con();
        try {
            String query = "SELECT * FROM anggota where Status_anggota= 'Diterima' order by Nama";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                NamaAnggotaPeminjmaman.addItem(rs.getString("Nama"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void tambah_peminjaman(String nama, int jmlpeminjaman, Date tanggal, String jangkaWaktu, String CatatanPemnjaman) {

    }

    protected void tambah_peminjaman(String nama, int jmlpeminjaman, Date tanggal, String jangkaWaktu, String administrasi, String jaminan, String CatatanPemnjaman) {

    }

    protected void tampilkanPengajuanpeminjaman(JTable pengajuanPeminjaman) {
        Object[] rows = {"Nama", "jumlah Peminjmana", "Jangka Waktu", "Status Peminjaman", "Tipe"};
        tabelAnggota = new DefaultTableModel(null, rows);
        pengajuanPeminjaman.setModel(tabelAnggota);
        koneksi DB = new koneksi();
        con = DB.con();

        try {
//            String sql = "SELECT * FROM anggota Where Status_anggota= 'Diterima'";
            String sql = "SELECT Nama,jumlah_pinjaman,jangka,status_peminjman,tipe FROM peminjaman_anggota pa join anggota a on pa.anggota_id = a.anggota_id where status_peminjman='diproses'"
                    + " union all SELECT Nama,jumlah_pinjaman,jangka,status_peminjman,tipe FROM peminjaman_canggota pc join calon_anggota ca on pc.Canggota_id = ca.calonAnggota_id where status_peminjman='diproses'";
            stat = con.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                System.out.println("anggota-----");
//                System.out.println(rs.getString("Nama"));
                String Nama = rs.getString("nama");
                String jml_peminjaman = rs.getString("jumlah_pinjaman");
                String jangka = rs.getString("jangka");
                String status_peminjaman = rs.getString("status_peminjman");
                String tipe = rs.getString("tipe");
                String[] tampil = {Nama, jml_peminjaman, jangka, status_peminjaman, tipe};
                tabelAnggota.addRow(tampil);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("1");
            popup.error("tidak bisa menmapilkan ada kesalahan !!");
        }
    }

    protected void tampilkanCAnggota(JTable tabcanggota) {
        Object[] rows = {"Nama ", "jumlah Peminjmana", "jaminan", "Status Peminjaman"};
        tabelAnggota = new DefaultTableModel(null, rows);
        tabcanggota.setModel(tabelAnggota);
        koneksi DB = new koneksi();
        con = DB.con();

        try {
//            String sql = "SELECT * FROM anggota Where Status_anggota= 'Diterima'";
            String sql = "SELECT * FROM peminjaman_canggota pc join calon_anggota ca on pc.Canggota_id = ca.calonAnggota_id order by Nama";
            stat = con.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                System.out.println("anggota-----");
                System.out.println(rs.getString("Nama"));
                String Nama = rs.getString("ca.Nama");
                String jml_peminjaman = rs.getString("pc.jumlah_pinjaman");
                String jaminan = rs.getString("pc.jaminan");
                String status_peminjaman = rs.getString("pc.status_peminjman");
                String[] tampil = {Nama, jml_peminjaman, jaminan, status_peminjaman};
                tabelAnggota.addRow(tampil);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("1");
            popup.error("tidak bisa menmapilkan ada kesalahan !!");
        }
    }

    protected void aprovePengajuanpeminjaman(JTable pengajuanPeminjaman) {

    }

    protected void simpanstatuspengajuan(String aprove, String namapengajuan, int jumlah_pinjaman, String status_peminjaman, String tipe) {

    }

    protected void daftarCanggota(JComboBox Cname) {
        koneksi DB = new koneksi();
        con = DB.con();
        try {
            String query = "SELECT * FROM calon_anggota where peminjaman = '0' order by Nama";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Cname.addItem(rs.getString("Nama"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void daftarCanggota_update_pengajuanpeminjaman(JComboBox Cname) {
        koneksi DB = new koneksi();
        con = DB.con();
        try {
            String query = "SELECT * FROM calon_anggota order by Nama";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Cname.addItem(rs.getString("Nama"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void tampildaftarterimapengajuanpeminjaman(JTable pengajuanPeminjaman) {

        Object[] rows = {"Nama", "jumlah Peminjmana", "Status Peminjaman", "Tipe"};
        tabelAnggota = new DefaultTableModel(null, rows);
        pengajuanPeminjaman.setModel(tabelAnggota);
        koneksi DB = new koneksi();
        con = DB.con();

        try {
//            String sql = "SELECT * FROM anggota Where Status_anggota= 'Diterima'";
            String sql = "SELECT Nama,jumlah_pinjaman,status_peminjman,tipe FROM peminjaman_anggota pa join anggota a on pa.anggota_id = a.anggota_id where status_peminjman= 'diterima' OR status_peminjman= 'ditolak'"
                    + " union all SELECT Nama,jumlah_pinjaman,status_peminjman,tipe FROM peminjaman_canggota pc join calon_anggota ca on pc.Canggota_id = ca.calonAnggota_id where status_peminjman= 'diterima' OR status_peminjman= 'ditolak'";
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

    protected void tampilkannewstatusPengajuanpeminjaman(JTable pengajuanPeminjaman) {
        Object[] rows = {"Nama", "jumlah Peminjmana", "Jangka Waktu", "Status Peminjaman", "Tipe"};
        tabelAnggota = new DefaultTableModel(null, rows);
        pengajuanPeminjaman.setModel(tabelAnggota);
        koneksi DB = new koneksi();
        con = DB.con();

        try {
//            String sql = "SELECT * FROM anggota Where Status_anggota= 'Diterima'";
            String sql = "SELECT Nama,jumlah_pinjaman,jangka,status_peminjman,tipe FROM peminjaman_anggota pa join anggota a on pa.anggota_id = a.anggota_id where status_peminjman='diterima' OR status_peminjman='ditolak'"
                    + " union all SELECT Nama,jumlah_pinjaman,jangka,status_peminjman,tipe FROM peminjaman_canggota pc join calon_anggota ca on pc.Canggota_id = ca.calonAnggota_id where status_peminjman='diterima' OR status_peminjman='ditolak'";
            stat = con.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                System.out.println("anggota-----");
//                System.out.println(rs.getString("Nama"));
                String Nama = rs.getString("nama");
                String jml_peminjaman = rs.getString("jumlah_pinjaman");
                String jangka = rs.getString("jangka");
                String status_peminjaman = rs.getString("status_peminjman");
                String tipe = rs.getString("tipe");
                String[] tampil = {Nama, jml_peminjaman, jangka, status_peminjaman, tipe};
                tabelAnggota.addRow(tampil);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("1");
            popup.error("tidak bisa menmapilkan ada kesalahan !!");
        }
    }

    protected void tampilkannewstatusPembayaran(JTable pengajuanPeminjaman) {
        Object[] rows = {"Nama", "jumlah Peminjmana", "tanggal peminjaman","tanggal pembayaran","Jangka Waktu", "Status Peminjaman", "Tipe","Sisa Peminjaman"};
        tabelAnggota = new DefaultTableModel(null, rows);
        pengajuanPeminjaman.setModel(tabelAnggota);
        koneksi DB = new koneksi();
        con = DB.con();

        try {
//            String sql = "SELECT * FROM anggota Where Status_anggota= 'Diterima'";
            String sql = "SELECT * FROM peminjaman_anggota pa join anggota a on pa.anggota_id = a.anggota_id where status_peminjman='diterima'";
            String sql2 = "SELECT * FROM peminjaman_canggota pc join calon_anggota ca on pc.Canggota_id = ca.calonAnggota_id where status_peminjman='diterima'";
            stat = con.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                System.out.println("anggota-----");
//                System.out.println(rs.getString("Nama"));
                String Nama = rs.getString("nama");
                String jml_peminjaman = rs.getString("jumlah_pinjaman");
                String jangka = rs.getString("jangka");
                String status_peminjaman = rs.getString("status_peminjman");
                String tipe = rs.getString("tipe");
                String tanggal = rs.getString("tanggal_peminjaman");
                String tanggal_pembayaran = rs.getString("tanggal_pembayaran");
                String Sisa_pembayaran = rs.getString("sisa_pengembalian");
                String[] tampil = {Nama, jml_peminjaman, tanggal, tanggal_pembayaran,jangka, status_peminjaman, tipe,Sisa_pembayaran};
                tabelAnggota.addRow(tampil);
            }
            stat.close();
            stat = con.createStatement();
            rs = stat.executeQuery(sql2);
            while (rs.next()) {
                System.out.println("anggota-----");
//                System.out.println(rs.getString("Nama"));
                String Nama = rs.getString("nama");
                String jml_peminjaman = rs.getString("jumlah_pinjaman");
                String jangka = rs.getString("jangka");
                String status_peminjaman = rs.getString("status_peminjman");
                String tipe = rs.getString("tipe");
                String tanggal = rs.getString("tanggal_pinjaman");
                String tanggal_pembayaran = rs.getString("tanggal_pembayaran");
                String Sisa_pembayaran = rs.getString("sisa_pengembalian");
                String[] tampil = {Nama, jml_peminjaman, tanggal, tanggal_pembayaran,jangka, status_peminjaman, tipe,Sisa_pembayaran};
                tabelAnggota.addRow(tampil);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("1");
            popup.error("tidak bisa menmapilkan ada kesalahan !!");
        }
    }

    static String Returnsisa_pengembalian, Returnjumlah_pinjaman,
            ReturnNama, Returntanggal_peminjaman, Returnstatus_peminjman,
            ReturnnoHp, Returnidentitas, Returnjangka, ReturnAlamat,
            ReturnAktifitas_Sepeda, ReturnStatusAnggota;

    public void return_detailAnggota(JTextField AnggotaAktifitas, JTextField AnggotaAlamat, JTextField AnggotaJangka,
            JTextField AnggotaIdentitas, JTextField AnggotaNohp, JTextField AnggotaPeminjmaan,
            JTextField AnggotaSisa, JTextField AnggotaStatus, JTextField AnggotaTanggal, JTextField Anggotanama, JTextField AnggotaStatusAnggota) {
        Anggotanama.setText(ReturnNama);
        AnggotaTanggal.setText(Returntanggal_peminjaman);
        AnggotaStatus.setText(Returnstatus_peminjman);
        AnggotaSisa.setText(Returnsisa_pengembalian);
        AnggotaPeminjmaan.setText(Returnjumlah_pinjaman);
        AnggotaNohp.setText(ReturnnoHp);
        AnggotaJangka.setText(Returnjangka);
        AnggotaIdentitas.setText(Returnidentitas);
        AnggotaAlamat.setText(ReturnAlamat);
        AnggotaAktifitas.setText(ReturnAktifitas_Sepeda);
        AnggotaStatusAnggota.setText(ReturnStatusAnggota);
        Returnsisa_pengembalian = "";
        Returnjumlah_pinjaman = "";
        ReturnNama = "";
        Returntanggal_peminjaman = "";
        Returnstatus_peminjman = "";
        ReturnnoHp = "";
        Returnidentitas = "";
        Returnjangka = "";
        ReturnAlamat = "";
        ReturnAktifitas_Sepeda = "";
        ReturnStatusAnggota = "";
    }

    protected void detail_anggota(String Nama, int jumlah_Peminjmana, String Jangka, String Status_Peminjaman, String Tipe) {
        try {
            String sql = "SELECT Nama,Status_anggota, tanggal_peminjaman, status_peminjman, sisa_pengembalian, jumlah_pinjaman, noHp, identitas, jangka, Alamat, Aktifitas_Sepeda FROM peminjaman_anggota pa join anggota a on pa.anggota_id = a.anggota_id WHERE a.Nama ='" + Nama + "' "
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
                ReturnNama = rs.getString("a.Nama");
                Returntanggal_peminjaman = rs.getString("pa.tanggal_peminjaman");
                Returnstatus_peminjman = rs.getString("pa.status_peminjman");
                Returnsisa_pengembalian = rs.getString("pa.sisa_pengembalian");
                Returnjumlah_pinjaman = rs.getString("pa.jumlah_pinjaman");
                ReturnnoHp = rs.getString("a.noHp");
                Returnidentitas = rs.getString("a.identitas");
                Returnjangka = rs.getString("pa.jangka");
                ReturnAlamat = rs.getString("a.Alamat");
                ReturnAktifitas_Sepeda = rs.getString("a.Aktifitas_Sepeda");
                ReturnStatusAnggota = rs.getString("a.Status_anggota");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getStackTrace());
            popup.gagal("Ada kesalahan silahan periksa pilihan anda");
        }
    }
    static String Retipe = null;

    protected void detail_anggota(String Nama, String Status_Anggota, String Alamat, String noHp) {
        detail detail = new detail();
        try {
            String sql = "SELECT tipe,Nama, Status_anggota,tanggal_peminjaman, status_peminjman, sisa_pengembalian, jumlah_pinjaman, noHp, identitas, jangka, Alamat, Aktifitas_Sepeda FROM peminjaman_anggota pa join anggota a on pa.anggota_id = a.anggota_id WHERE a.Nama ='" + Nama + "' "
                    + "AND a.Status_anggota ='" + Status_Anggota + "' AND a.Alamat ='" + Alamat + "' AND a.noHp ='" + noHp + "'";
            koneksi DB = new koneksi();
            con = DB.con();
            System.out.println("belum di eksekusi");
            stat = con.createStatement();
            rs = stat.executeQuery(sql);
            System.out.println("sudah d exsekusi");
            boolean hasil = rs.next();
            if (hasil) {
                System.out.println(rs.getString("a.Nama"));
                System.out.println(rs.getString("pa.tanggal_peminjaman"));
                ReturnNama = rs.getString("a.Nama");
                Returntanggal_peminjaman = rs.getString("pa.tanggal_peminjaman");
                Returnstatus_peminjman = rs.getString("pa.status_peminjman");
                Returnsisa_pengembalian = rs.getString("pa.sisa_pengembalian");
                Returnjumlah_pinjaman = rs.getString("pa.jumlah_pinjaman");
                ReturnnoHp = rs.getString("a.noHp");
                Returnidentitas = rs.getString("a.identitas");
                Returnjangka = rs.getString("pa.jangka");
                ReturnAlamat = rs.getString("a.Alamat");
                ReturnAktifitas_Sepeda = rs.getString("a.Aktifitas_Sepeda");
                ReturnStatusAnggota = rs.getString("a.Status_anggota");

                Retipe = rs.getString("pa.tipe");
            } else {
                stat.close();
                String sql2 = "SELECT Nama, Status_anggota, noHp, identitas, Alamat, Aktifitas_Sepeda FROM anggota WHERE Nama ='" + Nama + "' AND Status_anggota ='"
                        + Status_Anggota + "' AND Alamat ='" + Alamat + "' AND noHp ='" + noHp + "'";
                koneksi DB2 = new koneksi();
                con = DB2.con();
                System.out.println("belum di eksekusi 2");
                stat = con.createStatement();
                rs = stat.executeQuery(sql2);
                System.out.println("sudah d exsekusi 2");
                while (rs.next()) {
                    System.out.println(rs.getString("Nama"));
                    ReturnNama = rs.getString("Nama");
                    ReturnnoHp = rs.getString("noHp");
                    Returnidentitas = rs.getString("identitas");
                    ReturnAlamat = rs.getString("Alamat");
                    ReturnAktifitas_Sepeda = rs.getString("Aktifitas_Sepeda");
                    ReturnStatusAnggota = rs.getString("Status_anggota");
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getStackTrace());
            popup.gagal("Ada kesalahan silahan periksa pilihan anda");
        }
    }

    protected void detail_anggota(String Nama, int jumlah_Peminjmana, String Status_Peminjaman, String Tipe) {
        try {
            String sql = "SELECT Nama,Status_anggota, tanggal_peminjaman, status_peminjman, sisa_pengembalian, jumlah_pinjaman, noHp, identitas, jangka, Alamat, Aktifitas_Sepeda FROM peminjaman_anggota pa join anggota a on pa.anggota_id = a.anggota_id WHERE a.Nama ='" + Nama + "' "
                    + "AND pa.jumlah_pinjaman ='" + jumlah_Peminjmana + "' AND pa.status_peminjman ='" + Status_Peminjaman + "' "
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
                ReturnNama = rs.getString("a.Nama");
                Returntanggal_peminjaman = rs.getString("pa.tanggal_peminjaman");
                Returnstatus_peminjman = rs.getString("pa.status_peminjman");
                Returnsisa_pengembalian = rs.getString("pa.sisa_pengembalian");
                Returnjumlah_pinjaman = rs.getString("pa.jumlah_pinjaman");
                ReturnnoHp = rs.getString("a.noHp");
                Returnidentitas = rs.getString("a.identitas");
                Returnjangka = rs.getString("pa.jangka");
                ReturnAlamat = rs.getString("a.Alamat");
                ReturnAktifitas_Sepeda = rs.getString("a.Aktifitas_Sepeda");
                ReturnStatusAnggota = rs.getString("a.Status_anggota");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getStackTrace());
            popup.gagal("Ada kesalahan silahan periksa pilihan anda");
        }
    }

    protected void detail_anggota(String Nama, String Status_Anggota) {
        detail detail = new detail();
        try {
            String sql = "SELECT Nama, Status_anggota, noHp, identitas, Alamat, Aktifitas_Sepeda FROM anggota WHERE Nama ='" + Nama + "' AND Status_anggota ='" + Status_Anggota + "'";
            koneksi DB = new koneksi();
            con = DB.con();
            System.out.println("belum di eksekusi");
            stat = con.createStatement();
            rs = stat.executeQuery(sql);
            System.out.println("sudah d exsekusi");
            while (rs.next()) {
                System.out.println(rs.getString("Nama"));
                ReturnNama = rs.getString("Nama");
                ReturnnoHp = rs.getString("noHp");
                Returnidentitas = rs.getString("identitas");
                ReturnAlamat = rs.getString("Alamat");
                ReturnAktifitas_Sepeda = rs.getString("Aktifitas_Sepeda");
                ReturnStatusAnggota = rs.getString("Status_anggota");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getStackTrace());
            popup.gagal("Ada kesalahan silahan periksa pilihan anda");
        }
    }
    static String Calamat, Cangsuran, Cidentitas,
            Cjaminan, Cnama, Cnohp, Cpinjaman, Csisa,
            Cstatus_peminjaman, Ctanggalpeminjaman;

    protected void return_detail_Canggota(JTextField Calamat, JTextField Cangsuran, JTextField Cidentitas,
            JTextField Cjaminan, JTextField Cnama, JTextField Cnohp, JTextField Cpinjaman, JTextField Csisa,
            JTextField Cstatus_peminjaman, JTextField Ctanggalpeminjaman) {
        Calamat.setText(this.Calamat);
        Cangsuran.setText(this.Cangsuran);
        Cidentitas.setText(this.Cidentitas);
        Cjaminan.setText(this.Cjaminan);
        Cnama.setText(this.Cnama);
        Cnohp.setText(this.Cnohp);
        Cpinjaman.setText(this.Cpinjaman);
        Csisa.setText(this.Csisa);
        Cstatus_peminjaman.setText(this.Cstatus_peminjaman);
        Ctanggalpeminjaman.setText(this.Ctanggalpeminjaman);

        this.Calamat = "";
        this.Cangsuran = "";
        this.Cidentitas = "";
        this.Cjaminan = "";
        this.Cnama = "";
        this.Cnohp = "";
        this.Cpinjaman = "";
        this.Csisa = "";
        this.Cstatus_peminjaman = "";
        this.Ctanggalpeminjaman = "";
    }

    protected void detail_Canggota(String Nama, int jumlah_Peminjmana, String Jangka, String Status_Peminjaman, String Tipe) {
        try {
            String sql = "SELECT Nama, tanggal_pinjaman, status_peminjman, sisa_pengembalian, jumlah_pinjaman, noHp, identitas, jangka, Alamat, jaminan FROM peminjaman_canggota pc join calon_anggota ca on pc.Canggota_id = ca.calonAnggota_id WHERE ca.Nama ='" + Nama + "' "
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
                Cstatus_peminjaman = rs.getString("pc.status_peminjman");
                Csisa = rs.getString("pc.sisa_pengembalian");
                Cpinjaman = rs.getString("pc.jumlah_pinjaman");
                Cnohp = rs.getString("ca.noHp");
                Cidentitas = rs.getString("ca.identitas");
                Cangsuran = rs.getString("pc.jangka");
                Calamat = rs.getString("ca.Alamat");
                Cjaminan = rs.getString("pc.jaminan");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getStackTrace());
            popup.gagal("Ada kesalahan silahan periksa pilihan anda");
        }
    }
    static int id_canggota_data;

    protected void detail_Canggota(String Nama, int jumlah_Peminjmana, String jaminan, String Status_Peminjaman) {
        try {
            String sql = "SELECT Nama, tanggal_pinjaman, status_peminjman, sisa_pengembalian, jumlah_pinjaman, noHp, identitas, jangka, Alamat, jaminan FROM peminjaman_canggota pc join calon_anggota ca on pc.Canggota_id = ca.calonAnggota_id WHERE ca.Nama ='" + Nama + "' "
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
                Ctanggalpeminjaman = rs.getString("pc.tanggal_pinjaman");
                Cstatus_peminjaman = rs.getString("pc.status_peminjman");
                Csisa = rs.getString("pc.sisa_pengembalian");
                Cpinjaman = rs.getString("pc.jumlah_pinjaman");
                Cnohp = rs.getString("ca.noHp");
                Cidentitas = rs.getString("ca.identitas");
                Cangsuran = rs.getString("pc.jangka");
                Calamat = rs.getString("ca.Alamat");
                Cjaminan = rs.getString("pc.jaminan");

            } else {
                stat.close();
                String sql2 = "SELECT Nama, noHp, identitas, Alamat FROM anggota WHERE Nama ='" + Nama + "'";
                koneksi DB2 = new koneksi();
                con = DB2.con();
                System.out.println("belum di eksekusi 2");
                stat = con.createStatement();
                rs = stat.executeQuery(sql2);
                System.out.println("sudah d exsekusi 2");
                while (rs.next()) {
                    System.out.println(rs.getString("Nama"));
                    Cnama = rs.getString("Nama");
                    Cnohp = rs.getString("noHp");
                    Cidentitas = rs.getString("identitas");
                    Calamat = rs.getString("Alamat");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getStackTrace());
            popup.gagal("Ada kesalahan silahan periksa pilihan anda");
        }
    }

    protected void detail_Canggota(String Nama, int jumlah_Peminjmana, String Tipe) {
        try {
            String sql = "SELECT Nama, tanggal_pinjaman, status_peminjman, sisa_pengembalian, jumlah_pinjaman, noHp, identitas, jangka, Alamat, jaminan FROM peminjaman_canggota pc join calon_anggota ca on pc.Canggota_id = ca.calonAnggota_id WHERE ca.Nama ='" + Nama + "' "
                    + "AND pc.jumlah_pinjaman ='" + jumlah_Peminjmana + "' AND pc.tipe ='" + Tipe + "'";
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
                Cstatus_peminjaman = rs.getString("pc.status_peminjman");
                Csisa = rs.getString("pc.sisa_pengembalian");
                Cpinjaman = rs.getString("pc.jumlah_pinjaman");
                Cnohp = rs.getString("ca.noHp");
                Cidentitas = rs.getString("ca.identitas");
                Cangsuran = rs.getString("pc.jangka");
                Calamat = rs.getString("ca.Alamat");
                Cjaminan = rs.getString("pc.jaminan");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getStackTrace());
            popup.gagal("Ada kesalahan silahan periksa pilihan anda");
        }
    }

    protected void Hapus_peminjman(String Nama, int jumlah_Peminjmana, String Jangka, String Status_Peminjaman, String Tipe) {

    }

    protected boolean querySql(String query) {
        koneksi DB = new koneksi();
        con = DB.con();
        try {
            stat = con.createStatement();
            stat.executeUpdate(query);
            return true;

        } catch (Exception e) {
            System.out.println("gagal");
            System.out.println(e.getMessage());

        }
        return false;

    }

}
