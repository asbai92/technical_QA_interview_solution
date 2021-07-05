package com.capitoleconsulting.technicalqainterview.testengine.interfaces;

import okhttp3.Response;

import java.net.MalformedURLException;
import java.util.HashMap;

public interface Service {
    Response sendPostToService(String endPoint, String jsonBody, HashMap<String, String> headers) throws MalformedURLException;
    Response sendGetToService(String endPoint, HashMap<String, String> queries, HashMap<String, String> headers) throws MalformedURLException;
    Response sendPutToService(String endPoint, String jsonBody, HashMap<String, String> headers) throws MalformedURLException;
}
