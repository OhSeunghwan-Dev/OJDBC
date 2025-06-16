package view;

import java.sql.SQLException;
import java.util.Scanner;

import member.controller.MemberController;
import member.dto.MemberDTO;
import member.service.MemberService;

public class Signout {

	Scanner sc = new Scanner(System.in);
	MemberService memberService = new MemberService();
	MemberController memberController = new MemberController();
	
	public MemberDTO show(MemberDTO session) throws SQLException {
		
		System.out.print("비밀번호 : ");
		String pw = (sc.next());
		if (!pw.equals(session.getPw())) {
			System.out.println("\n비밀번호를 확인해주세요\n");
			return session;
		}
		else {
			session = memberController.signout(session);
		}
		
		return session;
	}
}
