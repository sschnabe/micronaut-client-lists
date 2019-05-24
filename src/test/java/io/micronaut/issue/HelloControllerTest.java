package io.micronaut.issue;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriBuilder;
import io.micronaut.test.annotation.MicronautTest;

@MicronautTest
class HelloControllerTest {

	@Inject
	HelloClient declarativeClient;
	@Inject
	@Client("/")
	RxHttpClient httpClient;

	@Test @DisplayName("Declarative client with list as query param")
	void declarative() {
		var list = List.of("a", "b");
		var response = declarativeClient.hello(list);
		Assertions.assertEquals(list, response);
	}

	@Test @DisplayName("Http client with list as query param")
	void httpWithValue() {
		var list = List.of("a", "b");
		var uri = UriBuilder.of("hello").queryParam("args", list.toArray()).build();
		var response = httpClient
				.retrieve(HttpRequest.GET(uri), Argument.of(List.class, String.class))
				.blockingFirst();
		Assertions.assertEquals(list, response);
	}
}