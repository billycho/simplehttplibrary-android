# simplehttplibrary-android
This is an android library I developed to fetch Json and images from url.<br>
The library has 3 main classes JsonArrayRequest, JsonObjectRequest, and ImageRequest (I use volley library as reference).<br><br>
This library is only for educational purpose as it obvious if you need to fetch json you should use Volley, Retrofit or other more developed HTTP library.

Library usage:

```
          //JsonArrayRequest
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("http://pastebin.com/raw/wgkJgazE","GET",null, new     Response.Listener<JSONArray>() {
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
        
        //ImageRequest
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
```
