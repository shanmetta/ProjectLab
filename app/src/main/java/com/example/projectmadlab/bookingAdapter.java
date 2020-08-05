/*package com.example.projectmadlab;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.List;

 class bookingCustomAdapter extends ArrayAdapter<dataBooking> {

    List<dataBooking> mdataBooking;
    Context context;
    int resources;
    databaseHelper2 db = new databaseHelper2(this.context);

    public bookingCustomAdapter(@NonNull Context context, int resource, List<dataBooking> mdataBooking) {
        super(context, resource, mdataBooking);
        this.context = context;
        this.resources = resource;
        this.mdataBooking = mdataBooking;
       // db = new databaseHelper2(context);


    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View row = layoutInflater.inflate(R.layout.isimybooking, null, false);
        TextView nama = row.findViewById(R.id.hotelnme_txt);
        TextView address = row.findViewById(R.id.almt_txt);
        TextView checkin = row.findViewById(R.id.cekin_txt);
        TextView checkout = row.findViewById(R.id.cekout_txt);
        TextView total = row.findViewById(R.id.total_txt);
        final Button cancel = row.findViewById(R.id.cancel_btn);

        dataBooking booking = mdataBooking.get(position);

        nama.setText(booking.getName());
        address.setText(booking.getAddress());
        checkin.setText(booking.getCekin());
        checkout.setText(booking.getCekout());
        total.setText(booking.getTotalharga());

        cancel.setTag(position);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,cancel.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        return row;

    }
/*
    private void remove(final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Are you sure want do delete this?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(),"Has been Remove",Toast.LENGTH_SHORT).show();
                mdataBooking.remove(position);
                notifyDataSetChanged();
            }


        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


}


 */



