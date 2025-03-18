package com.example.hellospring.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientApiExecutor implements ApiExecutor {
  
  @Override
  public String execute(URI uri) {
    
    HttpRequest request = HttpRequest.newBuilder()
        .uri(uri)
        .GET()
        .build();

    try (HttpClient httpClient = HttpClient.newBuilder().build()) {
      return httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    
  }
}
