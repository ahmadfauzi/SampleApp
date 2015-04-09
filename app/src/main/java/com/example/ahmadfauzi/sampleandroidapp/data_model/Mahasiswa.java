package com.example.ahmadfauzi.sampleandroidapp.data_model;

import android.net.Uri;

/**
 * Created by 5111100057 on 4/6/2015.
 */
public class Mahasiswa {

    private String _nrpMhs, _namaMhs, _fotoMhs, _kelaminMhs, _tglLahirMhs, _telpMhs, _alamatMhs, _emailMhs;
    private Uri _imageUri;
    private int _id;

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
}
