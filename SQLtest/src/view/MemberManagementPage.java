package view;

import java.sql.SQLException;
import java.util.Scanner;

import member.dto.MemberDTO;

public class MemberManagementPage {
	
	Scanner sc = new Scanner(System.in);

	public MemberDTO show(MemberDTO session) throws SQLException {
		
		boolean run = true;
		
		while (run) {
			
			System.out.println("\n======회원 관리 페이지======");
			System.out.println("1. 게시판 회원 목록 보기");
			System.out.println("2. 내 정보 보기");
			System.out.println("3. 회원 탈퇴");
			System.out.println("9. 뒤로 가기");
			System.out.print(">>> ");
			String select = sc.next();
			
			switch (select) {
			case "1" :
				new ShowMemberListPage().show();
				break;
			case "2" :
				session = new ShowMyInfoPage().show(session);
				break;
			case "3" :
				session = new Signout().show(session);
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
