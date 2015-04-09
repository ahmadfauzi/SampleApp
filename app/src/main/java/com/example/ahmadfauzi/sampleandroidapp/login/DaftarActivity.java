package com.example.ahmadfauzi.sampleandroidapp.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
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

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DaftarActivity extends ActionBarActivity {

    public int GALLERY_REQUEST_CODE = 1;
    public int CAMERA_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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


}
