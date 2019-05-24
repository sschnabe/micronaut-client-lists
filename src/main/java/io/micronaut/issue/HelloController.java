package io.micronaut.issue;

import java.util.List;

import io.micronaut.http.annotation.Controller;

@Controller
public class HelloController implements HelloApi {

	@Override
	public List<String> hello(List<String> args) {
		return args;
	}
}