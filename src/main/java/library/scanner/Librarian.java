package library.scanner;

import java.util.List;
import java.util.Scanner;

import library.dao.BookDao;
import library.dao.DaoLibrarian;
import library.entity.Book;

public class Librarian {
	private int id;
	private int cardNumber;
	private BookDao dao = new DaoLibrarian();
	
	private Scanner scan;
	private String title;
	private String author;

	public int enterCardNumber() {

		scan = new Scanner(System.in);
		System.out.println("Enter librarian number: ");
		String userId = scan.nextLine();
		int id = Integer.valueOf(userId);
		return id;
	}

	public String enterPassword() {

		scan = new Scanner(System.in);
		System.out.println("Enter password:");
		String pass = scan.nextLine();
		return pass;
	}

	public void showMenuForUsers() {
		System.out.println("\n * For viewing catalog press 1" 
				+ "\n * For viewing book info press 2");
		scan = new Scanner(System.in);
		System.out.println("Enter your number...");
		String choiceOfAction = scan.nextLine();
		int j = Integer.valueOf(choiceOfAction);

		switch (j) {

		case 1:
			List<Book> bookList = dao.viewCatalog();
			System.out.println("Available books: " + bookList);
			break;

		case 2:
			System.out.println("Choose a book: ");
			id = Integer.valueOf(scan.nextInt());
			Book book = dao.findBook(id);
			System.out.println("Book you are looking for: " + book);

			break;
		}
	}

	public void showMenuForLibrarian() {
		
		System.out.println("\n * For add new user, press 1"
				+ "\n * For add new book, press 2 " + "\n * Give a book, press 3 "
				+ "\n * For returning book press 4 ");
		scan = new Scanner(System.in);
		System.out.println("Enter your number");
		String choiceOfAction = scan.nextLine();
		int j = Integer.valueOf(choiceOfAction);

		switch (j) {

		case 1:
			System.out.println("Enter book id");
			id = Integer.valueOf(scan.nextLine());
			System.out.println("Enter card number");
			cardNumber = Integer.valueOf(scan.nextLine());
			dao.giveBook(id, cardNumber);
			break;

		default:
			System.out.println("Please, check the entered data, and try again");
			break;
		}
	}

	private void createBook() {

		System.out.println("Enter book ID...");
		id = Integer.valueOf(scan.nextLine());
		System.out.println("Enter book title...");
		title = scan.nextLine();
		System.out.println("Enter book author...");
		author = scan.nextLine();
		Book book = new Book(id, title, author);
		dao.addBook(book);
		System.out.println("Build success!!!");

	}

}
