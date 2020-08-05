package com.example.projectmadlab;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Homepages extends AppCompatActivity {


    private String TAG = Homepages.class.getSimpleName();

    private ListView lv;
    private ProgressDialog pd;

    List<dataHotel> datahotelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepages);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      datahotelList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.List_view);


        new muncul().execute();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(Homepages.this, mybooking.class);
                startActivity(intent);
                return true;

            case R.id.item2:
                Intent intent2 = new Intent(Homepages.this, MainActivity.class);
                startActivity(intent2);
                return true;

        }


        return super.onOptionsItemSelected(item);
    }


    private class muncul extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(Homepages.this);
            pd.setMessage("Tunggu sebentar ...");
            pd.setCancelable(false);
            pd.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler hh = new HttpHandler();
            String url = "https://bit.ly/34YfE67";
            String jsonstring = hh.makeServiceCall(url);

            Log.e(TAG, "Response from url : " + jsonstring);

            if (jsonstring != null) {
                try {

                    JSONArray hotel = new JSONArray(jsonstring);

                    for (int i = 0; i < hotel.length(); i++) {
                        JSONObject h = hotel.getJSONObject(i);

                        String id = h.getString("id");
                        String name = h.getString("name");
                        String address = h.getString("address");
                        String phone_number = h.getString("phone_number");
                        String price_per_night = Integer.toString(h.getInt("price_per_night"));
                        String LAT = h.getString("LAT");
                        String LNG = h.getString("LNG");
                        String image = h.getString("image");

                        datahotelList.add(new dataHotel(id, name, address, price_per_night, phone_number, image, LAT, LNG));



                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "JSON parsing error : " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Json error" + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }

            } else {
                Log.e(TAG, "Couldnt get json from server");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Json error", Toast.LENGTH_LONG).show();
                    }
                });

            }

            return null;

        }

        @Override
        protected void onPostExecute(Void hasil) {
            super.onPostExecute(hasil);

            if (pd.isShowing())
                pd.dismiss();
            setupLV();

        }

    }


    void setupLV() {

        ListView listView = findViewById(R.id.List_view);
        hotelListAdapter adapter = new hotelListAdapter(this, R.layout.isi_row, datahotelList);

        listView.setAdapter(adapter);
        Intent intent = new Intent(Homepages.this, detailhotel.class);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                for (int i = 0; i < datahotelList.size(); i++) {
                    if (position == i) {
                        Intent intent = new Intent(getApplicationContext(), detailhotel.class);


                        dataHotel hotel = datahotelList.get(i);

                        intent.putExtra("name", hotel.getName());
                        intent.putExtra("address", hotel.getAddress());
                        intent.putExtra("phone_number", hotel.getPhone_number());
                        intent.putExtra("LAT", hotel.getLAT());
                        intent.putExtra("LNG", hotel.getLNG());
                        intent.putExtra("image", hotel.getImage());
                        intent.putExtra("price_per_night", hotel.getPrice_per_night());

                        startActivity(intent);
                    }
                }


            }
        });


    }

    public class hotelListAdapter extends ArrayAdapter<dataHotel> {


        List<dataHotel> hotelList;
        Context context;
        int resource;

        public hotelListAdapter(Context context, int resource, List<dataHotel> hotelList) {
            super(context, resource, hotelList);
            this.context = context;
            this.resource = resource;
            this.hotelList = hotelList;

        }


        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View row = layoutInflater.inflate(resource, null, false);
            TextView namaHotel = row.findViewById(R.id.hotelname_txt);
            TextView alamatHotel = row.findViewById(R.id.addresshotel_txt);
            TextView hotel_price = row.findViewById(R.id.pricehotel_txt);
            ImageView gambarhotel = row.findViewById(R.id.gambarHotel);


            dataHotel hotel = hotelList.get(position);


            namaHotel.setText(hotel.getName());
            alamatHotel.setText(hotel.getAddress());
            hotel_price.setText(hotel.getPrice_per_night());
            Picasso.get().load(hotel.getImage()).into(gambarhotel);

            return row;


        }


    }

}
