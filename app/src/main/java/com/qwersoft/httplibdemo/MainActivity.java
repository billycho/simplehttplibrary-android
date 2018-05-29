package com.qwersoft.httplibdemo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.qwersoft.simplehttplibrary.ImageRequest;
import com.qwersoft.simplehttplibrary.JsonArrayRequest;
import com.qwersoft.simplehttplibrary.JsonObjectRequest;
import com.qwersoft.simplehttplibrary.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("http://pastebin.com/raw/wgkJgazE","GET",null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

            }
        });

        jsonArrayRequest.startRequest();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://api.androiddeft.com/json/employee.php", "GET", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("asdaxxx",response.toString());
            }
        });

        jsonObjectRequest.startRequest();

        final ImageRequest imageRequest = new ImageRequest("http://theopentutorials.com/totwp331/wp-content/uploads/totlogo.png", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                    imageView.setImageBitmap(response);
            }
        });

        imageRequest.startRequest();
    }
}
