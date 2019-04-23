/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.sql.Date;

/**
 *
 * @author coeng
 */
public class anggota extends users{
    
    public anggota(String nama,String alamat,String identitas,String aktifitas_Sepeda,String Nohp,Date tanggal_peminjaman,int jmlpinjaman,int sisa_pengembalian, String angsuran,String status){
        this.nama = nama;
        this.alamat = alamat;
        this.identitas = identitas;
        this.aktifitas_sepeda = aktifitas_Sepeda;
        this.nohp = Nohp;
        this.tanggal_peminjaman = tanggal_peminjaman;
        this.jumlah_pinjaman = jmlpinjaman;
        this.sisa_pengembalian = sisa_pengembalian;
        this.angsuran = angsuran;
        this.status_peminjaman =status;
    }
}
