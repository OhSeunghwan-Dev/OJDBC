package view;

import java.sql.SQLException;
import java.util.Scanner;

import member.dto.MemberDTO;

public class ShowMyInfoPage {
	
	Scanner sc = new Scanner(System.in);
	
	public MemberDTO show(MemberDTO session) throws SQLException {
		
		boolean run = true;
		
		while (run) {
			System.out.println("아이디 : " + session.getId());
			System.out.println("비밀번호 : " + session.getPw());
			System.out.println("주소 : " + session.getAddress());
			System.out.println("성별 : " + session.getGender());
			System.out.println("번호 : " + session.getPhoneNumber());
			System.out.println("이메일 : " + session.getEmail());
			System.out.println("가입일 : " + session.getRegDate());
			
			System.out.println("\n회원정보를 변경하시겠습니까?(yes/no)");
			System.out.print(">>> ");
			String select = sc.next();
			
			if (select.equals("yes"))		{
				session = new UpdateInfoPage().show(session);
				run = false;
			}
			else if (select.equals("no"))	run = false;
			else 							System.out.println("잘못된 입력입니다.");
		}
		
		return session;
	}
}
