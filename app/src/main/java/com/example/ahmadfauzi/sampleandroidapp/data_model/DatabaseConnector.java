package com.example.ahmadfauzi.sampleandroidapp.data_model;

import java.util.ArrayList;

/**
 * Created by 5111100057 on 4/6/2015.
 */
public interface DatabaseConnector {

    void open();
    void close();
    long tambahMahasiswa(Mahasiswa mahasiswa);
    Mahasiswa ambilSatuMahasiswa(String cariNrpMhs);
    ArrayList<Mahasiswa> ambilSemuaMhs();
    long updateMhs(Mahasiswa mahasiswa);
    void deleteMhs(String deleteNrpMhs);
    long tambahDosen(Dosen dosen);
    Dosen ambilSatuDosen(String cariNipDosen);
}
