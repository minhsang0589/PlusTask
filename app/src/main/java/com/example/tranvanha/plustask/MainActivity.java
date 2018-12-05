package com.example.tranvanha.plustask;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    private EditText edt1;
    private EditText edt2;
    private Button btnplus, btndownload;
    private TextView tvketqua;
    private int a, b;
    private ImageView imv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(edt1.getText().toString()) && !TextUtils.isEmpty(edt2.getText().toString())) {
                    a = Integer.parseInt(edt1.getText().toString());
                    b = Integer.parseInt(edt2.getText().toString());
                    new PlusTask().execute(a, b);
                } else
                    Toast.makeText(MainActivity.this, "Vui long nhap vao 2 so", Toast.LENGTH_SHORT).show();
            }
        });

        btndownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadImageTask down = new DownloadImageTask();
                down.execute("https://hinhanhdephd.com/wp-content/uploads/2016/01/hinh-anh-thien-nhien-dep-lam-hinh-nen-laptop-14.jpg");
            }
        });


    }

    private void initview() {
        edt1 = (EditText) findViewById(R.id.edt_1);
        edt2 = (EditText) findViewById(R.id.edt_2);
        btnplus = (Button) findViewById(R.id.btn_plus);
        btndownload = (Button) findViewById(R.id.btn_download);
        tvketqua = (TextView) findViewById(R.id.tv_ketqua);
        imv = (ImageView) findViewById(R.id.imv);


    }

    private class PlusTask extends AsyncTask<Integer, Void, Integer> {
        @Override
        protected Integer doInBackground(Integer... input) {
            int So1 = input[0];
            int So2 = input[1];
            int Tong = So1 + So2;
            return Tong;
        }

        @Override
        protected void onPostExecute(Integer Tong1) {
            super.onPostExecute(Tong1);
            tvketqua.setText(Tong1 + "");
            Toast.makeText(MainActivity.this, Tong1 + "", Toast.LENGTH_SHORT).show();
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap bm = null;
            try {
                Log.e("error","ok");
                URL url = new URL(urls[0]);
                InputStream in = url.openConnection().getInputStream();
                bm = BitmapFactory.decodeStream(in);
            } catch (MalformedURLException e) {
                Log.e("error","e");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("error","e1"+e.getMessage());
            }
            return bm;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imv.setImageBitmap(bitmap);
        }
    }

}


