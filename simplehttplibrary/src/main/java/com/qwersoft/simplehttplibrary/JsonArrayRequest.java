package com.qwersoft.simplehttplibrary;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

/**
 * Created by IT02106 on 29/05/2018.
 */

public class JsonArrayRequest {
    private InputStream is = null;
    private JSONArray jArray = null;
    private String json = "";
    private HttpURLConnection urlConnection = null;
    private Response.Listener listener;

    private String url,method;
    private Map<String,String> params;


    private JSONArray makeHttpRequest(String url, String method, Map<String, String> params)
    {
        try
        {
            Uri.Builder builder = new Uri.Builder();
            URL urlObj;
            String encodedParams = "";
            if(params != null)
            {
                for(Map.Entry<String, String> entry : params.entrySet())
                {
                    builder.appendQueryParameter(entry.getKey(), entry.getValue());
                }
            }

            if(builder.build().getEncodedQuery() != null)
            {
                encodedParams = builder.build().getEncodedQuery();
            }

            if("GET".equals(method))
            {
                url = url + "?" + encodedParams;
                urlObj = new URL(url);
                urlConnection = (HttpURLConnection) urlObj.openConnection();
                urlConnection.setRequestMethod(method);

            }
            else
            {
                urlObj = new URL(url);
                urlConnection = (HttpURLConnection) urlObj.openConnection();
                urlConnection.setRequestMethod(method);
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlConnection.setRequestProperty("Content-Length", String.valueOf(encodedParams.getBytes().length));
                urlConnection.getOutputStream().write(encodedParams.getBytes());
            }

            urlConnection.connect();

            is = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;

            while((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
            jArray = new JSONArray(json);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        } catch (Exception e) {
            Log.e("Exception", "Error parsing data " + e.toString());
        }

        // return JSON Object
        return jArray;
    }

    public JsonArrayRequest(String url, String method, Map<String,String> params, Response.Listener<JSONArray> listener)
    {
        this.url = url;
        this.method = method;
        this.params = params;
        this.listener = listener;
    }



    public void startRequest()
    {
        new DataLoader().execute();
    }

    private class DataLoader extends AsyncTask<String, String, JSONArray>
    {


        JSONArray response;


        @Override
        protected JSONArray doInBackground(String... strings) {

            response = makeHttpRequest( url,method,null);
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject a = (JSONObject) response.get(i);
                    //Log.d("asdaxxxx",a.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return response;
        }


        @Override
        protected void onPostExecute(JSONArray result)
        {
            listener.onResponse(result);
        }
    }
}
