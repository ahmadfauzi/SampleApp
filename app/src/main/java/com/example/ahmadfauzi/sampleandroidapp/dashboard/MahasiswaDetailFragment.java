package com.example.ahmadfauzi.sampleandroidapp.dashboard;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.ahmadfauzi.sampleandroidapp.R;
import com.example.ahmadfauzi.sampleandroidapp.data_model.Mahasiswa;
import com.example.ahmadfauzi.sampleandroidapp.data_model.MySQLiteHelper;
import com.example.ahmadfauzi.sampleandroidapp.util.CircularImageView;

/**
 * Created by Ahmad Fauzi on 4/20/2015.
 */
public class MahasiswaDetailFragment extends Fragment{
    Mahasiswa mahasiswa;
    EditText editTextMhsNrp, editTextMhsNama, editTextMhsEmail, editTextMhsAlamat, editTextMhsTglLahir,
            editTextMhsTelp;
    RadioButton radioButtonLaki, radioButtonPerempuan;
    TextView textViewDetailMhsNama;
    Button btnAmbilFotoMhs, btnAmbilTanggalLahir, btnAmbilGalleryMhs;
    CircularImageView ivFotoMhs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle!=null && bundle.containsKey(MySQLiteHelper.KOLOM_NAMA_TABLE_MHS)){
            mahasiswa = new Mahasiswa(bundle);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_mhs_fragment, container, false);

        ivFotoMhs = (CircularImageView) view.findViewById(R.id.detailMhsFoto);
        textViewDetailMhsNama = (TextView) view.findViewById(R.id.textDetailMhsNama);
        radioButtonLaki = (RadioButton) view.findViewById(R.id.radioButtonLaki);
        radioButtonPerempuan = (RadioButton)view.findViewById(R.id.radioButtonPerempuan);
        editTextMhsNrp = (EditText) view.findViewById(R.id.editTextDetailNrp);
        editTextMhsNama = (EditText) view.findViewById(R.id.editTextDetailNama);
        editTextMhsTglLahir = (EditText) view.findViewById(R.id.editTextDetailTglLahir);
        btnAmbilTanggalLahir =(Button) view.findViewById(R.id.btnAmbilTanggalLahir);
        editTextMhsAlamat = (EditText) view.findViewById(R.id.editTextDetailAlamat);
        editTextMhsEmail = (EditText) view.findViewById(R.id.editTextDetailEmail);
        editTextMhsTelp = (EditText) view.findViewById(R.id.editTextDetailTelp);
        btnAmbilFotoMhs =(Button)view.findViewById(R.id.btnAmbilFotoMhs);
        btnAmbilGalleryMhs =(Button)view.findViewById(R.id.btnAddFromGallery);

        if (mahasiswa != null) {
            if(mahasiswa.getFotoMhs() == null){
                ivFotoMhs.setImageResource(R.drawable.user_photo);
            }else{
                Bitmap fotoMhs = BitmapFactory.decodeFile(mahasiswa.getFotoMhs());
                ivFotoMhs.setImageBitmap(fotoMhs);
            }
            btnAmbilFotoMhs.setEnabled(false);

            btnAmbilGalleryMhs.setEnabled(false);

            textViewDetailMhsNama.setText(mahasiswa.getNamaMhs());
            editTextMhsNrp.setText(mahasiswa.getNrpMhs());
            editTextMhsNrp.setEnabled(false);
            editTextMhsNama.setText(mahasiswa.getNamaMhs());
            if(mahasiswa.getKelaminMhs().equals("Laki")){
                radioButtonLaki.setChecked(true);
            }else {
                radioButtonPerempuan.setChecked(true);
            }
            radioButtonLaki.setEnabled(false);
            radioButtonPerempuan.setEnabled(false);
            editTextMhsNama.setEnabled(false);
            editTextMhsTglLahir.setText(mahasiswa.getTglLahirMhs());
            editTextMhsTglLahir.setEnabled(false);
            btnAmbilTanggalLahir.setEnabled(false);
            editTextMhsAlamat.setText(mahasiswa.getAlamatMhs());
            editTextMhsAlamat.setEnabled(false);
            editTextMhsTelp.setText(mahasiswa.getTelpMhs());
            editTextMhsTelp.setEnabled(false);
            editTextMhsEmail.setText(mahasiswa.getEmailMhs());
            editTextMhsEmail.setEnabled(false);
        }
        return view;
    }
}
