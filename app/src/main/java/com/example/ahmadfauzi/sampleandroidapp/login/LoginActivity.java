package com.example.ahmadfauzi.sampleandroidapp.login;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmadfauzi.sampleandroidapp.R;
import com.example.ahmadfauzi.sampleandroidapp.dashboard.DashboardMainActivity;
import com.example.ahmadfauzi.sampleandroidapp.data_model.DatabaseConnector;
import com.example.ahmadfauzi.sampleandroidapp.data_model.Dosen;

public class LoginActivity extends ActionBarActivity {

    EditText mEditNipLogin, mEditPasswordLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEditNipLogin = (EditText) findViewById(R.id.editTextNIP);
        mEditPasswordLogin = (EditText) findViewById(R.id.editTextNIPPassword);

        TextView mTextDaftar = (TextView) findViewById(R.id.textViewDaftar);
        mTextDaftar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, DaftarActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    public void cobaLogin(View view) {
        String nip = "";
        String password;

        nip = mEditNipLogin.getText().toString();
        password = mEditPasswordLogin.getText().toString();
        if(cekLoginValid(nip, password)){
            Intent intent = new Intent(this, DashboardMainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private boolean cekLoginValid(String nip, String password){
        DatabaseConnector databaseConnector = new DatabaseConnector(this);

        String nipDB = "";
        String passwordDB = "";

        Dosen dosenDatabase = databaseConnector.ambilSatuDosen(nip);
        if(dosenDatabase != null){
            nipDB = dosenDatabase.getNipDosen();
            passwordDB = dosenDatabase.getPasswordDosen();
        }else{
            Toast.makeText(this, "Username Tidak Ditemukan", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(password.equals(passwordDB)){
            Toast.makeText(this, "Login Sukses", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            Toast.makeText(this, "Password Salah", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
