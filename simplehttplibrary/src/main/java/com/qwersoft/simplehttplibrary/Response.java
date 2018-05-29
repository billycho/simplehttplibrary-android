package com.qwersoft.simplehttplibrary;

/**
 * Created by IT02106 on 29/05/2018.
 */

public class Response {
    public interface Listener<T> {
        void onResponse(T response);
    }
}
