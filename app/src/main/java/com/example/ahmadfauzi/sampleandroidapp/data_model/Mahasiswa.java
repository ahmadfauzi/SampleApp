package com.example.ahmadfauzi.sampleandroidapp.data_model;

import android.net.Uri;

/**
 * Created by 5111100057 on 4/6/2015.
 */
public class Mahasiswa {

    public static final String TABLE = "Mahasiswa";

    public static final String KEY_ID = "id";
    public static final String KEY_nrpMhs = "nrpMhs";
    public static final String KEY_namaMhs = "namaMhs";
    public static final String KEY_fotoMhs = "fotoMhs";
    public static final String KEY_kelaminMhs = "kelaminMhs";
    public static final String KEY_tglLahirMhs = "tglLahirMhs";
    public static final String KEY_telpMhs = "telpMhs";
    public static final String KEY_alamatMhs = "alamatMhs";
    public static final String KEY_emailMhs = "emailMhs";

    public int mahasiswa_ID;
    public String nrpMhs;
    public String namaMhs;
    public String fotoMhs;
    public String kelaminMhs;
    public String tglLahirMhs;
    public String telpMhs;
    public String alamatMhs;
    public String emailMhs;

    /*
    public String _nrpMhs, _namaMhs, _fotoMhs, _kelaminMhs, _tglLahirMhs, _telpMhs, _alamatMhs, _emailMhs;
    public Uri _imageUri;
    public int _id;

    public Mahasiswa (int id, String nrpMhs, String namaMhs, String fotoMhs, String kelaminMhs, String tglLahirMhs, String telpMhs, String alamatMhs, String emailMhs, Uri ImageUri) {
        _id = id;
        _nrpMhs = nrpMhs;
        _namaMhs = namaMhs;
        _fotoMhs = fotoMhs;
        _kelaminMhs = kelaminMhs;
        _tglLahirMhs = tglLahirMhs;
        _telpMhs = telpMhs;
        _alamatMhs = alamatMhs;
        _emailMhs = emailMhs;
    }

    public int getId() {return  _id;}

    public String getNrpMhs() {return _nrpMhs;}

    public String getNamaMhs() {return  _namaMhs;}

    public String getFotoMhs() {return  _fotoMhs;}

    public String getKelaminMhs() {return  _kelaminMhs;}

    public String getTglLahirMhs() {return  _tglLahirMhs;}

    public String getTelpMhs() {return  _telpMhs;}

    public String getAlamatMhs() {return _alamatMhs;}

    public String getEmailMhs() {return  _emailMhs;}
    */
}
