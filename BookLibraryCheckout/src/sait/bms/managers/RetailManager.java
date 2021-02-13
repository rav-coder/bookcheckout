package sait.bms.managers;

import java.util.*;
import java.util.regex.Pattern;

import sait.bms.problemdomain.Books;
import sait.bms.problemdomain.ChildrensBook;
import sait.bms.problemdomain.CookBook;
import sait.bms.problemdomain.Paperback;
import sait.bms.problemdomain.Periodical;

import java.io.*;

/**
 * 
 * Description: Has 6 main options for Appdriver to run off: 1.Display menu, 2.Checkout
 * Book, 3.Find Books by Title, 4.Display Books by Type, 5.Produce Random Book List, 6.Save file & Exit
 *
 * @author: YunZe (David) Wei, Saurav Adhikari, Rafael Oporto
 * @version: Feb 11/2021
 */
public class RetailManager
{

	private ArrayList<Books> booksArrayList;
	private static final String FILE_PATH = "res/books.txt";
	private Scanner in;

	/**
	 * 
	 * Initializes the newly created RetailManager
	 * 
	 * @throws IOException
	 *             throws any exceptions when working with the books file
	 */
	public RetailManager() throws IOException
	{
		booksArrayList = new ArrayList<>();
		loadBooksFromFile();
		displayMenu();
	}

	/**
	 * Displays the selection
	 * 
	 * @throws IOException
	 *             throws any exceptions when working with the books file
	 */
	public void displayMenu() throws IOException
	{
		in = new Scanner(System.in);
		int counter = 0; // this counter is for error check on the user input
		int choice = 0; // choice is used to select the 5 menu options
		do
		{
			// print statements for the menu
			System.out.println("Welcome to ABC Book Company: How may we assist you?");
			System.out.printf("1%s%s\n", "    ", "Checkout Book");
			System.out.printf("2%s%s\n", "    ", "Find Books by Title");
			System.out.printf("3%s%s\n", "    ", "Display Books by Type");
			System.out.printf("4%s%s\n", "    ", "Produce Random Book List");
			System.out.printf("5%s%s\n", "    ", "Save & Exit");
			System.out.print("Enter option: ");
			String option = in.nextLine();

			// Error check when user inputs more than 1 character
			if (option.length() != 1)
			{
				System.out.println("Invalid option. Please select a valid option between 1 and 5.");
				counter++;
			}
			// Error check when the character length is one and is a numeric value between 1
			// and 5
			else if (option.length() == 1 && !(option.charAt(0) >= '1' && option.charAt(0) <= '5'))
			{
				System.out.println("Invalid option. Please select a valid option between 1 and 5.");
				counter++;
			} else
			{
				choice = Integer.parseInt(option);
				counter = 0;

				// switch statements to call different methods based on user choice
				switch (choice)
				{
					case 1:
						String userIsbn;
						System.out.println("Enter ISBN: ");
						userIsbn = in.next();
						bookCheckOut(userIsbn, booksArrayList);
						break;
					case 2:
						titleSearch();
						break;
					case 3:
						typeSearch();
						break;
					case 4:
						randomBooks();
						break;
					case 5:
						programExit();
						break;
				}
			}
		} while (counter != 0); // reprints the menu if user inputs an invalid option
	}

	/**
	 * bookCheckout() : A user inputs an isbn number into the system and is returned whether
	 * the book is available or not. If it is available, they receive all the information
	 * (toString()) about the book, and the availability decrements by one.
	 * 
	 * @param isbn
	 *            isbn number the user is looking for
	 * @param bookList
	 *            array list of all books in the library
	 * @throws IOException
	 *             throws any exceptions when working with the books file
	 */

	public void bookCheckOut(String isbn, ArrayList<Books> bookList) throws IOException
	{
		Pattern pattern = Pattern.compile("\\d+");
		boolean found = false;
		// check if isbn is all numbers and the right length
		if (!pattern.matcher(isbn).matches() || isbn.length() != 13)
		{
			System.out.println("Wrong isbn format, please try again.");
		} else
		{
			// loop to compare the last digit of isbn with each book's isbn saved into the
			// bookList array
			for (int i = 0; i < bookList.size(); i++)
			{

				if (isbn.charAt(isbn.length() - 1) == bookList.get(i).getIsbn()
						.charAt(bookList.get(i).getIsbn().length() - 1))
				{
					// if there's a match, compare all other digits, break if numbers don't match
					for (int j = 11; j > 1; j--)
					{

						if (isbn.charAt(j) != bookList.get(i).getIsbn().charAt(j))
						{
							break;
						}
						// if all numbers match, check availability, print message if book is/isn't
						// available
						if (j == 2)
						{
							if (bookList.get(i).getAvail() > 0)
							{
								bookList.get(i).setAvail(bookList.get(i).getAvail() - 1);
								System.out.println(bookList.get(i).toString());
								found = true;
							} else
							{
								System.out.println("Book is currently unavailable!");
								found = true;

							}

						}
					}

				}
				// error check if statement will print if the isbn format is not matched
				if (!found)
				{
					if (i == bookList.size() - 1)
					{
						System.out.println("Book not found");
					}
				}
			}
		}
		// returns to main menu
		displayMenu();
	}

	/**
	 * Gets a input string from the user and prints book(s) with titles matching that string.
	 * 
	 * @throws IOException
	 *             throws any exceptions when working with the books file
	 */
	public void titleSearch() throws IOException
	{
		boolean found = false; // checks if a matching title is found or not
		// asking the user to input a string
		System.out.print("Enter title to search for: ");
		String bookTitleContains = in.nextLine();
		System.out.print("Matching books: ");
		// this for loop goes through the book array list and checks if the user input
		// string matches a title
		for (int i = 0; i < booksArrayList.size(); i++)
		{

			// here contains the index of the string specified by the user. converted to
			// lower case to match user input and record
			int here = booksArrayList.get(i).getTitle().toLowerCase().indexOf(bookTitleContains.toLowerCase());

			// here is not -1 means a matching record(s) is found and it prints them
			if (here != -1)
			{
				System.out.println(booksArrayList.get(i));
				found = true;
			}
		}

		// if no matching record is found it prints that info to the user
		if (!found)
		{
			System.out.println("0");
			System.out.println("No matching records found.");
		}
		// calling the main menu if match found or not found
		displayMenu();
	}

	/**
	 * Asks the user to choose what type of book they want to search. Based on the type of
	 * the. Book the user has to choose the category they want to search within that type.
	 * 
	 * @throws IOException
	 *             throws any exceptions when working with the books file
	 * 
	 */
	private void typeSearch() throws IOException
	{
		// prints out the menu of options the user can select from
		System.out.printf("%s%n%s%n%s%n%s%n%s%n", "#     Type", "1     Children's Books", "2     CookBooks",
				"3     Paperbacks", "4     Periodicals");
		in = new Scanner(System.in);
		System.out.print("Enter type of book: ");

		// reads in user input
		String option = in.nextLine();

		// Error check for when user inputs more than 1 character
		if (option.length() != 1)
		{
			System.out.println("Invalid option. Please select a valid option between 1 and 4.");
			typeSearch();
		}
		// Error check for when the character length is one and is a numeric value
		// between 1 and 4
		else if (option.length() == 1 && !(option.charAt(0) >= '1' && option.charAt(0) <= '4'))
		{
			System.out.println("Invalid option. Please select a valid option between 1 and 4.");
			typeSearch();
		}
		// If all the error checks pass the user is prompted to select a category based
		// on the type of book they chose
		else
		{
			int choice = Integer.parseInt(option);
			boolean found = false;
			int counter = 0;
			String validation;
			// Based on the choice the user chose between 1 and 4 the switch statement shows
			// different categories to choose from
			switch (choice)
			{
				// If the user chose 1, category for childrens books show
				case 1:
					System.out.print(
							"Enter a format (P for Picture book, E for Early readers, " + "C for Chapter book): ");

					validation = in.nextLine();
					// validates if the user entered only 1 character
					if (validation.length() != 1)
					{
						System.out.println("Please enter the correct character identifier");
						typeSearch();
					} else
					{

						char format = validation.charAt(0);
						// for loop for running through the whole books array list
						for (int i = 0; i < booksArrayList.size(); i++)
						{
							// check if there is any books of the subclass ChildrensBook
							if (booksArrayList.get(i) instanceof ChildrensBook)
							{
								ChildrensBook temp = (ChildrensBook) booksArrayList.get(i);
								char formatChecker = temp.getFormat().charAt(0);
								// if the user entry and the down casted childrensbook arraylist file shows the same
								// category the book print to screen
								if (Character.toLowerCase(formatChecker) == Character.toLowerCase(format))
								{
									// counter for printing the statement below 1 time
									if (counter == 0)
									{
										System.out.println("Matching Books:");
										counter++;
									}
									// once a book is found the found boolean is changed to true so the if statement
									// below is not printed
									System.out.println(temp);
									found = true;
								}

							}

						}
					}
					// If user entered multiple characters this will print or a character that did
					// not match for any book this will print
					if (!found)
					{
						System.out.println("No books have been found under that format");
					}
					counter = 0;
					// returns to main menu
					displayMenu();
					break;

				// If the user chose 2, category for cook books show
				case 2:
					System.out.print("Enter a diet (D for Diabetic, V for Vegetarian, G for Gluten-free, "
							+ "I for International, N for None): ");
					validation = in.nextLine();
					// validates if the user entered only 1 character
					if (validation.length() != 1)
					{
						System.out.println("Please enter the correct character identifier");
						typeSearch();
					} else
					{
						char diet = validation.charAt(0);
						// for loop for running through the whole books array list
						for (int i = 0; i < booksArrayList.size(); i++)
						{
							// check if there is any books of the subclass CookBook
							if (booksArrayList.get(i) instanceof CookBook)
							{
								CookBook temp = (CookBook) booksArrayList.get(i);
								char dietChecker = temp.getDiet().charAt(0);
								// if the user entry and the down casted cookbook arraylist shows the same
								// category the book prints to screen
								if (Character.toLowerCase(dietChecker) == Character.toLowerCase(diet))
								{
									// counter for printing the statement below 1 time
									if (counter == 0)
									{
										System.out.println("Matching Books:");
										counter++;
									}
									// once a book is found and printed to the screen, the found boolean is changed
									// to true so the if statement below is not printed
									System.out.println(temp);
									found = true;
								}

							}

						}
					}
					// If user entered multiple characters this will print or a character that did
					// not match for any book this will print
					if (!found)
					{
						System.out.println("No books have been found for that diet");
					}
					counter = 0;
					// returns to main menu
					displayMenu();
					break;

				// If the user chose 3, category for paperback books show
				case 3:
					System.out.print("Enter a genre (A for Adventure, D for Drama, "
							+ "E for ducation, C for Classic, F for Fantasy, S for Science fiction): ");
					validation = in.nextLine();
					// validates if the user entered only 1 character
					if (validation.length() != 1)
					{
						System.out.println("Please enter the correct character identifier");
						typeSearch();
					} else
					{
						char genre = validation.charAt(0);
						// for loop for running through the whole array list
						for (int i = 0; i < booksArrayList.size(); i++)
						{
							// check if there is any books of the subclass Paperback
							if (booksArrayList.get(i) instanceof Paperback)
							{
								Paperback temp = (Paperback) booksArrayList.get(i);
								char genreChecker = temp.getGenre().charAt(0);
								// if the user entry and the down casted paperback arraylist file shows the same
								// category the book prints to screen
								if (Character.toLowerCase(genreChecker) == Character.toLowerCase(genre))
								{
									// counter for printing the statement below 1 time
									if (counter == 0)
									{
										System.out.println("Matching Books:");
										counter++;
									}
									// once a book is found and printed to the screen, the found boolean is changed to
									// true so the if statement below is not printed
									System.out.println(temp);
									found = true;
								}

							}

						}
					}
					// If user entered multiple characters this will print or a character that did
					// not match for any book this will print
					if (!found)
					{
						System.out.println("No books have been found under that genre");
					}
					counter = 0;
					// returns to main menu
					displayMenu();
					break;
				// If the user chose 4, category for periodical books show
				case 4:
					System.out.print("Enter a frequency (D for Daily, W for Weekly, "
							+ "M for Monthly, B for Biweekly, Q for Quarterly): ");
					validation = in.nextLine();
					// validates if the user entered only 1 character
					if (validation.length() != 1)
					{
						System.out.println("Please enter the correct character identifier");
						typeSearch();
					} else
					{
						char frequency = validation.charAt(0);
						// for loop for running through the whole array list
						for (int i = 0; i < booksArrayList.size(); i++)
						{
							// check if there is any books of the subclass Periodicals
							if (booksArrayList.get(i) instanceof Periodical)
							{
								Periodical temp = (Periodical) booksArrayList.get(i);
								char frequencyChecker = temp.getFrequency().charAt(0);
								// if the user entry and the down casted Periodical arraylist file shows the same
								// category the book prints to screen
								if (Character.toLowerCase(frequencyChecker) == Character.toLowerCase(frequency))
								{
									// counter for printing the statement below 1 time
									if (counter == 0)
									{
										System.out.println("Matching Books:");
										counter++;
									}
									// once a book is found and printed to the screen, the found boolean is changed to
									// true so the if statement below is not printed
									System.out.println(temp);
									found = true;
								}

							}
						}
					}
					// If user entered multiple characters this will print or a character that did
					// not match for any book this will print
					if (!found)
					{
						System.out.println("No books have been found under that frequency");
					}
					counter = 0;
					// returns to main menu
					displayMenu();
					break;
			}

		}
	}

	/**
	 * Gets a input amount from the user and prints the specified amount of random books.
	 * 
	 * @throws IOException
	 *             throws any exceptions when working with the books file
	 */
	public void randomBooks() throws IOException
	{
		int counter = 0;
		// getting amount from the user
		System.out.print("Enter number of books: ");
		String checkAmount = in.next();

		// set the pattern for digits only
		Pattern pattern = Pattern.compile("\\d+");
		boolean found = false;
		// check if entry is all numbers with the pattern class
		if (!pattern.matcher(checkAmount).matches())
		{
			System.out.println("Wrong entry format, please enter a number");
			randomBooks();
		}
		// prints out randomed books if the entered amount is a number
		else
		{
			int randomAmount = Integer.parseInt(checkAmount);
			// if statement to allow only the max amount of books to be randomed
			if (randomAmount > 75)
			{
				System.out.println("We can only random up to 75 books please enter a new amount");
				randomBooks();
			}
			// cloning the arraylist of books and shuffling them
			ArrayList<Books> bookshuffled = (ArrayList<Books>) booksArrayList.clone();
			Collections.shuffle(bookshuffled);

			// this loop prints the amount of books specified by the user
			System.out.println("Random books:");
			for (int i = 0; i < randomAmount; i++)
			{
				System.out.println(bookshuffled.get(i));
			}
		}

		// returns to main menu after printing random books
		displayMenu();
	}

	/**
	 * Loads in the books from books.txt into a Books class ArrayList.
	 * 
	 * @throws FileNotFoundException
	 *             throws file not found exception when reading the books file
	 */
	private void loadBooksFromFile() throws FileNotFoundException
	{
		Scanner in = new Scanner(new File(FILE_PATH));
		// While loop to keep reading the file while there is data
		while (in.hasNext())
		{
			String line = in.nextLine();
			String[] fields = line.split(";");

			String isbn = fields[0];
			String callNumber = fields[1];
			int avail = Integer.parseInt(fields[2]);
			int total = Integer.parseInt(fields[3]);
			String title = fields[4];
			char lastIsbn = isbn.charAt(isbn.length() - 1);
			// switch statement to assign right kind of sub class book along with its sub class
			// attributes based on the last number of the isbn read in the books.txt file
			switch (lastIsbn)
			{
				// if isbn ends with 0 or 1 assign it to the childrens book sub class
				case '0':
				case '1':
					String authors = fields[5];
					char format = fields[6].charAt(0);
					booksArrayList.add(new ChildrensBook(isbn, callNumber, avail, total, title, authors, format));
					break;
				// if isbn ends with 2 or 3 assign it to the cook book sub class
				case '2':
				case '3':
					String publisher = fields[5];
					char diet = fields[6].charAt(0);
					booksArrayList.add(new CookBook(isbn, callNumber, avail, total, title, publisher, diet));
					break;
				// if isbn ends between 4-7 assign it to the paperback book sub class
				case '4':
				case '5':
				case '6':
				case '7':
					String pAuthors = fields[5];
					int year = Integer.parseInt(fields[6]);
					char genre = fields[7].charAt(0);
					booksArrayList.add(new Paperback(isbn, callNumber, avail, total, title, pAuthors, year, genre));
					break;
				// if isbn ends with 8 or 9 assign it to the periodical book sub class
				case '8':
				case '9':
					char frequency = fields[5].charAt(0);
					booksArrayList.add(new Periodical(isbn, callNumber, avail, total, title, frequency));
					break;
			}

		}

		in.close();

	}

	/**
	 * Exits the program and reads in from the arraylist and saves all the data back into the
	 * books.txt file.
	 * 
	 * @throws IOException
	 *             throws any exceptions when working with the books file
	 */
	private void programExit() throws IOException
	{
		PrintWriter out = new PrintWriter(new FileWriter(FILE_PATH));
		// for loop for reading each element in the books ArrayList
		for (Books book : booksArrayList)
		{
			// if statement for checking if the book in the ArrayList has data
			if (book.isActive())
			{
				// print the data back into books.txt with this format
				out.print(book.getIsbn() + ";" + book.getCallNumber() + ";" + book.getAvail() + ";" + book.getTotal()
						+ ";" + book.getTitle() + ";");
				// if the book is the ChildrensBook class, print on the extra attributes in this if
				// statement
				if (book instanceof ChildrensBook)
				{
					ChildrensBook temp = (ChildrensBook) book;
					out.println(temp.getAuthors() + ";" + temp.getFormat().charAt(0));

				}
				// if the book is the CookBook class, print on the extra attributes in this else if
				// statement
				else if (book instanceof CookBook)
				{
					CookBook temp = (CookBook) book;
					out.println(temp.getPublisher() + ";" + temp.getDiet().charAt(0));
				}
				// if the book is the paperback class, print on the extra attributes in this else if
				// statement
				else if (book instanceof Paperback)
				{
					Paperback temp = (Paperback) book;
					out.println(temp.getAuthors() + ";" + temp.getYear() + ";" + temp.getGenre().charAt(0));
				}
				// else the book is the periodical class, print on the extra attributes in this else
				// statement
				else
				{
					Periodical temp = (Periodical) book;
					out.println(temp.getFrequency().charAt(0));
				}
			}
		}
		out.close();
		System.exit(0);
	}

}
