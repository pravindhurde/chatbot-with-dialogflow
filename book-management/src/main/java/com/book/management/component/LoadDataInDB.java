package com.book.management.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.book.management.domain.Author;
import com.book.management.domain.Book;
import com.book.management.repository.AuthorRepository;

@Component
public class LoadDataInDB implements CommandLineRunner {

	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public void run(String... args) throws Exception {

		if (authorRepository.count() > 0) {
			return;
		}

		// create 3 authors with 2 books each

		// create first author and add two books
		Author author1 = new Author("Mark", "Twain");

		Book book1 = new Book("Life on the Mississippi", "Biography", 1883);
		book1.setSummary("Life on the Mississippi is a memoir by Mark Twain detailing his days as a"
				+ " steamboat pilot on the Mississippi River before and after the American Civil War.");

		Book book2 = new Book("The Adventures of Tom Sawyer", "Biography", 1876);
		book2.setSummary("The Adventures of Tom Sawyer by Mark Twain is an 1876 novel about a young boy "
				+ "growing up along the Mississippi River. The story is set in the fictional town of"
				+ " St. Petersburg, inspired by Hannibal, Missouri, where Twain lived.");

		// save data to DB
		author1.addBook(book1);
		author1.addBook(book2);
		authorRepository.save(author1);

		// create second author and add two books
		Author author2 = new Author("William", "Shakespeare");

		Book book3 = new Book("Hamlet", "Drama", 1599);
		book3.setSummary(
				"The Tragedy of Hamlet, Prince of Denmark, or more simply Hamlet, is a tragedy by William Shakespeare,"
						+ " believed to have been written between 1599 and 1601. The play, set in Denmark, recounts how Prince"
						+ " Hamlet exacts revenge on his uncle Claudius, who has murdered Hamlet's father, the King, and then taken"
						+ " the throne and married Gertrude, Hamlet's mother. The play vividly charts the course of real and feigned"
						+ " madness—from overwhelming grief to seething rage—and explores themes of treachery, revenge, incest, and "
						+ "moral corruption.");

		Book book4 = new Book("King Lear", "Tragedy", 1606);
		book4.setSummary("King Lear is a tragedy by William Shakespeare, believed to have been written between"
				+ " 1603 and 1606. It is considered one of his greatest works. The play is based on the legend "
				+ "of Leir of Britain, a mythological pre-Roman Celtic king. It has been widely adapted for stage"
				+ " and screen, with the part of Lear played by many of the world's most accomplished actors.");

		// save data to DB
		author2.addBook(book3);
		author2.addBook(book4);
		authorRepository.save(author2);

		// create third author and two books
		Author author3 = new Author("Leo", "Tolstoy");
		Book book5 = new Book("War and Peace", "Novel", 1869);
		book5.setSummary(
				"Epic in scale, War and Peace delineates in graphic detail events leading up to Napoleon's invasion of Russia,"
						+ " and the impact of the Napoleonic era on Tsarist society, as seen through the eyes of five Russian"
						+ " aristocratic families.");

		Book book6 = new Book("Anna Karenina", "Novel", 1877);
		book6.setSummary(
				"Anna Karenina tells of the doomed love affair between the sensuous and rebellious Anna and the dashing"
						+ " officer, Count Vronsky. Tragedy unfolds as Anna rejects her passionless marriage and must endure the"
						+ " hypocrisies of society. Set against a vast and richly textured canvas of nineteenth-century Russia, "
						+ "the novel's seven major characters create a dynamic imbalance, playing out the contrasts of city and "
						+ "country life and all the variations on love and family happiness. While previous versions have softened"
						+ " the robust, and sometimes shocking, quality of Tolstoy's writing, Pevear and Volokhonsky have produced "
						+ "a translation true to his powerful voice. This award-winning team's authoritative edition also includes "
						+ "an illuminating introduction and explanatory notes. Beautiful, vigorous, and eminently readable, this "
						+ "Anna Karenina will be the definitive text for generations to come.");

		// save data to DB
		author3.addBook(book5);
		author3.addBook(book6);
		authorRepository.save(author3);
	}

}
