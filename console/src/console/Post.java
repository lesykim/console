package console;

public class Post {
	private String title;
	private String body;
	private User user;
	
	public Post(String title, String body, User user) {
		this.title = title;
		this.body = body;
		this.user = user;
	}
	
	public Post() {
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Post clone() {
		return new Post(title, body, user.clone());
	}

	@Override
	public String toString() {
		return String.format("title : %s\n작성자 : %s\n%s\n", this.title,user.getName(),this.body);
	}
}
