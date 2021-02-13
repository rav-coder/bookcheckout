package sait.bms.problemdomain;

/**
 * 
 * Description: Books super class that holds all common attributes for sub classes of
 * ChildrensBook, CookBook, Paperback, and Periodical books This subclass generates an
 * instance of a Periodical book.
 *
 * @author: YunZe (David) Wei, Saurav Adhikari, Rafael Oporto
 * @version: Feb 11/2021
 */
public class Periodical extends Books
{
	private char frequency;

	/**
	 * Initializes the newly created Periodical
	 */
	public Periodical()
	{
		super();
	}

	/**
	 * 
	 * Initializes the newly created Periodical
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
	 * @param frequency
	 *            frequency of publication
	 */
	public Periodical(String isbn, String callNumber, int avail, int total, String title, char frequency)
	{
		super(isbn, callNumber, avail, total, title);
		this.frequency = frequency;
	}

	/**
	 * Get frequency of publication for a book.
	 * 
	 * @return FUll periodical frequency
	 */
	public String getFrequency()
	{
		if (this.frequency == 'D')
			return "Daily";
		else if (this.frequency == 'W')
			return "Weekly";
		else if (this.frequency == 'M')
			return "Monthly";
		else if (this.frequency == 'B')
			return "Bi-monthly";
		else
			return "Quaterly";
	}

	/**
	 * Set frequency of publication for a book.
	 * 
	 * @param frequency
	 *            the frequency of the periodical book
	 */
	public void setFrequency(char frequency)
	{
		this.frequency = frequency;
	}

	/**
	 * Generates String with all the information associated with a book.
	 */
	@Override
	public String toString()
	{
		return String.format("%s%n%s%n%s%n%s%n%s%n%s%n", "ISBN:              " + super.getIsbn(),
				"Call Number:       " + super.getCallNumber(), "Available:         " + super.getAvail(),
				"Total:             " + super.getTotal(), "Title:             " + super.getTitle(),
				"Frequency:         " + this.getFrequency());

	}

}
