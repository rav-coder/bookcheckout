package sait.bms.problemdomain;

/**
 * 
 * Description: Cookbook which is a subclass of book
 *
 * @author: YunZe (David) Wei, Saurav Adhikari, Rafael Oporto
 * @version: Feb 11 /2021
 */
public class CookBook extends Books
{
	private String publisher;
	private char diet;

	/**
	 * No-arg constructor
	 */
	public CookBook()
	{
		super();
	}

	/**
	 * Default constructor for cookbook
	 * 
	 * @param isbn
	 *            13-digit unique book number
	 * @param callNumber
	 *            Call Number
	 * @param avail
	 *            Number of books available
	 * @param total
	 *            Total number of books
	 * @param title
	 *            Title of the book
	 * @param publisher
	 *            the publishers name
	 * @param diet
	 *            the diet type of the book
	 */
	public CookBook(String isbn, String callNumber, int avail, int total, String title, String publisher, char diet)
	{
		super(isbn, callNumber, avail, total, title);
		this.publisher = publisher;
		this.diet = diet;
	}

	/**
	 * Getter for Cookbook Publisher
	 * 
	 * @return Publisher the publishers name
	 */
	public String getPublisher()
	{
		return publisher;
	}

	/**
	 * Setter for Cookbook Publisher
	 * 
	 * @param publisher
	 *            the publisher of the book
	 */
	public void setPublisher(String publisher)
	{
		this.publisher = publisher;
	}

	/**
	 * Getter for Cookbook Diet Type which converts diet acronymn to full diet name
	 * 
	 * @return FUll Cookbook Diet Name
	 */
	public String getDiet()
	{
		if (this.diet == 'D')
			return "Diabetic";
		else if (this.diet == 'V')
			return "Vegetarian";
		else if (this.diet == 'G')
			return "Gluten-free";
		else if (this.diet == 'I')
			return "International";
		else
			return "None";
	}

	/**
	 * Setter for Diet Type
	 * 
	 * @param diet
	 *            the diet type of the book
	 */
	public void setDiet(char diet)
	{
		this.diet = diet;
	}

	/**
	 * toString method for cookbook
	 * 
	 * @return Formatted output for cookbook
	 */
	@Override
	public String toString()
	{
		return String.format("%s%n%s%n%s%n%s%n%s%n%s%n%s%n", "ISBN:              " + super.getIsbn(),
				"Call Number:       " + super.getCallNumber(), "Available:         " + super.getAvail(),
				"Total:             " + super.getTotal(), "Title:             " + super.getTitle(),
				"Publisher:         " + this.getPublisher(), "Diet:              " + this.getDiet());
	}
}
