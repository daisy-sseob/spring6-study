package com.example.hellospring.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLConnection;
import java.util.stream.Collectors;

public class SimpleApiExecutor implements ApiExecutor {
  
  @Override
  public String execute(URI uri) throws Exception {

    URLConnection urlConnection = uri.toURL().openConnection();
    BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
    String response = br.lines().collect(Collectors.joining());
    br.close();

    return response;
  }
  
}
