package console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserManager implements CRUD{
	Scanner sc = new Scanner(System.in);
	Map<User,ArrayList<Post>> users;
	Board board;
	
	public UserManager() {
		users = new HashMap<>();
	}
	
	public void setBoard(Board board) {
		this.board = board;
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
	
	public ArrayList<Post> getMap(User user){
		return users.get(user);
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

	public void readData(User user) {
		System.out.println(user);
		System.out.println("----게시글 목록 ▼");
		ArrayList<Post> post = users.get(user);
		int n = 1;
		for(Post info : post) {
			System.out.printf("(%d) %s\n",n++,info.getTitle());
		}
	}

	public void updateData(User user, int number) {
		ArrayList<Post> post = users.get(user);
		if(post.size() < number || number <= 0) {
			System.err.println("잘못된 입력");
			return;
		}
		Post updatePost = post.get(number-1);
		board.updateData(updatePost);
	}
	
	public void deleteData(User user, int number) {
		ArrayList<Post> post = users.get(user);
		if(post.size() < number || number <= 0) {
			System.err.println("잘못된 입력");
			return;
		}
		Post deletePost = post.get(number-1);
		post.remove(number-1);
		board.deleteData(deletePost);
	}
	
	public void deleteUser(User user) {
		users.remove(user);
		System.out.println("탈퇴되었습니다.");
	}

}
