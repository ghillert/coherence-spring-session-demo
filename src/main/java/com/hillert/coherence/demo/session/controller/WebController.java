package com.hillert.coherence.demo.session.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.SortedMap;
import java.util.TreeMap;

@RestController
public class WebController {

	final private HttpSession httpSession;

	public WebController(HttpSession httpSession) {
		this.httpSession = httpSession;
	}

	@GetMapping("/add-session-attribute")
	public SortedMap<String, Object> addAndReturnSessionAttributes(
			@RequestParam(name = "key") String key,
			@RequestParam(name = "value") String value) {
		httpSession.setAttribute(key, value);

		final SortedMap<String, Object> sessionAttributes = new TreeMap<>();

		final Enumeration<String> sessionAttributeNames = httpSession.getAttributeNames();
		while (sessionAttributeNames.hasMoreElements()) {
			final String sessionKey = sessionAttributeNames.nextElement();
			sessionAttributes.put(sessionKey, httpSession.getAttribute(sessionKey));
		}

		return sessionAttributes;

	}
}
