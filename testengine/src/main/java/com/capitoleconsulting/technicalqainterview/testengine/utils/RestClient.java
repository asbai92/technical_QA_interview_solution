package com.capitoleconsulting.technicalqainterview.testengine.utils;

import com.capitoleconsulting.technicalqainterview.testengine.okttp3custominterceptors.CurlLoggingInterceptor;
import com.capitoleconsulting.technicalqainterview.testengine.okttp3custominterceptors.ResponseInterceptor;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RestClient {

    public static Response sendGet(String baseUrl,  String port, String endPoint, HashMap<String, String> queries, HashMap<String, String> headers){

        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(new CurlLoggingInterceptor()).addInterceptor(new ResponseInterceptor()).build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl + port + endPoint ).newBuilder();
        for (Map.Entry<String, String> query : queries.entrySet()) {
            urlBuilder.addQueryParameter(query.getKey(), query.getValue());
        }
        String url = urlBuilder.build().toString();

        Headers.Builder builder = new Headers.Builder();
        for (Map.Entry<String, String> header : headers.entrySet()) {
            builder.add(header.getKey(), header.getValue());
        }
        Headers h = builder.build();

        Request request = new Request.Builder()
                .url(url)
                .headers(h)
                .build();

        try {
            Call call = client.newCall(request);
            return call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Response sendPost(String baseUrl, String port,String endPoint, String jsonBody, HashMap<String, String> headers){

        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(new CurlLoggingInterceptor()).addInterceptor(new ResponseInterceptor()).build();
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl + port + endPoint ).newBuilder();
        String url = urlBuilder.build().toString();

        RequestBody formBody = RequestBody.create(JSON, jsonBody);

        Headers.Builder builder = new Headers.Builder();
        for (Map.Entry<String, String> header : headers.entrySet()) {
            builder.add(header.getKey(), header.getValue());
        }
        Headers h = builder.build();

        Request request = new Request.Builder()
                .url(url)
                .headers(h)
                .post(formBody)
                .build();

        try {
            Call call = client.newCall(request);
            return call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Response sendPut(String baseUrl, String port, String endPoint, String jsonBody, HashMap<String, String> headers){

        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(new CurlLoggingInterceptor()).addInterceptor(new ResponseInterceptor()).build();
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl + port + endPoint ).newBuilder();
        String url = urlBuilder.build().toString();

        RequestBody formBody = RequestBody.create(JSON, jsonBody);

        Headers.Builder builder = new Headers.Builder();
        for (Map.Entry<String, String> header : headers.entrySet()) {
            builder.add(header.getKey(), header.getValue());
        }
        Headers h = builder.build();

        Request request = new Request.Builder()
                .url(url)
                .headers(h)
                .put(formBody)
                .build();

        try {
            Call call = client.newCall(request);
            return call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
