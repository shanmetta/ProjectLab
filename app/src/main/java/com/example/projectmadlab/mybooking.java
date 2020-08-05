package com.example.projectmadlab;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mybooking extends AppCompatActivity {


    databaseHelper2 db;

    EditText nameTxt, adressTxt, checkinDate, checkoutDate, totalpriceTxt, idText;
    Cursor cursor ;

    private AlertDialog.Builder build;
   private SQLiteDatabase dataBase;
    private  int temp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybooking);
        db = new databaseHelper2(this);
        final ArrayList<HashMap<String, String>> dataList = db.GetData ();
        final ListView booklv = findViewById(R.id.List_vview);
        final ListAdapter adapter = new SimpleAdapter(mybooking.this, dataList, R.layout.isimybooking, new String[]{"ID", "name", "address", "checkindate", "checkoutdate", "totalprice"}, new int[]
                {R.id.id_txt, R.id.hotelnme_txt, R.id.almt_txt, R.id.cekin_txt, R.id.cekout_txt, R.id.total_txt}) {
        String[] from = new String[]{databaseHelper2.TABLE_NAME};





            @Override
            public View getView(final int position, final View convertView, final ViewGroup parent) {

                View row = super.getView( position, convertView, parent) ;
                 final TextView nama = row.findViewById(R.id.hotelnme_txt);
                 final TextView id = row.findViewById(R.id.id_txt);
                TextView address = row.findViewById(R.id.almt_txt);
                TextView checkin = row.findViewById(R.id.cekin_txt);
                TextView checkout = row.findViewById(R.id.cekout_txt);
                TextView total = row.findViewById(R.id.total_txt);
                Button cancel = row.findViewById(R.id.cancel_btn);
                final  int idbook = Integer.valueOf(id.getText().toString());
                cancel.getTag(position);
                temp = position;



                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        build = new AlertDialog.Builder(mybooking.this);
                        build.setTitle("Are you sure want to delete this?");

                        build.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                db.deleteData(idbook);
                                dataList.remove(position);
                                notifyDataSetChanged();

                            }
                        });

                        build.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        AlertDialog alertDialog = build.create();
                        alertDialog.show();


                    }


                });


                return row;
            }
        };
        booklv.setAdapter(adapter);

    }





    }






