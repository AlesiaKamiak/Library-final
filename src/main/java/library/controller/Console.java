package library.controller;

import library.dao.BookDao;
import library.dao.DaoDB;
import library.entity.User;

public class Console {
	private static Console scanner;
	private static User user;
	
	public static void main(String[] args) {
	
	scanner = new Console();	
	BookDao dao = new DaoDB();
	
	
	int number = scanner.enterCardNumber();
	String pass = scanner.enterPassword();
	user = dao.authorize(number , pass);
	
	if(user!=null) {
		(scanner).showMenuForUsers();
	}
	
	
	}

}
