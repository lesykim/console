package console;

public class User {
	private String name;
	private String id;
	private String password;
	
	public User(String name, String id, String password) {
		this.name = name;
		this.id = id;
		this.password = password;
	}
	
	public User() {
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public User clone() {
		return new User(name, id, password);
	}
	
	@Override
	public String toString() {
		return String.format("%s | %s ) %s", this.id,this.password,this.name);
	}
}
