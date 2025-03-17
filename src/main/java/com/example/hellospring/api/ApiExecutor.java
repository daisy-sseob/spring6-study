package com.example.hellospring.api;

import java.net.URI;

public interface ApiExecutor {
  
  String execute(URI uri) throws Exception;

}
