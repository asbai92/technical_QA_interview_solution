package com.capitoleconsulting.technicalqainterview.testengine.okttp3custominterceptors;

import okhttp3.*;
import okio.Buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CurlLoggingInterceptor implements Interceptor {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private String curlOptions;
    private static final Charset UTF8 = StandardCharsets.UTF_8;

    public void setCurlOptions(String curlOptions) {
        this.curlOptions = curlOptions;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Headers headers = request.headers();

        if (headers.toMultimap().getOrDefault("no-log", List.of()).size() == 1) {
            return chain.proceed(request);
        }

        LOGGER.info("================== BEGIN REQUEST ==================");
        boolean compressed = false;

        String curlCmd = "curl";
        if (curlOptions != null) {
            curlCmd += " " + curlOptions;
        }
        curlCmd += " -X " + request.method();

        for (int i = 0, count = headers.size(); i < count; i++) {
            String name = headers.name(i);
            String value = headers.value(i);
            int start = 0;
            int end = value.length() - 1;
            if (value.charAt(start) == '"' && value.charAt(end) == '"') {
                value = "\\\"" + value.substring(1, end) + "\\\"";
            }
            if ("Accept-Encoding".equalsIgnoreCase(name) && "gzip".equalsIgnoreCase(value)) {
                compressed = true;
            }
            curlCmd += " -H " + "\"" + name + ": " + value + "\"";
        }

        RequestBody requestBody = request.body();
        if (requestBody != null) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            Charset charset = UTF8;
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            // try to keep to a single line and use a subshell to preserve any line breaks
            curlCmd += " --data $'" + buffer.readString(charset).replace("\n", "\\n") + "'";
        }

        curlCmd += ((compressed) ? " --compressed " : " ") + request.url();

        LOGGER.info("╭--- cURL (" + request.url() + ")");
        LOGGER.info(curlCmd);
        LOGGER.info("╰--- (copy and paste the above line to a terminal)");

        return chain.proceed(request);
    }
}
