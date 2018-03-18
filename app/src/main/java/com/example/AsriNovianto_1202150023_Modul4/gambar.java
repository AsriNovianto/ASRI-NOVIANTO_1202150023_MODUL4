package com.example.AsriNovianto_1202150023_Modul4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;

public class gambar extends AppCompatActivity {
    private EditText mtext;
    private Button mbutton;
    private ImageView mimage;
    private ProgressDialog progressDialog;
    private static int rotate = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //menginisiasikan widget yang ada
        setContentView(R.layout.activity_gambar);
        mtext =(EditText)findViewById(R.id.editText);
        mbutton =(Button)findViewById(R.id.button3);
        mimage = (ImageView)findViewById(R.id.imageView);
        //sebagai setText contoh icon gambar yang ingin dilihat
        mtext.setText("http://cdn.dota2.com/apps/dota2/images/nav/logo.png");
        //bila button di klik maka akan melakukan inisiasi pengecehkan pada pada savedInstanceState sudah terisi sesuatu atau belom?
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new cari().execute(mtext.getText().toString());
            }
        });

        if(savedInstanceState != null){
            if(savedInstanceState.getInt("LOAD")==1){
                new cari().execute(mtext.getText().toString());
                Log.d("gambar","Rotate Susccess"+savedInstanceState.getInt("LOAD"));
            }
        }


    }
    //method untuk melakukan penyimpanan instatncestate
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("LOAD",rotate);
    }
    //class asynctask
    private class cari extends AsyncTask<String,Bitmap,Bitmap>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //inisialisasi progress dialog
            progressDialog = new ProgressDialog(gambar.this);
            progressDialog = new ProgressDialog(gambar.this);
            progressDialog.setTitle("Loading Data");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMax(100);
            progressDialog.setProgressStyle(0);
            progressDialog.setMessage("Menampilkan Gambar");
            progressDialog.show();

        }
        //pada proses method ini, akan melakukan openstream ke link,lalu decode stream data yang diambil menjadi bitmap lalu dicetak
        @Override
        protected Bitmap doInBackground(String... URL) {
            String imageURL = URL[0];
            Bitmap bitmap = null;
            try{
                //melakuakn openstream ke link
                InputStream input = new java.net.URL(imageURL).openStream();
                //decode stream data yang di ambil menjadi bitmap
                bitmap = BitmapFactory.decodeStream(input);
                publishProgress(BitmapFactory.decodeStream(input));
            }catch (Exception e){
                e.printStackTrace();
            }
            return bitmap;
        }
        // pada proses method ini untuk set image yang telah didownload
        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            mimage.setImageBitmap(result);
            progressDialog.dismiss();
            rotate=1;
        }
    }
}
