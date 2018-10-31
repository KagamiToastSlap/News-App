package com.example.neope.newsapp;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class NetworkUtils {
    public static final String BASE_URL = "https://newsapi.org/v1/";
    public static final String QUERY_PARAMETER = "b047636108d94743b79e9473661bdc4b";

   public static URL buildURL (){
        Uri.Builder builder = Uri.parse(BASE_URL).buildUpon()
                .appendPath("articles")
                .appendQueryParameter("source", "the-next-web")
                .appendQueryParameter("sortBy", "latest")
                .appendQueryParameter("apiKey", QUERY_PARAMETER);

              URL myURL = null;
              try
              {
                  myURL = new URL(builder.toString());
              }
              catch (MalformedURLException e)
              {
                  e.printStackTrace();
              }
       return myURL;
   }

   public static String getResponseFromHttpUrl(URL url) throws IOException
   {
       HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
       try
       {
           InputStream in = urlConnection.getInputStream();

           Scanner scanner = new Scanner(in);
           scanner.useDelimiter("\\A");

           boolean hasInput = scanner.hasNext();
           if(hasInput)
           {
               return scanner.next();
           }
           else
           {
               return null;
           }
       }
       finally
       {
           urlConnection.disconnect();
       }
   }
}


