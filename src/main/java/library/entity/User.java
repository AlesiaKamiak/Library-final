package library.entity;

public class User {
	
	private String name;
	private int card_number;
	private String password;
	
	
	public User() {}
	
	public User( int cardNumber, String password) {
		super();
		this.card_number = cardNumber;
		this.password = password;
	}
	
	public User( int cardNumber,String name, String password) {
		super();
		this.name = name;
		this.card_number = cardNumber;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCardNumber() {
		return card_number;
	}

	public void setCardId(int cardId) {
		this.card_number = cardId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + card_number;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (card_number != other.card_number)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", cardId=" + card_number + ", password=" + password + "]";
	}
	
	
}
