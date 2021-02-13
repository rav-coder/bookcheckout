package sait.bms.problemdomain;

/**
 * 
 * Description:
 * Books super class that holds all common attributes for sub classes of ChildrensBook, CookBook, Paperback, and Periodical books
 * Has 1 no args constructor and 1 with all attributes
 * has all the getters and setters for the attributes
 * has a toString method to print the way we want to
 *
 * @author: YunZe (David) Wei, Saurav Adhikari, Rafael Oporto 
 * @version: Feb 1 /2021
 */
public class Books
{
	private String isbn;
	private String callNumber;
	private int avail;
	private int total;
	private String title;
	private boolean active;

	/**
	 * No-args constructor, Initializes the newly created Books
	 */
	public Books()
	{
		active = true;
	}

	/**
	 * Initializes the newly created Books with parameters listed below
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
	 */
	public Books(String isbn, String callNumber, int avail, int total, String title)
	{
		this.isbn = isbn;
		this.callNumber = callNumber;
		this.avail = avail;
		this.total = total;
		this.title = title;
		active = true;
	}

	/**
	 * gets the books isbn
	 * 
	 * @return the books isbn
	 */
	public String getIsbn()
	{
		return isbn;
	}

	/**
	 * sets the books isbn to a new isbn
	 * 
	 * @param isbn
	 *            the books isbn
	 */
	public void setIsbn(String isbn)
	{
		this.isbn = isbn;
	}

	/**
	 * gets the books call number
	 * 
	 * @return the books call number
	 */
	public String getCallNumber()
	{
		return callNumber;
	}

	/**
	 * sets the books call number to a new call number
	 * 
	 * @param callNumber
	 *            the books call number
	 */
	public void setCallNumber(String callNumber)
	{
		this.callNumber = callNumber;
	}

	/**
	 * gets the books availability
	 * 
	 * @return the books availability
	 */
	public int getAvail()
	{
		return avail;
	}

	/**
	 * sets the books availability
	 * 
	 * @param avail
	 *            the books availability
	 */
	public void setAvail(int avail)
	{
		this.avail = avail;
	}

	/**
	 * gets the total amount of a chosen book
	 * 
	 * @return the total amount of the books in the store
	 */
	public int getTotal()
	{
		return total;
	}

	/**
	 * sets the total amount of a chosen book
	 * 
	 * @param total
	 *            the total amount of the books in the store
	 */
	public void setTotal(int total)
	{
		this.total = total;
	}

	/**
	 * gets the title of a book
	 * 
	 * @return the title of chosen book
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * sets the title of a chosen book
	 * 
	 * @param title
	 *            the books title
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * checks if the book object is active and has data in it
	 * 
	 * @return a boolean true if there is data for the book object and false if
	 *         there is no data for the book object
	 */
	public boolean isActive()
	{
		return active;
	}

	/**
	 * overriding the toString format to what we want to print
	 * 
	 * @return the desired string format we want to output
	 */
	@Override
	public String toString()
	{
		return String.format("%s%n%s%n%s%n%s%n%s%n%s%n", 
				"ISBN:              " + this.getIsbn(),
				"Call Number:       " + this.getCallNumber(), 
				"Available:         " + this.getAvail(),
				"Total:             " + this.getTotal(), 
				"Title:             " + this.getTitle());
	}

}
