package com.example.ahmadfauzi.sampleandroidapp.data_model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 5111100057 on 4/6/2015.
 */
public class DatabaseConnector {
    /*
    void open();
    void close();
    long tambahMahasiswa(Mahasiswa mahasiswa);
    Mahasiswa ambilSatuMahasiswa(String cariNrpMhs);
    ArrayList<Mahasiswa> ambilSemuaMhs();
    long updateMhs(Mahasiswa mahasiswa);
    void deleteMhs(String deleteNrpMhs);
    long tambahDosen(Dosen dosen);
    Dosen ambilSatuDosen(String cariNipDosen);
    */
    private MySQLiteHelper mySQLiteHelper;

    public DatabaseConnector(Context context){
        mySQLiteHelper = new MySQLiteHelper(context);
    }

    public long tambahMahasiswa(Mahasiswa mahasiswa){
        SQLiteDatabase db = mySQLiteHelper.getWritableDatabase();
        ContentValues values  = new ContentValues();
        values.put(Mahasiswa.KEY_nrpMhs, mahasiswa.nrpMhs);
        values.put(Mahasiswa.KEY_namaMhs, mahasiswa.namaMhs);
        values.put(Mahasiswa.KEY_fotoMhs, mahasiswa.fotoMhs);
        values.put(Mahasiswa.KEY_kelaminMhs, mahasiswa.kelaminMhs);
        values.put(Mahasiswa.KEY_tglLahirMhs, mahasiswa.tglLahirMhs);
        values.put(Mahasiswa.KEY_telpMhs, mahasiswa.telpMhs);
        values.put(Mahasiswa.KEY_alamatMhs, mahasiswa.alamatMhs);
        values.put(Mahasiswa.KEY_emailMhs, mahasiswa.emailMhs);

        long mahasiswa_insert= db.insert(Mahasiswa.TABLE, null, values);
        db.close();
        return mahasiswa_insert;
    }

    public Mahasiswa ambilSatuMahasiswa(String cariNrpMhs){
        SQLiteDatabase db = mySQLiteHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                Mahasiswa.KEY_nrpMhs + "," +
                Mahasiswa.KEY_namaMhs + "," +
                Mahasiswa.KEY_fotoMhs + "," +
                Mahasiswa.KEY_kelaminMhs + "," +
                Mahasiswa.KEY_tglLahirMhs + "," +
                Mahasiswa.KEY_telpMhs + "," +
                Mahasiswa.KEY_alamatMhs + "," +
                Mahasiswa.KEY_emailMhs + "," +
                " FROM " + Mahasiswa.TABLE +
                " WHERE " +
                Mahasiswa.KEY_nrpMhs + "=?";

        Mahasiswa mahasiswa = new Mahasiswa();

        Cursor cursor = db.rawQuery(selectQuery, new String [] { cariNrpMhs } );

        if(cursor.moveToFirst()){
            do{
                mahasiswa.nrpMhs = cursor.getString(cursor.getColumnIndex(Mahasiswa.KEY_nrpMhs));
                mahasiswa.namaMhs = cursor.getString(cursor.getColumnIndex(Mahasiswa.KEY_namaMhs));
                mahasiswa.fotoMhs = cursor.getString(cursor.getColumnIndex(Mahasiswa.KEY_fotoMhs));
                mahasiswa.kelaminMhs = cursor.getString(cursor.getColumnIndex(Mahasiswa.KEY_kelaminMhs));
                mahasiswa.tglLahirMhs = cursor.getString(cursor.getColumnIndex(Mahasiswa.KEY_tglLahirMhs));
                mahasiswa.telpMhs = cursor.getString(cursor.getColumnIndex(Mahasiswa.KEY_telpMhs));
                mahasiswa.alamatMhs = cursor.getString(cursor.getColumnIndex(Mahasiswa.KEY_alamatMhs));
                mahasiswa.emailMhs = cursor.getString(cursor.getColumnIndex(Mahasiswa.KEY_emailMhs));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return mahasiswa;
    }

    public ArrayList<Mahasiswa> ambilSemuaMahasiswa(){
        SQLiteDatabase db = mySQLiteHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                Mahasiswa.KEY_nrpMhs + "," +
                Mahasiswa.KEY_namaMhs + "," +
                Mahasiswa.KEY_emailMhs + "," +
                " FROM " + Mahasiswa.TABLE;

        ArrayList<Mahasiswa> mahasiswaList = new ArrayList<Mahasiswa>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Mahasiswa mahasiswa = new Mahasiswa(cursor.getString(0), cursor.getString(1), cursor.getString(2));
                mahasiswaList.add(mahasiswa);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return mahasiswaList;
    }

    public long updateMhs(Mahasiswa mahasiswa){
        SQLiteDatabase db = mySQLiteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Mahasiswa.KEY_nrpMhs, mahasiswa.nrpMhs);
        values.put(Mahasiswa.KEY_namaMhs, mahasiswa.namaMhs);
        values.put(Mahasiswa.KEY_fotoMhs, mahasiswa.fotoMhs);
        values.put(Mahasiswa.KEY_kelaminMhs, mahasiswa.kelaminMhs);
        values.put(Mahasiswa.KEY_tglLahirMhs, mahasiswa.tglLahirMhs);
        values.put(Mahasiswa.KEY_telpMhs, mahasiswa.telpMhs);
        values.put(Mahasiswa.KEY_alamatMhs, mahasiswa.alamatMhs);
        values.put(Mahasiswa.KEY_emailMhs, mahasiswa.emailMhs);

        return db.update(Mahasiswa.TABLE, values, Mahasiswa.KEY_nrpMhs + "=?", new String[] { mahasiswa.nrpMhs });
    }

    public void deleteMhs(String deleteNrpMhs){
        SQLiteDatabase db = mySQLiteHelper.getWritableDatabase();

        db.delete(Mahasiswa.TABLE, Mahasiswa.KEY_nrpMhs + "=?", new String[] {deleteNrpMhs});
        db.close();
    }

    public long tambahDosen(Dosen dosen){
        SQLiteDatabase db = mySQLiteHelper.getWritableDatabase();
        ContentValues values  = new ContentValues();
        values.put(Dosen.KEY_nipDosen, dosen.nipDosen);
        values.put(Dosen.KEY_namaDosen, dosen.namaDosen);
        values.put(Dosen.KEY_passwordDosen, dosen.passwordDosen);
        values.put(Dosen.KEY_fotoDosen, dosen.fotoDosen);
        values.put(Dosen.KEY_emailDosen, dosen.emailDosen);

        long dosen_insert = db.insert(Dosen.TABLE, null, values);
        db.close();
        return dosen_insert;
    }

    public Dosen ambilSatuDosen(String cariNipDosen){
        SQLiteDatabase db = mySQLiteHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                Dosen.KEY_nipDosen + "," +
                Dosen.KEY_namaDosen + "," +
                Dosen.KEY_passwordDosen + "," +
                Dosen.KEY_fotoDosen + "," +
                Dosen.KEY_emailDosen + "," +
                " FROM " + Dosen.TABLE +
                " WHERE " +
                Dosen.KEY_nipDosen + "=?";

        Dosen dosen = new Dosen();

        Cursor cursor = db.rawQuery(selectQuery, new String[] {cariNipDosen});

        if(cursor.moveToFirst()){
            do{
                dosen.nipDosen = cursor.getString(cursor.getColumnIndex(Dosen.KEY_nipDosen));
                dosen.namaDosen = cursor.getString(cursor.getColumnIndex(Dosen.KEY_namaDosen));
                dosen.passwordDosen = cursor.getString(cursor.getColumnIndex(Dosen.KEY_passwordDosen));
                dosen.fotoDosen = cursor.getString(cursor.getColumnIndex(Dosen.KEY_fotoDosen));
                dosen.emailDosen = cursor.getString(cursor.getColumnIndex(Dosen.KEY_emailDosen));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return dosen;
    }
}
