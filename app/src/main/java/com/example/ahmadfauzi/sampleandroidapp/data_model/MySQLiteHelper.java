package com.example.ahmadfauzi.sampleandroidapp.data_model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 5111100057 on 4/6/2015.
 */
public class MySQLiteHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "academicV1.db",
    NAMA_TABLE_MHS = "mahasiswa",
    KOLOM_NRP_TABLE_MHS = "nrpMhs",
    KOLOM_NAMA_TABLE_MHS = "namaMhs",
    KOLOM_FOTO_TABLE_MHS = "fotoMhs",
    KOLOM_KELAMIN_TABLE_MHS = "kelaminMhs",
    KOLOM_TGLLAHIR_TABLE_MHS = "tglLahirMhs",
    KOLOM_TELP_TABLE_MHS = "telpMhs",
    KOLOM_ALAMAT_TABLE_MHS = "alamatMhs",
    KOLOM_EMAIL_TABLE_MHS = "emailMhs";

    public MySQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
