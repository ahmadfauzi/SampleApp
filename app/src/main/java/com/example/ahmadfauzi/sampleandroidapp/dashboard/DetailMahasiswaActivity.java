package com.example.ahmadfauzi.sampleandroidapp.dashboard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmadfauzi.sampleandroidapp.R;
import com.example.ahmadfauzi.sampleandroidapp.data_model.DatabaseConnector;
import com.example.ahmadfauzi.sampleandroidapp.data_model.Mahasiswa;
import com.example.ahmadfauzi.sampleandroidapp.util.CircularImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DetailMahasiswaActivity extends ActionBarActivity {

    boolean isUpdate = false;
    private static final int GALLERY_REQUEST_CODE = 1;
    public static final int CAMERA_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mahasiswa);

        getSupportActionBar().setTitle("Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            MahasiswaDetailFragment fragment = new MahasiswaDetailFragment();
            Bundle bundle = getIntent().getBundleExtra("paketDariDashboard");

            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().add(R.id.detailMhsContainer, fragment).commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_mahasiswa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                break;
            case android.R.id.home:
                finish();
                break;
            case R.id.action_simpan:
                simpanMhs();
                finish();
                break;
            case R.id.action_perbaiki:
                isUpdate = true;
                onMenuEditSelected();
                break;
            case R.id.action_delete:
                deleteMhs();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void simpanMhs() {
        Mahasiswa mahasiswa = new Mahasiswa();

        EditText editTextMhsNrp =  (EditText) findViewById(R.id.editTextDetailNrp);
        mahasiswa.setNrpMhs(editTextMhsNrp.getText().toString());

        EditText editTextMhsNama = (EditText) findViewById(R.id.editTextDetailNama);
        mahasiswa.setNamaMhs(editTextMhsNama.getText().toString());

        RadioButton radioButtonLaki = (RadioButton) findViewById(R.id.radioButtonLaki);
        if(radioButtonLaki.isChecked())
            mahasiswa.setKelaminMhs("Laki-Laki");

        RadioButton radioButtonPerempuan = (RadioButton) findViewById(R.id.radioButtonPerempuan);
        if(radioButtonPerempuan.isChecked())
            mahasiswa.setKelaminMhs("Perempuan");

        EditText editTextMhsTglLahir = (EditText) findViewById(R.id.editTextDetailTglLahir);
        mahasiswa.setTglLahirMhs(editTextMhsTglLahir.getText().toString());

        EditText editTextMhsAlamat = (EditText) findViewById(R.id.editTextDetailAlamat);
        mahasiswa.setAlamatMhs(editTextMhsAlamat.getText().toString());

        EditText editTextMhsTelp = (EditText) findViewById(R.id.editTextDetailTelp);
        mahasiswa.setTelpMhs(editTextMhsTelp.getText().toString());

        EditText editTextMhsEmail = (EditText) findViewById(R.id.editTextDetailEmail);
        mahasiswa.setEmailMhs(editTextMhsEmail.getText().toString());

        CircularImageView ivFotoMhs = (CircularImageView) findViewById(R.id.detailMhsFoto);
        Bitmap foto = ((BitmapDrawable) ivFotoMhs.getDrawable()).getBitmap();
        String fileAlamatFotoMhs = simpanKeSDCARD(foto);
        mahasiswa.setFotoMhs(fileAlamatFotoMhs);

        DatabaseConnector databaseConnector = new DatabaseConnector(this);

        long statusInsert = -1;

        if (isUpdate == true){
            statusInsert = databaseConnector.updateMhs(mahasiswa);
            Log.d("DetailMhsActivity", "updating mhs: " + mahasiswa.toString());
        }else{
            statusInsert = databaseConnector.tambahMahasiswa(mahasiswa);
        }

        if(statusInsert != -1){
            Log.d("DetailMhsActivity","berhasil simpan mhs: " + mahasiswa.toString());
            Toast.makeText(this, "berhasil simpan mhs " + mahasiswa.toString(), Toast.LENGTH_LONG).show();
            finish();
        }else{
            Log.d("DetailMhsActivity","gagal simpan mhs: " + mahasiswa.toString());
            Toast.makeText(this, "gagal simpan mhs "+mahasiswa.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public String simpanKeSDCARD(Bitmap gambar){
        EditText editTextMhsNrp = (EditText) findViewById(R.id.editTextDetailNrp);

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        gambar.compress(Bitmap.CompressFormat.PNG, 40, bytes);
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "academicV6" + File.separator + editTextMhsNrp.getText().toString() + ".png");
        try {
            file.createNewFile();
            FileOutputStream fo=new FileOutputStream(file);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //dikembalikan nama file
        Log.d("DetailMhsActivity","Foto Mhs berhasil disimpan di: " + file.getPath());
        Toast.makeText(this, "Foto mhs berhasil disimpan di: " + file.getPath(), Toast.LENGTH_LONG).show();
        return file.toString();
    }

    private void onMenuEditSelected() {
        TextView textViewDetailMhsNama = (TextView) findViewById(R.id.textDetailMhsNama);

        EditText editTextMhsNrp = (EditText) findViewById(R.id.editTextDetailNrp);
        editTextMhsNrp.setEnabled(false);

        EditText editTextMhsNama = (EditText) findViewById(R.id.editTextDetailNama);
        editTextMhsNama.setEnabled(true);

        RadioButton radioButtonLaki = (RadioButton)findViewById(R.id.radioButtonLaki);
        radioButtonLaki.setEnabled(true);

        RadioButton radioButtonPerempuan = (RadioButton)findViewById(R.id.radioButtonPerempuan);
        radioButtonPerempuan.setEnabled(true);

        Button btnAmbilTanggalLahir = (Button)findViewById(R.id.btnAmbilTanggalLahir);
        btnAmbilTanggalLahir.setEnabled(true);

        EditText editTextMhsTglLahir = (EditText) findViewById(R.id.editTextDetailTglLahir);
        editTextMhsTglLahir.setEnabled(false);

        EditText editTextMhsAlamat = (EditText) findViewById(R.id.editTextDetailAlamat);
        editTextMhsAlamat.setEnabled(true);

        EditText editTextMhsEmail = (EditText) findViewById(R.id.editTextDetailEmail);
        editTextMhsEmail.setEnabled(true);

        EditText editTextMhsTelp = (EditText) findViewById(R.id.editTextDetailTelp);
        editTextMhsTelp.setEnabled(true);

        Button btnAmbilFotoMhs = (Button) findViewById(R.id.btnAmbilFotoMhs);
        btnAmbilFotoMhs.setEnabled(true);

        Button btnAmbilGalleryMhs = (Button) findViewById(R.id.btnAddFromGallery);
        btnAmbilGalleryMhs.setEnabled(true);
    }

    private void deleteMhs() {

    }

    public void ambilFotoMhs(View view){
        EditText editTextMhsNrp = (EditText) findViewById(R.id.editTextDetailNrp);

        if(editTextMhsNrp.getText().length() != 0){
            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA_REQUEST_CODE);
        }else{
            Toast.makeText(this, "MhsDetailFragment: isi Nrp dulu", Toast.LENGTH_LONG).show();
        }
    }

    public void ambilFromGallery(View view){
        EditText editTextMhsNrp = (EditText)findViewById(R.id.editTextDetailNrp);

        if(editTextMhsNrp.getText().length() != 0){
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Pilih foto"), GALLERY_REQUEST_CODE);
        }else{
            Toast.makeText(this, "MhsDetailFragment: isi Nrp dulu", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap fotoMhs = null;
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == CAMERA_REQUEST_CODE){
                Bitmap bm = (Bitmap) data.getExtras().get("data");
                fotoMhs = ThumbnailUtils.extractThumbnail(bm, 300, 300);
                ImageView ivFotoMhs = (ImageView) findViewById(R.id.detailMhsFoto);
                ivFotoMhs.setImageBitmap(fotoMhs);

            }else if(requestCode==GALLERY_REQUEST_CODE){
                Uri selectedImageUri = data.getData();
                try {
                    ParcelFileDescriptor parcelFileDescriptor = getContentResolver().openFileDescriptor(selectedImageUri,"r");
                    FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                    Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                    parcelFileDescriptor.close();
                    CircularImageView ivFotoMhs =(CircularImageView)
                            findViewById(R.id.detailMhsFoto);
                    ivFotoMhs.setImageBitmap(image);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }}

