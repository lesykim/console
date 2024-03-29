package console;

import java.util.HashMap;
import java.util.Map;

public class Board {
	Map<Integer,Post> board;
	int number;
	
	public Board() {
		board = new HashMap<>();
		number = 1;
	}
	
	public void AddPost(Post post) {
		board.put(number++, post);
	}
}