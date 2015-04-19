package com.example.ahmadfauzi.sampleandroidapp.dashboard;

import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.util.Log;

import com.example.ahmadfauzi.sampleandroidapp.R;
import com.example.ahmadfauzi.sampleandroidapp.data_model.Mahasiswa;

import java.util.ArrayList;

/**
 * Created by Ahmad Fauzi on 4/19/2015.
 */
public class MahasiswaListFragment extends ListFragment{
    private ArrayList<Mahasiswa> mahasiswaSemua;

    public MahasiswaListFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getArguments();
        mahasiswaSemua = (ArrayList<Mahasiswa>) b.getSerializable("mahasiswaList");
        Log.d("MahasiswaListFragment", "diterima data mhs sebanyak: " + mahasiswaSemua.size());

        MahasiswaArrayAdapter adapter = new MahasiswaArrayAdapter(getActivity(), R.layout.mhs_list_item, mahasiswaSemua);
        setListAdapter(adapter);
    }
}
