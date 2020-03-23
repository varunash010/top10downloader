package com.example.top10downloader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: Starting Async Task");
        DownloadData d = new DownloadData();
        d.execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=10/xml");
        Log.d(TAG, "onCreate: done");

    }

    //AsyncTask<String,Void,String> --> String(accept a string like a url), Void(usually used for a progress bar),
    //String(string after everything is downloaded)
    private class DownloadData extends AsyncTask<String,Void,String>
    {
        private static final String TAG = "DownloadData";
        @Override
        protected String doInBackground(String... strings) {
            Log.d(TAG, "doInBackground: Starting with " + strings[0]);

            String rssFeed = downloadXML(strings[0]);

            if(rssFeed == null)
            {
                Log.e(TAG, "doInBackground: Error trying to download the url " + strings[0]);
            }

            return rssFeed;
        }

        private String downloadXML(String url)
        {
            StringBuilder xml = new StringBuilder();

            try{
                URL httpUrl = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
                int responseCode = connection.getResponseCode();

                Log.d(TAG, "downloadXML: Got the response. Response code : " + responseCode);

                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                char[] charArray = new char[500];
                int charsRead;

                while(true)
                {
                    charsRead = bufferedReader.read(charArray);
                    if(charsRead < 0){
                        break;
                    }
                    else if(charsRead > 0)
                    {
                        xml.append(String.copyValueOf(charArray,0,charsRead));
                    }
                }
                bufferedReader.close();
                return xml.toString();
            }
            catch(MalformedURLException e)
            {
                Log.e(TAG, "downloadXML: URL is invalid. Exception: " + e.getMessage());
            }
            catch(SecurityException s)
            {
                Log.e(TAG, "downloadXML: Received a security exception " + s.getMessage());
                s.printStackTrace();
            }
            catch(IOException e)
            {
                Log.e(TAG, "downloadXML: Caught an exception " + e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d(TAG, "onPostExecute: parameter is \n" + s);
        }
    }
}
