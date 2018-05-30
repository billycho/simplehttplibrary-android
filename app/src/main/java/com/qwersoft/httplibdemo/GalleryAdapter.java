package com.qwersoft.httplibdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qwersoft.simplehttplibrary.ImageRequest;
import com.qwersoft.simplehttplibrary.Response;

import java.util.ArrayList;
/**
 * Created by IT02106 on 30/05/2018.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    private ArrayList<String> galleryList;
    private Context context;

    public GalleryAdapter(Context context, ArrayList<String> galleryList) {
        this.galleryList = galleryList;
        this.context = context;
    }

    @NonNull
    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final @NonNull GalleryAdapter.ViewHolder holder, final int position) {

        ImageRequest imageRequest = new ImageRequest(galleryList.get(position), new Response.Listener<Bitmap>() {
            @Override
            public void onProgress() {
                Log.d("asdaxxx","Processing" + galleryList.get(position));
            }

            @Override
            public void onResponse(Bitmap response) {
                holder.img.setImageBitmap(response);

            }
        });

        imageRequest.startRequest();
//       holder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
//       holder.img.setImageResource((galleryList.get(position)));
    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView img;
        public ViewHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.img);
        }
    }
}
