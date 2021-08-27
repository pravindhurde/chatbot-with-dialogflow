package com.book.management.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookUtil {

	public static final String BOOK_NOT_FOUND_MESSAGE = "I did not found the requested book. Could you please try again?";
	
	public static final String BOOKS_NOT_FOUND_MESSAGE = "No books found based on request. Could you please try again?";
	
	public static final List<String> listOfBooksMessages = new ArrayList<>();
	public static final List<String> listOfBooksSelectionMessages = new ArrayList<>();
	public static final List<String> listBookDetailsMessages = new ArrayList<>();

	static {
		listOfBooksMessages.add("Sure, I got this books in my list: ");
		listOfBooksMessages.add("Ok, I found some books for you: ");
		listOfBooksMessages.add("I found the following books: ");

		listOfBooksSelectionMessages.add("Please select the book you want to know more details about.");
		listOfBooksSelectionMessages.add("Choose one book to continue.");
		listOfBooksSelectionMessages.add("I can provide book details if you select one of them.");

		listBookDetailsMessages.add("Here are some facts: ");
		listBookDetailsMessages.add("Good choice. Will provide you with some info on this book. ");
		listBookDetailsMessages.add("Ok, here are the book details: ");
	}

	public static String getRandomListOfBooksMessage() {
		Integer listOfBooksValue = new Random().nextInt(listOfBooksMessages.size());

		return listOfBooksMessages.get(listOfBooksValue);
	}

	public static String getRandomBookSelectionMessage() {
		Integer listOfBooksSelectionValue = new Random().nextInt(listOfBooksSelectionMessages.size());

		return listOfBooksSelectionMessages.get(listOfBooksSelectionValue);
	}

	public static String getRandomBookDetailsMessage() {
		Integer listBookDetailsValue = new Random().nextInt(listBookDetailsMessages.size());

		return listBookDetailsMessages.get(listBookDetailsValue);
	}
}
