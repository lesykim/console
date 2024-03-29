package console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManager {
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
	
	public void addUser(User user) {
		if(foundUserById(user.getId()) != null) {
			System.out.println("사용할 수 없는 아이디");
			return;
		}
		ArrayList<Post> post = new ArrayList<>();
		users.put(user, post);
		System.out.println("회원가입이 완료되었습니다.");
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
	
	

}
