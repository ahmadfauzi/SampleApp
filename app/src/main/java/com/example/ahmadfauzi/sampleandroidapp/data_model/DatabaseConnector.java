package com.example.ahmadfauzi.sampleandroidapp.data_model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

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

        long student_Id = db.insert(Mahasiswa.TABLE, null, values);
        db.close();
        return (int) student_Id;
    }

    public Mahasiswa ambilSatuMahasiswa(String cariNrpMhs){
        SQLiteDatabase db = mySQLiteHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                Mahasiswa.KEY_ID + "," +
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

        Cursor cursor = db.rawQuery(selectQuery, new String [] { String.valueOf(cariNrpMhs) } );

        if(cursor.moveToFirst()){
            do{
                mahasiswa.mahasiswa_ID = cursor.getInt(cursor.getColumnIndex(Mahasiswa.KEY_ID));
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


}
