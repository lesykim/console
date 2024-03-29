package console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserManager implements CRUD{
	Scanner sc = new Scanner(System.in);
	Map<User,ArrayList<Post>> users;
	
	public UserManager() {
		users = new HashMap<>();
	}
	
	private User foundUserById(String id) {
		List keySet = new ArrayList(users.keySet());
		User foundKey = null;
		for(Object key : keySet) {
			User user = (User) key;
			if(user.getId().equals(id)) {
				foundKey = user;
			}
		}
		return foundKey;
	}
	
	public User loginUser(String id, String password) {
		List keySet = new ArrayList(users.keySet());
		User foundKey = null;
		for(Object key : keySet) {
			User user = (User) key;
			if(user.getId().equals(id)&& user.getPassword().equals(password)) {
				foundKey = user;
			}
		}
		return foundKey;
	}

	public void addPost(Post post, User user) {
		ArrayList<Post> userPost = users.get(user);
		userPost.add(post);
	}
	
	public void readAllData() {
		List keySet = new ArrayList(users.keySet());
		for(Object key : keySet) {
			User user = (User) key;
			System.out.println(user);
		}
	}
	
	@Override
	public void createData(User user) {
		if(foundUserById(user.getId()) != null) {
			System.out.println("사용할 수 없는 아이디");
			return;
		}
		ArrayList<Post> post = new ArrayList<>();
		users.put(user, post);
		System.out.println("회원가입이 완료되었습니다.");
	}

	@Override
	public void readData(User user) {
		System.out.println(user);
		System.out.println("----게시글 목록 ▼");
		ArrayList<Post> post = users.get(user);
		int n = 1;
		for(Post info : post) {
			System.out.printf("(%d) %s\n",n++,info.getTitle());
		}
	}
	
	private String inputString(String message) {
		System.out.print(message + " : ");
		String input = sc.next();
		return input;
	}

	@Override
	public void updateData(User user, int number) {
		ArrayList<Post> post = users.get(user);
		if(post.size() < number || number <= 0) {
			System.err.println("잘못된 입력");
			return;
		}
		Post updatePost = post.get(number-1);
		String title = inputString("title");
		updatePost.setTitle(title);
		String body = inputString("body");
		updatePost.setBody(body);
		System.out.println("수정되었습니다.");
	}

	@Override
	public void deleteData(User user) {
		
	}
	
	

}
