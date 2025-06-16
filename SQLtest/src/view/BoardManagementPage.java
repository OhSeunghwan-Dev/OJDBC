package view;

import java.sql.SQLException;
import java.util.Scanner;

import member.dto.MemberDTO;

public class BoardManagementPage {
	
	Scanner sc = new Scanner(System.in);

	public MemberDTO show(MemberDTO session) throws SQLException {
		
		boolean run = true;
		
		while (run) {
			
			System.out.println("\n======게시판======");
			System.out.println("1. 게시글 목록");
			System.out.println("2. 게시글 작성");
			System.out.println("3. 내 게시글 보기");
			System.out.println("9. 뒤로 가기");
			String select = sc.next();
			
			switch (select) {
			case "1" :
				new BoardPage().show(session);
				break;
			case "2" :
				new CreatePostPage().show(session);
				break;
			case "3" :
				new MyPostPage().show(session);
				break;
			case "9" :
				run = false;
				break;
			default : 
				System.out.println("잘못된 입력입니다.");
			}
			
		}
		
		return session;
	}
}
