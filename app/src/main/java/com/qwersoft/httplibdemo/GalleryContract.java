package com.qwersoft.httplibdemo;

import java.util.ArrayList;

/**
 * Created by IT02106 on 30/05/2018.
 */

public interface GalleryContract {
    interface View
    {
        void showImages(ArrayList<String> imageUrls);
    }

    interface Presenter
    {
        void loadImages();

    }
}
