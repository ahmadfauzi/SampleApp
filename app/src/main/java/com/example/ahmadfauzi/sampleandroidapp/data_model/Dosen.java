package com.example.ahmadfauzi.sampleandroidapp.data_model;

import android.net.Uri;
import android.os.Bundle;

/**
 * Created by 5111100057 on 4/6/2015.
 */
public class Dosen {
    /*
    public static final String TABLE = "Dosen";


    public static final String KEY_nipDosen = "nipDosen";
    public static final String KEY_namaDosen = "namaDosen";
    public static final String KEY_passwordDosen = "passwordDosen";
    public static final String KEY_fotoDosen = "fotoDosen";
    public static final String KEY_emailDosen = "emailDosen";
    */
    public String nipDosen;
    public String namaDosen;
    public String passwordDosen;
    public String fotoDosen;
    public String emailDosen;

    //constructor =============================================================================

    public Dosen() {
        this.nipDosen = "";
        this.namaDosen = "";
        this.passwordDosen = "";
        this.fotoDosen = null;
        this.emailDosen = "";
    }
    public Dosen(String nipDosen, String namaDosen, String passwordDosen, String fotoDosen, String emailDosen) {
        this.nipDosen = nipDosen;
        this.namaDosen = namaDosen;
        this.passwordDosen = passwordDosen;
        this.fotoDosen = fotoDosen;
        this.emailDosen = emailDosen;
    }

    public Dosen (Bundle b) {
        if (b != null) {
            this.nipDosen = b.getString(MySQLiteHelper.KOLOM_NIP_TABLE_DOSEN);
            this.namaDosen = b.getString(MySQLiteHelper.KOLOM_NAMA_TABLE_DOSEN);
            this.passwordDosen = b.getString(MySQLiteHelper.KOLOM_PASSWORD_TABLE_DOSEN);
            this.fotoDosen= b.getString(MySQLiteHelper.KOLOM_FOTO_TABLE_DOSEN);
            this.emailDosen = b.getString(MySQLiteHelper.KOLOM_EMAIL_TABLE_DOSEN);
            //Log.d("dosen", "create");
        }
    }

    //getter & setter =============================================================================

    public void setNipDosen(String nipDosen) {
        this.nipDosen = nipDosen;
    }

    public void setNamaDosen(String namaDosen) {
        this.namaDosen = namaDosen;
    }

    public void setPasswordDosen(String passwordDosen) {
        this.passwordDosen = passwordDosen;
    }

    public void setFotoDosen(String fotoDosen) {
        this.fotoDosen = fotoDosen;
    }

    public void setEmailDosen(String emailDosen) {
        this.emailDosen = emailDosen;
    }

    public String getNipDosen() {
        return nipDosen;
    }

    public String getNamaDosen() {
        return namaDosen;
    }

    public String getPasswordDosen() {
        return passwordDosen;
    }

    public String getFotoDosen() {
        return fotoDosen;
    }

    public String getEmailDosen() {
        return emailDosen;
    }
}
