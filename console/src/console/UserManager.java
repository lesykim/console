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
	
	public void AddUser(User user) {
		if(foundUserById(user.getId()) != null) {
			return;
		}
		ArrayList<Post> post = new ArrayList<>();
		users.put(user, post);
	}
	
	private User LoginUser(String id, String password) {
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
