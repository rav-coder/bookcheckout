package sait.bms.problemdomain;

/**
 * 
 * Description: ChildrensBook, a subclass of Books 
 * Has 2 extra attributes of authors and format 
 * Includes all getters and setters for the local attributes
 * Overrides the toString in the super class to print the extra attributes
 *
 * @author: YunZe (David) Wei, Saurav Adhikari, Rafael Oporto 
 * @version: Feb 11/2021
 */
public class ChildrensBook extends Books
{
	private String authors;
	private char format;

	public ChildrensBook()
	{
		super();
	}

	/**
	 * 
	 * Initializes the newly created ChildrensBook
	 * 
	 * @param isbn
	 *            the books isbn number
	 * @param callNumber
	 *            the books call number
	 * @param avail
	 *            availability of the book
	 * @param total
	 *            total amount of the book the store has
	 * @param title
	 *            the books title
	 * @param authors
	 *            the books author(s)
	 * @param format
	 *            identifying what type of childrens book it is
	 */
	public ChildrensBook(String isbn, String callNumber, int avail, int total, String title, String authors,
			char format)
	{
		super(isbn, callNumber, avail, total, title);
		this.authors = authors;
		this.format = format;
	}

	/**
	 * get the book author(s)
	 * 
	 * @return the all the authors for the book
	 */
	public String getAuthors()
	{
		return authors;
	}

	/**
	 * sets author(s) for a book
	 * 
	 * @param authors
	 *            the books author(s)
	 */
	public void setAuthors(String authors)
	{
		this.authors = authors;
	}

	/**
	 * gets the char format and returns a string for the category of the book based
	 * on the format character
	 * 
	 * @return the category of the childrens book based on a char identifier
	 */
	public String getFormat()
	{
		if (this.format == 'P')
		{
			return "Picture Book";
		} else if (this.format == 'E')
		{
			return "Early Readers";
		} else
		{
			return "Chapter Book";
		}
	}

	/**
	 * sets the category for the book based one character
	 * 
	 * @param format
	 *            a one character identifier for the category of the childrens book
	 */
	public void setFormat(char format)
	{
		this.format = format;
	}

	/**
	 * overrides the super class toString to print extra attributes
	 * 
	 * @return the overridden to string format we want to print
	 */
	@Override
	public String toString()
	{

		return String.format("%s%n%s%n%s%n%s%n%s%n%s%n%s%n", "ISBN:              " + super.getIsbn(),
				"Call Number:       " + super.getCallNumber(), "Available:         " + super.getAvail(),
				"Total:             " + super.getTotal(), "Title:             " + super.getTitle(),
				"Authors:           " + this.getAuthors(), "Format:            " + this.getFormat());
	}

}
