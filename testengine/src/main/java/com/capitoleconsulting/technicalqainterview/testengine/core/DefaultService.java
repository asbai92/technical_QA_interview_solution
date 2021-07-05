package com.capitoleconsulting.technicalqainterview.testengine.core;

import com.capitoleconsulting.technicalqainterview.testengine.constants.Constant;
import com.capitoleconsulting.technicalqainterview.testengine.interfaces.Service;
import com.capitoleconsulting.technicalqainterview.testengine.models.ServiceInformation;
import com.capitoleconsulting.technicalqainterview.testengine.utils.RestClient;
import io.vavr.control.Option;
import okhttp3.Response;

import java.net.MalformedURLException;
import java.util.HashMap;

public class DefaultService implements Service {
    private final String serviceUrl;
    private final Option<String> servicePort;

    /**
     * Constructor
     *
     * @param serviceInformation
     */
    protected DefaultService(ServiceInformation serviceInformation){
        this.serviceUrl = serviceInformation.getServiceUrl();
        this.servicePort = serviceInformation.getServicePort();
    }

    /**
     * Send a POST request to the service
     *
     * @param endPoint endpoint we want to send the request on
     * @param jsonBody the body we want to send (serialized Json)
     * @param headers  the headers we want to add in our request
     * @return the response
     * @throws MalformedURLException
     */
    @Override
    public Response sendPostToService(String endPoint, String jsonBody, HashMap<String, String> headers) throws MalformedURLException {
        if (this.servicePort.isDefined())
            return RestClient.sendPost(this.serviceUrl, this.servicePort.get(), endPoint, jsonBody, headers);
        else if (this.serviceUrl.contains("https"))
            return RestClient.sendPost(this.serviceUrl, Constant.HTTPS_PORT, endPoint, jsonBody, headers);
        else if (this.serviceUrl.contains("http"))
            return RestClient.sendPost(this.serviceUrl, Constant.HTTP_PORT, endPoint, jsonBody, headers);
        else
            throw new MalformedURLException("Malformed URL, please check your configuration");
    }

    /**
     * Send a get request to the service
     *
     * @param endPoint endpoint we want to send the request on
     * @param queries  queries that will be added to the url (Hashmap)
     * @param headers  the headers we want to add in our request
     * @return the response
     * @throws MalformedURLException
     */
    @Override
    public Response sendGetToService(String endPoint, HashMap<String, String> queries, HashMap<String, String> headers) throws MalformedURLException {
        if (this.servicePort.isDefined())
            return RestClient.sendGet(this.serviceUrl, this.servicePort.get(), endPoint, queries, headers);
        else if (this.serviceUrl.contains("https"))
            return RestClient.sendGet(this.serviceUrl, Constant.HTTPS_PORT, endPoint, queries, headers);
        else if (this.serviceUrl.contains("http"))
            return RestClient.sendGet(this.serviceUrl, Constant.HTTP_PORT, endPoint, queries, headers);
        else
            throw new MalformedURLException("Malformed URL, please check your configuration");
    }

    /**
     * Send a PUT request to the service
     *
     * @param endPoint endpoint we want to send the request on
     * @param jsonBody the body we want to send (serialized Json)
     * @param headers  the headers we want to add in our request
     * @return the response
     * @throws MalformedURLException
     */
    @Override
    public Response sendPutToService(String endPoint, String jsonBody, HashMap<String, String> headers) throws MalformedURLException {
        if (this.servicePort.isDefined())
            return RestClient.sendPut(this.serviceUrl, this.servicePort.get(), endPoint, jsonBody, headers);
        else if (this.serviceUrl.contains("https"))
            return RestClient.sendPut(this.serviceUrl, Constant.HTTPS_PORT, endPoint, jsonBody, headers);
        else if (this.serviceUrl.contains("http"))
            return RestClient.sendPut(this.serviceUrl, Constant.HTTP_PORT, endPoint, jsonBody, headers);
        else
            throw new MalformedURLException("Malformed URL, please check your configuration");
    }
}
