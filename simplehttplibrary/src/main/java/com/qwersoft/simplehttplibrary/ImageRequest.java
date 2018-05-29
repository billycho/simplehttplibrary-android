package com.qwersoft.simplehttplibrary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by IT02106 on 29/05/2018.
 */

public class ImageRequest {
    private Response.Listener listener;
    private String url;

    public ImageRequest(String url, Response.Listener<Bitmap> listener)
    {
        this.url = url;
        this.listener = listener;
    }

    private  Bitmap downloadImage(String url)
    {
        Bitmap bitmap = null;
        InputStream stream = null;
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inSampleSize = 1;

        try
        {
            stream = getHttpConnection(url);
            bitmap = BitmapFactory.decodeStream(stream,null, bmOptions);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    private InputStream getHttpConnection(String urlString)  throws IOException
    {
        InputStream stream = null;
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();

        try
        {
            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                stream = httpURLConnection.getInputStream();
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return stream;
    }

    public void startRequest()
    {
        new ImageLoader().execute();
    }

    private class ImageLoader extends AsyncTask<String, Void, Bitmap>
    {

        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;

            map = downloadImage(url);

            return map;
        }

        @Override
        protected void onPostExecute(Bitmap result)
        {
            listener.onResponse(result);
        }




    }
}
