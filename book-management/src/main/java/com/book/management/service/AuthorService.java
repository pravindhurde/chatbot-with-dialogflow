package com.book.management.service;

import java.util.List;
import java.util.StringJoiner;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.management.domain.Author;
import com.book.management.repository.AuthorRepository;
import com.book.management.util.AuthorUtil;
import com.book.management.util.IntentUtil;
import com.google.actions.api.ActionRequest;
import com.google.actions.api.ActionResponse;
import com.google.actions.api.DialogflowApp;
import com.google.actions.api.ForIntent;
import com.google.actions.api.response.ResponseBuilder;

@Service
@Transactional
public class AuthorService extends DialogflowApp {

	private Logger logger = LoggerFactory.getLogger(AuthorService.class);

	@Autowired
	private AuthorRepository authorRepository;

	@ForIntent(IntentUtil.LIST_AUTHORS)
	public ActionResponse findAll(ActionRequest request) {
		logger.info("Executing intent - " + IntentUtil.LIST_AUTHORS);

		// find list of authors from DB
		List<Author> authorsList = authorRepository.findAll();

		// start creating the response
		StringBuilder response = new StringBuilder(AuthorUtil.getRandomListOfAuthorsMessage());

		// use StringJoiner to add comma delimited list of authors
		StringJoiner sj = new StringJoiner(", ");
		authorsList.forEach(author -> sj.add(author.toString()));

		// add list of authors to the response
		response.append(sj);
		response.append(". ");

		// add random message that will ask the user which author to select
		response.append(AuthorUtil.getRandomAuthorSelectionMessage());

		// build the 'ActionResponse' and add the 'response' to it
		ResponseBuilder responseBuilder = getResponseBuilder(request).add(response.toString());
		ActionResponse actionResponse = responseBuilder.build();
		logger.info(actionResponse.toJson());
		return actionResponse;

	}
}
