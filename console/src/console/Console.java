package console;

import java.util.Scanner;

public class Console {
	private Scanner sc = new Scanner(System.in);
	Board board;
	UserManager userManager;
	
	public Console() {
		board = new Board();
		userManager = new UserManager();
	}
	
	private void printConsole() {
		
	}
		
	public void printMainMenu() {
		System.out.println("-------------------");
		System.out.println("[1] 이전");
		System.out.println("[2] 이후");
		System.out.println("-------------------");		
		System.out.println("[3] 회원관리");
		System.out.println("[4] 게시글 작성");
		System.out.println("[5] 게시글 조회");
		System.out.println("[0] 종료");
		System.out.println("-------------------");
	}
	
	private int inputNumber(String message) {
		int n = -1;
		System.out.print(message+" : ");
		try {
			String input = sc.next();
			n = Integer.parseInt(input);
		} catch (Exception e) {
			System.out.println("숫자를 입력하세요.");
		}
		return n;
	}
	
	private void printUserSubMenu() {
		System.out.println("[1] 회원가입");
		System.out.println("[2] 로그인");
		System.out.println("[3] 로그아웃");
		System.out.println("[4] 내 게시글");
	}
	
	private String inputString(String message) {
		System.out.print(message + " : ");
		String input = sc.next();
		return input;
	}
	
	private void join() {
		System.out.println("----회원가입----");
		String id = inputString("id");
		String password = inputString("password");
		String name = inputString("name");
		
		User user = new User(id,password,name);
		
		userManager.AddUser(user);
	}
	
	private void runUserSubMenu(int select) {
		if(select == 1) {
			join();
		}else if(select == 2) {
			
		}else if(select == 3) {
			
		}else if(select == 4) {
			
		}
	}
	
	private void runMainMenu(int select) {
		if(select == 1) {
			
		}else if(select == 2) {
			
		}else if(select == 3) {
			printUserSubMenu();
			int sel = inputNumber("메뉴");
			runUserSubMenu(sel);
		}else if(select == 4) {
			
		}else if(select == 5) {
			
		}
	}
	
	public void run() {
		while(true) {
			printMainMenu();
			int select = inputNumber("메뉴");
			if(select == 0) {
				System.out.println("시스템 종료");
				break;
			}
			runMainMenu(select);			
		}
	}
}
