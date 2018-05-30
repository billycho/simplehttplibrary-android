package com.qwersoft.httplibdemo;

import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GalleryContract.View{



    private RecyclerView recyclerView;
    private GalleryPresenter galleryPresenter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.imagegallery);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

        galleryPresenter = new GalleryPresenter(this);
        galleryPresenter.loadImages();

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);

        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        galleryPresenter.loadImages();

                    }
                }
        );
    }


    @Override
    public void showImages(ArrayList<String> imageUrls) {
        GalleryAdapter galleryAdapter = new GalleryAdapter(this, imageUrls);
        recyclerView.setAdapter(galleryAdapter);
        swipeRefreshLayout.setRefreshing(false);
    }
}
