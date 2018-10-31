package com.example.neope.newsapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.view.MenuItem;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    NetworkUtils urlTest = new NetworkUtils();
    String TAG = "this is a tag";
    public TextView txtviews;
    String urlText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtviews = findViewById(R.id.textView2);
        URL test = urlTest.buildURL();
        Log.e(TAG, String.valueOf(test));
        NewsQueryTask viewing = new NewsQueryTask();
            viewing.execute();



    }


    class NewsQueryTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
             urlText= NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildURL());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            txtviews.setText(urlText);

        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem news) {
        int itemThatWasClickedId = news.getItemId();
        if (itemThatWasClickedId == R.id.refresh_news) {
            NewsQueryTask refresh = new NewsQueryTask();
            refresh.execute();
            return true;
        }
        return super.onOptionsItemSelected(news);
    }

}


