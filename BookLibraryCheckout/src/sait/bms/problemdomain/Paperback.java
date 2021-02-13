package sait.bms.problemdomain;

/**
 * 
 * Description: Books super class that holds all common attributes for sub classes of
 * ChildrensBook, CookBook, Paperback, and Periodical books. This subclass generates an
 * instance of a Paperback book.
 *
 * @author: YunZe (David) Wei, Saurav Adhikari, Rafael Oporto
 * @version: Feb 11/2021
 */
public class Paperback extends Books
{
	private String authors;
	private int year;
	private char genre;

	/**
	 * Initializes the newly created Paperback
	 */
	public Paperback()
	{
		super();
	}

	/**
	 * 
	 * Initializes the newly created Paperback
	 * 
	 * @param isbn
	 *            isbn number for the book
	 * @param callNumber
	 *            call number associated with the book
	 * @param avail
	 *            number of books available
	 * @param total
	 *            total number of books
	 * @param title
	 *            title of the book
	 * @param authors
	 *            author(s) that wrote this book
	 * @param year
	 *            year of publishing
	 * @param genre
	 *            genre of the book
	 */
	public Paperback(String isbn, String callNumber, int avail, int total, String title, String authors, int year,
			char genre)
	{
		super(isbn, callNumber, avail, total, title);
		this.authors = authors;
		this.year = year;
		this.genre = genre;
	}

	/**
	 * Gets book author.
	 * 
	 * @return author of the book
	 */
	public String getAuthors()
	{
		return authors;
	}

	/**
	 * Sets a book author.
	 * 
	 * @param authors
	 *            the authors of the books
	 */
	public void setAuthors(String authors)
	{
		this.authors = authors;
	}

	/**
	 * Get publishing year.
	 * 
	 * @return returns the year of release of the book
	 */
	public int getYear()
	{
		return year;
	}

	/**
	 * Set publishing year.
	 * 
	 * @param year
	 *            the year of release of the book
	 */
	public void setYear(int year)
	{
		this.year = year;
	}

	/**
	 * Get the genre of the book.
	 * 
	 * @return FUll paperback Genre Name
	 */
	public String getGenre()
	{
		if (this.genre == 'A')
			return "Adventure";
		else if (this.genre == 'D')
			return "Drama";
		else if (this.genre == 'E')
			return "Education";
		else if (this.genre == 'C')
			return "Classic";
		else if (this.genre == 'F')
			return "Fantasy";
		else
			return "Science Fiction";
	}

	/**
	 * Set the genre of the book
	 * 
	 * @param genre
	 *            Letter value associated with the genre of the book. For example, Adventure
	 *            is 'A'.
	 */
	public void setGenre(char genre)
	{
		this.genre = genre;
	}

	/**
	 * Generates String with all the information associated with a book.
	 */
	@Override
	public String toString()
	{
		return String.format("%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n", "ISBN:              " + super.getIsbn(),
				"Call Number:       " + super.getCallNumber(), "Available:         " + super.getAvail(),
				"Total:             " + super.getTotal(), "Title:             " + super.getTitle(),
				"Authors:           " + this.getAuthors(), "Year:              " + this.getYear(),
				"Genre:             " + this.getGenre());
	}

}
