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

public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
        EditText mEditNIP = (EditText) findViewById(R.id.editTextNIP);
        EditText mEditPassword = (EditText) findViewById((R.id.editTextNIPPassword));

        String nip = mEditNIP.getText().toString();
        String password = mEditPassword.getText().toString();

        if(nip.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"NIP atau Password harus diisi", Toast.LENGTH_SHORT).show();
            Log.d("LoginActivity","NIP atau Password kosong");
        }else{
            if(nip.equals("001") && password.equals("001")){
                Toast.makeText(this,"Login berhasil", Toast.LENGTH_SHORT).show();
                Log.d("LoginActivity","Login berhasil");
                Intent intent = new Intent(this, DashboardMainActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this,"NIP atau Password salah", Toast.LENGTH_SHORT).show();
                Log.d("LoginActivity","NIP atau Password salah");
            }
        }
    }
}
