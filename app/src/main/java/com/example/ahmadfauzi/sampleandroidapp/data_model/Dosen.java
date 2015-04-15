package com.example.ahmadfauzi.sampleandroidapp.data_model;

import android.net.Uri;

/**
 * Created by 5111100057 on 4/6/2015.
 */
public class Dosen {

    public static final String TABLE = "Dosen";

    public static final String KEY_nipDosen = "nipDosen";
    public static final String KEY_namaDosen = "namaDosen";
    public static final String KEY_passwordDosen = "passwordDosen";
    public static final String KEY_fotoDosen = "fotoDosen";
    public static final String KEY_emailDosen = "emailDosen";

    public String nipDosen;
    public String namaDosen;
    public String passwordDosen;
    public String fotoDosen;
    public String emailDosen;

    /*
    private String _nipDosen, _namaDosen, _passwordDosen, _fotoDosen, _emailDosen;
    private Uri _imageUri;
    private int _id;

    public Dosen (int _id, String nipDosen, String namaDosen, String passwordDosen, String fotoDosen, String emailDosen) {
        _nipDosen = nipDosen;
        _namaDosen = namaDosen;
        _passwordDosen = passwordDosen;
        _fotoDosen = fotoDosen;
        _emailDosen = emailDosen;
    }

    public int getId() {return _id;}

    public String getNipDosen() {return _nipDosen;}

    public String getNamaDosen() {return _namaDosen;}

    public String getPasswordDosen() {return _passwordDosen;}

    public String getFotoDosen() {return _fotoDosen;}

    public String getEmailDosen() {return _emailDosen;}

    public Uri getImageUri() {return  _imageUri;}
    */
}
