package com.capitoleconsulting.technicalqainterview.testengine.okttp3custominterceptors;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class ResponseInterceptor implements Interceptor {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Headers headers = request.headers();

        if (headers.toMultimap().getOrDefault("no-log", List.of()).size() == 1) return chain.proceed(request);

        Response response = chain.proceed(request);
        MediaType contentType = response.body().contentType();
        String bodyString = response.body().string();
        int statusCode = response.code();

        LOGGER.info("   ╭--- RESPONSE (" + response.code() + ") --- "  );
        LOGGER.info( "   " + bodyString.replaceAll("(\\r|\\n| )", ""));
        LOGGER.info("   ╰------------------------------------------");
        LOGGER.info("   ================== END REQUEST ====================");

        ResponseBody newResponseBody = ResponseBody.create(contentType, bodyString);
        return response.newBuilder().code(statusCode).body(newResponseBody).build();
    }
}
