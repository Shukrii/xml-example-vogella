package at.htl.xml.jaxb.model;

import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "book")
//If you want you can define the order in which the fields are written
//Optional
@XmlType(propOrder = { "author", "name", "isbn", "yearOfPublication", "authorList" })
public class Book {

	private String name;
	private Author author;

	private Set<Author> authorList;
	private String publisher;
	private int yearOfPublication;
	// Fehler: siehe http://docs.oracle.com/javase/tutorial/jaxb/intro/j2schema.html
	//@XmlAttribute(required = true)
	private String isbn;

	@XmlElementWrapper(name = "authorList")
	@XmlElement(name = "author")
	public void setAuthorList(Set<Author> authorList) {
		this.authorList = authorList;
	}

	public Set<Author> getAuthorList() {
		return authorList;
	}

	public void addAuthor(Author a) {
		this.authorList.add(a);
	}

	// If you like the variable name, e.g. "name", you can easily change this
	// name for your XML-Output:
	@XmlElement(name = "title")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}


	public String getPublisher() {
		return publisher;
	}

	@XmlAttribute(required = true)
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getYearOfPublication() {
		return yearOfPublication;
	}
	public void setYearOfPublication(int yearOfPublication) {
		this.yearOfPublication = yearOfPublication;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

}

