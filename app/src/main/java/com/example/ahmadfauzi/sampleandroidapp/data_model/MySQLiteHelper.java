package com.example.ahmadfauzi.sampleandroidapp.data_model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 5111100057 on 4/6/2015.
 */
public class MySQLiteHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "academicV1.db",
    NAMA_TABLE_MHS = "mahasiswa",
    KOLOM_NRP_TABLE_MHS = "nrpMhs",
    KOLOM_NAMA_TABLE_MHS = "namaMhs",
    KOLOM_FOTO_TABLE_MHS = "fotoMhs",
    KOLOM_KELAMIN_TABLE_MHS = "kelaminMhs",
    KOLOM_TGLLAHIR_TABLE_MHS = "tglLahirMhs",
    KOLOM_TELP_TABLE_MHS = "telpMhs",
    KOLOM_ALAMAT_TABLE_MHS = "alamatMhs",
    KOLOM_EMAIL_TABLE_MHS = "emailMhs",

    NAMA_TABLE_DOSEN = "dosen",
    KOLOM_NIP_TABLE_DOSEN = "nipDosen",
    KOLOM_NAMA_TABLE_DOSEN = "namaDosen",
    KOLOM_PASSWORD_TABLE_DOSEN = "passwordDosen",
    KOLOM_FOTO_TABLE_DOSEN = "fotoDosen",
    KOLOM_EMAIL_TABLE_DOSEN = "emailDosen";

    public MySQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + NAMA_TABLE_MHS + " (" + KOLOM_NRP_TABLE_MHS + " TEXT PRIMARY KEY," + KOLOM_NAMA_TABLE_MHS + " TEXT NOT NULL,"+ KOLOM_FOTO_TABLE_MHS + " TEXT," + KOLOM_KELAMIN_TABLE_MHS + " TEXT," + KOLOM_TGLLAHIR_TABLE_MHS + " TEXT," + KOLOM_TELP_TABLE_MHS + " TEXT," + KOLOM_ALAMAT_TABLE_MHS + " TEXT," + KOLOM_EMAIL_TABLE_MHS + " TEXT)");
        db.execSQL("CREATE TABLE " + NAMA_TABLE_DOSEN + " (" + KOLOM_NIP_TABLE_DOSEN + " TEXT PRIMARY KEY," + KOLOM_NAMA_TABLE_DOSEN + " TEXT NOT NULL," + KOLOM_PASSWORD_TABLE_DOSEN + " TEXT," + KOLOM_FOTO_TABLE_DOSEN + " TEXT," + KOLOM_EMAIL_TABLE_DOSEN + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(getClass().getName(), "Upgrade db dari versi "
                + oldVersion + " ke " + newVersion + "yg menghapus semua data");
        db.execSQL("DROP TABLE IF EXIST " + NAMA_TABLE_MHS);
        db.execSQL("DROP TABLE IF EXIST " + NAMA_TABLE_DOSEN);
        onCreate(db);
    }
}
