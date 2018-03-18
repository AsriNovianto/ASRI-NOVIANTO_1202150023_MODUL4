package com.example.AsriNovianto_1202150023_Modul4;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class listview extends AppCompatActivity {
    //membuat inisiasi untuk listview
    private ListView listView;
    private static int rotate =0;
    private ProgressDialog progressDialog;
    private String[] namaMahasiswa = {"Dina","Rian","Gina","Osas","Reno","Lina","Roi","Shifa"
            ,"Asri","NobuLA","Novianto","Isra"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mendeklarasikan komponen apa saja yang digunakan
        setContentView(R.layout.activity_list);
        listView =(ListView)findViewById(R.id.listviewxml);
        Button buton = (Button)findViewById(R.id.button);
        //melakukan set adapter dan menampilkan "namaMahasiswa" ke "simple_lust_item_1"
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, new ArrayList<String>()));
        //bila tombol tersebut diklik maka akan melakukan pengecekan, apakah savedinstanceState sudah berisi sesauatu atau belom?
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TaskList().execute();
            }
        });
        //pengecakan apakah savedInstanceState sudah berisi sesuatu
        if(savedInstanceState != null){
            if(savedInstanceState.getInt("LOAD")==1){
                new TaskList().execute();
                Log.d("Gambar","Rotate Susccess"+savedInstanceState.getInt("LOAD"));
            }
        }
    }
    //method yang menyimpan sesuatu pada package
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("LOAD",rotate);
    }


    //class asynctask
    class TaskList extends AsyncTask<Void,String,String>{
        ArrayAdapter<String> adapter;
        int i;
        @Override
        //pada method onPreExcute akan melakukan inisiasi sebelum proses dilakukan dimana akan mengisi list adapter
        protected void onPreExecute() {
            adapter = (ArrayAdapter<String>) listView.getAdapter();
            progressDialog = new ProgressDialog(listview.this);
            progressDialog.setTitle("Loading Data");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMax(100);
            progressDialog.setProgressStyle(0);
            progressDialog.show();
            i=0;
        }
        //pada method ini akan dilakukan pada saat proses berjalan dimana akan memanggil array si"namaMahasiswa"
        @Override
        protected String doInBackground(Void... voids) {
            for(String Name : namaMahasiswa){
                publishProgress(Name);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "List Nama Mahasiswa";
        }
        //pada method ini akan melakukan update saat publishProgress dikirim dari doInBackground
        @Override
        protected void onProgressUpdate(String... values) {
            adapter.add(values[0]);
            //perhitungan persentase waktu progress
            i++;
            int proses = i *100/namaMahasiswa.length;
            progressDialog.setProgress(proses);
            progressDialog.setMessage(proses+"%");
        }
        //pada method ini berjalan saat sudah selesai beroperasi, akan menghilangkan progressdialog
        @Override
        protected void onPostExecute(String result) {
            progressDialog.hide();
            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
            rotate =1;

        }
    }

}

