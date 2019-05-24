package io.micronaut.issue;

import java.util.List;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;

public interface HelloApi {

	@Get(uri = "/hello", produces = MediaType.APPLICATION_JSON)
	List<String> hello(@QueryValue List<String> args);
}