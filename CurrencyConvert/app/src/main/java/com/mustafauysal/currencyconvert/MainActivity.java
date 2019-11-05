package com.mustafauysal.currencyconvert;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getRates (View view) {

        DownloadData downloadData = new DownloadData();

        try {
            String url = "http://data.fixer.io/api/latest?access_key=110fbb974ff5eef0e81c9d415c9c8fa5";

            downloadData.execute(url);

        } catch (Exception e) {

        }

    }

    private class DownloadData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            String result = "";
            URL url;
            HttpURLConnection httpURLConnection;

            try {

                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                int data = inputStreamReader.read();

                while (data > 0) {

                    char  character = (char) data;
                    result += character;

                    data = inputStreamReader.read();

                }

                return result;


            } catch (Exception e) {
                return null;
            }



        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            //System.out.println("Alınan Data: " + s);

            try {

                JSONObject jsonObject = new JSONObject(s);
                String base = jsonObject.getString("base");
                System.out.println("base: " + base);

                String rates = jsonObject.getString("rates");
                System.out.println("rates: " + rates);

                JSONObject jsonObject1 = new JSONObject(rates);
                String turkishlira = jsonObject1.getString("TRY");
                System.out.println("try: " + turkishlira);

            } catch (Exception e) {

            }
        }
    }

}
