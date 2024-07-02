package com.example.hellospring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class WebApiExRateProvider implements ExRateProvider {
	
	@Override
	public BigDecimal getExRate(String currency) throws Exception {
		HttpClient client = HttpClient.newHttpClient();
		String response = client
				.send(
						HttpRequest.newBuilder()
								.uri(URI.create("https://open.er-api.com/v6/latest/" + currency))
								.build(),
						HttpResponse.BodyHandlers.ofString()
				)
				.body();
		ObjectMapper mapper = new ObjectMapper();
		ExRateData data = mapper.readValue(response, ExRateData.class);

		return data.rates().get("KRW");
	}

}
