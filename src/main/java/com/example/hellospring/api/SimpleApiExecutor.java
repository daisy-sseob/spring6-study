package com.example.hellospring.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SimpleApiExecutor implements ApiExecutor {
  
  @Override
  public String execute(URI uri) throws Exception {
    return HttpClient.newHttpClient()
        .send(
            HttpRequest.newBuilder()
                .uri(uri)
                .build(),
            HttpResponse.BodyHandlers.ofString()
        )
        .body();
  }
  
}
