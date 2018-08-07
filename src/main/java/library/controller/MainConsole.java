package library.controller;

import library.dao.BookDao;
import library.dao.DaoLibrarian;
import library.entity.User;
import library.scanner.Librarian;

public class MainConsole {
	public static void main(String[] args) {

		BookDao dao = new DaoLibrarian();
		Librarian librarian = new Librarian();

		int number = librarian.enterCardNumber();
		String pass = librarian.enterPassword();

		User user = dao.authorize(number, pass);

		if (user != null) {
			librarian.showMenuForUsers();
		}
	}

}
