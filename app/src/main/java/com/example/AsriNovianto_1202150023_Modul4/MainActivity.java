package com.example.AsriNovianto_1202150023_Modul4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //saat tombol " List Nama Mahasiswa " diklik maka akan dikirim dan ditujukan ke listview.class
    public void listNama(View view) {
        Intent i = new Intent(this,listview.class);
        startActivity(i);
    }

    public void downloadGambar(View view) {
        Intent i = new Intent(this,gambar.class);
        startActivity(i);
    }

}
