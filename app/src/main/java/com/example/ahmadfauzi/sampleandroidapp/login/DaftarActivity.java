package com.example.ahmadfauzi.sampleandroidapp.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ahmadfauzi.sampleandroidapp.R;
import com.example.ahmadfauzi.sampleandroidapp.data_model.DatabaseConnector;
import com.example.ahmadfauzi.sampleandroidapp.data_model.Dosen;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DaftarActivity extends ActionBarActivity {

    public int GALLERY_REQUEST_CODE = 1;
    public int CAMERA_REQUEST_CODE = 2;

    EditText editTextNIP;
    EditText editTextName;
    EditText editTextPassword;
    EditText editTextEmail;
    String fileFotoDosen ="";
    ImageView civFotoDosen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextNIP = (EditText) findViewById(R.id.editTextNIP);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        civFotoDosen = (ImageView) findViewById(R.id.imageView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_daftar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){
            case android.R.id.home:
                finish();
                break;
            case R.id.action_settings:
                return true;
        }
        /*
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        */

        return super.onOptionsItemSelected(item);
    }

    public void batal(View view) {
        finish();
    }

    public void takeFromGallery(View view) {
        EditText nipDosen = (EditText) findViewById(R.id.editTextNIP);
        if(nipDosen.getText().toString().isEmpty()){
            Toast.makeText(this,"Mohon isi NIP dahulu!", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Pilih foto"), GALLERY_REQUEST_CODE);
        }
    }

    public void takeFromCamera(View view) {
        EditText nipDosen = (EditText) findViewById(R.id.editTextNIP);
        if(nipDosen.getText().toString().isEmpty()){
            Toast.makeText(this, "Mohon isi NIP dulu !", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA_REQUEST_CODE);
        }
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap fotoDosen = null;

        if(resultCode == RESULT_OK){
            if(requestCode == GALLERY_REQUEST_CODE){
                Uri selectedImageUri = data.getData();
                try{
                    ParcelFileDescriptor parcelFileDescriptor = getContentResolver().openFileDescriptor(selectedImageUri, "r");
                    FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                    Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                    parcelFileDescriptor.close();

                    String fileFotoDosen = selectedImageUri.toString();
                    ImageView mIvFotoDosen = (ImageView) findViewById(R.id.imageView);
                    mIvFotoDosen.setImageBitmap(image);
                    Log.d("DaftarActivity","Foto Dosen berhasil diambil dari: " + fileFotoDosen.toString());
                    Toast.makeText(this,"Foto Dosen berhasil diambil: " + fileFotoDosen.toString(), Toast.LENGTH_SHORT).show();
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }else if(requestCode == CAMERA_REQUEST_CODE){
                Bitmap bm = (Bitmap) data.getExtras().get("data");
                if(bm != null){
                    Log.d("DaftarActivity", "Mengambil foto dari kamera berhasil");
                    fotoDosen = ThumbnailUtils.extractThumbnail(bm, 300, 300);
                    ImageView mIvFotoDosen = (ImageView) findViewById(R.id.imageView);
                    mIvFotoDosen.setImageBitmap(fotoDosen);
                }else{
                    Log.d("DaftarActivity", "Mengambil foto dari kamera gagal");
                }
            }
        }
    }

    public void simpanDosen(View view) {
        DatabaseConnector databaseConnector = new DatabaseConnector(this);

        Dosen newDosen = new Dosen();
        //Toast.makeText(this, newDosen.nipDosen, Toast.LENGTH_LONG).show();
        newDosen.nipDosen = editTextNIP.getText().toString();
        newDosen.namaDosen = editTextName.getText().toString();
        newDosen.passwordDosen = editTextPassword.getText().toString();
        newDosen.emailDosen = editTextEmail.getText().toString();

        Bitmap bm = ((BitmapDrawable) civFotoDosen.getDrawable()).getBitmap();
        fileFotoDosen = simpanKeSDCARD(bm);
        newDosen.fotoDosen = fileFotoDosen;

        long statusInsert = databaseConnector.tambahDosen(newDosen);
        if (statusInsert == -1) {
            Toast.makeText(this, "Gagal insert: " + newDosen.toString() + " ke Database", Toast.LENGTH_LONG).show();
            Log.d("Daftar Activity", "gagal insert: " + newDosen.toString());
        } else {
            Toast.makeText(this, "Berhasil insert: " + newDosen.toString() + "NIP: " + statusInsert, Toast.LENGTH_LONG).show();
            Log.d("Daftar Activity", "Berhasil insert: " + newDosen.toString() + "NIP: " + statusInsert);
            finish();
        }
    }


    public String simpanKeSDCARD(Bitmap gambar) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        gambar.compress(Bitmap.CompressFormat.PNG, 40, bytes);
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "academicV6" + File.separator + editTextNIP.getText().toString() + ".png");
        try{
            file.createNewFile();
            FileOutputStream fo = new FileOutputStream(file);
            fo.write(bytes.toByteArray());
            fo.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        Toast.makeText(this,"berhasil simpan ke SDCard: " + file.toString(), Toast.LENGTH_SHORT).show();
        Log.d("DaftarActivity", "berhasil simpan ke SDCard: " + file.toString());

        return  file.toString();
    }

}
