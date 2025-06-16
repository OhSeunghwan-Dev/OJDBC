package view;

import java.sql.SQLException;
import java.util.Scanner;

import member.controller.MemberController;
import member.dto.MemberDTO;

public class UpdateInfoPage {
	
	Scanner sc = new Scanner(System.in);
	MemberController memberController = new MemberController();
	
	public MemberDTO show(MemberDTO session) throws SQLException {
		
		System.out.print("변경할 비밀번호 : ");
		session.setPw(sc.next());
		sc.nextLine();
		System.out.print("변경할 주소 : ");
		session.setAddress(sc.nextLine());
		System.out.print("변경할 번호 : ");
		session.setPhoneNumber(sc.next());
		System.out.print("변경할 이메일 : ");
		session.setEmail(sc.next());
		
		return memberController.updateInfo(session);
	}

}
