package com.book.management.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.management.service.ActionService;
import com.book.management.service.AuthorService;
import com.book.management.service.BookService;
import com.book.management.util.IntentUtil;

@RestController
@RequestMapping("/api/actions/")
public class ActionsController {

	private Logger logger = LoggerFactory.getLogger(ActionsController.class);

	@Autowired
	private ActionService actionService;

	@Autowired
	private AuthorService authorService;

	@Autowired
	private BookService bookService;

	@PostMapping
	public ResponseEntity<?> executePostAction(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		logger.info("--- executePostAction ---");

		// get body from HttpServletRequest
		String body = request.getReader().lines().collect(Collectors.joining());
		logger.info(new JSONObject(body).toString(4));

		// get intent name (based on user request)
		try {
			String intentName = actionService.getIntentName(body);

			switch (intentName) {
			case IntentUtil.LIST_ACTIONS:
				String actionJsonResponse = actionService.handleRequest(body, getHeadersMap(request)).get();
				return new ResponseEntity<String>(actionJsonResponse, HttpStatus.OK);
			case IntentUtil.LIST_AUTHORS:
				// invoke authorService->list_authors intent (business logic)
				String authorJsonResponse = authorService.handleRequest(body, getHeadersMap(request)).get();
				return new ResponseEntity<String>(authorJsonResponse, HttpStatus.OK);
			case IntentUtil.LIST_BOOKS_BY_AUTHOR:
			case IntentUtil.GET_BOOK_DETAILS_BY_AUTHOR:
			case IntentUtil.LIST_BOOKS_BY_GENRE:
			case IntentUtil.GET_BOOK_DETAILS_BY_GENRE:
				String bookJsonResponse = bookService.handleRequest(body, getHeadersMap(request)).get();
				return new ResponseEntity<String>(bookJsonResponse, HttpStatus.OK);
			default:
				return new ResponseEntity<String>("Request could not be processed", HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error " + e.getMessage());
			return new ResponseEntity<String>("Could not process the request", HttpStatus.OK);
		}
	}

	@GetMapping
	public ResponseEntity<?> executeGetAction() {
		return new ResponseEntity<String>("ActionsController accepts only POST requests from Google Assistant",
				HttpStatus.OK);
	}

	// construct map of headers that will be sent to our intents (business logic)
	private Map<String, String> getHeadersMap(HttpServletRequest request) {
		Map<String, String> headersMap = new HashMap<>();

		Enumeration<?> headerNamesEnumeration = request.getHeaderNames();
		while (headerNamesEnumeration.hasMoreElements()) {
			String key = (String) headerNamesEnumeration.nextElement();
			String value = request.getHeader(key);
			headersMap.put(key, value);
		}

		return headersMap;
	}
}
