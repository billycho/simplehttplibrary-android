package com.qwersoft.httplibdemo;

import android.content.Context;
import android.util.Log;

import com.qwersoft.simplehttplibrary.JsonArrayRequest;
import com.qwersoft.simplehttplibrary.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by IT02106 on 30/05/2018.
 */

public class GalleryPresenter implements GalleryContract.Presenter {

    private final GalleryContract.View galleryView;
    private Context context;
    private JsonArrayRequest jsonArrayRequest;
    private ArrayList<String> imageUrls;

    public GalleryPresenter(GalleryContract.View galleryView)
    {
        this.galleryView = galleryView;
        this.context = (Context) galleryView;
        imageUrls = new ArrayList<String >();
    }

    @Override
    public void loadImages() {
        jsonArrayRequest = new JsonArrayRequest("http://pastebin.com/raw/wgkJgazE","GET",null, new Response.Listener<JSONArray>() {
            @Override
            public void onProgress() {

            }

            @Override
            public void onResponse(JSONArray response) {

                Log.d("asdaxxx",Integer.toString(response.length()));
                try {
                    for(int i = 0;i< response.length();i++)
                    {

                        JSONObject object = (JSONObject) response.get(i);
                        JSONObject userObject = (JSONObject) object.getJSONObject("user");
                        JSONObject profileImage = (JSONObject) userObject.getJSONObject("profile_image");
                        JSONObject urlsImage = (JSONObject) object.getJSONObject("urls");

                        String image1 = profileImage.getString("small");

                        imageUrls.add(image1);
                    }

                    galleryView.showImages(imageUrls);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


        jsonArrayRequest.startRequest();


        //Using JsonArrayRequest class to request the data
    }

}
