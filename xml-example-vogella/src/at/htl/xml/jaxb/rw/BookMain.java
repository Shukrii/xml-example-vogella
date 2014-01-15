package at.htl.xml.jaxb.rw;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import at.htl.xml.jaxb.model.Author;
import at.htl.xml.jaxb.model.Book;
import at.htl.xml.jaxb.model.Bookstore;

public class BookMain {

	private static final String BOOKSTORE_XML = "./bookstore-jaxb.xml";

	public static void main(String[] args) throws JAXBException, IOException {

		ArrayList<Book> bookList = new ArrayList<Book>();


		Author a1 = new Author();
		a1.setdateOfBirth(new GregorianCalendar(1956,Calendar.MARCH,17).getTime());
		a1.setName("Neil Strauss");
		Author a11 = new Author();
		a11.setdateOfBirth(new GregorianCalendar(1952,Calendar.MARCH,17).getTime());
		a11.setName("Neil2 Strauss2");

		Author a2 = new Author();
		a2.setName("Charlotte Roche");
		a2.setdateOfBirth(new GregorianCalendar(1976,Calendar.JANUARY,11).getTime());

		// create books
		Book book1 = new Book();

		book1.setIsbn("978-0060554736");
		book1.setName("The Game");
		//book1.setAuthor(a1);

		book1.setAuthorList(new HashSet<Author>());
		book1.addAuthor(a1);
		book1.addAuthor(a11);

		book1.setPublisher("Harpercollins");
		book1.setYearOfPublication(1990);
		bookList.add(book1);

		Book book2 = new Book();
		book2.setAuthorList(new HashSet<Author>());
		book2.addAuthor(a2);
		book2.setIsbn("978-3832180577");
		book2.setName("Feuchtgebiete");
		book2.setAuthor(a2);
		book2.setPublisher("Dumont Buchverlag");
		book2.setYearOfPublication(2010);
		bookList.add(book2);

		// create bookstore, assigning book
		Bookstore bookstore = new Bookstore();
		bookstore.setName("Fraport Bookstore");
		bookstore.setLocation("Frankfurt Airport");
		bookstore.setBookList(bookList);

		// create JAXB context and instantiate marshaller
		JAXBContext context = JAXBContext.newInstance(Bookstore.class);

		Marshaller m = context.createMarshaller();
		// formatted as human readable XML
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// Write to System.out
		m.marshal(bookstore, System.out);

		// Write to File
		m.marshal(bookstore, new File(BOOKSTORE_XML));

		// get variables from our xml file, created before
		System.out.println();
		System.out.println("Output from our XML File: ");

		// Unmarshalling (reading) XML instance documents into Java content trees
		Unmarshaller um = context.createUnmarshaller();
		Bookstore bookstore2 = (Bookstore) um.unmarshal(new FileReader(BOOKSTORE_XML));
		ArrayList<Book> list = bookstore2.getBooksList();

		for (Book book : list) {
			System.out.println("Book: " + book.getName() + " from "
					+ book.getAuthor());
		}
	}
}
