package com.example.tranvanha.plustask;

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

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
private EditText edt1;
private EditText edt2;
private Button btnplus,btndownload;
private TextView tvketqua;
private int a,b;
private ImageView imv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(edt1.getText().toString()) && !TextUtils.isEmpty(edt2.getText().toString())){
                    a = Integer.parseInt(edt1.getText().toString());
                b = Integer.parseInt(edt2.getText().toString());
                new PlusTask().execute(a, b);
            }
            else Toast.makeText(MainActivity.this, "Vui long nhap vao 2 so", Toast.LENGTH_SHORT).show();
            }
        });

        btndownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TaiAnhVeAndroid().execute("http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png");
            }
        });


    }

    private void initview() {
        edt1=(EditText)findViewById(R.id.edt_1);
        edt2=(EditText)findViewById(R.id.edt_2);
        btnplus=(Button) findViewById(R.id.btn_plus);
        btndownload=(Button) findViewById(R.id.btn_download);
        tvketqua=(TextView) findViewById(R.id.tv_ketqua);
        imv=(ImageView) findViewById(R.id.imv);


    }

    class PlusTask extends AsyncTask<Integer,Void,Integer>{


    @Override
    protected Integer doInBackground(Integer... input) {
        int So1= input[0];
        int So2=input[1];
        int Tong=So1+So2;
        return Tong;
    }

    @Override
    protected void onPostExecute(Integer Tong) {
        super.onPostExecute(Tong);
        tvketqua.setText(Tong+"");
        Toast.makeText(MainActivity.this, Tong+"", Toast.LENGTH_SHORT).show();
    }
}
class TaiAnhVeAndroid extends AsyncTask<String,Void,Bitmap>{

    @Override
    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }
    protected void onPostExecute(Bitmap result) {
        imv.setImageBitmap(result);
    } }
    //lam tiếp tải ảnh về đặt làm backgroud cho image view;

}

