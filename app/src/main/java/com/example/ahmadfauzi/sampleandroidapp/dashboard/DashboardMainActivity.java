package com.example.ahmadfauzi.sampleandroidapp.dashboard;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ahmadfauzi.sampleandroidapp.R;
import com.example.ahmadfauzi.sampleandroidapp.data_model.DatabaseConnector;
import com.example.ahmadfauzi.sampleandroidapp.data_model.Mahasiswa;

import java.util.ArrayList;

public class DashboardMainActivity extends ActionBarActivity implements MahasiswaListFragment.Callbacks{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_main);
    }

    @Override
    public void onResume(){
        super.onResume();
        refreshList();
    }

    public void refreshList(){
        Log.d("DashboardMainActivity", "refresh dijalankan");

        DatabaseConnector databaseConnector = new DatabaseConnector(this);
        ArrayList<Mahasiswa> mahasiswaSemua = (ArrayList<Mahasiswa>) databaseConnector.ambilSemuaMahasiswa();

        MahasiswaListFragment fragment = new MahasiswaListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("mahasiswaList", mahasiswaSemua);
        fragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.dashboardMainActivityContainer, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(Mahasiswa mahasiswa) {
        Bundle bundle = mahasiswa.toBundle();

        Intent detailIntent = new Intent(this, DetailMahasiswaActivity.class);
        detailIntent.putExtra("paketDariDashboard",bundle);
        startActivity(detailIntent);
    }
}
