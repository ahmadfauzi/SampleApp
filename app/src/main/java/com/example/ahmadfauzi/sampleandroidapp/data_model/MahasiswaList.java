package com.example.ahmadfauzi.sampleandroidapp.data_model;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 5111100057 on 4/6/2015.
 */
public class MahasiswaList implements Serializable{
    private List<Mahasiswa> mahasiswaSemua = new ArrayList<Mahasiswa>();
    DatabaseConnector databaseConnector;

    public MahasiswaList (Context context){
        databaseConnector = new DatabaseConnector(context);
        try {
            mahasiswaSemua = databaseConnector.ambilSemuaMahasiswa();
        }catch (Exception e){
            if(mahasiswaSemua.isEmpty()){
                Mahasiswa mahasiswa = new Mahasiswa();

                mahasiswa.setNrpMhs("kosong");
                mahasiswa.setNamaMhs("data masih kosong");
                addItem(mahasiswa);
            }
        }
    }

    public List<Mahasiswa> getMahasiswaSemua(){
        return mahasiswaSemua;
    }

    private void addItem(Mahasiswa item) {
        mahasiswaSemua.add(item);
    }

}
