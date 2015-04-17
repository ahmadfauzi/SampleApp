package com.example.ahmadfauzi.sampleandroidapp.data_model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.lang.reflect.Array;
import java.sql.SQLException;
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
        long mahasiswa_insert = 0;

        SQLiteDatabase db = mySQLiteHelper.getWritableDatabase();
        ContentValues values  = new ContentValues();
        values.put(MySQLiteHelper.KOLOM_NRP_TABLE_MHS, mahasiswa.getNrpMhs());
        values.put(MySQLiteHelper.KOLOM_NAMA_TABLE_MHS, mahasiswa.getNamaMhs());
        values.put(MySQLiteHelper.KOLOM_FOTO_TABLE_MHS, mahasiswa.getFotoMhs());
        values.put(MySQLiteHelper.KOLOM_KELAMIN_TABLE_MHS, mahasiswa.getKelaminMhs());
        values.put(MySQLiteHelper.KOLOM_TGLLAHIR_TABLE_MHS, mahasiswa.getTglLahirMhs());
        values.put(MySQLiteHelper.KOLOM_TELP_TABLE_MHS, mahasiswa.getTelpMhs());
        values.put(MySQLiteHelper.KOLOM_ALAMAT_TABLE_MHS, mahasiswa.getAlamatMhs());
        values.put(MySQLiteHelper.KOLOM_EMAIL_TABLE_MHS, mahasiswa.getEmailMhs());

        mahasiswa_insert = db.insert(MySQLiteHelper.NAMA_TABLE_MHS, null, values);
        db.close();
        return mahasiswa_insert;
    }

    public Mahasiswa ambilSatuMahasiswa(String cariNrpMhs) throws SQLException{
        SQLiteDatabase db = mySQLiteHelper.getReadableDatabase();
        /*
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
        Cursor cursor = db.rawQuery(selectQuery, new String [] { cariNrpMhs } );
        */
        Mahasiswa mahasiswa = new Mahasiswa();

        Cursor cursor= db.query(MySQLiteHelper.NAMA_TABLE_MHS, null, MySQLiteHelper.KOLOM_NRP_TABLE_MHS + "= '" + cariNrpMhs+"'", null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                mahasiswa.setNrpMhs(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.KOLOM_NRP_TABLE_MHS)));
                mahasiswa.setNamaMhs(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.KOLOM_NAMA_TABLE_MHS)));
                mahasiswa.setFotoMhs(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.KOLOM_FOTO_TABLE_MHS)));
                mahasiswa.setKelaminMhs(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.KOLOM_KELAMIN_TABLE_MHS)));
                mahasiswa.setTglLahirMhs(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.KOLOM_TGLLAHIR_TABLE_MHS)));
                mahasiswa.setTelpMhs(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.KOLOM_TELP_TABLE_MHS)));
                mahasiswa.setAlamatMhs(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.KOLOM_ALAMAT_TABLE_MHS)));
                mahasiswa.setEmailMhs(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.KOLOM_EMAIL_TABLE_MHS)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return mahasiswa;
    }

    public ArrayList<Mahasiswa> ambilSemuaMahasiswa(){
        SQLiteDatabase db = mySQLiteHelper.getReadableDatabase();

        ArrayList<Mahasiswa> mahasiswaSemua = new ArrayList<Mahasiswa>();

        Cursor cursor;
        //boolean ambilBerhasil = false;
        cursor = db.query(MySQLiteHelper.NAMA_TABLE_MHS, null, null, null, null, null, null);
        int counter = 0;
        if(cursor.moveToFirst()){
            while(cursor.isAfterLast()==false){
                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNrpMhs(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.KOLOM_NRP_TABLE_MHS)));
                mahasiswa.setNamaMhs(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.KOLOM_NAMA_TABLE_MHS)));
                mahasiswa.setFotoMhs(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.KOLOM_FOTO_TABLE_MHS)));
                mahasiswa.setKelaminMhs(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.KOLOM_KELAMIN_TABLE_MHS)));
                mahasiswa.setTglLahirMhs(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.KOLOM_TGLLAHIR_TABLE_MHS)));
                mahasiswa.setTelpMhs(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.KOLOM_TELP_TABLE_MHS)));
                mahasiswa.setAlamatMhs(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.KOLOM_ALAMAT_TABLE_MHS)));
                mahasiswa.setEmailMhs(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.KOLOM_EMAIL_TABLE_MHS)));
                mahasiswaSemua.add(mahasiswa);
                counter++;

                cursor.moveToNext();
            }
            Log.d("DatabaseConnector", "ambilSemuaMhs berhasil, sebanyak : "+counter);
            cursor.close();
            db.close();
        }else{
            Log.d("DatabaseConnector", "ambilSemuaMhs dari Db gagal");
            cursor.close();
            db.close();
        }
        return mahasiswaSemua;
    }

    public long updateMhs(Mahasiswa mahasiswa){
        long statusUpdate = 0;

        SQLiteDatabase db = mySQLiteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.KOLOM_NRP_TABLE_MHS, mahasiswa.getNrpMhs());
        values.put(MySQLiteHelper.KOLOM_NAMA_TABLE_MHS, mahasiswa.getNamaMhs());
        values.put(MySQLiteHelper.KOLOM_FOTO_TABLE_MHS, mahasiswa.getFotoMhs());
        values.put(MySQLiteHelper.KOLOM_KELAMIN_TABLE_MHS, mahasiswa.getKelaminMhs());
        values.put(MySQLiteHelper.KOLOM_TGLLAHIR_TABLE_MHS, mahasiswa.getTglLahirMhs());
        values.put(MySQLiteHelper.KOLOM_TELP_TABLE_MHS, mahasiswa.getTelpMhs());
        values.put(MySQLiteHelper.KOLOM_ALAMAT_TABLE_MHS, mahasiswa.getAlamatMhs());
        values.put(MySQLiteHelper.KOLOM_EMAIL_TABLE_MHS, mahasiswa.getEmailMhs());

        statusUpdate = db.update(MySQLiteHelper.NAMA_TABLE_DOSEN, values, MySQLiteHelper.KOLOM_NRP_TABLE_MHS + "= '" + mahasiswa.getNrpMhs() + "'", null );

        if(statusUpdate==-1){
            Log.d("DatabaseConnector", "Update gagal: "+ mahasiswa.toString());
        }else{
            Log.d("DatabaseConnector", "Update berhasil: "+ mahasiswa.toString());
        }
        return statusUpdate;
    }

    public void deleteMhs(String deleteNrpMhs){
        SQLiteDatabase db = mySQLiteHelper.getWritableDatabase();

        int statusDelete = 0;
        statusDelete = db.delete(MySQLiteHelper.NAMA_TABLE_MHS, MySQLiteHelper.KOLOM_NRP_TABLE_MHS + "= '" + deleteNrpMhs + "'", null);

        Log.d("DatabaseConnector", "Berhasil delete, sebanyak: "+ statusDelete);
        db.close();

        //db.delete(Mahasiswa.TABLE, Mahasiswa.KEY_nrpMhs + "=?", new String[] {deleteNrpMhs});
        //db.close();
    }

    public long tambahDosen(Dosen dosen){
        long dosenInsert = 0;

        SQLiteDatabase db = mySQLiteHelper.getWritableDatabase();
        ContentValues values  = new ContentValues();
        values.put(MySQLiteHelper.KOLOM_NIP_TABLE_DOSEN, dosen.getNipDosen());
        values.put(MySQLiteHelper.KOLOM_NAMA_TABLE_DOSEN, dosen.getNamaDosen());
        values.put(MySQLiteHelper.KOLOM_FOTO_TABLE_DOSEN,dosen.getFotoDosen());
        values.put(MySQLiteHelper.KOLOM_PASSWORD_TABLE_DOSEN, dosen.getPasswordDosen());
        values.put(MySQLiteHelper.KOLOM_EMAIL_TABLE_DOSEN, dosen.getEmailDosen());

        dosenInsert = db.insert(MySQLiteHelper.NAMA_TABLE_DOSEN, null, values);
        db.close();
        return dosenInsert;
    }

    public Dosen ambilSatuDosen(String cariNipDosen){
        SQLiteDatabase db = mySQLiteHelper.getReadableDatabase();

        Dosen dosen = new Dosen();

        Cursor cursor= db.query(MySQLiteHelper.NAMA_TABLE_DOSEN, null, MySQLiteHelper.KOLOM_NIP_TABLE_DOSEN + "= '" + cariNipDosen+"'", null, null, null, null);

        if(cursor.moveToFirst()){
            Log.d("DatabaseConnector", "ambilSatuDosen berhasil");
            do{
                dosen.setNipDosen(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.KOLOM_NIP_TABLE_DOSEN )));
                dosen.setNamaDosen(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.KOLOM_NAMA_TABLE_DOSEN )));
                dosen.setPasswordDosen(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.KOLOM_PASSWORD_TABLE_DOSEN )));
                dosen.setFotoDosen(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.KOLOM_FOTO_TABLE_DOSEN)));
                dosen.setEmailDosen(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.KOLOM_EMAIL_TABLE_DOSEN )));
            }while (cursor.moveToNext());
            cursor.close();
            db.close();
            return dosen;
        }else{
            Log.d("DatabaseConnector", "ambilSatuDosen tidak berhasil");
            cursor.close();
            return null;
        }
    }
}
