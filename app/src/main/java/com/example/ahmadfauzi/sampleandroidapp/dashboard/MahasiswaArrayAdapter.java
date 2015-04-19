package com.example.ahmadfauzi.sampleandroidapp.dashboard;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.support.v4.app.Fragment;

import com.example.ahmadfauzi.sampleandroidapp.R;
import com.example.ahmadfauzi.sampleandroidapp.data_model.Mahasiswa;
import com.example.ahmadfauzi.sampleandroidapp.util.CircularImageView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Ahmad Fauzi on 4/19/2015.
 */
public class MahasiswaArrayAdapter extends ArrayAdapter<Mahasiswa> {
    private Context context;
    private List<Mahasiswa> mahasiswaSemua;

    public MahasiswaArrayAdapter(Context context, int resource, List<Mahasiswa> objects){
        super(context, resource, objects);
        this.context = context;
        this.mahasiswaSemua = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Mahasiswa mahasiswa = mahasiswaSemua.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.mhs_list_item, null);

        CircularImageView image = (CircularImageView) view.findViewById(R.id.list_item_foto_mhs);
        if(mahasiswa.getFotoMhs() == null){
            image.setImageResource(R.drawable.user_photo);
        }else {
            image.setImageBitmap(BitmapFactory.decodeFile(mahasiswa.getFotoMhs()));
        }

        TextView labelNrp=(TextView) view.findViewById(R.id.tvListItemNrpMhs);
        if(mahasiswa.getNrpMhs().isEmpty()){
            labelNrp.setText("NRP : -");
        }else{
            labelNrp.setText("NRP : "+ mahasiswa.getNrpMhs());
        }

        SimpleDateFormat formatTanggal= new SimpleDateFormat("dd/MM/yyyy");
        Date tanggal=new Date();
        TextView tvUmurDetail=(TextView)view.findViewById(R.id.list_item_mhs_umur);
        if(mahasiswa.getTglLahirMhs().isEmpty()){

        }else{
            try{
                tanggal=formatTanggal.parse(mahasiswa.getTglLahirMhs());
            }catch(ParseException e){
                e.printStackTrace();
            }
            Calendar calendar= Calendar.getInstance();
            int tahunSekarang=calendar.get(Calendar.YEAR);
            calendar.setTime(tanggal);
            int tahunLahir=calendar.get(Calendar.YEAR);
            int umur=tahunSekarang-tahunLahir;
            Log.d("MahasiswaArrayAdapter", "umur:" + umur);

            tvUmurDetail.setText(String.valueOf(umur));
        }

        TextView tvAlamatListItem=(TextView)view.findViewById(R.id.list_item_alamat);
        tvAlamatListItem.setText(mahasiswa.getAlamatMhs());
        return view;
    }
}
