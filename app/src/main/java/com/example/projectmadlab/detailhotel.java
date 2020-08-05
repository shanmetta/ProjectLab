package com.example.projectmadlab;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class detailhotel extends AppCompatActivity implements OnMapReadyCallback, DatePickerDialog.OnDateSetListener {

    databaseHelper2 db;
    private GoogleMap mMap;
    private static final String TAG = detailhotel.class.getSimpleName();
    TextView hotelname, addresshotel, hotelphone, checkinTxt,checkOutTxt, totalpriceTxt;
    ImageView gambarHotel;
    Double lat;
    Double lng;
    Button checkinBtn, checkoutBtn, bookBtn;
    boolean startOrEnd = true;
    int harga;
    String date;
    int year, month, day;

    String checkinn, chekoutt;
    SmsManager smsManager;
    int sendSMSPermission;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hotel);

        db = new databaseHelper2(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);


        hotelname = findViewById(R.id.hotelname_txt);
        addresshotel = findViewById(R.id.addresshotel_txt);
        hotelphone = findViewById(R.id.hotel_phone);
        gambarHotel = findViewById(R.id.gambar_hotel);
        checkinBtn = findViewById(R.id.checkin_btn);
        checkoutBtn = findViewById(R.id.checkout_btn2);
        checkinTxt = findViewById(R.id.checkin_txt);
        checkOutTxt = findViewById(R.id.checkout_txt);
        totalpriceTxt = findViewById(R.id.totalprice_txt);
        bookBtn = findViewById(R.id.book_btn);

        smsManager = smsManager.getDefault();

        Intent i = this.getIntent();

        String namehotel = i.getExtras().getString("name");
        String hoteladress = i.getExtras().getString("address");
        String phonehotel = i.getExtras().getString("phone_number");
        String LAT = i.getExtras().getString("LAT");
        String LNG = i.getExtras().getString("LNG");
        String image = i.getExtras().getString("image");
        String price = i.getStringExtra("price_per_night");
        harga = Integer.valueOf(i.getStringExtra("price_per_night"));



        hotelname.setText(namehotel);
        addresshotel.setText(hoteladress);
        hotelphone.setText(phonehotel);


        lat = Double.valueOf(LAT);
        lng = Double.valueOf(LNG);

        Picasso.get().load(image).into(gambarHotel);


        checkinBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                showDatePickerDialog();
                startOrEnd = true;
            }
        });

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog2();
                startOrEnd = false;
            }
        });


        sendSMSPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if (sendSMSPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
        }


        bookBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String name = hotelname.getText().toString();
                String address = addresshotel.getText().toString();
                String checkin = checkinTxt.getText().toString();
                String checkout = checkOutTxt.getText().toString();
                String totalprice = totalpriceTxt.getText().toString();
                databaseHelper2 db = new databaseHelper2(detailhotel.this);
                db.addData(name, address, checkin, checkout, totalprice);
                Toast.makeText(getApplicationContext(), "Data inserted successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(detailhotel.this, Homepages.class);
                startActivity(intent);

                String sms =  name + address + checkin + checkout + totalprice;
                String notel = ("5554");

                if (notel.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "input number", Toast.LENGTH_SHORT).show();

                }else if (sms.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "input sms", Toast.LENGTH_SHORT).show();
                }else {


                    smsManager.sendTextMessage(
                            notel,
                            null, sms,
                            null,
                            null);

                }
            }


        });


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        LatLng hotel = new LatLng(lat,lng);

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lat,lng)));
        mMap.addMarker(new MarkerOptions().position(hotel).title("ini lat lng"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(hotel));
        }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(


                this, this,  Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
    }

    private void showDatePickerDialog2() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(


                this, this,  Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+1);
        datePickerDialog.show();
    }



    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String myDate = "MM/dd/yyyy";


        month = month + 1;
        String date = month + "/" + dayOfMonth + "/" + year;
        if (startOrEnd) {
            checkinTxt.setText(date);
        } else {
            checkOutTxt.setText(date);
        }

        DateFormat tanggal = new SimpleDateFormat(myDate);

        try {


            String todayy = tanggal.format(Calendar.getInstance().getTime());
            Date today = tanggal.parse(todayy);

             Date tanggalchekin = tanggal.parse(checkinTxt.getText().toString());
             Date tanggalcheckout = tanggal.parse(checkOutTxt.getText().toString());

            if(today.after(tanggalchekin)){
                Toast.makeText(detailhotel.this, "check-in date must not be earlier than today", Toast.LENGTH_SHORT).show();
            } else if(tanggalchekin.after(tanggalcheckout)) {
                Toast.makeText(detailhotel.this, "check-out date must not be earlier than check-in date.", Toast.LENGTH_SHORT).show();
            }else{
                long day1 = tanggalchekin.getTime();
                long day2 = tanggalcheckout.getTime();
                long shari = day2 - day1;
                long day = shari;
                long thari = shari / (24 * 60 * 60 * 1000) * harga;
                final String totalharga = Long.toString(thari);
                totalpriceTxt.setText(totalharga);


            }


        } catch (ParseException e) {
            e.printStackTrace();

        }



    }
}

