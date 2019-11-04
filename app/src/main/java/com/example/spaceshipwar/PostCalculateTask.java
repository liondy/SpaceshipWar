package com.example.spaceshipwar;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


public class PostCalculateTask {
    protected final String API_SCORE = "http://p3b.labftis.net/api.php";
    protected Context context;
    protected Gson gson;

    public PostCalculateTask(Context context){
        this.context =  context;
        this.gson = new Gson();
    }

    public void getExecute(final String npm){
        RequestQueue request = Volley.newRequestQueue(context);
        String url = this.API_SCORE + "?api_key=" + npm;
        StringRequest str = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("success");
                processResult(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error");
            }
        });
        request.add(str);
    }

    public void postExecute(final String npm, final int order, final int value){
        RequestQueue request = Volley.newRequestQueue(context);
        StringRequest str = new StringRequest(Request.Method.POST, this.API_SCORE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("success");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error");
            }
        }){
            protected Map<String, String> getParameter(){
                Map<String, String> parameter = new HashMap<String, String>();
                parameter.put("api_key", npm);
                parameter.put("order", order + "");
                parameter.put("value", value + "");
                return parameter;
            }
        };
        request.add(str);
    }

    public void processResult(String result){
        int idxHighscore = result.indexOf("value");
        int lastIdx = idxHighscore + 8;
        int i = 0;

        while (true){
            if((int) result.charAt(lastIdx+i)-48 <= 9 && (int) result.charAt(lastIdx+i)-48 >= 0){
                i++;
            }
            else{
                break;
            }
        }
    }
}

