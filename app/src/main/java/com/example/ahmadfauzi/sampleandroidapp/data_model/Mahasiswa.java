package com.example.ahmadfauzi.sampleandroidapp.data_model;

import android.net.Uri;
import android.os.Bundle;

import java.io.Serializable;

/**
 * Created by 5111100057 on 4/6/2015.
 */
public class Mahasiswa implements Serializable{

    /*
        public static final String TABLE = "Mahasiswa";

        public static final String KEY_nrpMhs = "nrpMhs";
        public static final String KEY_namaMhs = "namaMhs";
        public static final String KEY_fotoMhs = "fotoMhs";
        public static final String KEY_kelaminMhs = "kelaminMhs";
        public static final String KEY_tglLahirMhs = "tglLahirMhs";
        public static final String KEY_telpMhs = "telpMhs";
        public static final String KEY_alamatMhs = "alamatMhs";
        public static final String KEY_emailMhs = "emailMhs";
        */
    public String nrpMhs;
    public String namaMhs;
    public String fotoMhs;
    public String kelaminMhs;
    public String tglLahirMhs;
    public String telpMhs;
    public String alamatMhs;
    public String emailMhs;

    public Mahasiswa(){
        this.nrpMhs = "";
        this.namaMhs = "";
        this.fotoMhs = "";
        this.kelaminMhs = "";
        this.tglLahirMhs = "";
        this.telpMhs = "";
        this.alamatMhs = "";
        this.emailMhs = "";
    }

    public Mahasiswa(String nrpMhs, String namaMhs, String fotoMhs, String kelaminMhs, String tglLahirMhs, String telpMhs, String alamatMhs, String emailMhs) {
        this.nrpMhs = nrpMhs;
        this.namaMhs = namaMhs;
        this.fotoMhs = fotoMhs;
        this.kelaminMhs = kelaminMhs;
        this.tglLahirMhs = tglLahirMhs;
        this.telpMhs = telpMhs;
        this.alamatMhs = alamatMhs;
        this.emailMhs = emailMhs;
    }

    public Mahasiswa(Bundle b) {
        if (b != null) {
            this.nrpMhs = b.getString(MySQLiteHelper.KOLOM_NRP_TABLE_MHS);
            this.namaMhs = b.getString(MySQLiteHelper.KOLOM_NAMA_TABLE_MHS);
            this.fotoMhs = b.getString(MySQLiteHelper.KOLOM_FOTO_TABLE_MHS);
            this.kelaminMhs = b.getString(MySQLiteHelper.KOLOM_KELAMIN_TABLE_MHS);
            this.tglLahirMhs = b.getString(MySQLiteHelper.KOLOM_TGLLAHIR_TABLE_MHS);
            this.telpMhs = b.getString(MySQLiteHelper.KOLOM_TELP_TABLE_MHS);
            this.alamatMhs = b.getString(MySQLiteHelper.KOLOM_ALAMAT_TABLE_MHS);
            this.emailMhs = b.getString(MySQLiteHelper.KOLOM_EMAIL_TABLE_MHS);
        }
    }

    public String getNrpMhs() {
        return nrpMhs;
    }

    public void setNrpMhs(String nrpMhs) {
        this.nrpMhs = nrpMhs;
    }

    public String getNamaMhs() {
        return namaMhs;
    }

    public void setNamaMhs(String namaMhs) {
        this.namaMhs = namaMhs;
    }

    public String getFotoMhs() {
        return fotoMhs;
    }

    public void setFotoMhs(String fotoMhs) {
        this.fotoMhs = fotoMhs;
    }

    public String getKelaminMhs() {
        return kelaminMhs;
    }

    public void setKelaminMhs(String kelaminMhs) {
        this.kelaminMhs = kelaminMhs;
    }

    public String getTglLahirMhs() {
        return tglLahirMhs;
    }

    public void setTglLahirMhs(String tglLahirMhs) {
        this.tglLahirMhs = tglLahirMhs;
    }

    public String getTelpMhs() {
        return telpMhs;
    }

    public void setTelpMhs(String telpMhs) {
        this.telpMhs = telpMhs;
    }

    public String getAlamatMhs() {
        return alamatMhs;
    }

    public void setAlamatMhs(String alamatMhs) {
        this.alamatMhs = alamatMhs;
    }

    public String getEmailMhs() {
        return emailMhs;
    }

    public void setEmailMhs(String emailMhs) {
        this.emailMhs = emailMhs;
    }

    //=======================================================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mahasiswa mahasiswa = (Mahasiswa) o;

        if (!nrpMhs.equals(mahasiswa.nrpMhs)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return nrpMhs.hashCode();
    }

    //=======================================================================

    public Bundle toBundle() {
        Bundle b = new Bundle();
        b.putString(MySQLiteHelper.KOLOM_NRP_TABLE_MHS, this.nrpMhs);
        b.putString(MySQLiteHelper.KOLOM_NAMA_TABLE_MHS, this.namaMhs);
        b.putString(MySQLiteHelper.KOLOM_FOTO_TABLE_MHS, this.fotoMhs);
        b.putString(MySQLiteHelper.KOLOM_KELAMIN_TABLE_MHS, this.kelaminMhs);
        b.putString(MySQLiteHelper.KOLOM_TGLLAHIR_TABLE_MHS, this.tglLahirMhs);
        b.putString(MySQLiteHelper.KOLOM_TELP_TABLE_MHS, this.telpMhs);
        b.putString(MySQLiteHelper.KOLOM_ALAMAT_TABLE_MHS, this.alamatMhs);
        b.putString(MySQLiteHelper.KOLOM_EMAIL_TABLE_MHS, this.emailMhs);
        return b;
    }

    @Override
    public String toString() {
        return "Mahasiswa{" +
                "nrpMhs='" + nrpMhs + '\'' +
                ", namaMhs='" + namaMhs + '\'' +
                ", fotoMhs=" + fotoMhs +
                ", kelaminMhs='" + kelaminMhs + '\'' +
                ", tglLahirMhs='" + tglLahirMhs + '\'' +
                ", telpMhs='" + telpMhs + '\'' +
                ", alamatMhs='" + alamatMhs + '\'' +
                ", emailMhs='" + emailMhs + '\'' +
                '}';
    }
}
