package com.example.ahmadfauzi.sampleandroidapp.dashboard;

import android.app.Activity;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.ahmadfauzi.sampleandroidapp.R;
import com.example.ahmadfauzi.sampleandroidapp.data_model.Mahasiswa;

import java.util.ArrayList;

/**
 * Created by Ahmad Fauzi on 4/19/2015.
 */
public class MahasiswaListFragment extends ListFragment{
    private ArrayList<Mahasiswa> mahasiswaSemua;

    private Callbacks mCallback;

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

    public interface Callbacks{
        public void onItemSelected(Mahasiswa mahasiswa);
    }

    public void onAttach(Activity activity){
        super.onAttach(activity);

        this.mCallback=(Callbacks)activity;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Mahasiswa mahasiswa=mahasiswaSemua.get(position);
        mCallback.onItemSelected(mahasiswa);
    }
}
