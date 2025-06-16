package main;

import java.util.Scanner;

import member.dto.MemberDTO;
import view.BoardManagementPage;
import view.LoginPage;
import view.MemberManagementPage;
import view.SigninPage;

public class MBCBoard {
		
		public static Scanner sc = new Scanner(System.in);
		public static MemberDTO session = null;
		
		public static void main(String[] args) throws Exception {
			
			boolean run = true;
			
			while (run) {
				
				if (session == null) {
					System.out.println("MBC 아카데미 게시판에 오신것을 환영합니다.");
					System.out.println("1. 로그인");
					System.out.println("2. 회원가입");
					System.out.println("3. 종료");
					System.out.print(">>> ");
					String select = sc.next();
					
					switch (select) {
					case "1" :
						session = new LoginPage().show();
						if (session == null) System.out.println("로그인 실패. 비밀번호를 확인해주세요.\n");
						break;
					case "2" :
						new SigninPage().show();
						break;
					case "3" :
						run = false;
						break;
					default : 
						System.out.println("잘못된 입력입니다.\n");
					}
				}
				else {
					System.out.println("\n======" + session.getId()+ "님 환영합니다======");
					System.out.println("1. 회원 관리");
					System.out.println("2. 게시판");
					System.out.println("3. 로그아웃");
					System.out.println("9. 종료");
					System.out.print(">>> ");
					String select = sc.next();
					
					switch (select) {
					case "1" :
						session = new MemberManagementPage().show(session);
						break;
					case "2" :
						session = new BoardManagementPage().show(session);
						break;
					case "3" :
						session = null;
						System.out.println("\n로그아웃 되었습니다.\n");
						break;
					case "9" :
						run = false;
						break;
					default : 
						System.out.println("잘못된 입력입니다.");
					}
					
				}
			}
			


	}

}	

