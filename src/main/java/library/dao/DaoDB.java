package library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import library.entity.Book;
import library.entity.User;
import library.scanner.Librarian;

public class DaoDB implements BookDao {
	private static final String SELECT_BOOK = "select*from books  where id = ?";
	private static final String SELECT_USER = "select*from employees where card_number = ?";
	private static final String VIEW_All_BOOKS = "select*from books";
	private static final String ADD_BOOK = "INSERT INTO books (id, title, author) VALUES ( ?, ? , ? )";
	private static final String MOVE_BOOK = "INSERT INTO book_in_use (id_book, librarian_card_id) VALUES (?, ?)";

	private Connection connect() throws ClassNotFoundException, SQLException {

		ResourceBundle rb = ResourceBundle.getBundle("db_config");
		String driver = rb.getString("db.driver");
		String url = rb.getString("db.url");
		String login = rb.getString("db.login");
		String pass = rb.getString("db.pass");
		Connection conn = null;

		Class.forName(driver);
		conn = DriverManager.getConnection(url, login, pass);
		return conn;
	}

	public User authorize(int id, String pass) {

		if (id == 11111 && pass.equals("librarian")) {
			System.out.println("\nI'm librarian ");
			Librarian s = new Librarian();
			s.showMenuForLibrarian();
			return null;
		}

		Connection con;
		con = connect();

		User user = null;
		try {
			PreparedStatement ps = con.prepareStatement(SELECT_USER);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setCardId(rs.getInt("card_number"));
				user.setName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user = checkUser(user, id, pass);
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return user;
	}

	private User checkUser(User user, int number, String pass) {
		if (number == user.getCardNumber() && pass.equals(user.getPassword())) {
			System.out.println("I'm user");
		} else {
			System.out.println("Wrong id or password");
			return null;
		}
		return user;
	}


	@Override
	public List<Book> viewCatalog() {

		List<Book> bookList = new ArrayList<>();
		Connection con = connect();
		Book book = null;
		try {
			PreparedStatement st = con.prepareStatement(VIEW_All_BOOKS);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				book = new Book();
				book.setId(rs.getInt("id"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				bookList.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return bookList;
	}

	@Override
	public Book findBook(int id) {
		Connection con = connect();
		Book book = null;
		try {
			PreparedStatement ps = con.prepareStatement(SELECT_BOOK);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				book = new Book();

				book.setId(rs.getInt("id"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return book;
	}

	
	public void addBook(Book book) {
		Connection con = connect();
		try {
			PreparedStatement ps = con.prepareStatement(ADD_BOOK);
			ps.setInt(1, book.getId());
			ps.setString(2, book.getTitle());
			ps.setString(3, book.getAuthor());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}



	private void closeConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
