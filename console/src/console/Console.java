package console;

import java.util.Scanner;

public class Console {
	private Scanner sc = new Scanner(System.in);
	private Board board;
	private UserManager userManager;
	private User userLog;
	private int page;
	
	public Console() {
		userManager = new UserManager();
		board = new Board(userManager);
		userManager.setBoard(board);
		userLog = null;
		page = 1;
	}
	
	private boolean isLogin() {
		if(userLog == null) {
			return false;
		}
		return true;
	}
	
	private void printConsole() {
		if(!board.readPageData(page)) {
			page--;
		}
	}
	
	private void printLoginUser() {
		if(userLog != null) {
			System.out.printf("%s님 로그인중...\n",userLog.getId());			
		}
	}
	
	public void printMainMenu() {
		System.out.println("-------------------");
		System.out.println("[1] 이전");
		System.out.println("[2] 이후");
		System.out.println("-------------------");		
		printLoginUser();
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
		System.out.println("[2] 회원탈퇴");
		System.out.println("[3] 로그인");
		System.out.println("[4] 로그아웃");
		System.out.println("[5] 마이페이지");
		System.out.println("[6] 게시글 수정");
		System.out.println("[7] 게시글 삭제");
		System.out.println("[8] 회원정보 출력");
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
		
		userManager.createData(user);
	}
	
	private void leave() {
		String password = inputString("password");
		if(!password.equals(userLog.getPassword())) {
			System.out.println("비밀번호를 다시 확인하세요.");
			return;
		}
		board.deleteLeaveUserPost(userLog);
		userManager.deleteUser(userLog);
	}
	
	private void login() {
		System.out.println("----로그인----");
		String id = inputString("id");
		String password = inputString("password");
		
		User user = userManager.loginUser(id, password);
		if(user == null) {
			System.out.println("회원 정보를 다시 확인하세요.");
		}else {
			this.userLog = user;
			System.out.println("로그인되었습니다.");
		}
	}
	
	private void logout() {
		this.userLog = null;
		System.out.println("로그아웃 되었습니다.");
	}
	
	private void myPage() {
		userManager.readData(userLog);
	}
	
	private void updatePost() {
		userManager.readData(userLog);
		int number = inputNumber("수정할 게시글 번호");
		userManager.updateData(userLog,number);
	}

	private void deletePost() {
		userManager.readData(userLog);
		int number = inputNumber("삭제할 게시글 번호");
		userManager.deleteData(userLog, number);
	}

	private void printUserList() {
		userManager.readAllData();
	}
	
	private void runUserSubMenu(int select) {
		if(select == 1 && !isLogin()) {
			join();
		}else if(select == 2 && !isLogin()) {
			leave();
		}else if(select == 3 && isLogin()) {
			login();
		}else if(select == 4 && isLogin()) {
			logout();
		}else if(select == 5 && isLogin()) {
			myPage();
		}else if(select == 6 && isLogin()) {
			updatePost();
		}else if(select == 7) {
			deletePost();
		}else if(select == 8) {
			printUserList();			
		}
	}
	
	private void runCreatePost() {
		board.createData(userLog);
	}
	
	private void runReadPost() {
		int number = inputNumber("조회할 게시글 번호");
		
		if(number > page * 5 || page-1 * 5 >= number) {
			System.out.println("조회할 수 없는 번호입니다.");
			return;
		}
		
		board.readData(number);
	}
	
	private void runMainMenu(int select) {
		if(select == 1) {
			if(page == 1) {
				System.out.println("페이지를 넘길 수 없습니다.");
				return;
			}
			page--;
		}else if(select == 2) {
			page++;
		}else if(select == 3) {
			printUserSubMenu();
			int sel = inputNumber("메뉴");
			runUserSubMenu(sel);
		}else if(select == 4 && isLogin()) {
			runCreatePost();
		}else if(select == 5) {
			runReadPost();
		}
	}
	
	public void run() {
		while(true) {
			printConsole();
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
