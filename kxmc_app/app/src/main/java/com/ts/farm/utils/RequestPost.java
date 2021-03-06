package com.ts.farm.utils;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

/**
 * Created by ll on 2017/3/29.
 */
public class RequestPost {

    private RequestQueue requen;
    private Context context;
    private Dialog dg;
    private Response.Listener succeedRespon = null;


    private Response.ErrorListener error = new Response.ErrorListener() {

        public void onErrorResponse(VolleyError paramAnonymousVolleyError) {

            Toast.makeText(context,
                    "服务器正在升级，请稍后重试", Toast.LENGTH_SHORT)
                    .show();
//            Toast.makeText(context,
//                    "出现异常" + paramAnonymousVolleyError.getMessage(), Toast.LENGTH_SHORT)
//                    .show();
        }
    };


    public RequestPost(Context context,Response.Listener<StringRequest> succeedRespon) {
        this.succeedRespon = succeedRespon;
        this.context = context;
    }

    public void requestPost(Map dataMap) {

        requen = Volley.newRequestQueue(context);
        StringRequest localJsonObjectRequest = new StringRequest(Request.Method.POST,
                DataHandler.getBuildUrl(dataMap),succeedRespon ,
                this.error);
        Log.i("com.ts.pp",DataHandler.getBuildUrl(dataMap));
        this.requen.add(localJsonObjectRequest);

    }

}
