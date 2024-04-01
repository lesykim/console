package console;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Board implements CRUD{
	Scanner sc = new Scanner(System.in);
	ArrayList<Post> board;
	UserManager userManager;
	
	public Board(UserManager users) {
		board = new ArrayList<>();
		userManager = users;
	}
	
	public int getBoardSize() {
		return board.size();
	}
	
	private String inputString(String message) {
		System.out.print(message + " : ");
		String input = sc.next();
		return input;
	}
	
	public boolean readPageData(int page) {
		boolean isMove = true;
		if(page-1 > board.size()/5) {
			page = page - 1;
			System.out.println("페이지를 넘길 수 없습니다.");
			isMove = false;
		}
		int cnt = 0;
		for (int i = (page-1)*5; i < board.size(); i++) {
			Post post = board.get(i);
			System.out.println((i+1)+") "+post.getTitle());
			if(cnt == 4) {
				break;
			}
			cnt++;
		}
		System.out.println(page+"/"+((board.size()-1)/5+1));
		return isMove;
	}

	@Override
	public void createData(User user) {
		String title = inputString("title");
		String body = inputString("body");
		Post post = new Post(title, body, user);
		userManager.addPost(post, user);
		board.add(post);
		System.out.println("작성 완료");
	}

	public void readData(int number) {
		
	}

	public void updateData(Post post) {
		String title = inputString("title");
		post.setTitle(title);
		String body = inputString("body");
		post.setBody(body);
		System.out.println("수정되었습니다.");
	}

	@Override
	public void deleteData(User user) {
		
	}
	
}