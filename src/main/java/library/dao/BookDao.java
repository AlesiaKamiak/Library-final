package library.dao;


import java.util.List;

import library.entity.Book;
import library.entity.User;

public interface BookDao {

User authorize(int id, String pass);
	
	List<Book> viewCatalog();
	
	Book findBook(int id);
	
	void addBook(Book book);
	
	void giveBook(int id, int cardID);
}
